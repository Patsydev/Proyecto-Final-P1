package ui;

import model.Usuario;
import repository.UsuarioRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    // Panel principal y componentes de entrada
    private JPanel panel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnEntrar;
    private JButton btnRegistrarse;

    // Nueva paleta de colores personalizada
    private final Color BACKGROUND = new Color(255, 253, 238); // #FFFDEE
    private final Color CARD_BACKGROUND = new Color(254, 253, 238); // #FEFDEE
    private final Color PRIMARY_COLOR = new Color(126, 27, 39); // #7E1B27
    private final Color PRIMARY_DARK = new Color(100, 20, 30); // Versión más oscura
    private final Color SECONDARY_COLOR = new Color(240, 228, 214); // #F0E4D6
    private final Color SELECT_COLOR = new Color(200, 180, 170); // #C8B4AA //
    private final Color TEXT_PRIMARY = new Color(126, 27, 39); // #7E1B27
    private final Color TEXT_SECONDARY = new Color(126, 27, 39, 150); // #7E1B27 con transparencia

    // Constructor: configura la ventana y agrega componentes
    public LoginForm() {
        setTitle("Gestión de Usuarios - Login");
        setSize(450, 550);
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
        headerPanel.setPreferredSize(new Dimension(450, 90));
        headerPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("👋 Bienvenido");
        titleLabel.setFont(getEmojiFont(Font.BOLD, 32));
        titleLabel.setForeground(CARD_BACKGROUND);
        headerPanel.add(titleLabel);

        // Panel central con formulario
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));

        // Etiqueta y campo para el username
        JLabel lblU = new JLabel("👤 Usuario");
        lblU.setBounds(50, 30, 320, 25);
        lblU.setFont(getEmojiFont(Font.BOLD, 15));
        lblU.setForeground(TEXT_PRIMARY);
        panel.add(lblU);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 60, 320, 45);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsername.setForeground(TEXT_PRIMARY);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtUsername.setBackground(CARD_BACKGROUND);
        panel.add(txtUsername);

        // Etiqueta y campo para la contraseña
        JLabel lblP = new JLabel("🔒 Contraseña");
        lblP.setBounds(50, 130, 320, 25);
        lblP.setFont(getEmojiFont(Font.BOLD, 15));
        lblP.setForeground(TEXT_PRIMARY);
        panel.add(lblP);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 160, 320, 45);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPassword.setForeground(TEXT_PRIMARY);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPassword.setBackground(CARD_BACKGROUND);
        panel.add(txtPassword);

        // Botón para iniciar sesión
        btnEntrar = new JButton("🔓 INICIAR SESIÓN");
        btnEntrar.setBounds(50, 240, 320, 50);
        btnEntrar.setFont(getEmojiFont(Font.BOLD, 14));
        btnEntrar.setForeground(CARD_BACKGROUND);
        btnEntrar.setBackground(PRIMARY_COLOR);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.setBorder(BorderFactory.createEmptyBorder());
        panel.add(btnEntrar);

        // Separador con texto
        JLabel lblSeparador = new JLabel("¿No tienes cuenta?");
        lblSeparador.setBounds(50, 310, 320, 20);
        lblSeparador.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSeparador.setForeground(TEXT_SECONDARY);
        lblSeparador.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblSeparador);

        // Botón para abrir el formulario de registro
        btnRegistrarse = new JButton("✨ CREAR CUENTA");
        btnRegistrarse.setBounds(50, 340, 320, 50);
        btnRegistrarse.setFont(getEmojiFont(Font.BOLD, 14));
        btnRegistrarse.setForeground(PRIMARY_COLOR);
        btnRegistrarse.setBackground(CARD_BACKGROUND);
        btnRegistrarse.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true));
        btnRegistrarse.setFocusPainted(false);
        btnRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnRegistrarse);

        // Efectos hover para botones
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntrar.setBackground(PRIMARY_DARK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntrar.setBackground(PRIMARY_COLOR);
            }
        });

        btnRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarse.setBackground(SECONDARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarse.setBackground(CARD_BACKGROUND);
            }
        });

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);

        // ACCIÓN LOGIN: al presionar "Entrar" llama al metodo login()
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // ABRIR REGISTRO: cierra esta ventana y abre RegisterForm
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterForm().setVisible(true);
            }
        });
    }

    // Metodo auxiliar para obtener fuente que soporte emojis
    private Font getEmojiFont(int style, int size) {
        // Intentar con diferentes fuentes que soporten emojis
        Font font = new Font("Segoe UI Emoji", style, size);

        // Verificar si la fuente está disponible
        if (font.getFamily().equals("Dialog")) {
            // Si Segoe UI Emoji no está disponible, intentar con alternativas
            font = new Font("Apple Color Emoji", style, size);
            if (font.getFamily().equals("Dialog")) {
                font = new Font("Noto Color Emoji", style, size);
                if (font.getFamily().equals("Dialog")) {
                    // Si ninguna fuente de emojis está disponible, usar Segoe UI normal
                    font = new Font("Segoe UI", style, size);
                }
            }
        }
        return font;
    }

    // Metodo que valida campos y consulta el repositorio para autenticar
    private void login() {
        String username = txtUsername.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());

        // Validación simple: no permitir valores vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar username y password, si no está registrado debe registrarse.",
                    "Campos requeridos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llamada al repositorio para verificar credenciales
        Usuario user = UsuarioRepository.getInstance().login(username, password);

        // Si no hay usuario, mostrar error; si existe, abrir panel de gestión
        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Bienvenida " + user.getNombre(),
                    "Acceso concedido",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MainMenu().setVisible(true); // abrir mainmenu
        }
    }
}