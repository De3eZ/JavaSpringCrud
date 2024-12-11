package ru.dez.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.dez.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
       return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT into person VALUES (1,?,?,?)");

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) throws SQLException {
       PreparedStatement statement = connection.prepareStatement("Update Person set name=?, age=?, email=? where id=?");

        statement.setString(1, updatedPerson.getName());
        statement.setInt(2, updatedPerson.getAge());
        statement.setString(3, updatedPerson.getEmail());
        statement.setInt(4, id);

        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Person Where id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}