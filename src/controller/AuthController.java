package controller;

import model.Pengguna;
import service.AuthenticationService;
import service.AuthenticationService.LoginResult;

import javax.swing.JOptionPane;

/**
 * AuthController
 * -----------------------
 * Mengatur login, logout, dan register (jika dibutuhkan) 
 * dengan menggunakan AuthenticationService.
 * 
 * - login() sekarang menyesuaikan return type LoginResult dari AuthenticationService.
 * - menampilkan pesan GUI (JOptionPane) sesuai hasil login.
 * - mengarahkan user sesuai dengan peran (Admin, Donatur, Sekolah).
 * 
 * @author Ahmad Dani
 */
public class AuthController {
    
    private final AuthenticationService authService;
    
    public AuthController() {
        this.authService = new AuthenticationService();
    }

    /**
     * Proses login pengguna (Admin, Donatur, atau Pihak Sekolah)
     */
    public Pengguna login(String email, String password) {
        try {
            // Validasi input
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            // Panggil service untuk autentikasi
            LoginResult result = authService.login(email, password);

            if (result.isSuccess()) {
                Pengguna user = result.getUser();
                JOptionPane.showMessageDialog(
                        null, 
                        result.getMessage(), 
                        "Login Berhasil", 
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Simpan session (opsional)
                System.out.println("✅ Login sukses: " + user.getNama() + " (" + user.getRole() + ")");

                return user; // dikembalikan ke view untuk redirect dashboard
            } else {
                JOptionPane.showMessageDialog(
                        null, 
                        result.getMessage(), 
                        "Login Gagal", 
                        JOptionPane.ERROR_MESSAGE
                );
                return null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Terjadi kesalahan saat login: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    /**
     * Logout pengguna saat ini
     */
    public void logout() {
        if (authService.isLoggedIn()) {
            Pengguna user = AuthenticationService.getCurrentUser();
            JOptionPane.showMessageDialog(
                    null, 
                    "Sampai jumpa, " + user.getNama() + "!", 
                    "Logout Berhasil", 
                    JOptionPane.INFORMATION_MESSAGE
            );
            authService.logout();
        } else {
            JOptionPane.showMessageDialog(
                    null, 
                    "Tidak ada pengguna yang sedang login.", 
                    "Informasi", 
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Mendapatkan pengguna aktif (jika ada)
     */
    public Pengguna getPenggunaAktif() {
        return AuthenticationService.getCurrentUser();
    }
}
