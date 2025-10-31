package com.senac.aulafull.application.services;

import com.senac.aulafull.application.dto.login.EsqueciMinhaSenhaDto;
import com.senac.aulafull.application.dto.login.LoginResquestDto;
import com.senac.aulafull.application.dto.usuario.RegistrarNovaSenhaDto;
import com.senac.aulafull.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.aulafull.application.dto.usuario.UsuarioRequestDto;
import com.senac.aulafull.application.dto.usuario.UsuarioResponseDto;
import com.senac.aulafull.domain.entities.Usuario;
import com.senac.aulafull.domain.interfaces.IEnvioEmail;
import com.senac.aulafull.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private IEnvioEmail iEnvioEmail;

    public boolean validarSenha(LoginResquestDto login) {

        return usuarioRepository.existsUsuarioByEmailContainingAndSenha(login.email(), login.senha());
    }

    public UsuarioResponseDto consultarPorId(Long id) {
//        return usuarioRepository.findById(id)
//                .map(usuario -> new UsuarioResponseDto(usuario))
//                .orElse(null);

        return usuarioRepository.findById(id)
                .map(UsuarioResponseDto::new)
                .orElse(null);
    }

    public List<UsuarioResponseDto> consultarTodosSemFiltro() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto usuarioRequest) {

        var usuario = usuarioRepository.findByCPF(usuarioRequest.CPF())
                .map(u -> {
                    u.setNome(usuarioRequest.nome());
                    u.setSenha(usuarioRequest.senha());
                    u.setRole(usuarioRequest.role());
                    u.setEmail(usuarioRequest.email());
                    return u;
                })
                .orElse(new Usuario(usuarioRequest));

        usuarioRepository.save(usuario);

        return usuario.toDtoResponse();
    }

    public List<UsuarioResponseDto> consultarPaginadoFiltrado(Long take, Long page, String filtro) {

        return usuarioRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Usuario::getId).reversed())
                .filter(p -> p.getDataCadastro().isAfter(LocalDateTime.now().plusDays(-7)))
                .filter(a -> filtro != null ? a.getNome().contains(filtro) : true)
                .skip((long) page * take)
                .limit(take)
                .map(UsuarioResponseDto::new)
                .collect(Collectors.toList());
    }

    public void recuperarSenhaEnvio(UsuarioPrincipalDto usuarioLogado) {
        
        iEnvioEmail.enviarEmailSimples("samuel.santos.mtx@hotmail.com",
                "CódigoRecuperacao",
                gerarCodigoAleatorio(8)
        );
    }

    public String gerarCodigoAleatorio(int length) {

        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            senha.append(CHARS.charAt(randomIndex));
        }
        return senha.toString();
    }

    public void esqueciMinhaSenha(EsqueciMinhaSenhaDto esqueciMinhaSenhaDto) {


        var usuario = usuarioRepository.findByEmail(esqueciMinhaSenhaDto.email()).orElse(null);

        if(usuario != null) {
            var codigo = gerarCodigoAleatorio(8);

            usuario.setTokenSenha(codigo);

            usuarioRepository.save(usuario);

            iEnvioEmail.enviarEmailComTemplate(esqueciMinhaSenhaDto.email(),
                    "Código Recuperacao",
                    codigo
            );
        }
    }

    public void registrarNovaSenha(RegistrarNovaSenhaDto registrarNovaSenhaDto) {


        var usuario = usuarioRepository
                .findByEmailAndTokenSenha(
                        registrarNovaSenhaDto.email(),
                        registrarNovaSenhaDto.token())
                .orElse(null);

        if (usuario!=null){

            usuario.setSenha(registrarNovaSenhaDto.senha());
            usuarioRepository.save(usuario);
        }

    }
}
   