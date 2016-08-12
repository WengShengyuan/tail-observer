package org.wsy.observerutil.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TailObserver implements Observer {

	private static final Logger logger = LoggerFactory.getLogger(TailObserver.class);

	public void update(Observable o, Object arg) {
		logger.info("updating:" + o + ", obj:" + arg);
	}

}
