package factory;

import model.Producto;

public class ProductoFactory {

    // Crea un producto con id explícito
    public static Producto crear(int id, String nombre, String marca, String categoria, int precio, int stock) {
        return new Producto(id, nombre, marca, categoria, precio, stock);
    }

    // Sobrecarga para crear un producto sin id
    public static Producto crear(String nombre, String marca, String categoria, int precio, int stock) {
        return new Producto(0, nombre, marca, categoria, precio, stock);
    }
}
