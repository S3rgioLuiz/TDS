package br.edu.ifsul.tds_sergio.autenticacao;

import br.edu.ifsul.tds_sergio.usuario.Usuario;
import br.edu.ifsul.tds_sergio.infra.jwt.TokenJwtDto;
import br.edu.ifsul.tds_sergio.infra.jwt.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/autenticacao")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJwtDto> efetuaLogin(@RequestBody UsuarioAutenticacaoDto data) {
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        var authentication = manager.authenticate(authenticationDTO);
        var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDto(tokenJWT));
    }
}