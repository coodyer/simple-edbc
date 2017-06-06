package com.simple.db.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AspectUtil {

	public static ThreadLocal<Map<String, Object>> moduleThread = new ThreadLocal<Map<String, Object>>();

	private static final String JDBC_WRAPPER="JDBC_WRAPPER";
	public static Map<String, Object> getCurrRecord() {
		Map<String, Object> record = moduleThread.get();
		if (StringUtil.isNullOrEmpty(record)) {
			record = new HashMap<String, Object>();
		}
		return record;
	}


	@SuppressWarnings("unchecked")
	public static String getCurrDBTemplate() {
		Map<String, Object> record = getCurrRecord();
		List<String> dbTemplates = (List<String>) record
				.get(JDBC_WRAPPER);
		if (StringUtil.isNullOrEmpty(dbTemplates)) {
			return null;
		}
		return dbTemplates.get(dbTemplates.size() - 1);
	}

	@SuppressWarnings("unchecked")
	public static void writeDBTemplate(String dbTemplate) {
		Map<String, Object> record = getCurrRecord();
		List<String> dbTemplates = (List<String>) record
				.get(JDBC_WRAPPER);
		if (StringUtil.isNullOrEmpty(dbTemplates)) {
			dbTemplates = new ArrayList<String>();
		}
		dbTemplates.add(dbTemplate);
		record.put(JDBC_WRAPPER, dbTemplates);
		moduleThread.set(record);
	}

	@SuppressWarnings("unchecked")
	public static void minusDBTemplate() {
		Map<String, Object> record = getCurrRecord();
		List<String> dbTemplates = (List<String>) record
				.get(JDBC_WRAPPER);
		if (dbTemplates == null) {
			return;
		}
		if (dbTemplates.size() <= 1) {
			record.put(JDBC_WRAPPER, null);
			moduleThread.set(record);
			return;
		}
		dbTemplates.remove(dbTemplates.size() - 1);
		record.put(JDBC_WRAPPER, dbTemplates);
		moduleThread.set(record);
	}

}
