package networking.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSender extends Thread {
	private ObjectOutputStream out;
	private boolean running;

	private BlockingQueue<String> messagesToSend;

	ClientSender(ObjectOutputStream O) {
		this.out = O;

		messagesToSend = new LinkedBlockingQueue<String>();
	}

	@Override
	public void run() {
		running = true;

		System.out.println("CLIENT SENDER RUNNING");
		try {
			while (running) {
				// wait for message to be added to blocking queue
				String message = messagesToSend.take();
				out.writeObject(message);
			}
		} catch (IOException e) {
			System.out.println("The Object couldn't be sent");
		} catch (InterruptedException e) {
			System.out.println("Blocking queue error");
		}

		System.out.println("Client Sender Thread ended");
	}

	public void send(String s) {
		messagesToSend.add(s);
	}

	public void close() {
		running = false;
	}
}
