package com.repositories.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code_Info {

    private String keyword;

    private String projec_tname;

    private String get_time;

    private String project_url;

    private String download_url;

    private String watch_count;

    private String fork_count;

    private String star_count;

    private String branch_type;

    private String branch_count;

    private String tag_count;

    private String update_time;

    private String commit_count;

    private String about_text;

    private String tags_text;

}
