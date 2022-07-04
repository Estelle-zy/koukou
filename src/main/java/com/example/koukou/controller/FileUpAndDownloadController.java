package com.example.koukou.controller;

import com.example.koukou.basic.ResponseResult;
import com.example.koukou.config.FileProperties;
import com.example.koukou.config.LogInfoAnno;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Author
 * @Date 2022/5/31 18:52
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("api/upAndDonwload")
public class FileUpAndDownloadController {
    @Autowired
    private FileProperties fileProperties;


    @LogInfoAnno(description = "文件上传", businessType = "resume模块")
    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult upload(MultipartFile file) {
        try {
            String targetFile = fileProperties.getFilePath() + file.getOriginalFilename();
            System.out.println(targetFile);
            file.transferTo(new File(targetFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(200, "上传成功", null);
    }


    @LogInfoAnno(description = "文件下载", businessType = "resume模块")
    @GetMapping("/download/{fileName}")
    @ResponseBody
    public void download(@PathVariable(value = "fileName") String filename, HttpServletResponse response) {
        File file = new File(fileProperties.getFilePath() + filename);
        System.out.println(file);
        response.setContentType("img/png");
        try {
            ServletOutputStream os = response.getOutputStream();
            os.write(FileUtil.readAsByteArray(file));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
