package fts;

/**
 *
 * @author Daniel Moreau
 */
public interface KeyMap {
	public int getKey(String in) throws Exception;
	public boolean getShift();
	public boolean getAlt();
	public boolean getCtrl();
	public boolean getMeta();
}
