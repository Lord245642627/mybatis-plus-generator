package com.camelot.constant;

import com.camelot.enums.IdTypeEnum;
import com.camelot.util.PropertiesUtil;

/**
 * @author Lord Camelot
 * @date 2024/2/20
 * @description
 */
public class Constant {

    // 数据库信息
    public static String DB_URL;
    public static String DB_USERNAME;
    public static String DB_PASSWORD;
    public static String DB_TABLE;
    public static String PREFIX_TABLE;
    public static String PREFIX_FIELD;
    public static String ID_TYPE;

    // 作者信息
    public static String AUTHOR_NAME;

    // 文件信息
    public static String FILE_OUTPUT;


    public static String PROJECT_NAME;
    public static String PROJECT_PACKAGE;
    public static String PROJECT_MODULE;
    public static String PROJECT_OUTPUT;
    public static String PROJECT_XML_OUTPUT;
    public static String PROJECT_JAVA = "/src/main/java";
    public static String PROJECT_RESOURCES = "/src/main/resources/mapper";

    static {
        DB_URL = PropertiesUtil.getProperty("db.url");
        DB_USERNAME = PropertiesUtil.getProperty("db.username");
        DB_PASSWORD = PropertiesUtil.getProperty("db.password");
        DB_TABLE = PropertiesUtil.getProperty("db.table");
        PREFIX_TABLE = PropertiesUtil.getProperty("db.prefix.table");
        PREFIX_FIELD = PropertiesUtil.getProperty("db.prefix.field");
        ID_TYPE = PropertiesUtil.getProperty("db.id_type");

        AUTHOR_NAME = PropertiesUtil.getProperty("author.name");

        FILE_OUTPUT = PropertiesUtil.getProperty("file.output");

        PROJECT_NAME = PropertiesUtil.getProperty("project.name");
        PROJECT_PACKAGE = PropertiesUtil.getProperty("project.package");
        PROJECT_MODULE = PropertiesUtil.getProperty("project.module");
        PROJECT_OUTPUT = FILE_OUTPUT + "/"  + PROJECT_NAME + PROJECT_JAVA;
        PROJECT_XML_OUTPUT = FILE_OUTPUT + "/"  + PROJECT_NAME + PROJECT_RESOURCES;
    }

    public static void main(String[] args) {
        System.out.println(IdTypeEnum.getIdType(ID_TYPE));
    }
}
