package com.jpm.main.cacheManager;

import com.jpm.msgprocessor.external.messageTypes.AdjustmentOperation;

public class AdjustedInfo {

	private Long adjustmentValue;
	
	private AdjustmentOperation adjustmentOperation;
	
	public AdjustmentOperation getAdjustmentOperation() {
		return adjustmentOperation;
	}

	public void setAdjustmentOperation(AdjustmentOperation adjustmentOperation) {
		this.adjustmentOperation = adjustmentOperation;
	}

	public Long getAdjustmentValue() {
		return adjustmentValue;
	}

	public void setAdjustmentValue(Long adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}

	public AdjustedInfo(Long adjustmentValue, AdjustmentOperation adjustmentOperation) {
		this.adjustmentValue = adjustmentValue;
		this.adjustmentOperation = adjustmentOperation;
	}

}
