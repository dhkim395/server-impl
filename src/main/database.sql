create database server_impl_user;
use server_impl_user;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE board (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 회원가입
INSERT INTO users (username, password, email)
VALUES (#{username}, #{password}, #{email});

-- 조회
SELECT * FROM users WHERE username = #{username};

-- 수정
UPDATE users
SET password = #{password}, email = #{email}
WHERE username = #{username};

-- 작성
INSERT INTO boards (title, content, writer, created_at)
VALUES (#{title}, #{content}, #{writer}, NOW());

-- 목록
SELECT * FROM boards
ORDER BY created_at DESC
    LIMIT #{limit} OFFSET #{offset};

-- 게시글 총 개수
SELECT COUNT(*) FROM boards;

-- 상세 조회
SELECT * FROM boards WHERE id = #{id};

-- 게시글 수정
UPDATE boards
SET title = #{title}, content = #{content}
WHERE id = #{id} AND writer = #{writer};

-- 게시글 삭제
DELETE FROM boards
WHERE id = #{id} AND writer = #{writer};