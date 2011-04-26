package com.cliftonsnyder.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadRunner implements Runnable {

	private BufferedReader br;
	private int nThreads;

	public ThreadRunner(InputStream in, int nThreads) {
		br = new BufferedReader(new InputStreamReader(in));
		this.nThreads = nThreads;
	}

	public synchronized String getCommand() {
		String command = null;

		try {
			command = br.readLine();
		} catch (IOException e) {
			System.err.println("warning: unable to read command from input");
			command = null;
		}

		return command;
	}

	public void run() {
		Thread[] threads = new Thread[nThreads];
		for (int i = 0; i < nThreads; i++) {
			threads[i] = new Thread(new HelperThread(this));
			threads[i].start();
		}
		for (int i = 0; i < nThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.err.println("warning: helper thread interrupted");
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java "
					+ ThreadRunner.class.getCanonicalName()
					+ " num_threads infile|-");
			System.exit(1);
		}

		int nThreads = 0;
		try {
			nThreads = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("error: num_threads must be numerical");
			System.exit(1);
		}
		if (nThreads <= 0) {
			System.err.println("error: num_threads must be > 0");
			System.exit(1);
		}

		InputStream in = null;
		if ("-".equals(args[1])) {
			in = System.in;
		} else {
			try {
				in = new FileInputStream(args[1]);
			} catch (FileNotFoundException e) {
				System.err.println("error: file '" + args[1] + "' not found");
				System.exit(1);
			}
		}

		ThreadRunner tr = new ThreadRunner(in, nThreads);
		Thread t = new Thread(tr);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			System.err.println("warning: main thread interrupted");
		}
	}
}

class HelperThread implements Runnable {

	private ThreadRunner tr;
	private String command;

	public HelperThread(ThreadRunner tr) {
		this.tr = tr;
	}

	public void run() {
		while ((command = tr.getCommand()) != null) {
			// TODO need to correctly handle input/output streams
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				System.err
						.println("warning: I/O exception encountered when running '"
								+ command + "'");
			}
			// START DEBUG
			// System.out.println(command);
			// System.out.println("sleeping...");
			// try {
			// Thread.sleep(5000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// END DEBUG
		}
	}
}
