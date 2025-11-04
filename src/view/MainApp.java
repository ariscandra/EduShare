package view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.auth.LoginForm;

public class MainApp {
    public static void main(String[] args) {
        try {
            // Biarkan tampilannya pakai tema modern
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            // Jalankan frame login sebagai tampilan pertama
            LoginForm loginFrame = new LoginForm();
            loginFrame.setVisible(true);
        });
    }
}
