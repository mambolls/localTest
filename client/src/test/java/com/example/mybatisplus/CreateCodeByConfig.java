package com.example.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 推荐使用此类生成代码
 * <p>
 * CreateCodeByConfig （读取yml文件的数据库配置,生成代码类）
 * 详情参考地址:
 * http://baomidou.oschina.io/mybatis-plus-doc/#/generate-code
 * <p>
 * 注意:
 * 1: 若使用Eclipse生成代码,需要刷新工程,idea则不需要
 * 2: 第一次生成代码不能配置@MapperScan("org.app.springmvc.mybatis.module.mapper*")
 * 以及在application-mybatisplus.yml中的mapper-locations: classpath:mapper/*Mapper.xml,因为还没有这些文件,
 * 项目启动会报错. 后续有新表要再次生成,则需配置上面的配置,之前编写的代码,不会被覆盖
 *
 * @author (tianle)
 */


@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateCodeByConfig {

    // 模块名
    private final String ModuleName = "client";

    // 排除生成的表
    private final String[] ExcludeTables = {"kxl_zs", "zs_account", "zs_auth", "zs_autosettlement", "zs_settlement", "zs_invoice_info"};

    // 要生成的表
//    private final String[] IncludeTables = {"zs_invoice_info","zs_invoice_item","zs_sales_details","zs_source_target_data","zs_invoice_company"};
    private final String[] IncludeTables = {"user"};

    @Autowired(required = true)
    private DataSourceProperties dataSource;

    @Test
    public void createCode() {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(getCreatePath()); // 输出目录("/src/main/java")
        gc.setFileOverride(true); // 是否覆盖文件
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(true); // 生成代码之后,自动打开目录
        gc.setAuthor("lilinsong");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName(dataSource.getDriverClassName());
        dsc.setUsername(dataSource.getUsername());
        dsc.setPassword(dataSource.getPassword());
        dsc.setUrl(dataSource.getUrl());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // 如果没有排除生成的表,则生成全部的表
//        if (ExcludeTables.length != 0) {
//            strategy.setExclude(ExcludeTables);
//        }
        // 如果没有包含生成的表,则生成全部的表
        if (IncludeTables.length != 0) {
            strategy.setInclude(IncludeTables);
        }
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(getPackagePath());
        pc.setModuleName(ModuleName);
        //pc.setController("controller"); // 默认为web
        //pc.setEntity("dto");// 默认为entity
        mpg.setPackageInfo(pc);

        // 关闭默认 xml 生成，调整生成 至 根目录
        // TemplateConfig tc = new TemplateConfig();
        // tc.setXml(null);
        // mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 将默认生成在包下面的xml文件复制到resouce的mapper中
        String orginalPath = getCreatePath() + getPackagePath().replace(".", "/") + "/" + ModuleName + "/mapper/xml";
        String targertPath = System.getProperty("user.dir") + "/src/main/resources/mapper";
        copyFolder(orginalPath, targertPath);
        deleteDir(new File(orginalPath));
    }

    private static String getCreatePath() {
        String rootPaht = System.getProperty("user.dir");
        String createPath = rootPaht + "/src/main/java/";
        return createPath;
    }

    private static String getPackagePath() {
        return CreateCodeByConfig.class.getName().replace(".CreateCodeByConfig", "");
    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}
