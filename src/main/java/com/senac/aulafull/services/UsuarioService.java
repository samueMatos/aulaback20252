package com.senac.aulafull.services;

import com.senac.aulafull.dto.LoginResquestDto;
import com.senac.aulafull.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarSenha(LoginResquestDto login){

        return usuarioRepository.existsUsuarioByEmailContainingAndSenha(login.email(),login.senha());
    }

}
