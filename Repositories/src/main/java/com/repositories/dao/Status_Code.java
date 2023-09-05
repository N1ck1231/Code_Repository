package com.repositories.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status_Code {
    private boolean red;

    private boolean blue;

    private boolean green;

    private boolean err;
}
