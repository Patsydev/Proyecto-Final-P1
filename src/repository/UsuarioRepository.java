package repository;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Usuario;

// Repositorio responsable de las operaciones CRUD sobre la tabla 'usuarios'
public class UsuarioRepository {
    private static UsuarioRepository instance;
    // Conexión compartida obtenida del Singleton
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    private UsuarioRepository() {
    }

    // Devuelve la instancia Singleton del repositorio
    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }

        return instance;
    }

    // Inserta un nuevo usuario en la base de datos (PreparedStatement para evitar inyección)
    public boolean registrar(Usuario u) {
        try {
            String sql = "INSERT INTO usuarios (UserName, Nombre, Apellido, Telefono, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Autenticación simple por userName y password (en producción usar hashing)
    public Usuario login(String userName, String password) {
        try {
            String sql = "SELECT * FROM usuarios WHERE UserName=? AND Password=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("idUser"), rs.getString("UserName"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Telefono"), rs.getString("Email"), rs.getString("Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Recupera todos los usuarios
    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> lista = new ArrayList();

        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");

            while(rs.next()) {
                lista.add(new Usuario(rs.getInt("idUser"), rs.getString("UserName"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Telefono"), rs.getString("Email"), rs.getString("Password")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Actualiza los datos de un usuario existente
    public boolean actualizar(Usuario u) {
        try {
            String sql = "UPDATE usuarios SET UserName=?, Nombre=?, Apellido=?, Telefono=?, Email=?, Password=? WHERE idUser=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getIdUser());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Elimina un usuario por id
    public boolean eliminar(int idUser) {
        try {
            String sql = "DELETE FROM usuarios WHERE idUser=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Recupera un usuario por su id
    public Usuario getById(int id) {
        try {
            String sql = "SELECT * FROM usuarios WHERE idUser=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("idUser"), rs.getString("UserName"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Telefono"), rs.getString("Email"), rs.getString("Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
