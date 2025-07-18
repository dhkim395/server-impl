package com.koreait.serverimpl.mapper;

import com.koreait.serverimpl.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoards(@Param("offset") int offset, @Param("limit") int limit);
    int countBoards();
    Board findById(Long id);
    void updateBoard(Board board);
    void deleteBoard(@Param("id") Long id, @Param("writer") String writer);
}

