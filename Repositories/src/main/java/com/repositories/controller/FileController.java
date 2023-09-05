package com.repositories.controller;

import com.alibaba.fastjson.JSONObject;
import com.repositories.dao.Code_Info;
import com.repositories.dao.File_io;
import com.repositories.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin(value = "*",  maxAge = 3600)
public class FileController {
    @Resource
    private FileService service;
    @Value("${file.path}")
    private String path;
    /**文件上传*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JSONObject uploadFile(@RequestPart("file") MultipartFile file) {
        JSONObject output = new JSONObject();
        String url = "D:/work/fileSave";
        File_io file_obj = service.uploadFile(file, url);
        if (file_obj.getSaveInfo() != null) {
            output.put("msg", "failed");
            output.put("data", file_obj.getSaveInfo());
        } else {
            output.put("msg", "succeed");
            output.put("data", file_obj);
        }
        System.out.println(file);
        return output;
    }

//    @RequestMapping(value = "/download/{name}", method = RequestMethod.GET)
//    public void fileDownload(@PathVariable String name, HttpServletResponse response, HttpServletRequest request) throws Exception {
//        JSONObject output = new JSONObject();
//        /*String name = request.getParameter("fileName");*/
//        System.out.println(name);
//        String result = service.fileDownload(path, name, response);
//        System.out.println(name);
//        output.put("msg", result);
//        //return output;
//    }

//    @GetMapping(value = "/download")
//    public void fileDownload(String name ,HttpServletResponse response, HttpServletRequest request) throws Exception {
//        JSONObject output = new JSONObject();
//        /*String name = request.getParameter("fileName");*/
//        String result = service.fileDownload(path, name, response);
//        System.out.println(name);
//        output.put("msg", result);
//    }

        @GetMapping("/download")
        public void saveDataToCsv(@RequestBody Code_Info select_info) {
            try {
                    service.saveDataToCsv(select_info);
                    System.out.println("文件保存成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
