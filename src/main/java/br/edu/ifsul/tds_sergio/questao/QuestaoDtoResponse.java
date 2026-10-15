package br.edu.ifsul.tds_sergio.questao;

public record QuestaoDtoResponse(
        Long id,
        String pergunta,
        String explicacao,
        String referencia,
        Integer tipo,
        Long temaId
) {
    // Construtor que converte uma Questao real em um DTO de resposta
    public QuestaoDtoResponse(Questao questao) {
        this(
                questao.getId(),
                questao.getPergunta(),
                questao.getExplicacao(),
                questao.getReferencia(),
                questao.getTipo(),
                questao.getTema().getId() // Pega o ID do tema associado
        );
    }
}