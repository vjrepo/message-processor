package com.jpm.msgprocessor.external.messageTypes;

public class AdjustmentSaleMessage extends SimpleSaleMessage {
	public SimpleSaleMessage getSimpleSaleMessage() {
		return simpleSaleMessage;
	}

	public void setSimpleSaleMessage(SimpleSaleMessage simpleSaleMessage) {
		this.simpleSaleMessage = simpleSaleMessage;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	private SimpleSaleMessage simpleSaleMessage;
	private AdjustmentType adjustmentType;

	public AdjustmentSaleMessage() {
		super.setMessageType(MessageType.ADJUSTMENT);
	}
}
