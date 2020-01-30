package textAreaIO;

/**
 * a simple boolean wrapper
 */
public class Latch {
	/**
	 * the value of the latch
	 * can be changed by multiple threads
	 */
	private volatile boolean value;

	/**
	 * set up a latch with the given initial state
	 * @param initial
	 */
	public Latch(boolean initial) {
		value = initial;
	}
	
	/**
	 * set up a latch with false state
	 */
	public Latch() {
		this(false);
	}

	/**
	 * @return the current value of the latch
	 */
	public boolean state() {
		return value;
	}

	/**
	 * make the state true
	 */
	public void set() {
		value = true;
	}

	/**
	 * make the state false
	 */
	public void reset() {
		value = false;
	}
}
