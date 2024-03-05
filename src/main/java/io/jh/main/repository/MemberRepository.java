package io.jh.main.repository;

import io.jh.main.model.MemberVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberVO, Long> {

    Optional<MemberVO> findByNickName(String nickName);

    boolean existsByNickName(String nickName);

//    Page<MemberVO> findAll(Pageable pageable);
}
