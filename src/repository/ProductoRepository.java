package repository;

import db.DatabaseConnection;
import model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Repositorio para operaciones CRUD sobre la tabla productos
public class ProductoRepository {
    private static ProductoRepository instance;
    // Conexión compartida obtenida desde la clase DatabaseConnection
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    private ProductoRepository() {}

    // Obtiene la instancia única del repositorio
    public static ProductoRepository getInstance() {
        if (instance == null) {
            instance = new ProductoRepository();
        }
        return instance;
    }

    // Listar todos los productos desde la tabla productos
    public List<Producto> findAll() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT idProducto, NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto FROM productos";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("NombreProducto"),
                        rs.getString("MarcaProducto"),
                        rs.getString("CategoriaProducto"),
                        rs.getInt("PrecioProducto"),
                        rs.getInt("StockProducto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            // Envuelve la excepción SQL en RuntimeException para simplificar manejo en UI
            throw new RuntimeException("Error al listar productos", e);
        }
        return lista;
    }

    // Insertar producto (devuelve id generado o -1 si no se obtuvo key)
    public int create(Producto p) {
        String sql = "INSERT INTO productos (NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getMarcaProducto());
            ps.setString(3, p.getCategoriaProducto());
            ps.setInt(4, p.getPrecioProducto());
            ps.setInt(5, p.getStockProducto());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            // Reporta error al crear producto
            throw new RuntimeException("Error al crear producto", e);
        }
    }

    // Actualizar producto existente por id
    public void update(Producto p) {
        String sql = "UPDATE productos SET NombreProducto=?, MarcaProducto=?, CategoriaProducto=?, PrecioProducto=?, StockProducto=? WHERE idProducto=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getMarcaProducto());
            ps.setString(3, p.getCategoriaProducto());
            ps.setInt(4, p.getPrecioProducto());
            ps.setInt(5, p.getStockProducto());
            ps.setInt(6, p.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Envuelve y deja que la capa superior maneje la notificación al usuario
            throw new RuntimeException("Error al actualizar producto", e);
        }
    }

    // Eliminar producto por id
    public void delete(int idProducto) {
        String sql = "DELETE FROM productos WHERE idProducto=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto", e);
        }
    }
}
