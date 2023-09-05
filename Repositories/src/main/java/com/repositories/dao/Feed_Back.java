package com.repositories.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed_Back {
    private int project_id;

    private String account;

    private String username;

    private String project_name;

    private String project_url;

    private String issue;

    private String time;

    private boolean red;

    private boolean blue;

    private boolean green;

    private boolean err;

}
