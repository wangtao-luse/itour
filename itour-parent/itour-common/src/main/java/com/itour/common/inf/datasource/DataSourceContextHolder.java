package com.itour.common.inf.datasource;

public class DataSourceContextHolder{
	 // 存放当前线程使用的数据源类型
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    // 设置数据源
    public static void setDataSource(DataSourceType type){
        contextHolder.set(type);
    }

    // 获取数据源
    public static DataSourceType getDataSource(){
        return contextHolder.get();
    }

    // 清除数据源
    public static void clearDataSource(){
        contextHolder.remove();
    }
}
