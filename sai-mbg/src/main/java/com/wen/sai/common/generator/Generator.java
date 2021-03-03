package com.wen.sai.common.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MyBatis Dynamic SQL 代码生成器
 * </p>
 *
 * @author wenjun
 * @since 2021/2/6
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        List<String> warningList = new ArrayList<>();
        ConfigurationParser parser = new ConfigurationParser(warningList);
        InputStream inputStream = Generator.class.getResourceAsStream("/com.wen.sai/generatorConfig.xml");
        Configuration configuration = parser.parseConfiguration(inputStream);
        inputStream.close();
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warningList);
        generator.generate(null);
        warningList.forEach(System.out::println);
    }
}
