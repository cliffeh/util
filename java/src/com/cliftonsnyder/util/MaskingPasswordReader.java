/*
 * Copyright (c) 2010 Clifton L. Snyder
 * All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the names of Clifton L. Snyder nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.cliftonsnyder.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

/**
 * reads a password from stdin while masking the input
 * 
 * @author Clifton L. Snyder
 * @created July 7, 2006
 * 
 */
public class MaskingPasswordReader {

	public static String readPassword() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		EraserThread masker = new EraserThread("password: ");
		new Thread(masker).start();

		String password = null;

		try {
			password = in.readLine();
		} catch (IOException ioe) {
			// TODO - handle this better?
			System.err.println("error reading password");
			System.exit(1);
		}

		masker.stopMasking();

		return password;
	}

	public static void main(String[] args) {
		String password = MaskingPasswordReader.readPassword();
		System.out.println("The password is: " + password);
	}
}

/**
 * 
 * thread for prompting and replacing characters with a '*' character as they
 * are typed
 * 
 * code for this class found on the Sun Developer Network (SDN) at:
 * http://java.sun.com/developer/technicalArticles/Security/pwordmask/
 * 
 */
class EraserThread implements Runnable {
	private boolean stop;

	/**
	 * @param The
	 *            prompt displayed to the user
	 */
	public EraserThread(String prompt) {
		System.err.print(prompt);
	}

	/**
	 * Begin masking...display asterisks (*)
	 */
	public void run() {
		stop = true;
		while (stop) {
			System.err.print("\010*");
			try {
				// Thread.currentThread().sleep(1);
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Instruct the thread to stop masking
	 */
	public void stopMasking() {
		this.stop = false;
	}
}
