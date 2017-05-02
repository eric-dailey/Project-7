/*
 *	Project 7 ChatRoom
 *	Eric Dailey <emd925>
 *	Shariq Memon <skm2662>
 *	4/29/2017
 *	Slip day used: Yes
 */

package assignment7;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

public class ClientObserver extends PrintWriter implements Observer {
	public ClientObserver(OutputStream out) {
		super(out);
	}
	@Override
	public void update(Observable o, Object arg) {
		this.println(arg); //writer.println(arg);
		this.flush(); //writer.flush();
	}

}
