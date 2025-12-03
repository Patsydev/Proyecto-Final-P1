package ui;

import factory.UsuarioFactory;
import model.Usuario;
import repository.UsuarioRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterForm extends JFrame {
    // Panel y campos del formulario de registro
    private JPanel panel;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtUserName;
    private JPasswordField txtPass;
    private JPasswordField txtPass2;
    private JButton btnRegistrar;

    // Nueva paleta de colores personalizada
    private final Color BACKGROUND = new Color(255, 253, 238); // #FFFDEE
    private final Color CARD_BACKGROUND = new Color(254, 253, 238); // #FEFDEE
    private final Color PRIMARY_COLOR = new Color(126, 27, 39); // #7E1B27
    private final Color PRIMARY_DARK = new Color(100, 20, 30); // Versión más oscura
    private final Color SECONDARY_COLOR = new Color(240, 228, 214); // #F0E4D6
    private final Color TEXT_PRIMARY = new Color(126, 27, 39); // #7E1B27
    private final Color TEXT_SECONDARY = new Color(126, 27, 39, 150); // #7E1B27 con transparencia

    // Constructor: crea la interfaz de registro
    public RegisterForm() {
        setTitle("Crear Nueva Cuenta");
        setSize(500, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal con fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // Panel superior con título
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(500, 75));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("📝 Registro de Usuario");
        titleLabel.setFont(getEmojiFont(Font.BOLD, 28));
        titleLabel.setForeground(CARD_BACKGROUND);
        headerPanel.add(titleLabel);

        // Panel central con formulario
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 50, 30, 50));

        int yPosition = 20;
        int labelHeight = 25;
        int fieldHeight = 45;
        int spacing = 85;

        // Campo Nombre
        JLabel lblNombre = new JLabel("👤 Nombre");
        lblNombre.setBounds(50, yPosition, 370, labelHeight);
        lblNombre.setFont(getEmojiFont(Font.BOLD, 15));
        lblNombre.setForeground(TEXT_PRIMARY);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNombre.setForeground(TEXT_PRIMARY);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtNombre.setBackground(CARD_BACKGROUND);
        panel.add(txtNombre);

        yPosition += spacing;

        // Campo Apellido
        JLabel lblApellido = new JLabel("👥 Apellido");
        lblApellido.setBounds(50, yPosition, 370, labelHeight);
        lblApellido.setFont(getEmojiFont(Font.BOLD, 15));
        lblApellido.setForeground(TEXT_PRIMARY);
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtApellido.setForeground(TEXT_PRIMARY);
        txtApellido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtApellido.setBackground(CARD_BACKGROUND);
        panel.add(txtApellido);

        yPosition += spacing;

        // Campo Telefono
        JLabel lblTel = new JLabel("📞 Teléfono");
        lblTel.setBounds(50, yPosition, 370, labelHeight);
        lblTel.setFont(getEmojiFont(Font.BOLD, 15));
        lblTel.setForeground(TEXT_PRIMARY);
        panel.add(lblTel);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtTelefono.setForeground(TEXT_PRIMARY);
        txtTelefono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtTelefono.setBackground(CARD_BACKGROUND);
        panel.add(txtTelefono);

        yPosition += spacing;

        // Campo Email
        JLabel lblEmail = new JLabel("📧 Correo Electrónico");
        lblEmail.setBounds(50, yPosition, 370, labelHeight);
        lblEmail.setFont(getEmojiFont(Font.BOLD, 15));
        lblEmail.setForeground(TEXT_PRIMARY);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.setForeground(TEXT_PRIMARY);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtEmail.setBackground(CARD_BACKGROUND);
        panel.add(txtEmail);

        yPosition += spacing;

        // Campo Username
        JLabel lblUsuario = new JLabel("🆔 Nombre de Usuario");
        lblUsuario.setBounds(50, yPosition, 370, labelHeight);
        lblUsuario.setFont(getEmojiFont(Font.BOLD, 15));
        lblUsuario.setForeground(TEXT_PRIMARY);
        panel.add(lblUsuario);

        txtUserName = new JTextField();
        txtUserName.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtUserName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUserName.setForeground(TEXT_PRIMARY);
        txtUserName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtUserName.setBackground(CARD_BACKGROUND);
        panel.add(txtUserName);

        yPosition += spacing;

        // Campo Password
        JLabel lblPass = new JLabel("🔒 Contraseña");
        lblPass.setBounds(50, yPosition, 370, labelHeight);
        lblPass.setFont(getEmojiFont(Font.BOLD, 15));
        lblPass.setForeground(TEXT_PRIMARY);
        panel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setForeground(TEXT_PRIMARY);
        txtPass.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPass.setBackground(CARD_BACKGROUND);
        panel.add(txtPass);

        yPosition += spacing;

        // Campo de confirmar Password
        JLabel lblPass2 = new JLabel("✅ Confirmar Contraseña");
        lblPass2.setBounds(50, yPosition, 370, labelHeight);
        lblPass2.setFont(getEmojiFont(Font.BOLD, 15));
        lblPass2.setForeground(TEXT_PRIMARY);
        panel.add(lblPass2);

        txtPass2 = new JPasswordField();
        txtPass2.setBounds(50, yPosition + 30, 370, fieldHeight);
        txtPass2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass2.setForeground(TEXT_PRIMARY);
        txtPass2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPass2.setBackground(CARD_BACKGROUND);
        panel.add(txtPass2);

        yPosition += spacing + 10;

        // Botón Registrar
        btnRegistrar = new JButton("✨ CREAR CUENTA");
        btnRegistrar.setBounds(50, yPosition, 370, 50);
        btnRegistrar.setFont(getEmojiFont(Font.BOLD, 15));
        btnRegistrar.setForeground(CARD_BACKGROUND);
        btnRegistrar.setBackground(PRIMARY_COLOR);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder());
        panel.add(btnRegistrar);

        // Efecto hover para botón registrar
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(PRIMARY_DARK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(PRIMARY_COLOR);
            }
        });

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        // Al pulsar registrar, ejecutar la lógica de registro
        btnRegistrar.addActionListener(e -> registrar());
    }

    // Metodo auxiliar para obtener fuente que soporte emojis
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

    // Metodo que valida los campos individualmente, crea el usuario y lo registra
    private void registrar() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El campo de nombre es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
        if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El campo de apellido es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtApellido.requestFocus();
            return;
        }
        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El campo de teléfono es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El campo de correo electrónico es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;
        }
        if (txtUserName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El campo de nombre de usuario es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtUserName.requestFocus();
            return;
        }
        if (txtPass.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this,
                    "El campo de contraseña es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return;
        }
        if (txtPass2.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this,
                    "El campo de confirmar contraseña es obligatorio.",
                    "Campo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            txtPass2.requestFocus();
            return;
        }

        // Verificar que las contraseñas coincidan
        String pass1 = String.valueOf(txtPass.getPassword());
        String pass2 = String.valueOf(txtPass2.getPassword());
        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(this,
                    "Las contraseñas no coinciden.",
                    "Error de contraseña",
                    JOptionPane.ERROR_MESSAGE);
            txtPass.requestFocus();
            return;
        }

        // Crear usuario con la fábrica y registrar en el repositorio
        Usuario u = UsuarioFactory.crear(
                txtUserName.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                pass1
        );

        boolean ok = UsuarioRepository.getInstance().registrar(u);

        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Usuario registrado exitosamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginForm().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar usuario.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
