package service;

import dao.*;
import model.*;
import java.util.Optional;

/**
 * AuthenticationService
 * Service untuk handle autentikasi (login/logout)
 * Menerapkan ENCAPSULATION untuk business logic
 * 
 * @author EduShare Team
 */
public class AuthenticationService {
    
    private final PenggunaDAO penggunaDAO;
    private final AdminDAO adminDAO;
    private final DonaturDAO donaturDAO;
    private final PihakSekolahDAO pihakSekolahDAO;
    
    // Session management (simple implementation)
    private static Pengguna currentUser = null;
    
    public AuthenticationService() {
        this.penggunaDAO = new PenggunaDAOImpl();
        this.adminDAO = new AdminDAOImpl();
        this.donaturDAO = new DonaturDAOImpl();
        this.pihakSekolahDAO = new PihakSekolahDAOImpl();
    }
    
    /**
     * POLYMORPHISM: Login method yang handle berbagai tipe user
     */
    public LoginResult login(String email, String password) {
        // Validasi input
        if (email == null || email.trim().isEmpty()) {
            return new LoginResult(false, "Email tidak boleh kosong", null);
        }
        if (password == null || password.trim().isEmpty()) {
            return new LoginResult(false, "Password tidak boleh kosong", null);
        }
        
        try {
            // Coba login sebagai Admin
            Optional<Admin> admin = adminDAO.authenticate(email, password);
            if (admin.isPresent()) {
                currentUser = admin.get();
                return new LoginResult(true, "Login berhasil sebagai Admin", admin.get());
            }
            
            // Coba login sebagai Donatur
            Optional<Donatur> donatur = donaturDAO.authenticate(email, password);
            if (donatur.isPresent()) {
                currentUser = donatur.get();
                return new LoginResult(true, "Login berhasil sebagai Donatur", donatur.get());
            }
            
            // Coba login sebagai Pihak Sekolah
            Optional<PihakSekolah> pihakSekolah = pihakSekolahDAO.authenticate(email, password);
            if (pihakSekolah.isPresent()) {
                currentUser = pihakSekolah.get();
                return new LoginResult(true, "Login berhasil sebagai Pihak Sekolah", pihakSekolah.get());
            }
            
            // Jika semua gagal
            return new LoginResult(false, "Email atau password salah", null);
            
        } catch (Exception e) {
            return new LoginResult(false, "Error: " + e.getMessage(), null);
        }
    }
    
    /**
     * Logout user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Get current logged in user
     */
    public static Pengguna getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Check if current user is Admin
     */
    public boolean isAdmin() {
        return currentUser instanceof Admin;
    }
    
    /**
     * Check if current user is Donatur
     */
    public boolean isDonatur() {
        return currentUser instanceof Donatur;
    }
    
    /**
     * Check if current user is Pihak Sekolah
     */
    public boolean isPihakSekolah() {
        return currentUser instanceof PihakSekolah;
    }
    
    /**
     * Get role of current user
     */
    public String getCurrentUserRole() {
        if (currentUser == null) return null;
        return currentUser.getRole();
    }
    
    /**
     * Inner class untuk Login Result
     */
    public static class LoginResult {
        private final boolean success;
        private final String message;
        private final Pengguna user;
        
        public LoginResult(boolean success, String message, Pengguna user) {
            this.success = success;
            this.message = message;
            this.user = user;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
        
        public Pengguna getUser() {
            return user;
        }
    }
    
    /**
     * Clean up resources
     */
    public void close() {
        if (penggunaDAO instanceof PenggunaDAOImpl) {
            ((PenggunaDAOImpl) penggunaDAO).close();
        }
        if (adminDAO instanceof AdminDAOImpl) {
            ((AdminDAOImpl) adminDAO).close();
        }
        if (donaturDAO instanceof DonaturDAOImpl) {
            ((DonaturDAOImpl) donaturDAO).close();
        }
        if (pihakSekolahDAO instanceof PihakSekolahDAOImpl) {
            ((PihakSekolahDAOImpl) pihakSekolahDAO).close();
        }
    }
}
