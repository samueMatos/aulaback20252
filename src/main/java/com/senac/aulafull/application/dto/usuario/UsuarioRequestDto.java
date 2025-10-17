package com.senac.aulafull.application.dto.usuario;

public record UsuarioRequestDto(Long id, String nome, String CPF, String email, String senha, String role) {
}

