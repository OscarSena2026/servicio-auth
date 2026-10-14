package co.edu.sena.auth.repository;

import co.edu.sena.auth.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio: acceso a la tabla "usuarios".
 * Spring genera las consultas SQL a partir del nombre de cada metodo.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Indica si ya existe un usuario con ese nombre
    boolean existsByNombreUsuario(String nombreUsuario);

    // Busca un usuario por su nombre
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
