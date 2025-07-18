package com.koreait.serverimpl.model;

import lombok.Data;

@Data
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer; // username
    private String createdAt;
}