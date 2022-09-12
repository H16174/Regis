package com.example.regis_take_out.controller;

import com.example.regis_take_out.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * User：H11
 * Date：2022/8/18
 * Description：处理文件上传等公共功能
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${regis.path}")
    private String basePath;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        //file是一个临时文件，需要转存到指定位置，否则本次请求结束后临时文件会被删除
        log.info(file.toString());
        String fileName = file.getOriginalFilename();
        //使用UUID生成文件名，防止文件覆盖
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String randomName = UUID.randomUUID() +suffix;
        //将临时文件转存到指定位置
        //如果目录不存在，创建一个
        File dir = new File(basePath);
        if(!dir.exists()) dir.mkdir();
        file.transferTo(new File(basePath + randomName));
        return R.success(randomName);
    }
    @GetMapping("/download")
    public void download(HttpServletResponse response, String name) throws Exception{
        //通过输入流读取文件
        FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
        //设置响应回的是什么类型的文件
        response.setContentType("image/jpg");
        //通过输出流将文件返回浏览器
        ServletOutputStream outputStream = response.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = fileInputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        //关闭资源
        outputStream.close();
        fileInputStream.close();
    }
}
