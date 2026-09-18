package br.edu.ifsul.tds_sergio.nivel;

import br.edu.ifsul.tds_sergio.questao.Questao;
import br.edu.ifsul.tds_sergio.tema.Tema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "niveis")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Nivel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer dificuldade;

    @ManyToOne
    @JoinColumn(name = "tema_id", referencedColumnName = "id", nullable = false)
    private Tema tema;

    @ManyToOne
    @JoinColumn(name = "questao_id", referencedColumnName = "id", nullable = false)
    private Questao questao;
}

