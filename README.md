Spring Boot를 이용한 회원가입, 로그인 및 게시판 서비스 입니다. 
MyBatis + MySQL을 사용하며 JWT를 통한 인증 시스템이 포함되어 있습니다.
사용한 기술은 MyBatis,Mysql,Lombok,Jwt,Spring Boot 입니다.
기능은 회원가입과 JWT 토큰을 발급 하여 로그인을 하고 자시만 회원정보를 수정할 수 있습니다.
로그인한 사용자만 게시글을 작성할 수 있고 목록은 최신순으로 10개를 볼 수 있습니다.
한 게시물을 상세 조회도 가능하고 작성자 본인만 게시글 수정 및 삭제를 할 수 있습니다.
실행 방법은 .env에 
spring.datasource.url=jdbc:mysql://localhost:3306/server_impl_user?serverTimezone=Asia/Seoul
spring.datasource.username=root
spring.datasource.password=987654321
을 작성후 실행 가능합니다.
