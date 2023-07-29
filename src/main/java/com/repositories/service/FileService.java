package com.repositories.service;
import com.repositories.dao.File_io;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Service
public class FileService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
    public File_io uploadFile(MultipartFile file, String url) {
        File_io fileInfo = new File_io();
        // 在 uploadPath 文件夹中通过日期对上传的文件归类保存
        String format = sdf.format(new Date());
        File folder = new File(url + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 避免文件重名
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存
            file.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath = folder.getPath()+newName;
            // 返回上传文件详细信息
            fileInfo.setFileName(newName);
            fileInfo.setFilePath(filePath);
            fileInfo.setFileSize(setSize(file.getSize()));
            fileInfo.setFileType(file.getContentType());
            return fileInfo;
        } catch (IOException e) {
            fileInfo.setSaveInfo(e.toString());
            return fileInfo;
        }
    }


    //将文件大小调整至合适单位
    public String setSize(Long size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            resultSize = df.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            resultSize = df.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            resultSize = df.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    public String fileDownload(String path, String name, HttpServletResponse response) throws Exception {
        File file = new File(path + File.separator + name);
        if (!file.exists()) {
            return (name + "文件不存在");
        }
        response.setContentType("application/force-download");
        //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
        response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(file.getName(), "utf-8")));
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        return "succeed";
    }
}
