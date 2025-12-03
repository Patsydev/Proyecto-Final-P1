package ui;

import model.Producto;
import repository.ProductoRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ProductManagement extends JFrame {

    private JPanel panel;
    private JTable tabla;
    private DefaultTableModel model;
    private ProductoRepository repo;
    private JButton btnNuevo, btnVolver;

    // Paleta de colores consistente
    private final Color BACKGROUND = new Color(255, 253, 238); // #FFFDEE
    private final Color CARD_BACKGROUND = new Color(254, 253, 238); // #FEFDEE
    private final Color PRIMARY_COLOR = new Color(126, 27, 39); // #7E1B27
    private final Color PRIMARY_DARK = new Color(100, 20, 30); // Versión más oscura
    private final Color SECONDARY_COLOR = new Color(240, 228, 214); // #F0E4D6
    private final Color SELECT_COLOR = new Color(200, 180, 170); // #C8B4AA
    private final Color TEXT_PRIMARY = new Color(126, 27, 39); // #7E1B27
    private final Color TABLE_HEADER = new Color(126, 27, 39); // #7E1B27
    private final Color TABLE_ROW_EVEN = new Color(254, 253, 238); // #FEFDEE
    private final Color TABLE_ROW_ODD = new Color(240, 228, 214); // #F0E4D6

    public ProductManagement() {
        // Constructor: prepara repositorio, UI y carga datos iniciales
        repo = ProductoRepository.getInstance();
        initUI();
        cargarProductos();
    }

    private void initUI() {
        setTitle("Panel de Administración - Productos");
        setSize(1050, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel principal con fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // Panel superior con título y descripción
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(1050, 100));
        headerPanel.setLayout(new BorderLayout());

        JPanel titleContainer = new JPanel();
        titleContainer.setBackground(PRIMARY_COLOR);
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        titleContainer.setBorder(new EmptyBorder(20, 40, 10, 40));

        JLabel titleLabel = new JLabel("Gestión de Productos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(CARD_BACKGROUND);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Administra el inventario de productos del sistema");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleContainer.add(titleLabel);
        titleContainer.add(Box.createVerticalStrut(5));
        titleContainer.add(subtitleLabel);

        headerPanel.add(titleContainer, BorderLayout.CENTER);

        // Panel central con tabla
        // (Tabla muestra lista de productos y permite ordenar/seleccionar)
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Tabla y modelo
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable directamente
            }
        };
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Marca");
        model.addColumn("Categoría");
        model.addColumn("Precio");
        model.addColumn("Stock");

        tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setForeground(TEXT_PRIMARY);
        tabla.setRowHeight(40);
        tabla.setShowGrid(true);
        tabla.setGridColor(PRIMARY_COLOR);
        tabla.setSelectionBackground(SELECT_COLOR);
        tabla.setSelectionForeground(TEXT_PRIMARY);
        tabla.setAutoCreateRowSorter(true);

        // Estilo del header de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(TABLE_HEADER);
        header.setForeground(CARD_BACKGROUND);
        header.setPreferredSize(new Dimension(header.getWidth(), 45));
        header.setBorder(BorderFactory.createEmptyBorder());

        // Renderer para alternar colores de filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setForeground(TEXT_PRIMARY);
                if (!isSelected) {
                    // Alterna color de fila para mejorar legibilidad
                    c.setBackground(row % 2 == 0 ? TABLE_ROW_EVEN : TABLE_ROW_ODD);
                }
                if (column == 0) {
                    setHorizontalAlignment(CENTER);
                    setFont(new Font("Segoe UI", Font.BOLD, 12));
                } else {
                    setHorizontalAlignment(LEFT);
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        };

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(120);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(40, 30, 930, 380);
        scroll.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 2));
        scroll.getViewport().setBackground(CARD_BACKGROUND);
        panel.add(scroll);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(40, 440, 930, 70);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND);

        // Botón Nuevo
        btnNuevo = createStyledButton("📦 Nuevo Producto", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnNuevo);

        // Botón Volver al Menú
        btnVolver = createStyledButton("⬅ Volver al Menú", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnVolver);

        panel.add(buttonPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        // Eventos
        // Doble clic en tabla para editar: abre diálogo con los datos de la fila
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                JTable source = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = source.rowAtPoint(p);

                if (e.getClickCount() == 2 && row != -1) {
                    abrirFormularioEditar(row);
                }
            }
        });

        // Botón Nuevo abre diálogo para crear nuevo producto
        btnNuevo.addActionListener(e -> abrirFormularioNuevo());

        // Botón Volver regresa al menú principal
        btnVolver.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });
    }

    // Metodo auxiliar para crear botones estilizados
    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(getEmojiFont(Font.BOLD, 13));
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

    // Método auxiliar para obtener fuente que soporte emojis
    private Font getEmojiFont(int style, int size) {
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

    private void cargarProductos() {
        // Carga todos los productos desde el repositorio y los muestra en la tabla
        model.setRowCount(0);
        List<Producto> lista = repo.findAll();
        for (Producto p : lista) {
            model.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombreProducto(),
                    p.getMarcaProducto(),
                    p.getCategoriaProducto(),
                    p.getPrecioProducto(),
                    p.getStockProducto()
            });
        }
    }

    private void abrirFormularioNuevo() {
        // Abre diálogo vacío para crear nuevo producto y recarga la tabla al cerrar
        ProductFormDialog dialog = new ProductFormDialog(this, null);
        dialog.setVisible(true);
        cargarProductos();
    }

    private void abrirFormularioEditar(int fila) {
        // Construye un objeto Producto desde la fila seleccionada y abre el diálogo de edición
        int id = Integer.parseInt(model.getValueAt(fila, 0).toString());
        String nombre = model.getValueAt(fila, 1).toString();
        String marca = model.getValueAt(fila, 2).toString();
        String categoria = model.getValueAt(fila, 3).toString();
        int precio = Integer.parseInt(model.getValueAt(fila, 4).toString());
        int stock = Integer.parseInt(model.getValueAt(fila, 5).toString());

        Producto p = new Producto(id, nombre, marca, categoria, precio, stock);

        ProductFormDialog dialog = new ProductFormDialog(this, p);
        dialog.setVisible(true);
        cargarProductos();
    }
}

