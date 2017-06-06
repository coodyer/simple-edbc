package com.simple.db.handle;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.simple.db.util.AspectUtil;
import com.simple.db.util.StringUtil;

/**
 * 动态数据源
 * @author websos
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource  {
	@Override
	protected Object determineCurrentLookupKey() {
		String template=AspectUtil.getCurrDBTemplate();
		if(StringUtil.isNullOrEmpty(template)){
			return "defaultTargetDataSource";
		}
		return template;
		//通过此处获得当前数据源 
	}
}
