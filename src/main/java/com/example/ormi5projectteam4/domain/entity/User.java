package com.example.ormi5projectteam4.domain.entity;

import com.example.ormi5projectteam4.domain.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "password_answer")
    private String passwordAnswer;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "password_question_id")
    private PasswordQuestion passwordQuestion;

    //추가
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Post> posts = new ArrayList<>();
//
//    public void addPost(Post post) {
//        posts.add(post);
//        post.setUser(this);
//    }
//
//    public void removePost(Post post) {
//        posts.remove(post);
//        post.setUser(null);
//    }
}
