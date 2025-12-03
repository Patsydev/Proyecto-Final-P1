package ui;

import model.Usuario;
import repository.UsuarioRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class UserManagement extends JFrame {

    // Panel principal, tabla y botones para acciones CRUD
    private JPanel panel;
    private JTable tabla;
    private DefaultTableModel model;
    private JButton btnActualizar, btnEliminar, btnCerrar, btnNuevo;

    // Nueva paleta de colores personalizada
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

    // Constructor: configura la ventana y añade componentes
    public UserManagement() {
        setTitle("Panel de Administración - Usuarios");
        setSize(1050, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        JLabel titleLabel = new JLabel("Gestión de Usuarios");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(CARD_BACKGROUND);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Administra los usuarios registrados en el sistema");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleContainer.add(titleLabel);
        titleContainer.add(Box.createVerticalStrut(5));
        titleContainer.add(subtitleLabel);

        headerPanel.add(titleContainer, BorderLayout.CENTER);

        // Panel central con tabla
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Tabla y modelo: columnas que muestran los datos del usuario
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID no es editable
            }
        };
        model.addColumn("ID");
        model.addColumn("Usuario");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Teléfono");
        model.addColumn("Correo");

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
        tabla.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(220);

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
        btnNuevo = createStyledButton("👤 Nuevo Usuario", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnNuevo);

        // Botón Actualizar
        btnActualizar = createStyledButton("💾 Guardar/Actualizar", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnActualizar);

        // Botón Eliminar
        btnEliminar = createStyledButton("🗑️ Eliminar Usuario", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnEliminar);

        // Botón Cerrar Sesión
        btnCerrar = createStyledButton("🔒 Cerrar Sesión", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnCerrar);

        // Botón Volver al Menú
        JButton btnVolver = createStyledButton("⬅ Volver al Menú", PRIMARY_COLOR, PRIMARY_DARK);
        buttonPanel.add(btnVolver);

        panel.add(buttonPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        // Cargar datos al inicio desde el repositorio
        cargarUsuarios();

        // Listeners: cerrar sesión y operaciones CRUD
        btnCerrar.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });

        // Volver al menú principal
        btnVolver.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnNuevo.addActionListener(e -> nuevoUsuario());
    }

    // Metodo auxiliar para crear botones estilizados
    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {

        // Configuración básica del botón
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(CARD_BACKGROUND);
        button.setBackground(bgColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());

        // Configurar fuente con emojis
        Font font = new Font("Segoe UI Emoji", Font.BOLD, 13);
        if (font.getFamily().equals("Dialog")) {
            // Si no está disponible intenta con estas
            font = new Font("Apple Color Emoji", Font.BOLD, 13);
            if (font.getFamily().equals("Dialog")) {
                font = new Font("Noto Color Emoji", Font.BOLD, 13);
            }
        }
        button.setFont(font);

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

    // Carga todos los usuarios desde el repositorio y los muestra en la tabla
    private void cargarUsuarios() {
        model.setRowCount(0);
        ArrayList<Usuario> lista = UsuarioRepository.getInstance().getAll();
        for (Usuario u : lista) {
            model.addRow(new Object[]{
                    u.getIdUser(),
                    u.getUserName(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getTelefono(),
                    u.getEmail()
            });
        }
    }

    // Elimina el usuario seleccionado en la tabla después de confirmar
    private void eliminarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un usuario para eliminar.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tabla.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar este usuario?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = UsuarioRepository.getInstance().eliminar(id);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Usuario eliminado exitosamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al eliminar usuario.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Actualiza el usuario seleccionado o crea uno nuevo si la fila tiene id = 0
    private void actualizarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un usuario para actualizar o crear.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Finaliza edición de celdas si está activo
        if (tabla.isEditing()) {
            tabla.getCellEditor().stopCellEditing();
        }

        int id = (int) tabla.getValueAt(fila, 0);

        // Si es un nuevo usuario (fila recién añadida con id = 0)
        if (id == 0) {
            Usuario nuevo = new Usuario(
                    String.valueOf(tabla.getValueAt(fila, 1)), // username
                    String.valueOf(tabla.getValueAt(fila, 2)), // nombre
                    String.valueOf(tabla.getValueAt(fila, 3)), // apellido
                    String.valueOf(tabla.getValueAt(fila, 4)), // telefono
                    String.valueOf(tabla.getValueAt(fila, 5)), // email
                    "1234" // contraseña temporal
            );

            boolean ok = UsuarioRepository.getInstance().registrar(nuevo);
            if (ok) {
                JOptionPane.showMessageDialog(this,
                        "Usuario registrado exitosamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al registrar usuario.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return; // salir porque ya se insertó
        }

        // Si es un usuario existente, tomar los valores modificados y actualizarlos
        String username = String.valueOf(tabla.getValueAt(fila, 1));
        String nombre = String.valueOf(tabla.getValueAt(fila, 2));
        String apellido = String.valueOf(tabla.getValueAt(fila, 3));
        String telefono = String.valueOf(tabla.getValueAt(fila, 4));
        String email = String.valueOf(tabla.getValueAt(fila, 5));

        // Recuperar usuario original para conservar la contraseña
        Usuario original = UsuarioRepository.getInstance().getById(id);
        if (original == null) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo recuperar el usuario original.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario actualizado = new Usuario(
                id,
                username,
                nombre,
                apellido,
                telefono,
                email,
                original.getPassword() // conservar password actual
        );

        boolean ok = UsuarioRepository.getInstance().actualizar(actualizado);
        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Usuario actualizado exitosamente.",
                    "Actualización exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar usuario.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Añade una fila vacía en la tabla para crear un nuevo usuario (id temporal = 0)
    private void nuevoUsuario() {
        model.addRow(new Object[]{0, "", "", "", "", ""});

        // Seleccionar la nueva fila y comenzar edición en la columna Username
        int fila = model.getRowCount() - 1;
        tabla.setRowSelectionInterval(fila, fila);

        tabla.editCellAt(fila, 1);
        tabla.requestFocus();
    }
}