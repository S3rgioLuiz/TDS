package br.edu.ifsul.tds_sergio.usuario;

import br.edu.ifsul.tds_sergio.questao.Questao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

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
    private String foto = "Foto.svg";
    private Integer pontacao = 0;
    private Integer status = 0;
    private String senha;
    private Integer dificuldade = 1;
    private String chave = "NULO";

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Questao> questoes;
}
