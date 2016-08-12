package org.wsy.observerutil.observer;

import java.util.Observable;
import java.util.Observer;

public class SysoutObserver implements Observer {

	public void update(Observable o, Object arg) {
		System.out.println("SysoutObserver updating:" + arg);
	}

}
