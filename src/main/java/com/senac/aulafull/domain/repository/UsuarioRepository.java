package com.senac.aulafull.domain.repository;


import com.senac.aulafull.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmailAndNome(String email, String nome);

    boolean existsUsuarioByEmailContainingAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndTokenSenha(String email, String tokenSenha);

    Optional<Usuario> findByCPF(String cpf);
}
