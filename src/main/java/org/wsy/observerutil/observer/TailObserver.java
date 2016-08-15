package org.wsy.observerutil.observer;

import java.io.UnsupportedEncodingException;
import java.util.Observer;

public abstract class TailObserver implements Observer {

	private String filePath;
	private boolean convertEncode = false;
	private String encode = "";
	private String ISO8859 = "ISO-8859-1";
	protected TailObserver(String filePath) {
		this.filePath = filePath;
	}
	
	protected TailObserver(String filePath,String encode){
		this.filePath = filePath;
		this.convertEncode = true;
		this.encode = encode;
	}
	
	public String getFilePath (){
		return this.filePath;
	} 
	
	public String convert(String line) throws UnsupportedEncodingException{
		return convertEncode? new String(line.getBytes(ISO8859),this.encode) : line;
	}

}
