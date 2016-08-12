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
		TailObserver ob1 = new TailObserver();
		TailObserver ob2 = new TailObserver();
		TailObserver ob3 = new TailObserver();
		TailPool.getInstance().subscribe(file).addObserver(ob1);
		TailPool.getInstance().subscribe(file).addObserver(ob2);
		TailPool.getInstance().subscribe(file2).addObserver(ob3);
		TailPool.getInstance().unSubscribe(file, ob1);
		TailPool.getInstance().unSubscribe(file, ob2);
		TailPool.getInstance().unSubscribe(file2, ob3);
	}
}
