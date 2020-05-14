package com.itour.common.tool;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

/**
 * @Auther ShuPF
 * @Create 2017/7/5 0005
 */

    public class MpGenerator{
        public static void main(String[] args) throws InterruptedException {
            AutoGenerator mpg = new AutoGenerator();
            mpg.setTemplateEngine(new BeetlTemplateEngine());
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir("D://mysql");
            gc.setFileOverride(true);
            gc.setActiveRecord(true);
            gc.setEnableCache(true);// XML 二级缓存
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

		/*
		 * dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
		 * dsc.setUrl("jdbc:oracle:thin:@10.103.32.24:1521:onlinetest");
		 * dsc.setUsername("onlinetest"); dsc.setPassword("onlinetest438");
		 * mpg.setDataSource(dsc);
		 */
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUrl("jdbc:mysql://localhost:3306/itour?serverTimezone=UTC");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
            strategy.setTablePrefix(new String[] { "t_m_", "t_p_", "t_s_", "t_r_", "t_c_","t_a_","t_b_"});// 此处可以修改为您的表前缀
            strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
            strategy.setInclude(new String[] {"t_a_messageinfo"}); // 需要生成的表
            // strategy.setExclude(new String[]{"test"}); // 排除生成的表
            mpg.setStrategy(strategy);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("src");
//            pc.setModuleName("member");
            mpg.setPackageInfo(pc);
            

            // 执行生成
            mpg.execute();
        }

    }