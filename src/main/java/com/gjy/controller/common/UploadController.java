package com.gjy.controller.common;

import com.gjy.utils.Constants;
import com.gjy.utils.MessageConstant;
import com.gjy.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {
    @RequestMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename= file.getOriginalFilename();
        //得到后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //设置随机的图片文件名
        String fileName= UUID.randomUUID().toString()+suffixName;
        File realPath= new File(Constants.FILE_UPLOAD_DIC);
        //创建文件夹
        File destFile=new File(realPath+"/"+fileName);
        System.out.println(destFile);
        if(!realPath.exists()){
            realPath.mkdir();
        }
        try {
            file.transferTo(destFile);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.UPLOAD_FAIL);
        }
    }

}
