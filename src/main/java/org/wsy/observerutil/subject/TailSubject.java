package org.wsy.observerutil.subject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Observable;

import org.apache.commons.io.output.ThresholdingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TailSubject extends Observable implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(TailSubject.class);
	private File logFile;
	private long sampleInterval = 200;
	private boolean startAtTop = false;
	private boolean tailing = false;
	
	public TailSubject(String filePath) {
		this.logFile = new File(filePath);
	}

	public TailSubject(File file) {
		this.logFile = file;
	}

	public void startTailing() {
		logger.info("set tailing");
		this.tailing = true;
	}

	public void kill() {
		logger.info("kill tailing");
		this.tailing = false;
	}

	public void run() {
		logger.info("thread started");
		long filePointer = 0;
		if (this.startAtTop) {
			logger.info("start from top");
			filePointer = 0;
		} else {
			logger.info("start from " + this.logFile.length());
			filePointer = this.logFile.length();
		}

		try {
			RandomAccessFile file = new RandomAccessFile(this.logFile, "r");/* readOnlyFile */
			while (this.tailing) {
				long fileLength = this.logFile.length();
				if (fileLength < filePointer) {
					logger.info("log file length reduced.");
				}
				if (fileLength > filePointer) {
					file.seek(filePointer);
					String line = file.readLine();
					while (line != null) {
						setChanged();
						notifyObservers(line);
						line = file.readLine();
					}
					filePointer = file.getFilePointer();
				}
				Thread.currentThread();
				Thread.sleep(this.sampleInterval);
			}
		} catch (IOException e) {
			logger.error(e.toString());
		} catch (InterruptedException e) {
			logger.error(e.toString());
		}
	}
	
}