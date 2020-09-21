package com.ciel.wj.dao;

import com.ciel.wj.pojo.OrderRange;
import com.ciel.wj.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRangeDAO extends JpaRepository<OrderRange,Integer> {
    OrderRange findById(int id);
    List<OrderRange> findAllBySubmitUser(User user);
    @Query("SELECT o FROM OrderRange o WHERE o.submitUser.name like %?1%")
    List<OrderRange> findAllBySubmitUserName(String name);
    @Query("SELECT o FROM OrderRange o WHERE o.member.nameZh like %?1%")
    List<OrderRange> findAllByMemberName(String name);
//    @Query(
//            value = "SELECT * FROM order_range"
//                    + " WHERE (submit_uid = ?1)"
//                    + " ORDER BY ?#{#pageable}",
//            countQuery = "SELECT COUNT(1) FROM order_range"
//                    + " WHERE (submit_uid = ?1)"
//                    + " ORDER BY ?#{#pageable}",
//            nativeQuery = true
//    )
    @Query("SELECT o FROM OrderRange o WHERE o.submitUser.id=?1")
    Page<OrderRange> findAllBySubmitUid(int uid, Pageable pageable);

    @Query("SELECT o FROM OrderRange o WHERE o.submitUser.name like %?1% ")
    Page<OrderRange> findAllBySubmitUserName(String name, Pageable pageable);

    @Query("SELECT o FROM OrderRange o WHERE o.member.nameZh like %?1%")
    Page<OrderRange> findAllByMemberName(String name, Pageable pageable);
}
