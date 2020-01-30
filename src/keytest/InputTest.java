package keytest;

import javax.swing.JFrame;

import textAreaIO.PrintingTextArea;

public class InputTest extends JFrame {
	private static final long serialVersionUID = -4456525641409425628L;
	private PrintingTextArea pta;

	public InputTest(){
		super("Inputtest");
		pta = new PrintingTextArea();
		add(pta);
		pack();
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		PrintingTextArea pta = new InputTest().pta;
		Thread.sleep(1000);
		pta.output.println("test");
		Thread.sleep(2000);
		pta.output.println("please enter a text: ");
		pta.output.println("input: " + pta.input.nextLine());
		pta.output.print("please enter a number: ");
		pta.output.println("num: " + pta.input.nextInt());
	}
}
