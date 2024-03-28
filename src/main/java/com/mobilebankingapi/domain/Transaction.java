package com.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Setter
@Getter
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account sender;

    private BigDecimal amount;

    private String remark;

    @ManyToOne
    private Account receiver;

    private Boolean isPayment;

    private LocalDateTime transaction;

    private Boolean isDeleted;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "transaction")
    private List<Notification> notifications;
}
