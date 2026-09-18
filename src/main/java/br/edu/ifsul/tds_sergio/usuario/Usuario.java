package br.edu.ifsul.tds_sergio.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String apelido;
    @Column(unique = true)
    private String email;
    private String nome;
    private Integer pontacao = 0;
    private Integer status = 0;
    private String senha;
    private Integer dificuldade = 1;
    private String chave = "NULO";


}
