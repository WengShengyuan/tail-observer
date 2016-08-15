package org.wsy.observerutil;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsy.observerutil.core.TailPool;
import org.wsy.observerutil.observer.LoggingObserver;
import org.wsy.observerutil.observer.SysoutObserver;
import org.wsy.observerutil.observer.TailObserver;

public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException {
		String file = "C://log1";
		String file2 = "C://log2";
		TailObserver ob1 = new LoggingObserver(file);
		TailObserver ob2 = new SysoutObserver(file);
		TailObserver ob3 = new LoggingObserver(file2);
		TailPool.getInstance().subscribe(ob1);
		TailPool.getInstance().subscribe(ob2);
		TailPool.getInstance().subscribe(ob3);
		System.in.read();
		logger.info("unsubscribing...");
		TailPool.getInstance().unSubscribe(ob1);
		TailPool.getInstance().unSubscribe(ob2);
		TailPool.getInstance().unSubscribe(ob3);
	}
}
