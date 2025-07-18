package com.koreait.serverimpl.controller;

import com.koreait.serverimpl.dto.BoardDTO;
import com.koreait.serverimpl.model.Board;
import com.koreait.serverimpl.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public String create(@RequestBody BoardDTO dto, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (username == null) {
            return "로그인 필요";
        }

        boardService.writeBoard(username, dto);
        return "작성 완료";
    }
    @GetMapping
    public Map<String, Object> getBoards(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<Board> boards = boardService.getBoards(page, size);
        int total = boardService.getBoardCount();

        Map<String, Object> result = new HashMap<>();
        result.put("boards", boards);
        result.put("totalCount", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }
    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.getBoardDetail(id);
    }
    @PutMapping("/{id}")
    public String updateBoard(
            @PathVariable Long id,
            @RequestBody Board dto,
            HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        if (username == null) return "로그인 필요";

        boolean success = boardService.updateBoard(id, username, dto);
        return success ? "수정 완료" : "게시글 없음";
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(
            @PathVariable Long id,
            HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        if (username == null) return "로그인 필요";

        boolean success = boardService.deleteBoard(id, username);
        return success ? "삭제 완료" : "게시글 없음";
    }

}
