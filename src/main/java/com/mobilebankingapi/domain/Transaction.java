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
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Account transferReceiver; // uses when transaction type is TRANSFER

    private String paymentReceiver;

    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false, length = 30)
    private String transactionType; // transfer and payment

    private Boolean status; // Pending, Completed, Failed

    private LocalDateTime transactionAt;

    private Boolean isDeleted;

    @ManyToOne
    private User user;

}
