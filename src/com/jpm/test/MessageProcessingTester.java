package com.jpm.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.jpm.main.Startup;
import com.jpm.main.message.receiver.MessageReceiver;
import com.jpm.main.message.receiver.MessageRecieverImpl;
import com.jpm.main.reporting.ReportGenerator;
import com.jpm.main.reporting.ReportGeneratorImpl;

public class MessageProcessingTester {

	public void test_Simple_Messages() {
		MessageReceiver messageReceiver = new MessageRecieverImpl();
		ReportGenerator generator = new ReportGeneratorImpl();
		try (Stream<String> stream = Files
				.lines(Paths.get(Startup.class.getResource("/structuredSimpleMessages.txt").toURI()))) {
			stream.forEach(messageReceiver::recieveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		generator.generateInterimReport();
		generator.generateFinalReport();
	}

	public void test_Complex_Messages() {
		MessageReceiver messageReceiver = new MessageRecieverImpl();
		try (Stream<String> stream = Files
				.lines(Paths.get(Startup.class.getResource("/structuredComplexMessages.txt").toURI()))) {
			stream.forEach(messageReceiver::recieveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	public void testMax_Mixed_Message_Processing() {
		MessageReceiver messageReceiver = new MessageRecieverImpl();
		try (Stream<String> stream = Files
				.lines(Paths.get(Startup.class.getResource("/structuredMixMessages.txt").toURI()))) {
			stream.forEach(messageReceiver::recieveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MessageProcessingTester messageProcessingTester = new MessageProcessingTester();
		messageProcessingTester.test_Simple_Messages();
//		messageProcessingTester.test_Complex_Messages();
//		messageProcessingTester.testMax_Mixed_Message_Processing();
	}
}
