package com.jpm.main.message.processor;

import com.jpm.msgprocessor.external.messageTypes.MessageType;

/**
 * 
 * this class is not used, 
 * it expects raw/unformatted sale messages for processing
 * @author viji
 *
 */
@Deprecated
public class UnStructuredMessageProcessor implements MessageProcessor {

	/**
	 * 
	 * <p>
	 * Format of message:
	 * </p>
	 * 
	 * <pre>
	 * A message is either simple, complex or adjustment type
	 * 
	 * Simple message -
	 * simple:apple at 10p
	 * 
	 * Complex message -
	 * complex:20 sales of apples at 10p each
	 * 
	 * Adjustment message -
	 * adjustment:apple at 10p add 20p apples
	 * 
	 * @param message
	 * @return
	 * 
	 **/
	@Override
	public void processMessage(String message) {
		MessageType messageType = MessageType.SIMPLE;
		if (messageType.equals(MessageType.SIMPLE)) {

		} else if (messageType.equals(MessageType.COMPLEX)) {

		} else if (messageType.equals(MessageType.ADJUSTMENT)) {

		} else {
			throw new UnsupportedOperationException(
					String.format("The operation %d is undefined", messageType));
		}
	}

}
