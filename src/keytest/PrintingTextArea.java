package keytest;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.CharBuffer;
import java.util.Scanner;

public class PrintingTextArea extends IOTextArea implements Closeable {
	private static final long serialVersionUID = 7000005244229705652L;
	public final PrintStream output = new PrintStream(new TextAreaStream());
	public final Scanner input = new Scanner(new Readable() {
		public int read(CharBuffer cb) throws IOException {
			Latch reading = new Latch(true);
			readln((l) -> {cb.put(l); reading.reset();});
			while (reading.state())
				Thread.onSpinWait();
			//re-add the newline
			cb.append((char) 10);
			return cb.length();
		}
	});


	/**
	 * stop accepting outputs
	 */
	public void close() throws IOException {
		output.close();
		input.close();
	}


	public class TextAreaStream extends OutputStream {
		private volatile boolean closed;
	
		/**
		 * @throws IOException if the stream is closed
		 */
		private void ensureOpen() throws IOException {
	        if (closed) {
	            throw new IOException("Stream closed");
	        }
	    }
	
		@Override
		public void write(int b) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(b);
		}
		
		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(new String(b,off,len));
		}
		
		@Override
		public void write(byte[] b) throws IOException {
			ensureOpen();
			PrintingTextArea.this.write(new String(b));
		}
		
		/**
		 * stop accepting inputs
		 */
		@Override
		public void close() {
			closed = true;
		}
	}
}
