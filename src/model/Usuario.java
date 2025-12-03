//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

// Clase que representa un usuario del sistema
public class Usuario {
    // Identificador único del usuario (PK)
    private int idUser;
    // Nombre de usuario para autenticación
    private String userName;
    // Nombre propio
    private String nombre;
    // Apellido
    private String apellido;
    // Teléfono de contacto
    private String telefono;
    // Correo electrónico
    private String email;
    // Contraseña (en producción almacenar hashed)
    private String password;

    // Constructor completo con id (para cargar desde BD)
    public Usuario(int idUser, String userName, String nombre, String apellido, String telefono, String email, String password) {
        this.idUser = idUser;
        this.userName = userName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    // Constructor para nuevos usuarios (sin id)
    public Usuario(String userName, String nombre, String apellido, String telefono, String email, String password) {
        this(0, userName, nombre, apellido, telefono, email, password);
    }

    // Getters: devuelven los valores de los campos
    public int getIdUser() {
        return this.idUser;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    // Setters: permiten modificar los campos del usuario
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
