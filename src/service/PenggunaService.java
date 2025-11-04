package service;

import dao.*;
import model.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * PenggunaService
 * Service untuk handle business logic pengguna
 * Termasuk registrasi, update profile, dll
 * 
 * @author EduShare Team
 */
public class PenggunaService {
    
    private final PenggunaDAO penggunaDAO;
    private final AdminDAO adminDAO;
    private final DonaturDAO donaturDAO;
    private final PihakSekolahDAO pihakSekolahDAO;
    private final SekolahDAO sekolahDAO;
    
    // Regex untuk validasi email
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    public PenggunaService() {
        this.penggunaDAO = new PenggunaDAOImpl();
        this.adminDAO = new AdminDAOImpl();
        this.donaturDAO = new DonaturDAOImpl();
        this.pihakSekolahDAO = new PihakSekolahDAOImpl();
        this.sekolahDAO = new SekolahDAOImpl();
    }
    
    /**
     * BUSINESS LOGIC: Registrasi Donatur baru
     */
    public RegistrationResult registerDonatur(String nama, String email, String password, 
                                               String confirmPassword, String noHp, String alamat) {
        // Validasi input
        ValidationResult validation = validateRegistration(nama, email, password, confirmPassword, noHp);
        if (!validation.isValid()) {
            return new RegistrationResult(false, validation.getMessage());
        }
        
        try {
            // Cek apakah email sudah terdaftar
            if (penggunaDAO.isEmailExists(email)) {
                return new RegistrationResult(false, "Email sudah terdaftar");
            }
            
            // Buat Donatur baru
            Donatur donatur = new Donatur();
            donatur.setNama(nama);
            donatur.setEmail(email);
            donatur.setPassword(password); // TODO: Hash password di production
            donatur.setNoHp(noHp);
            donatur.setAlamat(alamat);
            donatur.setSekolah(null);
            
            // Simpan ke database
            donaturDAO.save(donatur);
            
            return new RegistrationResult(true, "Registrasi berhasil!");
            
        } catch (Exception e) {
            return new RegistrationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * BUSINESS LOGIC: Registrasi Pihak Sekolah baru
     */
    public RegistrationResult registerPihakSekolah(String nama, String email, String password,
                                                    String confirmPassword, String noHp, String alamat,
                                                    Integer idSekolah, String jabatan) {
        // Validasi input
        ValidationResult validation = validateRegistration(nama, email, password, confirmPassword, noHp);
        if (!validation.isValid()) {
            return new RegistrationResult(false, validation.getMessage());
        }
        
        // Validasi sekolah
        if (idSekolah == null) {
            return new RegistrationResult(false, "Sekolah harus dipilih");
        }
        
        if (jabatan == null || jabatan.trim().isEmpty()) {
            return new RegistrationResult(false, "Jabatan harus diisi");
        }
        
        try {
            // Cek apakah email sudah terdaftar
            if (penggunaDAO.isEmailExists(email)) {
                return new RegistrationResult(false, "Email sudah terdaftar");
            }
            
            // Cek apakah sekolah exists
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isEmpty()) {
                return new RegistrationResult(false, "Sekolah tidak ditemukan");
            }
            
            // Buat Pihak Sekolah baru
            PihakSekolah pihakSekolah = new PihakSekolah();
            pihakSekolah.setNama(nama);
            pihakSekolah.setEmail(email);
            pihakSekolah.setPassword(password); // TODO: Hash password
            pihakSekolah.setNoHp(noHp);
            pihakSekolah.setAlamat(alamat);
            pihakSekolah.setSekolah(sekolahOpt.get());
            pihakSekolah.setJabatan(jabatan);
            
            // Simpan ke database
            pihakSekolahDAO.save(pihakSekolah);
            
            return new RegistrationResult(true, "Registrasi berhasil!");
            
        } catch (Exception e) {
            return new RegistrationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * VALIDATION: Validasi data registrasi
     */
    private ValidationResult validateRegistration(String nama, String email, String password,
                                                   String confirmPassword, String noHp) {
        // Validasi nama
        if (nama == null || nama.trim().isEmpty()) {
            return new ValidationResult(false, "Nama tidak boleh kosong");
        }
        if (nama.length() < 3) {
            return new ValidationResult(false, "Nama minimal 3 karakter");
        }
        
        // Validasi email
        if (email == null || email.trim().isEmpty()) {
            return new ValidationResult(false, "Email tidak boleh kosong");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return new ValidationResult(false, "Format email tidak valid");
        }
        
        // Validasi password
        if (password == null || password.trim().isEmpty()) {
            return new ValidationResult(false, "Password tidak boleh kosong");
        }
        if (password.length() < 6) {
            return new ValidationResult(false, "Password minimal 6 karakter");
        }
        
        // Validasi confirm password
        if (!password.equals(confirmPassword)) {
            return new ValidationResult(false, "Password dan konfirmasi password tidak sama");
        }
        
        // Validasi no HP
        if (noHp == null || noHp.trim().isEmpty()) {
            return new ValidationResult(false, "No HP tidak boleh kosong");
        }
        
        return new ValidationResult(true, "Validasi berhasil");
    }
    
    /**
     * Update profil pengguna
     */
    public UpdateResult updateProfil(Integer idPengguna, String nama, String noHp, String alamat) {
        // Validasi input
        if (nama == null || nama.trim().isEmpty()) {
            return new UpdateResult(false, "Nama tidak boleh kosong");
        }
        if (nama.length() < 3) {
            return new UpdateResult(false, "Nama minimal 3 karakter");
        }
        
        try {
            Optional<Pengguna> penggunaOpt = penggunaDAO.findById(idPengguna);
            if (penggunaOpt.isEmpty()) {
                return new UpdateResult(false, "Pengguna tidak ditemukan");
            }
            
            Pengguna pengguna = penggunaOpt.get();
            pengguna.setNama(nama);
            pengguna.setNoHp(noHp);
            pengguna.setAlamat(alamat);
            
            penggunaDAO.updateProfil(pengguna);
            
            return new UpdateResult(true, "Profil berhasil diupdate");
            
        } catch (Exception e) {
            return new UpdateResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Ganti password
     */
    public UpdateResult gantiPassword(Integer idPengguna, String passwordLama, 
                                       String passwordBaru, String confirmPasswordBaru) {
        // Validasi input
        if (passwordBaru == null || passwordBaru.trim().isEmpty()) {
            return new UpdateResult(false, "Password baru tidak boleh kosong");
        }
        if (passwordBaru.length() < 6) {
            return new UpdateResult(false, "Password minimal 6 karakter");
        }
        if (!passwordBaru.equals(confirmPasswordBaru)) {
            return new UpdateResult(false, "Password baru dan konfirmasi tidak sama");
        }
        
        try {
            Optional<Pengguna> penggunaOpt = penggunaDAO.findById(idPengguna);
            if (penggunaOpt.isEmpty()) {
                return new UpdateResult(false, "Pengguna tidak ditemukan");
            }
            
            Pengguna pengguna = penggunaOpt.get();
            
            // Validasi password lama
            if (!pengguna.getPassword().equals(passwordLama)) {
                return new UpdateResult(false, "Password lama salah");
            }
            
            // Update password
            boolean success = penggunaDAO.gantiPassword(idPengguna, passwordBaru);
            
            if (success) {
                return new UpdateResult(true, "Password berhasil diubah");
            } else {
                return new UpdateResult(false, "Gagal mengubah password");
            }
            
        } catch (Exception e) {
            return new UpdateResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Get all donatur (untuk admin)
     */
    public List<Donatur> getAllDonatur() {
        return donaturDAO.findAll();
    }
    
    /**
     * Search donatur by nama
     */
    public List<Donatur> searchDonatur(String nama) {
        return donaturDAO.searchByNama(nama);
    }
    
    /**
     * Inner class untuk Registration Result
     */
    public static class RegistrationResult {
        private final boolean success;
        private final String message;
        
        public RegistrationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
    }
    
    /**
     * Inner class untuk Validation Result
     */
    private static class ValidationResult {
        private final boolean valid;
        private final String message;
        
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getMessage() {
            return message;
        }
    }
    
    /**
     * Inner class untuk Update Result
     */
    public static class UpdateResult {
        private final boolean success;
        private final String message;
        
        public UpdateResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
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
        if (sekolahDAO instanceof SekolahDAOImpl) {
            ((SekolahDAOImpl) sekolahDAO).close();
        }
    }
}
