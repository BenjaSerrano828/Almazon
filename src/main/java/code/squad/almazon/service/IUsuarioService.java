package code.squad.almazon.service;

import code.squad.almazon.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
}
