package com.jpm.main.cacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
	
	private static final int MAX_PROCESSING_CAPACITY = 50;

	public static CacheManager getInstance() {
		return singletonInstance;
	}
	
	private static CacheManager singletonInstance = new CacheManager();
	
	private CacheManager() {}
	
	private ConcurrentHashMap<String, List<ProcessedMessage>> processedMessageCache = new ConcurrentHashMap<>();
	
	public void addProcessedMessage(String productType, ProcessedMessage processedMessage) {
		List<ProcessedMessage> processedList = processedMessageCache.get(productType);
		if(processedList == null) {
			processedList = new ArrayList<>();
		}
		processedList.add(processedMessage);
		processedMessageCache.put(productType, processedList);
	}
	
	public List<ProcessedMessage> getProcessedMessagesForProduct(String productType) {
		return processedMessageCache.get(productType);
	}

	public void adjustProcessedMessages(String productType, List<ProcessedMessage> processedMessageList) {
		processedMessageCache.put(productType, processedMessageList);
	}

	public Set<String> getAllProcessedProducts() {
		return processedMessageCache.keySet();
	}

}
