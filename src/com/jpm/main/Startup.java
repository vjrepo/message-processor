package com.jpm.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.jpm.main.message.receiver.MessageReceiver;
import com.jpm.main.message.receiver.MessageRecieverImpl;

public class Startup {

	public static void main(String[] args) {
		// sample test case, see more at @see MessageProcessingTester
		MessageReceiver messageReceiver = new MessageRecieverImpl();
		try (Stream<String> stream = Files
				.lines(Paths.get(Startup.class.getResource("/structuredMixMessages.txt").toURI()))) {
			stream.forEach(messageReceiver::recieveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		// sample console user interface
		// Scanner scanner = new Scanner(System.in);
		// while (true) {
		// System.out.println("1 - process messages, 2 for exit");
		// System.out.println("Enter your choice");
		// int input = scanner.nextInt();
		// scanner.nextLine();
		// if (input == 1) {
		// System.out.println("Enter the message to be processed");
		// String message = scanner.nextLine();
		// messageReceiver.recieveMessage(message);
		// } else if (input == 2) {
		// scanner.close();
		// break;
		// }
		// }
	}
}
