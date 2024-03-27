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

    @Column(unique = true, nullable = false, length = 9)
    private String account_no;

    @Column(unique = true, nullable = false, length = 100)
    private String account_name;

    private BigDecimal transferLimit;
    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UserAccount> userAccounts;
}
