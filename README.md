# 🎵 음원 스트리밍 웹 서비스 YogiAudio
<p align="center">
  <img src="https://github.com/songkidong/YogiAudio/assets/141198018/ee34ce45-dfd1-4fb5-9ece-6f9217d3132f">
</p>

<br>

## 프로젝트 소개
- 전체 시연 영상 (유튜브) : https://youtu.be/yCJU5bN61ro
- Spring Boot, MyBatis, Mysql 을 활용한 음원 파일 등록 및 결제를 통한 다운로 가능
- 클라이언트가 샘플 데이터를 들어보고 원하는 음악을 선택해서 좋아요 클릭 가능
- 관리자 / 고객센터등의 운영으로 클라이언트의 클레임 실시간 반영 후 변경 가능

<br>

## 팀원 구성
![Screenshot_43](https://github.com/songkidong/YogiAudio/assets/136422040/0be06d5a-383f-4595-a4a4-0d089e3b51b7)

<br>

## 개발 환경
- 개발 툴 : Spring Tools 4 (4.21.1)
- Backend : JAVA 17, SpringBoot 3.1.8, MyBatis, JSP, MySQL 8.0.26, lombok, BCrypt HASH, Apache Tomcat : 9.0
- Frontend : bootstrap : 4.6.2, HTML5, CSS3, JavaScript : 1.16.1, JQuery : 3.6.4
- 버전/이슈관리 : GitHub, GitBash
- 협업 툴 : Discord, Notion
- 외부 API : 토스 결제 API, 카카오/구글/네이버 소셜로그인 API, 이메일 전송(네이버 SMTP 프로토콜) API
- 게시판 : Summernote editor

<br>

## 브랜치 전략
Git-flow 전략을 기반으로 main, develop 브랜치와 feature 보조 브랜치를 운용했습니다.
- main, develop, Feat 브랜치로 나누어 개발을 하였습니다.
- main 브랜치는 배포 단계에서만 사용하는 브랜치입니다.
- develop 브랜치는 개발 단계에서 git-flow의 master 역할을 하는 브랜치입니다.
- Feat 브랜치는 기능 단위로 독립적인 개발 환경을 위하여 사용하고 merge 후 각 브랜치를 삭제해주었습니다.

<br>

## ⚙ 의존성
```java
implementation 'com.google.code.gson:gson:2.8.8'
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
implementation group: 'org.glassfish.web', name: 'jakarta.servlet.jsp.jstl', version: '2.0.0'
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3'
runtimeOnly 'com.mysql:mysql-connector-j'
implementation 'org.springframework.boot:spring-boot-starter-web'
compileOnly 'org.projectlombok:lombok'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
runtimeOnly 'com.h2database:h2'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
implementation 'org.springframework.security:spring-security-crypto'
implementation 'org.springframework.boot:spring-boot-starter-mail'
```	

<br>

## 개발기간 / 일정관리
개발기간 (총 12일)</p>
- 2024.03.11 ~ 2024.03.26
작업관리
- GitHub Projects통한 작업 항목 관리, 노션을 이용하여 일일 작업 내용을 기록하였습니다.
- 노션 주소 : "https://phase-ground-168.notion.site/14c5a9ac94484ad29fdf3f0db3c4c613?v=d9e697a019a945f0a10048e72c89218e&amp;pvs=4

![일정관리1](https://github.com/songkidong/YogiAudio/assets/141198018/acf3ef69-3f4c-4379-8353-40e6b3def14d)
![일정관리2](https://github.com/songkidong/YogiAudio/assets/141198018/83b0141b-42c8-46ae-9568-11b130cd9470)

## 1️⃣ 프로젝트 개요
* 핵심 기능이 많으며 실무에서 활용할 수 있는 기능이 포함됐다고 생각했고, 실시간 스트리밍/다운로드를 구현해보고 싶어서 음원 스트리밍 사이트로 주제 선정
* 현행 시스템 벤치마킹 (지니 뮤직)

<br>

## 2️⃣ 프로젝트 구조

<details>
    <summary>⚡️ 구조 자세히 살펴보기</summary>

    📦src
     ┗ 📂main
       ┣ 📂java
       ┃ ┗ 📂com
       ┃   ┗ 📂project3
       ┃     ┗ 📂yogiaudio
       ┃       ┣ 📂config
       ┃       ┣ 📂controller
       ┃       ┣ 📂dto
       ┃       ┣ 📂filedb
       ┃       ┣ 📂handler
       ┃       ┃ ┗ 📂exception
       ┃       ┣ 📂repository
       ┃       ┃ ┗ 📂entity
       ┃       ┃ ┗ 📂interfaces       
       ┃       ┣ 📂service
       ┃       ┗ 📂util
       ┃       
       ┣ 📂resources
       ┃ ┣ 📂db
       ┃ ┣ 📂mapper
       ┃ ┗ 📂static
       ┃   ┣ 📂album
       ┃   ┣ 📂assets
       ┃   ┣ 📂banner
       ┃   ┣ 📂css
       ┃   ┣ 📂favicon
       ┃   ┣ 📂img
       ┃   ┣ 📂js
       ┃   ┣ 📂logo       
       ┃   ┣ 📂main
       ┃   ┗ 📂music
       ┃       
       ┗ 📂webapp
         ┗ 📂WEB-INF
           ┗ 📂view
             ┣ 📂admin
             ┣ 📂board
             ┣ 📂layout
             ┣ 📂product             
             ┗ 📂user          


</details>

<br>

## 3️⃣ ERD
![erd](https://github.com/songkidong/YogiAudio/assets/141198018/9bae477b-bbfa-4ff0-9e1d-614473974c61)

<br>

## 4️⃣ SiteMap
![sitemap](https://github.com/songkidong/YogiAudio/assets/141198018/433c4c3d-d4e5-40d2-97c6-93952974931f)

<br>

## 5️⃣ 나의 기능 
### 소셜로그인, 게시판(공지사항, 1:1문의하기, 자유게시판+댓글), 관리자(게시판 등록 및 상태관리)
<table>
    <tr>
        <td>소셜 로그인</td>
    </tr>
    <tr>
        <td><img src="https://github.com/songkidong/YogiAudio/assets/136422040/fbf09085-6a39-40e0-b868-49b8c4cc2c89"></td>
    </tr>
    <tr>
        <td>게시판</td>
    </tr>
    <tr>
        <td><img src="https://github.com/songkidong/YogiAudio/assets/136422040/badbac2c-e3b4-4e2b-9ed5-267023957191"></td>
    </tr>
    <tr>
        <td>관리자-게시판</td>
    </tr>
    <tr>
        <td><img src="https://github.com/songkidong/YogiAudio/assets/136422040/e65404a6-03bd-4255-ad42-cc2e7887c1da"></td>
    </tr>
</table>
