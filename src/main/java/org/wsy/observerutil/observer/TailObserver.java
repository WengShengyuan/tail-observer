package org.wsy.observerutil.observer;

import java.util.Observer;

public abstract class TailObserver implements Observer {

	protected String filePath;
	
	protected TailObserver(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath (){
		return this.filePath;
	} 

}
