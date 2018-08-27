/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.MovieDto;
import com.kalavit.javulna.model.Movie;
import com.kalavit.javulna.services.autodao.MovieAutoDao;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author peti
 */
@Service
public class MovieService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    MovieAutoDao movieAutoDao;
    
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
    
    public Movie saveMovieFromXml(String xml){
        try {
            Movie m = new Movie();
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            Element root = doc.getDocumentElement();
            m.setTitle(getText(root, "title"));
            m.setDescription(getText(root, "description"));
            m.setGenre(getText(root, "genre"));
            movieAutoDao.save(m);
            return m;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } 
    }

    private String getText(Element el, String tagName) {
        NodeList nl = el.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() >0){
            NodeList children = nl.item(0).getChildNodes();
            if(children != null && children.getLength() > 0){
                return children.item(0).getTextContent();
            }
        }
        LOG.debug("no text content of tag with name: {}", tagName);
        return null;
    }
    
}
