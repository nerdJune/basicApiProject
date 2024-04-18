package io.jh.main.repository;

import io.jh.main.domain.board.BoardCategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository extends JpaRepository<BoardCategoryVO, Long> {
}
