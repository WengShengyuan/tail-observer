package org.wsy.observerutil.observer;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingObserver extends TailObserver {

	public LoggingObserver(String filePath) {
		super(filePath);
	}

	private static final Logger logger = LoggerFactory.getLogger(LoggingObserver.class);

	public void update(Observable o, Object arg) {
		logger.info("LoggingObserver updating:" + arg);
	}

}
