package com.letg.day_cut.generator;

import com.letg.day_cut.util.DateUtil;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * 基本代码生成
 */
public class CodeGenerator {
    private final static String absolutePath = System.getProperty("user.dir");
    private final static String mainPath = "\\src\\main\\java";
    private final static String resourcePath = "\\src\\main\\resources";
    private final static String projectName = "\\day_cut";
    private static final String SERVICE = "Service.java";
    private static final String SERVICE_IMPL = "ServiceImpl.java";
    private static final String CONTROLLER = "Controller.java";
    private static final String DAO = "Mapper.java";
    private static final String MAPPER = "Mapper.xml";
    private static String model;
    private static String packge;
    private static String tableName;
    private static List<Map> columnList;
    private static Map<String, String> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put("tinyint", "Integer");
        typeMap.put("int", "Integer");
        typeMap.put("timestamp", "Date");
        typeMap.put("float", "float");
        typeMap.put("char", "char");
        typeMap.put("blob", "String");
        typeMap.put("date", "Date");
        typeMap.put("double", "double");
        typeMap.put("bigint", "long");
        typeMap.put("decimal", "BigDecimal");
        typeMap.put("varchar", "String");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

    }

    @SneakyThrows
    public static void generate(String relativePackge, String modelName) {
        model = modelName;
        packge = relativePackge;
        generateService();
        generateServiceImpl();
        generateController();
    }

    @SneakyThrows
    public static void generate(List<Object> params) {
        columnList = (List<Map>) params.get(0);
        model = params.get(1).toString();
        packge = params.get(2).toString();
        tableName = params.get(8).toString();
        boolean hasController = (boolean) params.get(3);
        boolean hasService = (boolean) params.get(4);
        boolean hasServiceImpl = (boolean) params.get(5);
        boolean hasModel = (boolean) params.get(6);
        boolean hasMapper = (boolean) params.get(7);


        generateQuery();

        if (hasController) {
            generateController();
        }
        if (hasService) {
            generateService();
        }
        if (hasServiceImpl) {
            generateServiceImpl();
        }
        if (hasMapper) {
            generateMapper();
        }
        if (hasModel) {
            generateModel();
        }

    }

    private static void generateQuery() {
        String filePath = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\util\\Query.java";
        commonGenerate("/generater/queryMod", filePath, "");
    }

    private static void generateModel() {
        String filePath = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\model\\" + model + ".java";
        commonGenerate("/generater/modelMod", filePath, "model");
    }

    private static void generateMapper() {
        String filePath1 = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\mapper\\" + model + DAO;
        String filePath2 = absolutePath + projectName + resourcePath + "\\mapper\\" + model + MAPPER;
        commonGenerate("/generater/daoMod", filePath1, "");
        commonGenerate("/generater/mapperMod", filePath2, "mapper");
    }


    @SneakyThrows
    static void generateService() {
        String filePath = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\service\\" + model + SERVICE;
        commonGenerate("/generater/serviceMod", filePath, "");

    }

    @SneakyThrows
    static void generateServiceImpl() {
        String filePath = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\service\\impl\\" + model + SERVICE_IMPL;
        commonGenerate("/generater/serviceImplMod", filePath, "");

    }

    @SneakyThrows
    static void generateController() {
        String filePath = absolutePath + projectName + mainPath + coverPackgeToPath(packge) + "\\controller\\" + model + CONTROLLER;
        commonGenerate("/generater/controllerMod", filePath, "");

    }


    private static String coverPackge(String packge) {
        return packge.replaceAll("\\\\", ".").substring(1);
    }

    /**
     * @param mod    模板
     * @param target 目标路径
     * @param type   特殊处理类型
     */
    @SneakyThrows
    static void commonGenerate(String mod, String target, String type) {
        String dateFormat = DateUtil.getFormatDate(new Date(), "yyyy:MM:dd hh:mm:ss");
        File file = new File(target);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }

        FileOutputStream os = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        URL resource = CodeGenerator.class.getResource(mod);
        InputStream in = resource.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s;
        String tempStr = "";
        while (!(s = br.readLine()).equals("#end")) {
            if (type.equals("mapper")) {
                //特殊处理mapper
                String propertyRegex = "\\$\\{property\\}";
                String columnRegex = "\\$\\{column\\}";
                String tagTypeRegex = "\\$\\{tagType\\}";
                String jdbcTypeRegex = "\\$\\{jdbcType\\}";
                String temp = s;
                if (!StringUtils.isEmpty(temp) && temp.indexOf("${property}") > 0) {
                    //temp为模板
                    for (Map map : columnList) {
                        s = temp;
                        String column = map.get("COLUMN_NAME").toString();
                        String propety = columnToProperty(column);
                        String columnType = map.get("COLUMN_TYPE").toString();
                        String columnKey = map.get("COLUMN_KEY").toString();
                        String tagType = (!StringUtils.isEmpty(columnType) && columnKey.equals("PRI")) ? "id" : "result";
                        String jdbcType = columnType.toUpperCase();

                        s = s.replaceAll(propertyRegex, propety);
                        s = s.replaceAll(columnRegex, column);
                        s = s.replaceAll(tagTypeRegex, tagType);
                        s = s.replaceAll(jdbcTypeRegex, coverToJdbcType(jdbcType));
                        bw.write(s + "\n");
                    }
                    temp = null;
                    continue;
                }

            } else if (type.equals("model")) {
                //model
                String propertyRegex = "\\$\\{property\\}";
                String commentRegex = "\\$\\{comment\\}";
                String javaTypeRegex = "\\$\\{javaType\\}";
                //mod
                StringBuilder temp = new StringBuilder(s);
                if (s.indexOf("/***") > 0) {
                    //结束符读完
                    while (!(s.indexOf("**/") > 0)) {
                        s = br.readLine();
                        temp.append("\n").append(s);
                    }
                    //多读一行，读取成员变量
                    s = br.readLine();
                    temp.append("\n").append(s);
                }
                if (!StringUtils.isEmpty(temp) && temp.indexOf("/***") > 0  ) {
                    for (Map map : columnList) {
                        s = temp.toString();
                        //如果读到comment注解部分，需要多次读获取整体
                        //开始符读取
                        String column = map.get("COLUMN_NAME").toString();

                        String columnType = map.get("COLUMN_TYPE").toString();
                        String comment = map.get("COLUMN_COMMENT").toString();
                        String propety = columnToProperty(column);
                        String columnKey = map.get("COLUMN_KEY").toString();
                        String javaType = typeMap.get(columnType);

                        s = s.replaceAll(propertyRegex, propety);
                        s = s.replaceAll(commentRegex, comment);
                        s = s.replaceAll(javaTypeRegex, javaType );

                        if (columnKey != null && columnKey.equals("PRI")) {
                            bw.write("    @TableId(type = IdType.AUTO)\n");
                        }
                        bw.write(s + "\n");
                    }
                    temp = null;
                    continue;
                }

            }

            String relativePackgeRegex = "\\$\\{relativePackge\\}";
            String modelNameRegex = "\\$\\{modelName\\}";
            String createTimeRegex = "\\$\\{createTime\\}";
            String tableNameRegex = "\\$\\{tableName\\}";
            String apiRegex = "\\$\\{api\\}";
            s = s.replaceAll(relativePackgeRegex, packge);
            s = s.replaceAll(apiRegex, model.toLowerCase());
            s = s.replaceAll(modelNameRegex, model);
            s = s.replaceAll(tableNameRegex, tableName);
            s = s.replaceAll(createTimeRegex, dateFormat);
            bw.write(s + "\n");
        }
        bw.close();
        os.close();
        br.close();
        in.close();
    }

    public static String columnToProperty(String columnName) {
        int index = 0;
        while (columnName.indexOf("_") > 0 && (index + 2) < columnName.length()) {
            index = columnName.indexOf("_");
            columnName = columnName.substring(0, index) + columnName.substring(index + 1, index + 2).toUpperCase() + columnName.substring(index + 2);
        }
        return columnName;
    }


    private static String coverToJdbcType(String columnType) {
        if (columnType.contains("int")) {
            return "integer".toUpperCase();
        }
        return columnType.toUpperCase();
    }

    private static String coverPackgeToPath(String packge) {
        return "\\" + packge.replace(".", "\\");

    }


}
