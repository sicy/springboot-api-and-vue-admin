package com.sicy.common.controller;

import com.sicy.common.config.ProjectConfig;
import com.sicy.common.interceptor.RequestUtils;
import com.sicy.common.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 上传控制器
 * 案例： http://www.mkyong.com/spring-boot/spring-boot-file-upload-example-ajax-and-rest/
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <br/>
 *          <br/>修订人		修订时间			描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy		2018/5/9		初始创建
 */
@RestController
public class UploadController {

    @Autowired
    private ProjectConfig projectConfig;

    /**
     * 限定上传文件最大为200MB
     * @return MultipartConfigElement
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(projectConfig.getUploadMaxFileSize());
        //设置总上传数据总大小
        factory.setMaxRequestSize(projectConfig.getUploadMaxRequestSize());
        return factory.createMultipartConfig();
    }

    /**
     * 上传方法 支持多个文件上传
     * @param uploadfiles 上传的文件列表
     * @return 返回上传结果 如果成功 返回token
     * @throws IOException 异常
     */
    @RequestMapping("/api/upload")
    public ReturnMsg uploadFileMulti(@RequestParam("file") MultipartFile[] uploadfiles) throws IOException {
        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(MultipartFile::getOriginalFilename)
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ReturnMsg(false, "no files");
        }
        String token = UUID.randomUUID().toString();
        saveUploadedFiles(Arrays.asList(uploadfiles), token);
        return new ReturnMsg(true, "upload success", token);
    }


    private void saveUploadedFiles(List<MultipartFile> files, String token) throws IOException {
        if(!new File(ProjectConfig.config.getBasePath() + "/upload/").exists()){
            new File(ProjectConfig.config.getBasePath() + "/upload/").mkdirs();
        }
        List<Map<String, String>> filePathList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            //设置一个随机文件名称
            String originalFileName = file.getOriginalFilename();
            String suffix = "";
            if (originalFileName.contains(".")) {
                suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            Path path = Paths.get(ProjectConfig.config.getBasePath() + "/upload/" + newFileName);
            Files.write(path, bytes);
            System.out.println(path.toString());
            Map<String, String> fileInfo = new HashMap<>(4);
            fileInfo.put("originalFileName", originalFileName);
            fileInfo.put("filePath", path.toString());
            filePathList.add(fileInfo);
        }
        RequestUtils.setSessionAttribte(token, filePathList);
    }

}
