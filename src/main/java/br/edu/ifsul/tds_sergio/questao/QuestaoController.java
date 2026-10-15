package br.edu.ifsul.tds_sergio.questao;

import br.edu.ifsul.tds_sergio.tema.Tema;
import br.edu.ifsul.tds_sergio.tema.TemaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/questoes")
public class QuestaoController {

    private final QuestaoRepository questaoRepository;
    private final TemaRepository temaRepository;

    // Injeção de dependências
    public QuestaoController(QuestaoRepository questaoRepository, TemaRepository temaRepository) {
        this.questaoRepository = questaoRepository;
        this.temaRepository = temaRepository;
    }

    // =======================================================
    // ROTAS DE LEITURA (Acesso: Administrador e Usuário Comum)
    // =======================================================

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_COMUM"})
    public ResponseEntity<List<QuestaoDtoResponse>> findAll() {
        // Busca todas as questões, converte para DTO e retorna
        List<QuestaoDtoResponse> questoes = questaoRepository.findAll()
                .stream()
                .map(QuestaoDtoResponse::new)
                .toList();
        return ResponseEntity.ok(questoes);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_COMUM"})
    public ResponseEntity<QuestaoDtoResponse> findById(@PathVariable Long id) {
        Optional<Questao> questao = questaoRepository.findById(id);
        return questao.map(q -> ResponseEntity.ok(new QuestaoDtoResponse(q)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =======================================================
    // ROTAS DE ESCRITA (Acesso: Somente Administrador)
    // =======================================================

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> insert(@RequestBody QuestaoDtoRequest dto, UriComponentsBuilder uriBuilder) {
        // 1. Verifica se o Tema informado realmente existe no banco
        Optional<Tema> temaOptional = temaRepository.findById(dto.temaId());

        if (temaOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: Tema com ID " + dto.temaId() + " não encontrado!");
        }

        // 2. Cria a nova questão
        Questao novaQuestao = new Questao(
                0L, // ID nulo/0 para o banco gerar automaticamente
                dto.pergunta(),
                dto.explicacao(),
                dto.referencia(),
                0, // status padrão
                dto.tipo(),
                temaOptional.get(), // Tema encontrado
                null, // Alternativas vazias por enquanto
                null  // Niveis vazios por enquanto
        );

        // 3. Salva no banco
        questaoRepository.save(novaQuestao);

        // 4. Retorna status 201 Created com a URL da nova questão
        var location = uriBuilder.path("api/v1/questoes/{id}").buildAndExpand(novaQuestao.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<QuestaoDtoResponse> update(@PathVariable Long id, @RequestBody QuestaoDtoRequest dto) {
        Optional<Questao> questaoExistente = questaoRepository.findById(id);
        Optional<Tema> temaOptional = temaRepository.findById(dto.temaId());

        if (questaoExistente.isPresent() && temaOptional.isPresent()) {
            Questao q = questaoExistente.get();
            q.setPergunta(dto.pergunta());
            q.setExplicacao(dto.explicacao());
            q.setReferencia(dto.referencia());
            q.setTipo(dto.tipo());
            q.setTema(temaOptional.get());

            questaoRepository.save(q);
            return ResponseEntity.ok(new QuestaoDtoResponse(q));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (questaoRepository.existsById(id)) {
            questaoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}