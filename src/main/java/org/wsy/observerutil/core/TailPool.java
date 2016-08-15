package org.wsy.observerutil.core;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsy.observerutil.observer.TailObserver;
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
	
	public synchronized void subscribe(TailObserver tobs){
		String filePath = tobs.getFilePath();
		if(subjects.containsKey(filePath)){
			logger.info("subject already exist.");
			subjects.get(filePath).addObserver(tobs);
			return;
		}
		logger.info("creating new subject:"+filePath);
		TailSubject subject = new TailSubject(filePath);
		subject.startTailing();
		subjects.put(filePath, subject);
		new Thread(subject).start();
		subject.addObserver(tobs);
	}
	
	public synchronized void unSubscribe(TailObserver tobs){
		String filePath = tobs.getFilePath();
		TailSubject subject = subjects.get(filePath);
		if(subject == null){
			logger.info("subject doesn't exist");
		} else {
			subject.deleteObserver(tobs);
			int observerCount = subject.countObservers();
			logger.info("subject has "+observerCount+" observers left.");
			if(observerCount == 0){
				logger.info("no observer, stopping, remove subject from subject cache.");
				subject.kill();
				subjects.remove(filePath);
			}
		}
	}
}
