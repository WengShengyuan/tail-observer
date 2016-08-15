package org.wsy.observerutil.observer;

import java.io.UnsupportedEncodingException;
import java.util.Observable;

public class SysoutObserver extends TailObserver {

	public SysoutObserver(String filePath) {
		super(filePath);
	}

	public SysoutObserver(String filePath,String encode){
		super(filePath,encode);
	}
	
	public void update(Observable o, Object arg) {
		try {
			System.out.println("SysoutObserver updating:" + convert((String) arg));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
