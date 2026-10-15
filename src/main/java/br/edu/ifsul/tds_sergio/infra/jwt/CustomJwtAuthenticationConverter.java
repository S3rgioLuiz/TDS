package br.edu.ifsul.tds_sergio.infra.jwt;

import br.edu.ifsul.tds_sergio.autenticacao.AutenticacaoRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final AutenticacaoRepository repository;

    public CustomJwtAuthenticationConverter(AutenticacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var usuario = repository.findByEmail(jwt.getSubject());

        if (usuario == null) {
            throw new BadCredentialsException("Usuário do token JWT não encontrado");
        }
        return new UsernamePasswordAuthenticationToken(usuario, jwt, usuario.getAuthorities());
    }
}