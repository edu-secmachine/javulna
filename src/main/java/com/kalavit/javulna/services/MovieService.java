/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.MovieDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author peti
 */
@Service
public class MovieService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<MovieDto> findMovie(String title, String description, String genre, String id) {
        int conditions = 0;
        StringBuilder sql = new StringBuilder("select description, title, genre, id from movie ");
        if (StringUtils.hasText(title)) {
            appendCondition(sql, conditions);
            conditions++;
            sql.append("title LIKE '%").append(title).append("%'");

        }
        if (StringUtils.hasText(description)) {
            appendCondition(sql, conditions);
            conditions++;
            sql.append("description LIKE '%").append(description).append("%'");
        }
        if (StringUtils.hasText(genre)) {
            appendCondition(sql, conditions);
            conditions++;
            sql.append("genre LIKE '%").append(genre).append("%'");
        }
        if (StringUtils.hasText(id)) {
            appendCondition(sql, conditions);
            conditions++;
            sql.append("id = '").append(id).append("'");
        }
        LOG.debug(sql.toString());
        List<MovieDto> users = this.jdbcTemplate.query(sql.toString(), new RowMapper<MovieDto>() {
            @Override
            public MovieDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                MovieDto ret = new MovieDto();
                ret.setDescription(rs.getString("description"));
                ret.setTitle(rs.getString("title"));
                ret.setGenre(rs.getString("genre"));
                ret.setId(rs.getString("id"));
                return ret;
            }
        });

        return users;
    }

    private void appendCondition(StringBuilder sb, int conditions) {
        if (conditions == 0) {
            sb.append(" where ");
        } else {
            sb.append(" and ");
        }
    }
    
}
