package com.jpm.main.message.receiver;

import com.jpm.main.message.processor.MessageProcessor;
import com.jpm.main.message.processor.StructuredMessageProcessor;
import com.jpm.main.reporting.ReportGenerator;
import com.jpm.main.reporting.ReportGeneratorImpl;

public class MessageRecieverImpl implements MessageReceiver {

	private MessageProcessor messageProcessor = new StructuredMessageProcessor();
	private ReportGenerator reportGenerator = new ReportGeneratorImpl();
	private static final int MAX_PROCESSING_CAPACITY = 50;

	private int messageCounter = 0;

	@Override
	public void recieveMessage(String saleMessage) {

		if (validMessage(saleMessage)) {
			if (messageCounter < MAX_PROCESSING_CAPACITY) {
				if (messageCounter != 0 && messageCounter % 10 == 0) {
					reportGenerator.generateInterimReport();
				}
				messageProcessor.processMessage(saleMessage);
				// System.out.println("Processed message : " + saleMessage);

			} else if (messageCounter == MAX_PROCESSING_CAPACITY) {
				System.out.println("Message processing is halted. No more messages will be processed in the system.");
				reportGenerator.generateFinalReport();
			} else {
				// ignore further messages
			}
			messageCounter++;
		}
	}

	/**
	 * 
	 * All validation to be carried on the incoming message here
	 * The message will be ignored if it doesn't confirm to SET rules
	 * @param saleMessage
	 * @return
	 */
	private boolean validMessage(String saleMessage) {
		return !saleMessage.isEmpty();
	}

}
