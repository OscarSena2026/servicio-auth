package co.edu.sena.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa la tabla "usuarios" de la base de datos.
 * Cada objeto Usuario es una fila de la tabla.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Identificador unico, se genera automaticamente (autoincremental)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de usuario: obligatorio y no se puede repetir
    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    // Contrasena protegida con BCrypt (nunca se guarda en texto plano)
    @Column(nullable = false)
    private String contrasena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
