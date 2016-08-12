package org.wsy.observerutil.core;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsy.observerutil.subject.TailSubject;

public class TailPool {

	private static final Logger logger = LoggerFactory.getLogger(TailPool.class);
	private static HashMap<String, TailSubject> subjects;
	public static volatile TailPool instance;

	private TailPool() {
		subjects = new HashMap<String, TailSubject>();
	}
	
	public static TailPool getInstance(){
		if(instance == null){
			synchronized (TailPool.class) {
				if(instance == null){
					instance = new TailPool();
				}
			}
		}
		return instance;
	}
	
	public synchronized TailSubject subscribe(String filePath){
		if(subjects.containsKey(filePath)){
			return subjects.get(filePath);
		}
		logger.info("creating new subject:"+filePath);
		TailSubject subject = new TailSubject(filePath);
		subject.startTailing();
		subjects.put(filePath, subject);
		new Thread(subject).start();
		return subject;
	}
	
	public synchronized void unSubscribe(String filePath, Observer obs){
		TailSubject subject = subjects.get(filePath);
		if(subject == null){
			logger.info("subject doesn't exist");
		} else {
			subject.deleteObserver(obs);
			int observerCount = subject.countObservers();
			logger.info("subject contains: "+observerCount+" observers");
			if(observerCount == 0){
				logger.info("no observers, stop tailing, remove from subjects.");
				subject.kill();
				subjects.remove(filePath);
			}
		}
	}
}
