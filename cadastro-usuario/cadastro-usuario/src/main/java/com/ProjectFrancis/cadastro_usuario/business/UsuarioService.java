package com.ProjectFrancis.cadastro_usuario.business;

import com.ProjectFrancis.cadastro_usuario.infrastructure.entitys.Usuario;
import com.ProjectFrancis.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () ->new RuntimeException("Email nao encontrado")
        );
    }
    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id,Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(()->
                new RuntimeException("Usuario nao encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getNome() :
                        usuarioEntity.getNome())
                .nome(usuario.getNome() != null ? usuario.getNome() :
                        usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

    }
}

