package br.edu.ifsul.tds_sergio.questao;

import br.edu.ifsul.tds_sergio.alternativa.Alternativa;
import br.edu.ifsul.tds_sergio.nivel.Nivel;
import br.edu.ifsul.tds_sergio.tema.Tema;
import br.edu.ifsul.tds_sergio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "questoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Questao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String foto;
    private String pergunta;
    private String explicacao;
    private String referencia;
    private Integer status = 0;
    private Integer tipo = 1;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tema_id", referencedColumnName = "id", nullable = false)
    private Tema tema;

    @OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
    private Collection<Alternativa> alternativas;

    @OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
    private Collection<Nivel> niveis;

}
