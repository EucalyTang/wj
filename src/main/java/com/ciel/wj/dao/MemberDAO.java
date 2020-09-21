package com.ciel.wj.dao;

import com.ciel.wj.pojo.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberDAO extends JpaRepository<Member, Integer> {
    Member findById(int id);
    Member findByPassportNumber(String passport);
    List<Member> findAllByUserInviteCode(String code);
    @Query(
            value = "SELECT * FROM member"
                + " WHERE (user_invite_code = ?1)"
                + " ORDER BY ?#{#pageable}",
            countQuery = "SELECT COUNT(1) FROM member"
                    + " WHERE (user_invite_code = ?1)"
                    + " ORDER BY ?#{#pageable}",
            nativeQuery = true
    )
    Page<Member> findByUserInviteCode(String code, Pageable pageable);

}
