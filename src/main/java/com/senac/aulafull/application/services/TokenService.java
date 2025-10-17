package com.senac.aulafull.application.services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.senac.aulafull.application.dto.login.LoginResquestDto;
import com.senac.aulafull.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.aulafull.domain.entities.Token;
import com.senac.aulafull.domain.entities.Usuario;
import com.senac.aulafull.domain.repository.TokenRepository;
import com.senac.aulafull.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${spring.secretkey}")
    private String secret;

    @Value("${spring.tempo_expiracao}")
    private Long tempo;

    private String emissor = "DEVTEST";

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String gerarToken(LoginResquestDto loginResquestDto){

        var usuario = usuarioRepository.findByEmail(loginResquestDto.email()).orElse(null);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(usuario.getEmail())
                .withExpiresAt(this.gerarDataExpiracao())
                .sign(algorithm);

        tokenRepository.save(new Token(null,token,usuario));
        return token;
    }


    public UsuarioPrincipalDto validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(emissor)
                .build();
        verifier.verify(token);

        var tokenResult = tokenRepository.findByToken(token).orElse(null);

        if(tokenResult == null){
            throw new IllegalArgumentException("Token invalido!");
        }

        return new UsuarioPrincipalDto(tokenResult.getUsuario());
    }

    

    private Instant gerarDataExpiracao(){
        var dataAtual = LocalDateTime.now();
        dataAtual = dataAtual.plusMinutes(tempo);

        return dataAtual.toInstant(ZoneOffset.of("-03:00"));

    }
}
