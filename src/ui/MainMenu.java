package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    // Paleta de colores consistente con las otras clases
    private final Color BACKGROUND = new Color(255, 253, 238); // #FFFDEE
    private final Color CARD_BACKGROUND = new Color(254, 253, 238); // #FEFDEE
    private final Color PRIMARY_COLOR = new Color(126, 27, 39); // #7E1B27
    private final Color PRIMARY_DARK = new Color(100, 20, 30); // Versión más oscura
    private final Color SECONDARY_COLOR = new Color(240, 228, 214); // #F0E4D6
    private final Color TEXT_PRIMARY = new Color(126, 27, 39); // #7E1B27

    public MainMenu() {
        // Constructor: configura ventana, compone la interfaz y registra eventos
        setTitle("Menú Principal - Sistema de Gestión");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal con fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // Panel superior con título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(700, 120));
        headerPanel.setLayout(new BorderLayout());

        JPanel titleContainer = new JPanel();
        titleContainer.setBackground(PRIMARY_COLOR);
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        titleContainer.setBorder(new EmptyBorder(30, 40, 20, 40));

        JLabel titleLabel = new JLabel("Menú Principal");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(CARD_BACKGROUND);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Selecciona el módulo que deseas gestionar");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitleLabel.setForeground(SECONDARY_COLOR);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleContainer.add(titleLabel);
        titleContainer.add(Box.createVerticalStrut(8));
        titleContainer.add(subtitleLabel);

        headerPanel.add(titleContainer, BorderLayout.CENTER);

        // Panel central con botones
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 0, 12, 0);

        // Botón Gestión de Usuarios
        JButton btnUsuarios = createStyledButton("👤 Gestión de Usuarios", PRIMARY_COLOR, PRIMARY_DARK);
        gbc.gridy = 0;
        contentPanel.add(btnUsuarios, gbc);

        // Botón Gestión de Productos
        JButton btnProductos = createStyledButton("📦 Gestión de Productos", PRIMARY_COLOR, PRIMARY_DARK);
        gbc.gridy = 1;
        contentPanel.add(btnProductos, gbc);

        // Separador
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 12, 0);
        JSeparator separator = new JSeparator();
        separator.setForeground(PRIMARY_COLOR);
        contentPanel.add(separator, gbc);

        // Botón Cerrar Sesión
        JButton btnCerrar = createStyledButton("🔒 Cerrar Sesión", PRIMARY_COLOR, PRIMARY_DARK);
        gbc.gridy = 3;
        gbc.insets = new Insets(12, 0, 12, 0);
        contentPanel.add(btnCerrar, gbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Eventos de los botones: abren las ventanas correspondientes
        btnUsuarios.addActionListener(e -> {
            dispose();
            new UserManagement().setVisible(true);
        });

        btnProductos.addActionListener(e -> {
            dispose();
            new ProductManagement().setVisible(true);
        });

        btnCerrar.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    // Metodo para crear botones estilizados
    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(400, 65));
        button.setFont(getEmojiFont(Font.BOLD, 20));
        button.setForeground(CARD_BACKGROUND);
        button.setBackground(bgColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());

        // Efecto hover: cambia color de fondo cuando el ratón entra/sale
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

    // Metodo para obtener fuente que soporte emojis
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
}