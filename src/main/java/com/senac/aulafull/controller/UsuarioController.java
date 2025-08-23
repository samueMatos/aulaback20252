package com.senac.aulafull.controller;


import com.senac.aulafull.model.Usuario;
import com.senac.aulafull.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de usuários",description = "Camada responsavel por controlar os registos de usuário!")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id){
       var usuario=  usuarioRepository.findById(id)
               .orElse(null);

       if(usuario == null){
           return ResponseEntity.notFound().build();
       }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "usuarios",description = "Metodo responsavel de calcular os custos da folhga de pagamento e apos faz os lancamentos comtabeis na tabela x !")
    public ResponseEntity<?> consultarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());

    }

    @PostMapping
    @Operation(summary = "Salvar Usuério", description = "Método resposavel em criar usuarios!")
    public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario){

        try {

            var usuarioResponse = usuarioRepository.save(usuario);

            return ResponseEntity.ok(usuarioResponse);

        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }





}
