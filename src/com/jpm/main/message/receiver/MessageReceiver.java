package com.jpm.main.message.receiver;

/*
 * 
 * An interface that is the root of all message receiver implementations
 * 
 * It's purpose is to accept incoming sale messages after due validation
 * then forward the message for processing the sale defined in the message
 * 
 * Current implementation @see {@link DummyMessageREciever}
 * 
 */
public interface MessageReceiver {
	public void recieveMessage(String saleMessage);
}
