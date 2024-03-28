package com.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 8)
    private String gender;

    @Column(unique = true)
    private String one_signal_id;


    private boolean is_deleted;

    private boolean is_student;

    private String student_card_id;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccountList;

    @OneToMany(mappedBy = "sender")
    private List<Notification> sender;

    @OneToMany(mappedBy = "receiver")
    private List<Notification> receiver;

}
