package com.example.JavaMartiniApp.Interface;

import com.example.JavaMartiniApp.Model.Cocktail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MyDAO")
public class CocktailDataAccessService implements CocktailInterface {

    private final JdbcTemplate jdbcTemplate;

    public CocktailDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertCocktail(Cocktail cocktail) {
        final String sql = "INSERT INTO cocktail (id, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, cocktail.getId(), cocktail.getName());
    }

    @Override
    public List<Cocktail> selectAllCocktails() {
        final String sql = "SELECT id, name FROM cocktail";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Cocktail(id, name);
        });
    }

    @Override
    public Optional<Cocktail> selectCocktailById(UUID id) {
        final String sql = "SELECT id, name FROM cocktail WHERE id = ?";

        Cocktail cocktail = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID cocktailId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Cocktail(cocktailId, name);
                });
        return Optional.ofNullable(cocktail);
    }

    @Override
    public int updateCocktailById(UUID id, Cocktail cocktail) {
        final String sql = "UPDATE cocktail SET name=? WHERE id = ?";
        return jdbcTemplate.update(sql, id, cocktail.getName());
    }

    @Override
    public int deleteCocktailById(UUID id) {
        final String sql = "DELETE FROM cocktail WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
