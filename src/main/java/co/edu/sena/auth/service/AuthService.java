package co.edu.sena.auth.service;

import co.edu.sena.auth.dto.CredencialesDTO;
import co.edu.sena.auth.model.Usuario;
import co.edu.sena.auth.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Logica de negocio del registro y del inicio de sesion.
 * Es reutilizable: cualquier controlador puede usar estos metodos.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    // Herramienta que protege (encripta) y compara contrasenas con BCrypt
    private final BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra un usuario nuevo.
     * @return true si se registro; false si el nombre de usuario ya existe
     */
    public boolean registrar(CredencialesDTO datos) {
        // Validacion: no se permiten usuarios repetidos
        if (usuarioRepository.existsByNombreUsuario(datos.getUsuario())) {
            return false;
        }
        Usuario nuevo = new Usuario();
        nuevo.setNombreUsuario(datos.getUsuario());
        // La contrasena se guarda protegida, nunca en texto plano
        nuevo.setContrasena(encriptador.encode(datos.getContrasena()));
        usuarioRepository.save(nuevo);
        return true;
    }

    /**
     * Verifica las credenciales de un usuario.
     * @return true si el usuario existe y la contrasena coincide
     */
    public boolean autenticar(CredencialesDTO datos) {
        return usuarioRepository.findByNombreUsuario(datos.getUsuario())
                // Compara la contrasena escrita con la protegida de la base de datos
                .map(u -> encriptador.matches(datos.getContrasena(), u.getContrasena()))
                // Si el usuario no existe, no autentica
                .orElse(false);
    }
}
