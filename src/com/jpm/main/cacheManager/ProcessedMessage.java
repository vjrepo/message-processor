package com.jpm.main.cacheManager;

import java.util.List;

public class ProcessedMessage {
	public ProcessedMessage(Long saleAmount, Long occurances) {
		this.saleAmount = saleAmount;
		this.occurances = occurances;
	}

	public Long getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Long saleAmount) {
		this.saleAmount = saleAmount;
	}

	public Long getOccurances() {
		return occurances;
	}

	public void setOccurances(Long occurances) {
		this.occurances = occurances;
	}
	
	public List<AdjustedInfo> getAdjustedInfoList() {
		return adjustedInfoList;
	}

	public void setAdjustedInfoList(List<AdjustedInfo> adjustedInfoList) {
		this.adjustedInfoList = adjustedInfoList;
	}

	private Long saleAmount;
	private Long occurances;
	private List<AdjustedInfo> adjustedInfoList;
}
