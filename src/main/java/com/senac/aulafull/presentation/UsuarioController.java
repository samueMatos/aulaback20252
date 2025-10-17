package com.senac.aulafull.presentation;


import com.senac.aulafull.application.dto.usuario.UsuarioRequestDto;
import com.senac.aulafull.application.dto.usuario.UsuarioResponseDto;
import com.senac.aulafull.application.services.UsuarioService;
import com.senac.aulafull.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controlador de usuários",description = "Camada responsavel por controlar os registos de usuário!")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> consultaPorId(@PathVariable Long id){

       var usuario=  usuarioService.consultarPorId(id);

       //SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
       if(usuario == null){
           return ResponseEntity.notFound().build();
       }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "usuarios",description = "Metodo responsavel de calcular os custos da folhga de pagamento e apos faz os lancamentos comtabeis na tabela x !")
    public ResponseEntity<List<UsuarioResponseDto>> consultarTodos(){

        return ResponseEntity.ok(usuarioService.consultarTodosSemFiltro());

    }

    @GetMapping("/grid")
    @Operation(summary = "usuarios grid filtrada",description = "Metod responsavel por consultar dados do usuaril paginados e filtrados")
    public ResponseEntity<List<UsuarioResponseDto>> consultarPaginadoFiltrado(
          @Parameter(description = "Parametro de quantidade de registro por pagina!") @RequestParam Long take,
          @Parameter(description = "Parametro de quantidade de paginas!")  @RequestParam Long page ,
          @Parameter(description = "Parametro de filtro!")  @RequestParam(required = false) String filtro
    ){
        return ResponseEntity.ok(usuarioService.consultarPaginadoFiltrado(take,page,filtro));
    }


    @PostMapping
    @Operation(summary = "Salvar Usuério", description = "Método resposavel em criar usuarios!")
    public ResponseEntity<UsuarioResponseDto> salvarUsuario(@RequestBody UsuarioRequestDto usuario){

        try {

            var usuarioResponse = usuarioService.salvarUsuario(usuario);

            return ResponseEntity.ok(usuarioResponse);

        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }






}
