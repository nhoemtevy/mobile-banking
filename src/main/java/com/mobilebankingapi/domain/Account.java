package com.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 9)
    private String actNo;

    @Column(unique = true, nullable = false, length = 100)
    private String actName;

    @Column(length = 100)
    private String alias;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal transferLimit;

    // Account has a type
    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccountList;

    @OneToOne
    private Card card;

    private Boolean isHidden; // uses to hide account on mobile app
}
