package com.ciel.wj.dao;

import com.ciel.wj.pojo.Bullet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulletDAO extends JpaRepository<Bullet,Integer> {
    Bullet findById(int id);

    List<Bullet> findAllByBore(String bore);
}
