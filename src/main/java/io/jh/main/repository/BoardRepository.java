package io.jh.main.repository;

import io.jh.main.model.board.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardVO, Long> {
}
