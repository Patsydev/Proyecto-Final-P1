package ui;

import model.Producto;
import repository.ProductoRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProductFormDialog extends JDialog {

    private JTextField txtNombre, txtMarca, txtCategoria, txtPrecio, txtStock;
    private Producto producto; // null = nuevo
    private ProductoRepository repo;

    // Paleta de colores consistente
    private final Color BACKGROUND = new Color(255, 253, 238); // #FFFDEE
    private final Color CARD_BACKGROUND = new Color(254, 253, 238); // #FEFDEE
    private final Color PRIMARY_COLOR = new Color(126, 27, 39); // #7E1B27
    private final Color PRIMARY_DARK = new Color(100, 20, 30); // Versión más oscura
    private final Color SECONDARY_COLOR = new Color(240, 228, 214); // #F0E4D6
    private final Color TEXT_PRIMARY = new Color(126, 27, 39); // #7E1B27

    public ProductFormDialog(Frame owner, Producto producto) {
        // Constructor: inicializa repositorio, UI y carga datos si es edición
        super(owner, true);
        this.producto = producto;
        repo = ProductoRepository.getInstance();

        initUI();
        cargarDatosSiEsEdicion();
    }

    private void initUI() {
        setTitle(producto == null ? "Nuevo Producto" : "Editar Producto");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // Panel superior con título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(500, 90));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel(producto == null ? "📦 Nuevo Producto" : "✏️ Editar Producto");
        titleLabel.setFont(getEmojiFont(Font.BOLD, 26));
        titleLabel.setForeground(CARD_BACKGROUND);
        headerPanel.add(titleLabel);

        // Panel central con formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(BACKGROUND);
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        int yPos = 20;
        int labelHeight = 25;
        int fieldHeight = 45;
        int spacing = 85;

        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre del Producto");
        lblNombre.setBounds(40, yPos, 380, labelHeight);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(TEXT_PRIMARY);
        formPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(40, yPos + 30, 380, fieldHeight);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtNombre.setForeground(TEXT_PRIMARY);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtNombre.setBackground(CARD_BACKGROUND);
        formPanel.add(txtNombre);

        yPos += spacing;

        // Campo Marca
        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(40, yPos, 380, labelHeight);
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMarca.setForeground(TEXT_PRIMARY);
        formPanel.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(40, yPos + 30, 380, fieldHeight);
        txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtMarca.setForeground(TEXT_PRIMARY);
        txtMarca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtMarca.setBackground(CARD_BACKGROUND);
        formPanel.add(txtMarca);

        yPos += spacing;

        // Campo Categoría
        JLabel lblCategoria = new JLabel("Categoría");
        lblCategoria.setBounds(40, yPos, 380, labelHeight);
        lblCategoria.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCategoria.setForeground(TEXT_PRIMARY);
        formPanel.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(40, yPos + 30, 380, fieldHeight);
        txtCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtCategoria.setForeground(TEXT_PRIMARY);
        txtCategoria.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtCategoria.setBackground(CARD_BACKGROUND);
        formPanel.add(txtCategoria);

        yPos += spacing;

        // Campo Precio
        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(40, yPos, 380, labelHeight);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrecio.setForeground(TEXT_PRIMARY);
        formPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(40, yPos + 30, 380, fieldHeight);
        txtPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPrecio.setForeground(TEXT_PRIMARY);
        txtPrecio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPrecio.setBackground(CARD_BACKGROUND);
        formPanel.add(txtPrecio);

        yPos += spacing;

        // Campo Stock
        JLabel lblStock = new JLabel("Cantidad Disponible");
        lblStock.setBounds(40, yPos, 380, labelHeight);
        lblStock.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblStock.setForeground(TEXT_PRIMARY);
        formPanel.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(40, yPos + 30, 380, fieldHeight);
        txtStock.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtStock.setForeground(TEXT_PRIMARY);
        txtStock.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtStock.setBackground(CARD_BACKGROUND);
        formPanel.add(txtStock);

        yPos += spacing + 20;

        // Panel de botones
        // (Guardar siempre, Eliminar solo en edición, Cerrar para cancelar)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(40, yPos, 380, 60);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(BACKGROUND);

        // Botón Guardar
        JButton btnGuardar = createStyledButton("💾 Guardar", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnGuardar);

        // Botón Eliminar (solo si es edición)
        if (producto != null) {
            JButton btnEliminar = createStyledButton("🗑️ Eliminar", PRIMARY_COLOR, PRIMARY_DARK);
            buttonPanel.add(btnEliminar);

            // Evento de eliminación: confirma y borra desde repositorio
            btnEliminar.addActionListener(e -> {
                int confirmar = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro que desea eliminar este producto?\nEsta acción no se puede deshacer.",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirmar == JOptionPane.YES_OPTION) {
                    repo.delete(producto.getIdProducto());
                    JOptionPane.showMessageDialog(this,
                            "Producto eliminado exitosamente.",
                            "Eliminación exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            });
        }

        // Botón Cerrar
        JButton btnCerrar = createStyledButton("❌ Cerrar", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnCerrar);

        formPanel.add(buttonPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Eventos: cerrar dialogo y guardar producto
        btnCerrar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardar());
    }

    // Metodo para crear botones estilizados
    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(110, 40));
        button.setFont(getEmojiFont(Font.BOLD, 12));
        button.setForeground(CARD_BACKGROUND);
        button.setBackground(bgColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());

        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // Metodo auxiliar para obtener fuente que soporte emojis
    private Font getEmojiFont(int style, int size) {
        // Prueba varias familias de fuente para emojis y cae a Segoe UI
        Font font = new Font("Segoe UI Emoji", style, size);
        if (font.getFamily().equals("Dialog")) {
            font = new Font("Apple Color Emoji", style, size);
            if (font.getFamily().equals("Dialog")) {
                font = new Font("Noto Color Emoji", style, size);
                if (font.getFamily().equals("Dialog")) {
                    font = new Font("Segoe UI", style, size);
                }
            }
        }
        return font;
    }

    private void cargarDatosSiEsEdicion() {
        // Si se abrió para editar, llenar campos con los valores del producto
        if (producto != null) {
            txtNombre.setText(producto.getNombreProducto());
            txtMarca.setText(producto.getMarcaProducto());
            txtCategoria.setText(producto.getCategoriaProducto());
            txtPrecio.setText(String.valueOf(producto.getPrecioProducto()));
            txtStock.setText(String.valueOf(producto.getStockProducto()));
        }
    }

    private void guardar() {
        // Valida campos, convierte tipos y crea/actualiza el producto en repo
        try {
            String nombre = txtNombre.getText().trim();
            String marca = txtMarca.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String precioStr = txtPrecio.getText().trim();
            String stockStr = txtStock.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty() ||
                    precioStr.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Todos los campos son obligatorios.",
                        "Campos requeridos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int precio = Integer.parseInt(precioStr);
            int stock = Integer.parseInt(stockStr);

            if (precio <= 0 || stock < 0) {
                JOptionPane.showMessageDialog(this,
                        "El precio debe ser mayor a 0 y el stock no puede ser negativo.",
                        "Valores inválidos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (producto == null) {
                // Crear nuevo producto
                producto = new Producto(0, nombre, marca, categoria, precio, stock);
                repo.create(producto);
                JOptionPane.showMessageDialog(this,
                        "Producto registrado exitosamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Actualizar producto existente
                producto.setNombreProducto(nombre);
                producto.setMarcaProducto(marca);
                producto.setCategoriaProducto(categoria);
                producto.setPrecioProducto(precio);
                producto.setStockProducto(stock);
                repo.update(producto);
                JOptionPane.showMessageDialog(this,
                        "Producto actualizado exitosamente.",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El precio y el stock deben ser números válidos.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el producto: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

