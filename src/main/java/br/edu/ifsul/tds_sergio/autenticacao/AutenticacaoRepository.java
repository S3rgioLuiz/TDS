package br.edu.ifsul.tds_sergio.autenticacao;

import br.edu.ifsul.tds_sergio.usuario.Usuario;
import org.springframework.data.repository.Repository;

public interface AutenticacaoRepository extends Repository<Usuario, Long> {
    Usuario findByEmail(String email);
}