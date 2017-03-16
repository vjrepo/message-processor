package com.jpm.msgprocessor.external.messageTypes;

public class SimpleSaleMessage {
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(Long saleValue) {
		this.saleValue = saleValue;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	private String productType;
	private Long saleValue;
	private MessageType messageType;


	public SimpleSaleMessage() {
		messageType = MessageType.SIMPLE;
	}
}
