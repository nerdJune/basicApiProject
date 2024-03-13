package io.jh.main.repository;

import io.jh.main.domain.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberVO, Long> {

    Optional<MemberVO> findByNickName(String nickName);

    Optional<MemberVO> findByEmail(String email);

    boolean existsByNickName(String nickName);

//    Page<MemberVO> findAll(Pageable pageable);
}
