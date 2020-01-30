package textAreaIO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import javax.swing.JTextArea;

/**
 * a JTextArea that receives text from a PrintStream
 */
public class IOTextArea extends JTextArea {
	private static final long serialVersionUID = -3174114718655799243L;
	private StringBuffer input = new StringBuffer();
	private final Queue<Consumer<String>> consumerQueue = new ConcurrentLinkedQueue<>();
	
	/**
	 * sets up a new TextArea and the PrintStream
	 */
	public IOTextArea() {
		super();
		setEditable(false);
		setUpKeyListener();
	}

	private void setUpKeyListener() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int kc = e.getKeyCode();
				if (kc >= KeyEvent.VK_PAGE_UP && kc <= KeyEvent.VK_DOWN) {
					//the navigating buttons: left, right, up, down, home, end, page up, page down
					e.consume();
				} else if (kc == KeyEvent.VK_BACK_SPACE) {
					if (input.length() == 0)
						e.consume();
				}
			}
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					if (!consumerQueue.isEmpty())
						consumerQueue.remove().accept(input.toString());
					if (consumerQueue.isEmpty())
						setEditable(false);
					input = new StringBuffer();
				} else if (c == KeyEvent.VK_BACK_SPACE) {
					if (input.length() > 0)
						input.deleteCharAt(input.length()-1);
				} else {
					input.append(c);
				}
			}
		});
	}

	/**
	 * write a character
	 * @param b the character to append
	 */
	public void write(int b) {
		append(Character.toString(b));
	}
	
	/**
	 * write a string
	 * @param s the string to append
	 */
	public void write(String s) {
		append(s);
	}
	
	/**
	 * enable the user input
	 * adds a consumer to the queue that receives the typed string when the user presses enter
	 * each consumer will receive a separate line
	 * @param inputConsumer the consumer that receives the text input
	 */
	public void readln(Consumer<String> inputConsumer) {
		consumerQueue.add(inputConsumer);
		setEditable(true);
		updateUI();
		setCaretPosition(getText().length());
	}
}
