package com.repositories.controller;

import com.alibaba.fastjson.JSONObject;
import com.repositories.dao.File_io;
import com.repositories.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file,
                                 @RequestParam("savePath") String url) {
        JSONObject output = new JSONObject();
        File_io file_obj = service.uploadFile(file, url);
        if (file_obj.getSaveInfo() != null) {
            output.put("msg", "failed");
            output.put("data", file_obj.getSaveInfo());
        } else {
            output.put("msg", "succeed");
            output.put("data", file_obj);
        }
        return output;
    }

    @RequestMapping(value = "/download/{name}", method = RequestMethod.GET)
    public void fileDownload(@PathVariable String name, HttpServletResponse response) throws Exception {
        JSONObject output = new JSONObject();
        String result = service.fileDownload(path, name, response);
        output.put("msg", result);
        //return output;
    }

}
