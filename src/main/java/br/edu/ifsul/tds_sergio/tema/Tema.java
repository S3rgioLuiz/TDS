package br.edu.ifsul.tds_sergio.tema;

import br.edu.ifsul.tds_sergio.nivel.Nivel;
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
@Table(name = "temas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Tema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String foto;
    private String nome;
    private String descricao;
    private Integer status = 0;

    @OneToMany(mappedBy = "tema", fetch = FetchType.LAZY)
    private Collection<Questao> questoes;

    @OneToMany(mappedBy = "tema", fetch = FetchType.LAZY)
    private Collection<Nivel> niveis;
}
