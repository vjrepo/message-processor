package com.jpm.main.message.processor;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jpm.main.cacheManager.AdjustedInfo;
import com.jpm.main.cacheManager.CacheManager;
import com.jpm.main.cacheManager.ProcessedMessage;
import com.jpm.msgprocessor.external.messageTypes.AdjustmentOperation;
import com.jpm.msgprocessor.external.messageTypes.AdjustmentSaleMessage;
import com.jpm.msgprocessor.external.messageTypes.AdjustmentType;
import com.jpm.msgprocessor.external.messageTypes.ComplexSaleMessage;
import com.jpm.msgprocessor.external.messageTypes.MessageType;
import com.jpm.msgprocessor.external.messageTypes.SimpleSaleMessage;

public class StructuredMessageProcessor implements MessageProcessor {
	private static Gson gson = new Gson();

	/**
	 * 
	 * <p>
	 * Format of message:
	 * </p>
	 * 
	 * A message is either simple @see SimpleSaleMessage, complex @see
	 * ComplexSaleMessage or adjustment @see AdjustmentSaleMessage
	 * 
	 * <pre>
	 * Simple message -
	 * {"productType":"apple","saleValue":12,"messageType":"SIMPLE"}
	 * 
	 * Complex message -
	 * {"simpleSaleMessage":{"productType":"laptop","saleValue":12,"messageType"
	 * :"SIMPLE"},"occurrence":5,"messageType":"COMPLEX"}
	 * 
	 * Adjustment message -
	 * {"simpleSaleMessage":{"productType":"apple","saleValue":12,"messageType":
	 * "SIMPLE"},"adjustmentType":{"productType":"apple","adjustmentValue":20,
	 * "adjustmentOperation":"ADD"},"messageType":"ADJUSTMENT"}
	 * 
	 * @param message
	 * @return
	 * 
	 **/
	@Override
	public void processMessage(String message) {
		JsonElement jelement = new JsonParser().parse(message);
		JsonObject obj = jelement.getAsJsonObject();
		String messageType = obj.get("messageType").getAsString();

		switch (MessageType.valueOf(messageType)) {
		case SIMPLE:
			SimpleSaleMessage saleMessage = gson.fromJson(message, SimpleSaleMessage.class);
			CacheManager.getInstance().addProcessedMessage(saleMessage.getProductType(),
					new ProcessedMessage(saleMessage.getSaleValue(), 1l));
			break;

		case COMPLEX:
			ComplexSaleMessage complexSaleMessage = gson.fromJson(message, ComplexSaleMessage.class);
			saleMessage = complexSaleMessage.getSimpleSaleMessage();
			CacheManager.getInstance().addProcessedMessage(saleMessage.getProductType(),
					new ProcessedMessage(saleMessage.getSaleValue(), complexSaleMessage.getOccurrences()));
			break;

		case ADJUSTMENT:
			AdjustmentSaleMessage adjustmentSaleMessage = gson.fromJson(message, AdjustmentSaleMessage.class);
			saleMessage = adjustmentSaleMessage.getSimpleSaleMessage();
			CacheManager.getInstance().addProcessedMessage(saleMessage.getProductType(),
					new ProcessedMessage(saleMessage.getSaleValue(), 1l));
			processAdjustments(adjustmentSaleMessage);

			break;
		default:
			throw new UnsupportedOperationException(
					String.format("The operation %d is undefined", messageType));
		}
	}

	private void processAdjustments(AdjustmentSaleMessage adjustmentSaleMessage) {
		AdjustmentType adjustmentType = adjustmentSaleMessage.getAdjustmentType();
		List<ProcessedMessage> processedMessageList = CacheManager.getInstance()
				.getProcessedMessagesForProduct(adjustmentType.getProductType());

		for (ProcessedMessage processedMessage : processedMessageList) {
			switch (adjustmentType.getAdjustmentOperation()) {
			case ADD:
				Long saleAmount = processedMessage.getSaleAmount();
				saleAmount += (adjustmentType.getAdjustmentValue() * processedMessage.getOccurances());
				processedMessage.setSaleAmount(saleAmount);
				addAdjustments(adjustmentType, processedMessage, AdjustmentOperation.ADD);

				break;

			case SUBTRACT:
				saleAmount = processedMessage.getSaleAmount();
				saleAmount -= (adjustmentType.getAdjustmentValue() * processedMessage.getOccurances());
				// if(saleAmount < 0)
				// System.out.println("Warning!!! The sale amount cannot be
				// below zero after adjustment");
				processedMessage.setSaleAmount(saleAmount);
				addAdjustments(adjustmentType, processedMessage, AdjustmentOperation.SUBTRACT);

				break;

			case MULTIPLY:
				saleAmount = processedMessage.getSaleAmount();
				saleAmount *= (adjustmentType.getAdjustmentValue() * processedMessage.getOccurances());
				processedMessage.setSaleAmount(saleAmount);
				addAdjustments(adjustmentType, processedMessage, AdjustmentOperation.MULTIPLY);

				break;

			default:
				throw new UnsupportedOperationException(String.format("The adjustment operation %d is undefined",
						adjustmentType.getAdjustmentOperation()));
			}

		}
		CacheManager.getInstance().adjustProcessedMessages(adjustmentType.getProductType(), processedMessageList);
	}

	private void addAdjustments(AdjustmentType adjustmentType, ProcessedMessage processedMessage,
			AdjustmentOperation adjustmentOperation) {
		List<AdjustedInfo> adjustedInfoList = processedMessage.getAdjustedInfoList();
		AdjustedInfo adjustedInfo = new AdjustedInfo(adjustmentType.getAdjustmentValue(), adjustmentOperation);
		if (adjustedInfoList == null || adjustedInfoList.isEmpty()) {
			adjustedInfoList = new ArrayList<>();
		}
		adjustedInfoList.add(adjustedInfo);
		processedMessage.setAdjustedInfoList(adjustedInfoList);
	}
}
