package com.ciel.wj.dao;

import com.ciel.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>{
    User findByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
    User findById(int id);
    //@Query(value = "select new User(u.id, u.username, u.name, u.phone, u.email, u.inviteCode, u.enabled) from User u")
    //@Query(value = "select id,username,password,salt from User",nativeQuery = true)
    //List<User> list();
}
