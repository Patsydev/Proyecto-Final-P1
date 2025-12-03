package model;

// Clase que representa un producto en el dominio de la aplicación
public class Producto {
    // Identificador único del producto
    private int idProducto;
    // Nombre legible del producto
    private String nombreProducto;
    // Marca del producto
    private String marcaProducto;
    // Categoría a la que pertenece el producto
    private String categoriaProducto;
    // Precio (entero) del producto en la unidad definida por la app
    private int precioProducto;
    // Cantidad en stock disponible
    private int stockProducto;

    // Constructor completo para crear instancias con todos los datos
    public Producto(int idProducto, String nombreProducto, String marcaProducto,
                    String categoriaProducto, int precioProducto, int stockProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.categoriaProducto = categoriaProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }

    // getters y setters (acceso y mutación de campos)
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getMarcaProducto() { return marcaProducto; }
    public void setMarcaProducto(String marcaProducto) { this.marcaProducto = marcaProducto; }

    public String getCategoriaProducto() { return categoriaProducto; }
    public void setCategoriaProducto(String categoriaProducto) { this.categoriaProducto = categoriaProducto; }

    public int getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(int precioProducto) { this.precioProducto = precioProducto; }

    public int getStockProducto() { return stockProducto; }
    public void setStockProducto(int stockProducto) { this.stockProducto = stockProducto; }
}
