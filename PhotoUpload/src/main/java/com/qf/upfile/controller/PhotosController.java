/**
 * Copyright(c) 2013-2017 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.qf.upfile.controller;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: PhotosController
 * @Description: ${DESCRIPTION}
 * @author: zhbo
 * @date: 2017/6/7 16:28
 * @Copyright: 2017 . All rights reserved.
 */
@Controller
@RequestMapping("/photo")
public class PhotosController {

    @RequestMapping("/uploadFtp")
    @ResponseBody
    public String uploadPhotoFtp(HttpServletRequest request, @RequestParam MultipartFile fileUpload) throws IOException {
        String basePath = request.getServletContext().getRealPath("/");
        String filePath =  "test.png" ;
        File saveDir = new File(basePath + fileUpload.getOriginalFilename());

        // 转存文件
        fileUpload.transferTo(saveDir);


       /* FTPClient cfc = new FTPClient();
        cfc.connect("192.168.239.128", 21);
        cfc.login("ftpuser","123456");

        cfc.changeWorkingDirectory("/usr");
        cfc.storeFile("mytext.png", fileUpload.getInputStream());*/

        /*BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(
                        fileUpload.getOriginalFilename())));
        out.write(fileUpload.getBytes());
        out.flush();
        out.close();*/
        return "success";
    }


    @RequestMapping("/uploadSftp")
    @ResponseBody
    public String uploadPhotoSftp(HttpServletRequest request, @RequestParam MultipartFile fileUpload) throws IOException {
        ChannelSftp channel = null;
        try {
            /*FTPClient cfc = new FTPClient();
            cfc.connect("192.168.239.128", 21);
            cfc.login("ftpuser","123456");
            cfc.changeWorkingDirectory("/usr");
            cfc.storeFile("mytext.png", photoFiles[0].getInputStream());*/
/*
            String ftpHost = "192.168.239.128";
            String port = "22";
            String ftpUserName = "root";
            String ftpPassword = "123456";
            JSch jsch = new JSch();
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            Session session =  jsch.getSession(ftpUserName, ftpHost, 22);
            session.setTimeout(5000);
            session.setPassword(ftpPassword);
            session.setConfig(config);

            session.connect(); // 通过Session建立链接
            channel = (ChannelSftp)session.openChannel("sftp"); // 打开SFTP通道
            channel.connect();
            channel.cd("/usr/tomcat/");
            channel.put(fileUpload.getInputStream(),"my1212.png");*/
            return null;
        } catch (Exception e){
            e.printStackTrace();

            return null;
        } finally {
            if(channel != null)
                channel.disconnect();
            return null;
        }

    }

    @RequestMapping("/index")

    public String index(@RequestBody MultipartFile[] photoFiles){

        return "photo";
    }
}
