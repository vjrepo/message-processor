package com.jpm.msgprocessor.external.messageTypes;

public class AdjustmentType {
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Long getAdjustmentValue() {
		return adjustmentValue;
	}
	public void setAdjustmentValue(Long adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}
	public AdjustmentOperation getAdjustmentOperation() {
		return adjustmentOperation;
	}
	public void setAdjustmentOperation(AdjustmentOperation adjustmentOperation) {
		this.adjustmentOperation = adjustmentOperation;
	}
	private String productType;
	private Long adjustmentValue;
	private AdjustmentOperation adjustmentOperation;
}
