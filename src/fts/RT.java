/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fts;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author grendel
 */
public class RT implements Runnable {
	private Robot robot;
	private File input;
	private ArrayList<String> command = new ArrayList<String>();
	private int count = -1;
	private int iteration = 0;
	private DecimalFormat df = new DecimalFormat("0.00");
	private KeyMap keys;
	private boolean debug = false;
	private int lastX = -1;
	private int lastY = -1;
	boolean shift = false;
	
	public RT() {
		
	}

	/**
	 * This method will parse and perform the given command
	 * @param str The command to parse
	 * @throws InterruptedException 
	 */
	private void parseCommand(String str) throws InterruptedException {
		//TODO:
		//double-click convenience command
		//drag convenience command
		//set double-click delay command
		//randomize wait option
		//key command for single key stroke
		//keydown command for single key down
		//keyup command for single key up
		//slowmode for simulating "real" mouse movemnt
		String[] part = str.split(" ");
		if(part[0].equalsIgnoreCase("wait")) {
			long wait = Long.parseLong(part[1]);
			if(debug) {
				//System.out.println(input.getName() + ": Waiting " + (wait / 1000) + " seconds");
			}
			Thread.sleep(wait);
		}
		else if(part[0].equalsIgnoreCase("move")) {
			int x = Integer.parseInt(part[1]);
			int y = Integer.parseInt(part[2]);
			if(debug) {
				//System.out.println(input.getName() + ": Moving to " + x + "," + y);
			}
			robot.mouseMove(x, y);
		}
		else if(part[0].equalsIgnoreCase("left-click") || part[0].equalsIgnoreCase("click")) {
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		else if(part[0].equalsIgnoreCase("middle-click")) {
			robot.mousePress(InputEvent.BUTTON2_MASK);
			robot.mouseRelease(InputEvent.BUTTON2_MASK);
		}
		else if(part[0].equalsIgnoreCase("right-click")) {
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
		}
		else if(part[0].equalsIgnoreCase("type")) {
			String text = null;
			for(int i = 1; i < part.length; ++i) {
				if(text == null) {
					text = part[i];
				}
				else {
					text += " " + part[i];
				}
			}
			typeString(text);
		}
		else {
			if(debug) {
				//System.out.println(input.getName() + ": Ignoring unknown command: " + str);
			}
		}
	}

	private void typeString(String str) {
		for(int i = 0; i < str.length(); ++i) {
			try {
				int k = keys.getKey("" + str.charAt(i));
				if(debug) {
					//System.out.println(input.getName() + ": " + str.charAt(i) + " = " + k);
				}
				if(keys.getShift()) {
					robot.keyPress(KeyEvent.VK_SHIFT);
				}
				robot.keyPress(k);
				robot.keyRelease(k);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			catch(Exception e) {
				//System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private void generateNumber() {
		String str = "012340567890";
		try {
			for(int i = 0; i < 8; ++i) {
				int seed = (int) (Math.random() * 12);
				int k = keys.getKey("" + str.charAt(seed));
				if(debug) {
					//System.out.println(input.getName() + ": " + str.charAt(i) + " = " + k);
				}
				if(keys.getShift()) {
					robot.keyPress(KeyEvent.VK_SHIFT);
				}
				robot.keyPress(k);
				robot.keyRelease(k);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			robot.keyPress(KeyEvent.VK_ENTER);
		}
		catch(Exception e) {
			//System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean needsUpdate(Point location) {
		//return location.x == lastX && location.y == lastY;
		int n = (int) (Math.random() * 10);
		for(int i = 0; i < n; ++i) {
			int d = (int) (Math.random() * 20);
			robot.keyPress(KeyEvent.VK_F24);
			robot.delay(100 + d);
			robot.keyPress(KeyEvent.VK_F24);
			d = (int) (Math.random() * 20);
		}
		return true;
	}
	
	private void getPosition() {
		lastX = MouseInfo.getPointerInfo().getLocation().x;
		lastY = MouseInfo.getPointerInfo().getLocation().y;
	}
	
	private void update(Point location) {
		/*
		int seedX = (int) (Math.random() * 100);
		int seedY = (int) (Math.random() * 100);
		if(seedX % 2 == 0) {
			seedX = 0 - seedX;
		}
		if(seedY % 2 == 0) {
			seedY = 0 - seedY;
		}
		int newX = location.x + seedX;
		int newY = location.y + seedY;
		for(int i = 0; i < Math.abs(newX - lastX); ++i) {
			robot.mouseMove(location.x + i, location.y);
			robot.delay(10);
		}
		for(int i = 0; i < Math.abs(newY - lastY); ++i) {
			robot.mouseMove(newX, location.y + i);
			robot.delay(10);
		}
		robot.mouseMove(newX, newY);
		System.out.println(lastX + " " + lastY);
		*/
		robot.mouseMove(1200, 660);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		altTab();
	}
	
	private void altTab() {
		robot.keyPress(KeyEvent.VK_ALT);
		//robot.keyPress(KeyEvent.VK_CONTROL);
		//int seed = (int) (Math.random() * 5);
		int seed = 1;
		//System.out.println("0x0" + seed);
		for(int i = 0; i < seed; ++i) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(100);
		}
		//robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_ALT);
	}

	
	@Override
	public void run() {
		try {
			//System.out.println("Starting up");
			robot = new Robot();
			keys = new DefaultKeyMap();
			getPosition();
			count = 0;
			while(true) {
				++count;
				int seed = (int) (Math.random() * 2000);
				if(seed % 2 == 0) {
					seed = 0 - seed;
				}
				//System.out.println(25000 + seed);
				//Thread.sleep(25000 + seed);
				robot.delay(5000 + seed);
				Point location = MouseInfo.getPointerInfo().getLocation();
				if(needsUpdate(location)) {
					update(location);
					//generateNumber();
					if(count % 5 == 0) {
						//altTab();
					}
				}
				getPosition();
			}
		}
		catch (Exception e) {
			//System.out.println(input.getName() + ": I wasn't done!");
			e.printStackTrace();
		}
	}
}
