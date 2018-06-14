package com.sicy.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * (填写类功能描述)
 *
 * @author SiChunyang
 * @version 1.0
 *          <p>
 *          <p>
 *          <p>修订人		修订时间			描述信息
 *          <p>-----------------------------------------------------
 *          <p>SiChunyang		2018/6/14		初始创建
 */
@ConfigurationProperties(prefix = "project.config")
@Component
public class ProjectConfig {

    @Autowired
    private ProjectConfig projectConfig;

    public static ProjectConfig config;

    @PostConstruct
    private void init(){
        config = projectConfig;
    }

    private String basePath;

    private String interceptorExcludeUrl;

    private String baseUrl;

    /**
     * 最大文件上传体积, 默认50MB
     */
    private String uploadMaxFileSize = "50MB";

    /**
     * 最大的请求体体积, 默认1024000KB
     */
    private String uploadMaxRequestSize = "1024000KB";

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getInterceptorExcludeUrl() {
        return interceptorExcludeUrl;
    }

    public void setInterceptorExcludeUrl(String interceptorExcludeUrl) {
        this.interceptorExcludeUrl = interceptorExcludeUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUploadMaxFileSize() {
        return uploadMaxFileSize;
    }

    public void setUploadMaxFileSize(String uploadMaxFileSize) {
        this.uploadMaxFileSize = uploadMaxFileSize;
    }

    public String getUploadMaxRequestSize() {
        return uploadMaxRequestSize;
    }

    public void setUploadMaxRequestSize(String uploadMaxRequestSize) {
        this.uploadMaxRequestSize = uploadMaxRequestSize;
    }
}
