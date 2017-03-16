package com.jpm.main.reporting;

import java.util.List;
import java.util.Set;

import com.jpm.main.cacheManager.AdjustedInfo;
import com.jpm.main.cacheManager.CacheManager;
import com.jpm.main.cacheManager.ProcessedMessage;

public class ReportGeneratorImpl implements ReportGenerator {

	@Override
	public void generateInterimReport() {
		Set<String> productSet = CacheManager.getInstance().getAllProcessedProducts();

		System.out.println("--------------------Message processed report after 10 messages---------------");
		System.out.println("-----------------------------------------------------------------------------");
		for (String product : productSet) {
			System.out.print("Product Name : ");
			System.out.println(product);
			List<ProcessedMessage> processedMessageList = CacheManager.getInstance()
					.getProcessedMessagesForProduct(product);
			Long saleValue = 0l;
			Long saleOccurances = 0l;
			for (ProcessedMessage processedMessage : processedMessageList) {
				saleOccurances += processedMessage.getOccurances();
				saleValue += (processedMessage.getSaleAmount() * processedMessage.getOccurances());
			}
			System.out.print("Number of sales for : ");
			System.out.println(saleOccurances);
			System.out.print("Total sale by value : ");
			System.out.println(saleValue);
		}
	}

	@Override
	public void generateFinalReport() {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("--------------------Final message processing report------------------");
		System.out.println("---------------------------------------------------------------------");
		for (String product : CacheManager.getInstance().getAllProcessedProducts()) {
			for (ProcessedMessage processedMessage : CacheManager.getInstance()
					.getProcessedMessagesForProduct(product)) {
				List<AdjustedInfo> adjustedInfoList = processedMessage.getAdjustedInfoList();
				if (adjustedInfoList != null) {
					System.out.println("----------Adjustments for the each sale message---------");
					System.out.println(product);
					for (AdjustedInfo adjustedInfo : adjustedInfoList) {
						Long adjustmentValue = adjustedInfo.getAdjustmentValue();
						System.out.println("Adjustment Type : " + adjustedInfo.getAdjustmentOperation());
						System.out.println("Adjustment value : " + adjustmentValue);
					}
				}
				// here continue with total and average adjustments reporting
			}
		}
	}

}
