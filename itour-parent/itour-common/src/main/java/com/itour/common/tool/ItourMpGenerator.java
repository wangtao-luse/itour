package com.itour.common.tool;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

/**
 * @Auther wangtao
 * @Create 2017/7/5 0005
 */

    public class ItourMpGenerator{
    	public static final String MODEL_TRAVEL="itour-travel-service";
    	public static final String MODEL_DICTIONARY="itour-dictionary-service";
    	public static final String MODEL_ACCOUNT="itour-account-service";
    	public static final String MODEL_QUARTZ="itour-quartz-service";
    	public static final String MODEL_WORK="itour-work-service";
    	public static final String MODEL_FOLLOW="itour-follow-service";
    	public static final String MODEL_FIND="itour-find-service";
    	
        public static void main(String[] args) throws InterruptedException, IOException {
        	/***
        	 * 1.待解决问题
        	 * 1.生成Service的时候implement xxxxxService 不知道怎么去掉
        	 */
        	/**
        	 * 模块名称
        	 */
  			 String model=ItourMpGenerator.MODEL_WORK;
  			        
  			        
  			 /**
  			  * 是否为视图
  			  */
  			 boolean isView =true;//视图
  			 /**
  			  * 需要生成的表名
  			  */
  			 String [] include= new String[] {
  					 "view_workInfo_worktext"
  			           		 
  			 };
  			 /**
  			  * 需要忽略的表前缀
  			  */
  			 String [] tableprefix = new String [] {};
  			 /**
  			  * 是否需要覆盖Service  （是否创建service）
  			  */
		     boolean isOverflowService=true;
			GenneratorCode(model, isView, include, tableprefix,isOverflowService);
		 
        	 
        }

		public static void GenneratorCode(String model,boolean isView,String [] include,String []tableprefix,boolean isOverflowService ) {
			String mod = model.substring(0, model.lastIndexOf("-"));
			//获取项目目录
			String absolutePath = System.getProperty("user.dir");
			String location= new File(absolutePath).getParent();
			
			String service_path=location+"/"+mod+"-service"+"/src/main/java/com/itour/service";
			String mapper_path=location+"/"+mod+"-persist"+"/src/main/java/com/itour/persist";
			String xml_path=location+"/"+mod+"-persist"+"/src/main/resources/mapping";
			String subStr = mod.substring(mod.indexOf("-")+1);
			String sub=subStr.contains("-")==true?subStr.substring(0, subStr.indexOf("-")):subStr;
			String v =isView==true?"/vo":"";
			String model_path=location+"/itour-model/src/main/java/com/itour/model/"+sub+v;
		
			AutoGenerator mpg = new AutoGenerator();
            mpg.setTemplateEngine(new BeetlTemplateEngine());
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            //gc.setOutputDir("D://mysql");
            gc.setFileOverride(true);
            gc.setActiveRecord(true);
            gc.setEnableCache(false);// XML 二级缓存
            gc.setBaseResultMap(true);// XML ResultMap
            gc.setBaseColumnList(true);// XML columList
            gc.setAuthor("wangtao");

            // 自定义文件命名，注意 %s 会自动填充表实体属性！
            gc.setMapperName("%sMapper");
            gc.setXmlName("%sMapper");
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sService");
            gc.setControllerName("%sController");
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
           // dsc.setDbType(DbType.ORACLE);
            dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/

		
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUrl("jdbc:mysql://106.54.162.159:3308/itour?serverTimezone=UTC");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            String [] tablePrefix = new String[] { "t_m_", "t_p_", "t_s_", "t_r_", "t_c_","t_a_","t_b_","t_d_","t_t_","t_w_"};
            tablePrefix =tableprefix.length<=0?tablePrefix:tableprefix;
            strategy.setTablePrefix(tablePrefix);// 此处可以修改为您的表前缀
            strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库列映射到实体类的命名策略
            strategy.setInclude(include); // 需要生成的表
            // strategy.setExclude(new String[]{"test"}); // 排除生成的表
            mpg.setStrategy(strategy);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.itour");
            //model的包名
            if(isView) {
            	   pc.setEntity("model."+subStr+".dto");
            }else {
            	   pc.setEntity("model."+subStr);	
            }
            pc.setMapper("persist");
            pc.setServiceImpl("service");
            mpg.setPackageInfo(pc);
            //设置自定义输出目录（分布式项目使用）
            Map<String, String> pathInfo = new HashMap<>();
            String pojodir = model_path;
            String mapperdir = mapper_path;
            String serviceImpldir =isOverflowService==true? service_path:"";
            String mapper_xml_dir=xml_path;
			pathInfo.put(ConstVal.ENTITY_PATH, pojodir);
			pathInfo.put(ConstVal.MAPPER_PATH, mapperdir);
			pathInfo.put(ConstVal.XML_PATH, mapper_xml_dir);
			pathInfo.put(ConstVal.SERVICE_PATH, "");
            pathInfo.put(ConstVal.SERVICE_IMPL_PATH, serviceImpldir);
            pc.setPathInfo(pathInfo);

            // 执行生成
            mpg.execute();
		}

    }