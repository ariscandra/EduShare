package dao;

import model.Admin;
import model.Sekolah;
import java.util.List;
import java.util.Optional;

/**
 * AdminDAO Interface
 * Interface untuk operasi database pada entity Admin
 * 
 * @author EduShare Team
 */
public interface AdminDAO extends GenericDAO<Admin, Integer> {
    
    /**
     * Mencari admin berdasarkan email (untuk login)
     * @param email Email admin
     * @return Optional berisi Admin jika ditemukan
     */
    Optional<Admin> findByEmail(String email);
    
    /**
     * Mencari admin berdasarkan sekolah
     * @param sekolah Sekolah yang dikelola admin
     * @return List admin di sekolah tersebut
     */
    List<Admin> findBySekolah(Sekolah sekolah);
    
    /**
     * Mencari admin berdasarkan ID sekolah
     * @param idSekolah ID sekolah
     * @return List admin di sekolah tersebut
     */
    List<Admin> findByIdSekolah(Integer idSekolah);
    
    /**
     * Menghitung jumlah admin per sekolah
     * @param idSekolah ID sekolah
     * @return Jumlah admin di sekolah tersebut
     */
    long countBySekolah(Integer idSekolah);
    
    /**
     * Autentikasi admin
     * @param email Email admin
     * @param password Password admin
     * @return Optional berisi Admin jika credentials benar
     */
    Optional<Admin> authenticate(String email, String password);
}
