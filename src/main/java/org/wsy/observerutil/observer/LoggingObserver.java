package org.wsy.observerutil.observer;

import java.io.UnsupportedEncodingException;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingObserver extends TailObserver {

	public LoggingObserver(String filePath) {
		super(filePath);
	}
	
	public LoggingObserver(String filePath,String encode){
		super(filePath,encode);
	}

	private static final Logger logger = LoggerFactory.getLogger(LoggingObserver.class);

	public void update(Observable o, Object arg) {
		try {
			logger.info("LoggingObserver updating:" + convert((String) arg));
		} catch (UnsupportedEncodingException e) {
			logger.error("update fail: "+e);
		}
	}

}
