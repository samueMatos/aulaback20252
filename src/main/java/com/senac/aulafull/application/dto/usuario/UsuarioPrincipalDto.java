package com.senac.aulafull.application.dto.usuario;

import com.senac.aulafull.domain.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UsuarioPrincipalDto(Long id, String email, Long empresaId , Collection<? extends GrantedAuthority> autorizacao) {

    public UsuarioPrincipalDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getId(),
                usuario.getAuthorities()
        );
    }
}
