package com.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Data
@Setter
@Getter
@Table(name = "role_authorities")
public class RoleAuthorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;





}
