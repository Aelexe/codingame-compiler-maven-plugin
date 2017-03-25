/**
 * The same as abstract class but with added interface.
 * 
 * @author Aelexe
 *
 */
public abstract class AbstractImplementsClass implements AnInterface {
	
	/**
	 * Gets the string at the given index or something.
	 * 
	 * @param stringIndex Index of the string to return.
	 * @return The string.
	 */
	public String getTheString(int stringIndex);
	
	/**
	 * Takes two ints, disregards them and returns 42.
	 * 
	 * @param x X
	 * @param y Y
	 * @return 42
	 */
	public String getTheInt(int x, int y) {
		return 42;
	}
	
	/**
	 * This method is harder than you might imagine.
	 */
	public void beTheGuy();
}