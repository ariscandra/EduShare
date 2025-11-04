package dao;

import model.Pengguna;
import java.util.Optional;
import java.util.List;

/**
 * PenggunaDAO Interface
 * Interface untuk operasi database pada entity Pengguna
 * Extends GenericDAO dan menambahkan custom methods
 * 
 * @author EduShare Team
 */
public interface PenggunaDAO extends GenericDAO<Pengguna, Integer> {
    
    /**
     * Mencari pengguna berdasarkan email (untuk login)
     * @param email Email pengguna
     * @return Optional berisi Pengguna jika ditemukan
     */
    Optional<Pengguna> findByEmail(String email);
    
    /**
     * Mencari pengguna berdasarkan email dan password (untuk autentikasi)
     * @param email Email pengguna
     * @param password Password pengguna
     * @return Optional berisi Pengguna jika credentials benar
     */
    Optional<Pengguna> findByEmailAndPassword(String email, String password);
    
    /**
     * Mencari pengguna berdasarkan jenis akun (role)
     * @param jenisAkun Jenis akun (admin, donatur, pihak_sekolah)
     * @return List pengguna dengan jenis akun tertentu
     */
    List<Pengguna> findByJenisAkun(String jenisAkun);
    
    /**
     * Mencari pengguna berdasarkan nama (search)
     * @param nama Nama atau sebagian nama pengguna
     * @return List pengguna yang namanya mengandung keyword
     */
    List<Pengguna> searchByNama(String nama);
    
    /**
     * Mengecek apakah email sudah terdaftar
     * @param email Email yang akan dicek
     * @return true jika email sudah ada, false jika belum
     */
    boolean isEmailExists(String email);
    
    /**
     * Mengganti password pengguna
     * @param idPengguna ID pengguna
     * @param passwordBaru Password baru
     * @return true jika berhasil, false jika gagal
     */
    boolean gantiPassword(Integer idPengguna, String passwordBaru);
    
    /**
     * Update profil pengguna (nama, no hp, alamat)
     * @param pengguna Pengguna dengan data baru
     * @return Pengguna yang telah diupdate
     */
    Pengguna updateProfil(Pengguna pengguna);
}
