package org.wsy.observerutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsy.observerutil.core.TailPool;
import org.wsy.observerutil.observer.TailObserver;

public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		String file = "D://test.txt";
		String file2 = "D://test2.txt";
		TailPool.getInstance().subscribe(file).addObserver(new TailObserver());
		TailPool.getInstance().subscribe(file).addObserver(new TailObserver());
		TailPool.getInstance().subscribe(file2).addObserver(new TailObserver());
	}
}
