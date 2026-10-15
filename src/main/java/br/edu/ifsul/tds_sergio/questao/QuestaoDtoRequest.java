package br.edu.ifsul.tds_sergio.questao;

public record QuestaoDtoRequest(
        String pergunta,
        String explicacao,
        String referencia,
        Integer tipo,
        Long temaId // Recebemos apenas o ID do Tema para vincular
) {
}