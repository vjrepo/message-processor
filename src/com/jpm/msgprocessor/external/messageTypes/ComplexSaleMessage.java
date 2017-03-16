package com.jpm.msgprocessor.external.messageTypes;

public class ComplexSaleMessage extends SimpleSaleMessage {
	public SimpleSaleMessage getSimpleSaleMessage() {
		return simpleSaleMessage;
	}

	public void setSimpleSaleMessage(SimpleSaleMessage simpleSaleMessage) {
		this.simpleSaleMessage = simpleSaleMessage;
	}

	public Long getOccurrences() {
		return occurrence;
	}

	public void setOccurrences(Long occurances) {
		this.occurrence = occurances;
	}

	private SimpleSaleMessage simpleSaleMessage;
	private Long occurrence;
	
	public ComplexSaleMessage() {
		super.setMessageType(MessageType.COMPLEX);
	}
}
