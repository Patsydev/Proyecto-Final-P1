package ui;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Punto de entrada: lanzar la UI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
