package fts;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 *
 * @author Daniel Moreau
 */
public class DefaultKeyMap implements KeyMap {
	@Override
	public int getKey(String in) throws Exception {
		if(in.length() > 1) {
			in = in.substring(0, 1);
		}
		String constant = "VK_" + convert(in);
		Class keyClass = Class.forName("java.awt.event.KeyEvent");
		Field field = keyClass.getField(constant);  
        Integer i = (Integer) field.get(null);
		return i;
	}

	private String convert(String in) {
		shift = Character.isUpperCase(in.charAt(0)); 
		if(" ".equalsIgnoreCase(in)) {
			in = "SPACE";
		}
		else if("\t".equalsIgnoreCase(in)) {
			in = "TAB";
		}
		else if(",".equalsIgnoreCase(in)) {
			in = "COMMA";
		}
		else if(".".equalsIgnoreCase(in)) {
			in = "PERIOD";
		}
		else if("!".equalsIgnoreCase(in)) {
			in = "1";
			shift = true;
		}
		return in.toUpperCase();
	}
	
	public boolean getShift() {
		return shift;
	}

	@Override
	public boolean getAlt() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean getCtrl() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean getMeta() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	private boolean shift = false;
}
