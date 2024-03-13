package io.jh.main.repository;

import io.jh.main.domain.board.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardVO, Long> {
}
