package com.camelot.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.camelot.constant.Constant;
import com.camelot.enums.IdTypeEnum;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Lord Camelot
 * @date 2024/2/20
 * @description
 */
public class CodeGenerator {
    public static void generateCode() {
        FastAutoGenerator.create(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author(Constant.AUTHOR_NAME) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(Constant.PROJECT_OUTPUT); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent(Constant.PROJECT_PACKAGE) // 设置父包名
                            .moduleName(Constant.PROJECT_MODULE) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, Constant.PROJECT_XML_OUTPUT)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    List<String> tables = getTables(Constant.DB_TABLE);
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(Constant.PREFIX_TABLE) // 设置过滤表前缀
                            .addFieldPrefix(Constant.PREFIX_FIELD) // 设置过滤字段前缀
                            .entityBuilder() // 设置 entity 配置
                            .enableLombok() // 添加 lombok 配置
                            .naming(NamingStrategy.underline_to_camel) // 数据表映射实体命名策略：默认下划线转驼峰 underline_to_camel
                            .columnNaming(NamingStrategy.underline_to_camel) // 表字段映射实体属性命名规则：默认null，不指定按照 naming 执行
                            .idType(IdTypeEnum.getIdType(Constant.ID_TYPE)) // 添加全局主键类型
                            .enableFileOverride() // 生成时新文件覆盖旧文件
                            .controllerBuilder() // 设置 controller 配置
                            .enableRestStyle() //  设置 RestController 注解
                            .enableFileOverride() // 生成时新文件覆盖旧文件
                            .serviceBuilder() // 设置 service 配置
                            .formatServiceFileName("%sService") // 格式化实体名称，%s取消首字母I
                            .enableFileOverride() // 生成时新文件覆盖旧文件
                            .mapperBuilder() // 设置 mapper 配置
                            .enableFileOverride(); // 生成时新文件覆盖旧文件
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    private static List<String> getTables(String tables) {
        List<String> list = new ArrayList<>();
        if (tables == null) {
            return list;
        }
        tables = tables.replaceAll(" ", "");
        if (tables.isEmpty()) {
            return list;
        }
        list = Arrays.asList(tables.split(","));
        return list;
    }
}
