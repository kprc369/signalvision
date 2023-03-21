package com.semtle.psj.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BaseDao {
    @Autowired
     JdbcTemplate jdbcTemplate;
}
