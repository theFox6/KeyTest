package keytest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Keytest extends JFrame {
	private static final long serialVersionUID = -5861901296349638473L;
	private PrintingTextArea pta;

	public Keytest(){
		super("Keytest");
		pta = new PrintingTextArea();
		pta.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				println("-- key typed [" + e.getID() + "] --");
				printKeyEventData(e);
				e.consume();
			}

			public void keyPressed(KeyEvent e) {
				println("-- key pressed [" + e.getID() + "] --");
				printKeyEventData(e);
				e.consume();
			}

			public void keyReleased(KeyEvent e) {
				println("-- key released [" + e.getID() + "] --");
				printKeyEventData(e);
				e.consume();
			}
		});
		add(pta);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void printKeyEventData(KeyEvent e) {
		println("key code: " + e.getKeyCode());
		println("extended key code: " + e.getExtendedKeyCode());
		//TODO: move to method
		char kc = e.getKeyChar();
		if (kc == KeyEvent.CHAR_UNDEFINED) {
			println("key char undefined");
		} else {
			try {
				println("key char: " + kc);
			} catch (IllegalArgumentException ex) {
				if (ex.getMessage().startsWith("Not a valid Unicode code point")) {
					println("(numeric) " + (int) kc);
				} else {
					ex.printStackTrace();
				}
			}
		}
		//TODO: show string representations of location
		int kl = e.getKeyLocation();
		if (kl == KeyEvent.KEY_LOCATION_UNKNOWN) {
			println("key location unknown");
		} else {
			println("key location: "+ kl);
		}
		int mod = e.getModifiersEx();
		println("modifiers: "+ mod);
		if (e.isActionKey()) {
			println("it's an action key");
		} else {
			println("it's not an action key");
		}
	}

	private void println(String string) {
		pta.output.println(string);
	}

	public static void main(String[] args) {
		new Keytest();
	}

}
