package com.mobilebankingapi.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Setter
@Getter
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account account;

    private Boolean isDeleted;
    private Boolean isBlocked;
    private LocalDateTime createdAt;
}
