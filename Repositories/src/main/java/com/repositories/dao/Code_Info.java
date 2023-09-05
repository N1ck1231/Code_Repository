package com.repositories.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code_Info {
    //项目id
    private Integer project_id;
    //关键词
    private String keyword;
    //项目名
    private String project_name;
    //数据获取时间
    private String get_time;
    //项目地址
    private String project_url;
    //下载链接
    private String download_url;
    //关注数量
    private String watch_count;
    //克隆数量
    private String fork_count;
    //点赞数量
    private String star_count;
    //分支类型
    private String branch_type;
    //分支数
    private String branch_count;
    //tag数
    private String tag_count;
    //更新时间
    private String update_time;
    //提交数量
    private String commit_count;
    //相关文档
    private String about_text;
    //tags文档
    private String tags_text;
    //按列查询条件
    private String condition;

    private String language;

    private String time;

    private String attention;

    private String likes;

    private String key;


}
