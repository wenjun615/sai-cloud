package com.wen.sai.common.generator;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.wen.sai.common.domain.BaseController;
import com.wen.sai.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MyBatis-Plus 代码生成器
 * </p>
 *
 * @author wenjun
 * @since 2021/1/27
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        // 项目绝对路径
        String projectPath = System.getProperty("user.dir");
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        autoGenerator.setGlobalConfig(initGlobalConfig(projectPath))
                // 数据源配置
                .setDataSource(initDataSourceConfig())
                // 包名配置
                .setPackageInfo(initPackageConfig())
                // 策略配置
                .setStrategy(initStrategyConfig())
                // 模板引擎
                .setTemplateEngine(new VelocityTemplateEngine())
                // 执行
                .execute();
    }

    /**
     * 初始化全局配置
     *
     * @param projectPath 项目绝对路径
     * @return 全局配置
     */
    private static GlobalConfig initGlobalConfig(String projectPath) {
        GlobalConfig globalConfig = new GlobalConfig();
        // 生成文件的输出目录。默认值：D 盘根目录
        globalConfig.setOutputDir(projectPath + "/sai-mbg/src/main/java");
        // 是否打开输出目录。默认值：true
        globalConfig.setOpen(false);
        // 是否覆盖已有文件。默认值：false
        globalConfig.setFileOverride(true);
        // 开发人员。默认值：null
        globalConfig.setAuthor("wenjun");
        // 实体命名方式。默认值：null，%s 为占位符
        globalConfig.setEntityName("%s");
        // Controller 命名方式。默认值：null
        globalConfig.setControllerName("%sController");
        // Service 命名方式。默认值：null
        globalConfig.setServiceName("%sService");
        // ServiceImpl 命名方式。默认值：null
        globalConfig.setServiceImplName("%sServiceImpl");
        // Mapper 命名方式。默认值：null
        globalConfig.setMapperName("%sMapper");
        // Mapper.xml 命名方式。默认值：null
        globalConfig.setXmlName("%sMapper");
        // 开启 Mapper.xml 中的 BaseResultMap。默认值：false
        globalConfig.setBaseResultMap(true);
        // 开启 Mapper.xml 中的 BaseColumnList。默认值：false
        globalConfig.setBaseColumnList(true);
        // 开启 Swagger 模式。默认值：false
        globalConfig.setSwagger2(true);
        // 时间类型对应策略。默认值：TIME_PACK
        globalConfig.setDateType(DateType.ONLY_DATE);
        return globalConfig;
    }

    /**
     * 初始化数据源配置
     *
     * @return 数据源配置
     */
    private static DataSourceConfig initDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 获取 generator.properties 配置文件对象
        Props props = new Props("com.wen.sai/generator.properties");
        dataSourceConfig.setDriverName(props.getStr("dataSource.driverName"));
        dataSourceConfig.setUrl(props.getStr("dataSource.url"));
        dataSourceConfig.setUsername(props.getStr("dataSource.username"));
        dataSourceConfig.setPassword(props.getStr("dataSource.password"));
        return dataSourceConfig;
    }

    /**
     * 初始化包配置
     *
     * @return 包配置
     */
    private static PackageConfig initPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        Props props = new Props("com.wen.sai/generator.properties");
        // 父包名。默认值：com.baomidou
        packageConfig.setParent(props.getStr("package.base"));
        // 模块名。默认值：空字符串
        packageConfig.setModuleName("");
        // entity 包名。默认值：entity
        packageConfig.setEntity("model");
        // controller 包名。默认值：controller
        // packageConfig.setController("controller");
        // service 包名。默认值：service
        // packageConfig.setService("service");
        // serviceImpl 包名。默认值：service.impl
        // packageConfig.setServiceImpl("service.impl");
        // mapper 包名。默认值：mapper
        // packageConfig.setMapper("mapper");
        // xml 包名。默认值：mapper.xml
        // packageConfig.setXml("mapper.xml");
        return packageConfig;
    }

    /**
     * 初始化策略配置
     *
     * @return 策略配置
     */
    private static StrategyConfig initStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(createTime);
        tableFillList.add(updateTime);
        // 数据库表映射到实体命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
                // 数据库表字段映射到实体属性命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 是否为 Lombok 模型。默认值：false
                .setEntityLombokModel(true)
                // 生成 @RestController 控制器。默认值：false
                .setRestControllerStyle(true)
                // 自定义 Entity 基类
                .setSuperEntityClass(BaseEntity.class)
                // Entity 基类公共字段
                // .setSuperEntityColumns("id", "is_deleted", "create_time", "update_time")
                // 自定义 Controller 基类
                .setSuperControllerClass(BaseController.class)
                // .setSuperServiceClass()
                // .setSuperServiceImplClass()
                // .setSuperMapperClass()
                // 逻辑删除字段
                .setLogicDeleteFieldName("is_deleted")
                // 乐观锁字段
                // .setVersionFieldName("version")
                // 表填充字段
                .setTableFillList(tableFillList)
                // 数据表 Boolean 类型字段是否移除 is 前缀。默认值：false
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 驼峰转连字符（是否开启 Controller 映射连字符风格）
                .setControllerMappingHyphenStyle(true)
                // 是否在实体中生成表、字段注释。默认值：false
                .setEntityTableFieldAnnotationEnable(true)
                // 表前缀
                .setTablePrefix("pms_")
                // 字段前缀
                .setFieldPrefix("is_")
                // 表名，多个用英文逗号分隔，不写则表示生成全部表
                // .setInclude("t_cost_basic", "t_cost_set", "t_cost_phase_rate", "t_cost_summary_record")
                // 模糊匹配表名，跟 notLikeTable() 二选一配置
                .setLikeTable(new LikeTable("pms_"));
        return strategyConfig;
    }
}
