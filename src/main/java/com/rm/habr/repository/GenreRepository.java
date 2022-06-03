package com.rm.habr.repository;

import com.rm.habr.model.Comment;
import com.rm.habr.model.Genre;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository {

    private static final RowMapper<Genre> GENRE_MAPPER = JdbcTemplateMapperFactory.newInstance()
            .ignorePropertyNotFound()
            .newRowMapper(Genre.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public GenreRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> findAll() {
        final String sql = """
                SELECT genre.genre_id AS id, genre_name AS name
                FROM genre
                """;

        return jdbcTemplate.getJdbcTemplate().query(sql, GENRE_MAPPER);
    }

}