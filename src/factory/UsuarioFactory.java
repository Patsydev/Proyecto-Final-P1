package factory;

import model.Usuario;

// Clase fábrica para crear instancias de Usuario
public class UsuarioFactory {
    /**
     * Crea un Usuario a partir de campos simples.
     * Atención: confirmar el orden de parámetros esperado por el constructor de Usuario.
     */
    public static Usuario crear(String nombre, String apellido, String telefono, String email, String userName, String password) {
        return new Usuario(nombre, apellido, telefono, email, userName, password);
    }
}
