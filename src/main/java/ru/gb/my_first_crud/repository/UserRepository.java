package ru.gb.my_first_crud.repository;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.my_first_crud.model.User;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName,lastName) VALUES ( ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    //public void deleteById(int id)
    //"DELETE FROM userTable WHERE id=?"

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id = ?";
        jdbc.update(sql,id);
    }

    public User getOne(int id){
        String sql = "SELECT id, firstName, lastName FROM usertable WHERE id = ?";
        return jdbc.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(User.class));
        //
    }

    public  void updateUser(User user){
        String sql = "UPDATE usertable SET  firstName =?,lastname =? WHERE id = ?";
        jdbc.update(sql,user.getFirstName(),user.getLastName(),user.getId());
    }






}
