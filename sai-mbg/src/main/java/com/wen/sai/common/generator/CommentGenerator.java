package com.wen.sai.common.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * MyBatis Dynamic SQL 注释生成器
 * </p>
 *
 * @author wenjun
 * @since 2021/2/6
 */
public class CommentGenerator extends DefaultCommentGenerator {

    public CommentGenerator() {
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    @Override
    public void addComment(XmlElement xmlElement) {
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
    }

    @Override
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    }

    @Override
    protected String getDateString() {
        return DateUtil.format(new Date(), "yyyy/MM/dd");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String tableRemarks = introspectedTable.getRemarks();
        if (StrUtil.isNotBlank(tableRemarks)) {
            topLevelClass.addJavaDocLine("/**");
            // System.getProperty("line.separator")，系统配置信息，表示行分隔符
            for (String remark : tableRemarks.split(System.getProperty("line.separator"))) {
                topLevelClass.addJavaDocLine(" * " + remark);
            }
            topLevelClass.addJavaDocLine(" *");
            topLevelClass.addJavaDocLine(" * @author wenjun");
            topLevelClass.addJavaDocLine(" * @since " + getDateString());
            topLevelClass.addJavaDocLine(" */");
        }
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
                                           Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
                                           IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        String columnRemarks = introspectedColumn.getRemarks();
        if (StrUtil.isNotBlank(columnRemarks)) {
            field.addJavaDocLine("/**");
            for (String remark : columnRemarks.split(System.getProperty("line.separator"))) {
                field.addJavaDocLine(" * " + remark);
            }
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
                                   Set<FullyQualifiedJavaType> imports) {
    }

    @Override
    public void addFileComment(KotlinFile kotlinFile) {
    }
}
