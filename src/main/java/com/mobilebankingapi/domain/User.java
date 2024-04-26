package com.mobilebankingapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
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

    @Column(unique = true, nullable = false)
    private String nationalCardId;

    @Column(nullable = false)
    private Integer pin;  // Store 4-digit

    @Column(unique = true, nullable = false, length = 30)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 8)
    private String gender;

    private LocalDate dob;

    @Column(length = 100)
    private String cityOrProvince;

    @Column(length = 100)
    private String khanOrDistrict;

    @Column(length = 100)
    private String sangkatOrCommune;

    @Column(length = 100)
    private String village;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String employeeType;

    @Column(length = 100)
    private String position;

    @Column(length = 100)
    private String companyName;

    @Column(length = 100)
    private String mainSourceOfIncome;

    private BigDecimal monthlyIncomeRange;

    @Column(unique = true)
    private String oneSignalId;

    @Column(unique = true)
    private String studentIdCard;

    private boolean isDeleted; // manage delete status (admin want to disable or remove an account)

    private boolean isStudent;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccountList;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    private boolean isAccountNonExpired;

    boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    private Boolean isBlocked; // manage block status (when there is bad action happened)

    private LocalDateTime createdAt;


}
