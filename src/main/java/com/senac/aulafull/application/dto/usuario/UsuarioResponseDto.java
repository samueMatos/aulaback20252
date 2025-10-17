package com.senac.aulafull.application.dto.usuario;

import com.senac.aulafull.domain.entities.Usuario;

public record UsuarioResponseDto(Long id, String nome, String CPF, String email) {

    public UsuarioResponseDto(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCPF(),
                usuario.getEmail()
        );
    }
}
