package com.ciel.wj.dao;

import com.ciel.wj.pojo.OrderEquip;
import com.ciel.wj.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderEquipDAO extends JpaRepository<OrderEquip,Integer> {
    OrderEquip findById(int id);
    List<OrderEquip> findAllBySubmitUser(User user);
    @Query("SELECT o FROM OrderEquip o WHERE o.submitUser.name like %?1%")
    List<OrderEquip> findAllBySubmitUserName(String name);
    @Query("SELECT o FROM OrderEquip o WHERE o.member.nameZh like %?1%")
    List<OrderEquip> findAllByMemberName(String name);

    @Query("SELECT o FROM OrderEquip o WHERE o.submitUser.id=?1")
    Page<OrderEquip> findAllBySubmitUid(int uid, Pageable pageable);

    @Query("SELECT o FROM OrderEquip o WHERE o.submitUser.name like %?1% ")
    Page<OrderEquip> findAllBySubmitUserName(String name, Pageable pageable);

    @Query("SELECT o FROM OrderEquip o WHERE o.member.nameZh like %?1%")
    Page<OrderEquip> findAllByMemberName(String name, Pageable pageable);
}
