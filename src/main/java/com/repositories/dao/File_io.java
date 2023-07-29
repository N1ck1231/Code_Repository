package com.repositories.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File_io {
    //文件名
    private String fileName;
    //文件路径
    private String filePath;
    //文件大小
    private String fileSize;
    //文件类型
    private String fileType;
    //保存信息
    private String saveInfo;
}
