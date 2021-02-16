package com;

import java.util.Timer;
import java.util.TimerTask;

public class test {
public static void main(String[] args) {
	
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
	    int i = 60;
	   // String time = String.format("%02d:%02d", i / 60, i % 60);
	    public void run(){
	        if (i >= 0) {
	        	System.out.println( i--);
	        }
	    }
	};
	timer.scheduleAtFixedRate(task, 0, 1000);
	
//	int i =60;
//	String time = String.format("%02d:%02d", i / 60, i % 60);
//	System.out.println(time); 
}
}
