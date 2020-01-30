package keytest;

public class Latch {
	private volatile boolean value;

	public Latch(boolean initial) {
		value = initial;
	}
	
	public Latch() {
		this(false);
	}

	public boolean state() {
		return value;
	}

	public void set() {
		value = true;
	}

	public void reset() {
		value = false;
	}
}
