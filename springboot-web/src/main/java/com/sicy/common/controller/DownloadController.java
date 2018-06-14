package com.sicy.common.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.sicy.common.config.DownloadConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * (填写类功能描述)
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <p>
 *          <p>修订人		修订时间			描述信息
 *          <p>-----------------------------------------------------
 *          <p>github.com/sicy		2018/5/29		初始创建
 */
@Controller
public class DownloadController {

    @Autowired
    private DownloadConfig downloadConfig;

    private static Log logger = LogFactory.getLog("DownloadController");

    /**
     * 检查是否在允许下载目录内
     * @param path 被检测路径
     * @return 是否允许下载
     */
    private boolean checkInDir(String path){
        path = path.replaceAll("\\\\", "/");
        for(String parent : downloadConfig.getAllowPath()){
            if(path.contains(parent)){
                return true;
            }
        }
        return false;
    }

    /**
     * 下载文件
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IOException
     */
    @RequestMapping(value = "/api/downloadFile")
    public void enterIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(downloadConfig.getAllowPath() == null){
            BaseController.renderText("没有允许下载的目录!!", response);
            return;
        }
        String path = request.getParameter("path");
        path = new String(path.getBytes("iso-8859-1"), "UTF-8");
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);

            //增加一次校验  必须是在运行下载的列表内
            if(!checkInDir(file.getParent())){
                BaseController.renderText("没有权限!!", response);
                return;
            }

            // 取得文件名。
            String filename = file.getName();

            //记录日志
            logger.info("下载文件:" + filename);

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            // 清空response
            response.reset();
            String userAgent = request.getHeader("User-Agent");
            String new_filename = URLEncoder.encode(filename, "UTF8");

            String rtn = "filename=" + new_filename + "";
            if (userAgent != null) {
                userAgent = userAgent.toLowerCase();
                if (userAgent.contains("msie")) {
                    // IE浏览器
                    rtn = "filename=" + new_filename + "";
                }else if (userAgent.contains("opera")) {
                    // Opera浏览器
                    rtn = "filename*=UTF-8''" + new_filename;
                }else if (userAgent.contains("safari")) {
                    // Safari浏览器
                    rtn = "filename=" + new String(filename.getBytes("UTF-8"),"ISO8859-1") + "";
                }else if (userAgent.contains("applewebkit")) {
                    // Chrome浏览器
                    new_filename = MimeUtility.encodeText(filename, "UTF8", "B");
                    rtn = "filename=" + new_filename + "";
                }else if (userAgent.contains("mozilla")) {
                    // FireFox浏览器
                    rtn = "filename*=UTF-8''" + new_filename;
                }
            }
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;" + rtn);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("APPLICATION/OCTET-STREAM; ");
            byte[] buffer = new byte[1024];
            int byteread;
            while ((byteread = fis.read(buffer)) != -1) {
                toClient.write(buffer, 0, byteread);
            }
            fis.close();
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
            logger.error("下载文件异常", ex);
            throw ex;
        }
    }


}
