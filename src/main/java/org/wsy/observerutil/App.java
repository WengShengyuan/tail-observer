package org.wsy.observerutil;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsy.observerutil.core.TailPool;
import org.wsy.observerutil.observer.LoggingObserver;
import org.wsy.observerutil.observer.SysoutObserver;

public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException {
		String file = "C://log1";
		String file2 = "C://log2";
		LoggingObserver ob1 = new LoggingObserver();
		SysoutObserver ob2 = new SysoutObserver();
		LoggingObserver ob3 = new LoggingObserver();
		TailPool.getInstance().subscribe(file).addObserver(ob1);
		TailPool.getInstance().subscribe(file).addObserver(ob2);
		TailPool.getInstance().subscribe(file2).addObserver(ob3);
		System.in.read();
		logger.info("unsubscribing...");
		TailPool.getInstance().unSubscribe(file, ob1);
		TailPool.getInstance().unSubscribe(file, ob2);
		TailPool.getInstance().unSubscribe(file2, ob3);
	}
}
