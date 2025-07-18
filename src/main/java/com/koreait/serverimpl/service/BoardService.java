package com.koreait.serverimpl.service;

import com.koreait.serverimpl.dto.BoardDTO;
import com.koreait.serverimpl.mapper.BoardMapper;
import com.koreait.serverimpl.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public void writeBoard(String writer, BoardDTO dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(writer);

        boardMapper.insertBoard(board);
    }
    public List<Board> getBoards(int page, int size) {
        int offset = (page - 1) * size;
        return boardMapper.getBoards(offset, size);
    }

    public int getBoardCount() {
        return boardMapper.countBoards();
    }
    public Board getBoardDetail(Long id) {
        return boardMapper.findById(id);
    }
    public boolean updateBoard(Long id, String username, Board dto) {
        Board board = boardMapper.findById(id);
        if (board == null || !board.getWriter().equals(username)) return false;

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setWriter(username);
        board.setId(id);

        boardMapper.updateBoard(board);
        return true;
    }

    public boolean deleteBoard(Long id, String username) {
        Board board = boardMapper.findById(id);
        if (board == null || !board.getWriter().equals(username)) return false;

        boardMapper.deleteBoard(id, username);
        return true;
    }

}
