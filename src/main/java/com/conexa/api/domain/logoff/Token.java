package com.conexa.api.domain.logoff;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tokens")
@Entity(name = "Token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_excluido", nullable = false)
    private String tokenExcluido;


}