package com.rm.habr.repository;

import com.rm.habr.model.Genre;
import com.rm.habr.model.Tag;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private static final RowMapper<Tag> TAG_MAPPER = JdbcTemplateMapperFactory.newInstance()
            .ignorePropertyNotFound()
            .newRowMapper(Tag.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TagRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> findAll() {
        final String sql = """
                SELECT tag.tag_id AS id, tag_name AS name
                FROM tag
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, TAG_MAPPER);
    }

}