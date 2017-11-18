package fts;

import java.lang.reflect.Field;

/**
 *
 * @author Daniel Moreau
 */
public class SpecialKeyMap implements KeyMap {
	@Override
	public int getKey(String in) throws Exception {
		String constant = "VK_" + convert(in);
		Class keyClass = Class.forName("java.awt.event.KeyEvent");
		Field field = keyClass.getField(constant);  
        Integer i = (Integer) field.get(null);
		return i;
	}

	private String convert(String in) {
		if("<ENTER>".equalsIgnoreCase(in)) {
			in = "ENTER";
		}
		else if("<BACKSPACE>".equalsIgnoreCase(in)) {
			in = "BACKSPACE";
		}
		return in.toUpperCase();
	}

	@Override
	public boolean getShift() {
		throw new UnsupportedOperationException("Not supported yet.");
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
}
