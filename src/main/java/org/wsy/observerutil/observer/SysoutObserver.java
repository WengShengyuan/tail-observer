package org.wsy.observerutil.observer;

import java.util.Observable;

public class SysoutObserver extends TailObserver {

	public SysoutObserver(String filePath) {
		super(filePath);
	}

	public void update(Observable o, Object arg) {
		System.out.println("SysoutObserver updating:" + arg);
	}

}
