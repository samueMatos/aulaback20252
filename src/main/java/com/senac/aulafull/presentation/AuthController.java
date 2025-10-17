package com.senac.aulafull.presentation;

import com.senac.aulafull.application.dto.login.LoginResponseDto;
import com.senac.aulafull.application.dto.login.LoginResquestDto;
import com.senac.aulafull.application.services.TokenService;
import com.senac.aulafull.application.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação controller",description = "Controller responsavel pela autencicação da aplicação!")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Método responsavel por efetuar o login do usuário!")
    public ResponseEntity<?> login(@RequestBody LoginResquestDto resquest){

        if(!usuarioService.validarSenha(resquest)){
            return  ResponseEntity.badRequest().body("Usuário ou senha Invalido!");
        }

        var token =tokenService.gerarToken(resquest);

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

}
