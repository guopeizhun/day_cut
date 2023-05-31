package com.letg.day_cut.generator;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 基本代码生成
 */
public class CodeGenerator {
    private final static String absolutePath = System.getProperty("user.dir");
    private final static String mainPath = "\\src\\main\\java";
    private final static String projectName = "\\day_cut";
    private static final String SERVICE = "Service.java";
    private static final String SERVICE_IMPL = "ServiceImpl.java";
    private static final String CONTROLLER = "Controller.java";
    private static String model;
    private static String packge;


    public static void main(String[] args) throws IOException, URISyntaxException {
        generate("\\com\\letg\\day_cut", "Feedback");
    }

    @SneakyThrows
    private static void generate(String relativePackge, String modelName) {
        model = modelName;
        packge = relativePackge;
        generateService();
        generateServiceImpl();
        generateController();
    }




    @SneakyThrows
    static void generateService() {
        String filePath = absolutePath + projectName + mainPath + packge + "\\service\\" + model + SERVICE;
        commonGenerate("/generater/serviceMod", filePath);

    }

    @SneakyThrows
    static void generateServiceImpl() {
        String filePath = absolutePath + projectName + mainPath + packge + "\\service\\impl\\" + model + SERVICE_IMPL;
        commonGenerate("/generater/serviceImplMod", filePath);

    }

    @SneakyThrows
    static void generateController() {
        String filePath = absolutePath + projectName + mainPath + packge + "\\controller\\" + model + CONTROLLER;
        commonGenerate("/generater/controllerMod", filePath);

    }


    private static String coverPackge(String packge) {
        return packge.replaceAll("\\\\", ".").substring(1);
    }

    @SneakyThrows
    static void commonGenerate(String mod, String target) {
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
        while (!(s = br.readLine()).equals("#end")) {
            String regex = "\\$\\{relativePackge\\}";
            String regex2 = "\\$\\{modelName\\}";
            s = s.replaceAll(regex, coverPackge(packge));
            s = s.replaceAll(regex2, model);
            bw.write(s + "\n");
        }
        bw.close();
        os.close();
        br.close();
        in.close();
    }

}
