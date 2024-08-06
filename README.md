# 🐶 Happy Tales 🐱
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/html-61DAFB?style=for-the-badge&logoColor=black"><img src="https://img.shields.io/badge/JavaScript-f5f242?style=for-the-badge&logo=javascript&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4169E1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=AmazonAWS&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

> 유기동물을 개인 간에 입양할 수 있는 커뮤니티 👉 [[링크]](http://43.203.58.44:8080)

## 🐾 Happy Tales: 유기동물 입양 커뮤니티

유기동물들이 새로운 가족을 찾을 수 있도록 돕는 곳이 많지 않고, 입양 과정을 투명하고 안전하게 진행하기 어려운 경우가 많습니다. **Happy Tales**는 유기동물을 사랑하는 사람들이 모여 자유롭게 소통하며, 안전하고 투명한 입양 과정을 통해 유기동물들이 행복한 새 가정을 찾을 수 있도록 지원하는 커뮤니티입니다.

### 주요 특징:

- **쉽고 간편한 접근**: 회원이라면 누구나 쉽게 사이트에 접속하여 정보를 얻을 수 있습니다.
- **자유로운 소통**: 입양 희망자와 유기동물 보호자 간의 자유로운 소통을 지원합니다.
- **투명한 정보 교류**: 입양 과정을 투명하게 공개하며, 모든 과정에서의 정보 교류를 자유롭게 할 수 있습니다.

### 📢 우리의 목표:

유기동물들이 사랑받으며 행복하게 살아갈 수 있는 가정을 찾을 수 있도록, 누구나 쉽게 참여하고 도울 수 있는 커뮤니티를 만드는 것입니다. **Happy Tales**는 유기동물을 보호하고 입양을 희망하는 모든 사람들이 모여 따뜻한 마음을 나눌 수 있는 공간이 되기를 바랍니다.

### 📌 우리의 비전:

1. **모두에게 열려있는 커뮤니티**: 입양을 희망하는 사람들, 유기동물 보호자들, 그리고 동물 사랑에 관심이 있는 모든 사람들이 모여 따뜻한 마음을 나눌 수 있는 커뮤니티를 지향합니다.
2. **안전하고 투명한 입양 과정**: 입양 과정을 투명하게 공개하여, 모든 참여자가 신뢰할 수 있는 커뮤니티를 만듭니다.
3. **행복한 이야기**: 유기동물들이 새로운 가정에서 행복한 삶을 살 수 있도록 돕고, 그들의 이야기를 함께 나누는 커뮤니티가 되기를 바랍니다.

### 함께 만들어가는 **Happy Tales**:

유기동물을 입양하고자 하는 분들, 그리고 유기동물을 보호하고 있는 분들이 서로를 도울 수 있는 따뜻한 공간입니다. 유기동물들에게 두 번째 기회를 주고, 행복한 이야기들이 가득한 커뮤니티를 함께 만들어가요!




### 1. 💾**개발 환경**
- Java JDK 17, JavaScript
- 프론트엔드 : HTML,CSS, JavaScript, Thymeleaf
- 백엔드 : Spring Boot
- 데이터베이스 : MySQL
- ORM : JPA
- 배포환경 : AWS LightSail
- 협업도구 : GitHub, Notion, dbdiagram, Figma

### 2. 🤔**기능 정의서**
- [기능 정의 목록 바로가기](https://github.com/Sunryeo/ormi5-project-team4/wiki/Work-specification)

## ✨UI(화면) 설계서

- [화면 설계서 바로가기]([https://www.figma.com/file/UxckBh583uPenF9pmlpJUd/Untitled?type=design&node-id=0%3A1&mode=design&t=xacdis5Qkeh8AcrZ-1])

## 📂Project Structure

### 🌐 Front-End
```
📁 src
├── 📁 static
│   ├── 📁 images
│   ├── 📁 js
│   └── 📁 styles
└── 📁 templates
```
### ⚙️ Back-End
```
📁 src
├── 📁 annotation
│   └── 📃 Secured.java
├── 📁 aspect
│   └── 📃 SecurityAspect.java
├── 📁 controller
│   ├── 📁 rest_controller
│   │    ├── 📃 AdminController.java
│   │    ├── 📃 AuthController.java
│   │    ├── 📃 ImageController.java
│   │    ├── 📃 MyPageController.java
│   │    ├── 📃 NoticeController.java
│   │    ├── 📃 PostController.java
│   │    └── 📃 UserController.java
│   └── 📁 thymeleaf_controller
│   │    ├── 📃 AdminPageController.java
│   │    ├── 📃 HomeController.java
│   │    ├── 📃 MainController.java
│   │    ├── 📃 MyInfoPageController.java
│   │    └── 📃 WebController.java
├── 📁 domain
│   ├── 📁 constant
│   │    ├── 📃 AdoptionStatus.java
│   │    ├── 📃 ApproveStatus.java
│   │    ├── 📃 Gender.java
│   │    ├── 📃 NeuteringStatus.java
│   │    ├── 📃 Role.java
│   │    ├── 📃 Species.java
│   │    ├── 📃 StatusCode.java
│   │    └── 📃 StatusMessage.java
│   ├── 📁 dto
│   └── 📁 entity
├── 📁 repository
├── 📁 security
└── 📁 service
```

## 🏭System Structure
![img.png](readme/SystemStructure.png)


## 🔐ERD Structure
![ERD](https://github.com/user-attachments/assets/b6ee03f3-3bbe-4ced-8aff-2ec900212a9b)

## 🎈API 명세서

### 📁 User

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| generateMember | POST | /member | 회원 가입 |
| deleteUser | DELETE | /member/{id} | 회원 탈퇴 |
| validateDuplicateUserEmail | GET | /email-duplication | 이메일 중복 확인 |
| validateDuplicateUserName | GET | /nickname-duplication | 닉네임 중복 확인 |
| getAllPasswordQuestions | GET | /password-question | 비밀번호 찾기 질문 조회 |

### 📁 Auth

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| login | POST | /auth/login | 로그인 |
| logout | POST | /auth/logout | 로그아웃 |
| findUserForPasswordChange | POST | /auth/password-certification | 비밀번호 찾기 |
| changePassword | PUT | /auth/password | 비밀번호 변경 |
| getCurrentUserDto | GET | /auth/this | 로그인 하고 있는 유저 정보 |

### 📁 MyPage

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| getUserInfo | GET | /mypage/this/info/{userId} | 사용자 정보 조회 |
| updateUserInfo | PUT | /mypage/this/{userId} | 사용자 정보 수정 |
| deleteUser | DELETE | /mypage/this/{userId} | 사용자 정보 제거 |
| getPostsByUser | GET | /mypage/this/post | 사용자가 작성한 글 목록 조회 |
| getPostDetail | GET | /mypage/read-post/{postId} | 사용자가 작성한 글 상세 조회 |

### 📁 Post

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| getAllPosts | GET | /post | 모든 글 조회 |
| getProceedPosts | GET | /post/proceed | 승인된 모든 글 조회 |
| getPostsByFoundAt | GET | /post/location | 지역 필터링에 포함된 모든 글 조회 |
| createPost | POST | /post | 글 생성 |
| getPostById | GET | /post/{id} | 글 상세 조회 |
| updatePost | PUT | /post/{id} | 글 수정 |
| deletePost | DELETE | /post/{id} | 글 삭제 |

### 📁 Image

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| uploadImage | POST | /upload | 이미지 업로드 |
| getImageUrl | GET | /{id} | 이미지 주소 조회 |

### 📁 Notice

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| createNotice | POST | /admin/notice | 새 공지사항 추가 |
| getAllNotices | GET | /admin/notice | 모든 공지사항 조회 |
| getNoticeById | GET | /admin/notice/{id} | 특정 id의 공지사항 조회 |
| updateNotice | PUT | /admin/notice/{id} | 공지사항 정보 업데이트 |
| deleteNotice | DELETE | /admin/notice/{id} | 공지사항 삭제 |
| getLatestNotices | GET | /notice | 최신 공지사항 조회(사용자 화면) |

### 📁 Admin

| NAME | ⚙METHOD | 📎URL | 📖DESCRIPTION |
| --- | --- | --- | --- |
| getPostsByConditions | GET | /admin/post | 게시글 목록 조회 |
| getUsersByConditions | GET | /admin/member | 유저 목록 조회 |
| changeUserRole | PUT | /admin/member/{id} | 유저 등급 수정 |
| changePostApproveStatus | PUT | /admin/post/{id} | 글 승인 여부 수정 |

## 🛠 Convention

### [Java Convention](https://github.com/Sunryeo/ormi5-project-team4/wiki/01-Java-Coding-Convention)

### [Other Convention](https://github.com/Sunryeo/ormi5-project-team4/wiki/02-Other-Convention)

## 👨‍💻Participation Member
- [박철안](https://github.com/The-Shinee)
- [문순려](https://github.com/Sunryeo)
- [안정민](https://github.com/jminiiiz)
- [성창용](https://github.com/Sungchangyong)
- [윤준호](https://github.com/yunjunho97)
