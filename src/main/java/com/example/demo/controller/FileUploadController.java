package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: rogue
 * @Description:    文件上传
 * @Package: com.example.demo.controller
 * @Date: 2017/12/1
 * @Time: 16:47
 */
@Controller
public class FileUploadController {

    private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private void executeUpload(String uploadDir,MultipartFile file) throws IOException {
        //上传文件名(文件本身名称)
        //String filename = file.getOriginalFilename();
        //获取文件名后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID()+suffix;
        //在服务器端生成文件
        File serverFile = new File(uploadDir+filename);
        //将上传文件写入服务器端文件对象
        file.transferTo(serverFile);
    }


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest request, MultipartFile file){
        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
            logger.info("上传文件目录地址："+uploadDir);
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            executeUpload(uploadDir,file);
        } catch (IOException e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public @ResponseBody String uploads(HttpServletRequest request, MultipartFile[] file){
        try {
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
            System.out.println("上传文件目录地址："+uploadDir);
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            //遍历文件数组执行上传
            for (int i=0;i<file.length;i++){
                if (file[i]!=null && !file[i].getOriginalFilename().isEmpty()){
                    executeUpload(uploadDir,file[i]);
                }
            }
        } catch (IOException e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

}
