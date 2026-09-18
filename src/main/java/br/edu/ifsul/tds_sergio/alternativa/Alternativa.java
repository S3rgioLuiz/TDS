package br.edu.ifsul.tds_sergio.alternativa;

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
@Table(name = "alternativas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Alternativa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String opcao;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "questao_id", referencedColumnName = "id", nullable = false)
    private Questao questao;
}
