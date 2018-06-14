package com.sicy.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 下载配置 配置允许下载的路径
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <p>
 *          <p>修订人		修订时间			描述信息
 *          <p>-----------------------------------------------------
 *          <p>github.com/sicy		2018/5/29		初始创建
 */
@Component
@ConfigurationProperties(prefix = "download")
public class DownloadConfig {

    private List<String> allowPath;

    public List<String> getAllowPath() {
        return allowPath;
    }

    public void setAllowPath(List<String> allowPath) {
        this.allowPath = allowPath;
    }
}
