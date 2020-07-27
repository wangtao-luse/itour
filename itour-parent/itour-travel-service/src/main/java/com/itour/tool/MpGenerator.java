package com.itour.tool;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

/**
 * @Auther ShuPF
 * @Create 2017/7/5 0005
 */

    public class MpGenerator{
        public static void main(String[] args) throws InterruptedException, IOException {
        	File file = new File("itour-account-service");
        	//获取绝对路径
			String absolutePath = file.getAbsolutePath();
			String path = file.getPath();
			String location= new File(absolutePath).getParentFile().getParent()+"\\"+file.getPath();
			System.out.println(location);
		
		  LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		  map.put("itour-model", "/src/main/java");
		  //map.forEach((key,value)->{ GenneratorCode(key,value); });
		 
        }

		public static void GenneratorCode(String model,String path) {
			File file = new File(model);
			String absolutePath = file.getAbsolutePath();
			String location= new File(absolutePath).getParentFile().getParent()+"\\"+file.getPath();
			System.out.println(location);
			AutoGenerator mpg = new AutoGenerator();
            mpg.setTemplateEngine(new BeetlTemplateEngine());
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir(location+path);
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
            gc.setServiceImplName("%sServiceImpl");
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
            dsc.setUrl("jdbc:mysql://localhost:3306/itour?serverTimezone=UTC");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setTablePrefix(new String[] { "t_m_", "t_p_", "t_s_", "t_r_", "t_c_","t_a_","t_b_","t_d_","t_t_"});// 此处可以修改为您的表前缀
            strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
            strategy.setInclude(new String[] {"T_T_TRAVEL_COMMENT"}); // 需要生成的表
            // strategy.setExclude(new String[]{"test"}); // 排除生成的表
           
            mpg.setStrategy(strategy);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.itour");
            //model的包名
            pc.setEntity("model.travel");
            pc.setMapper("persist");
            mpg.setPackageInfo(pc);
            TemplateConfig tc = new TemplateConfig();
            if("itour-model".equals(model)) {
                tc.setXml(null);
                tc.setMapper(null);
                tc.setService(null);
                tc.setServiceImpl(null);
                tc.setController(null);
            }else if("itour-account-service".equals(model)) {
            	tc.setEntity(null);
            	tc.setController(null);
            	tc.setService(null);
            }
           
            mpg.setTemplate(tc);

            // 执行生成
            mpg.execute();
		}

    }