package dao;

import model.PihakSekolah;
import model.Sekolah;
import java.util.List;
import java.util.Optional;

/**
 * PihakSekolahDAO Interface
 * Interface untuk operasi database pada entity PihakSekolah
 * 
 * @author EduShare Team
 */
public interface PihakSekolahDAO extends GenericDAO<PihakSekolah, Integer> {
    
    /**
     * Mencari pihak sekolah berdasarkan email
     * @param email Email pihak sekolah
     * @return Optional berisi PihakSekolah jika ditemukan
     */
    Optional<PihakSekolah> findByEmail(String email);
    
    /**
     * Autentikasi pihak sekolah
     * @param email Email pihak sekolah
     * @param password Password pihak sekolah
     * @return Optional berisi PihakSekolah jika credentials benar
     */
    Optional<PihakSekolah> authenticate(String email, String password);
    
    /**
     * Mencari pihak sekolah berdasarkan sekolah
     * @param sekolah Sekolah yang dikelola
     * @return List pihak sekolah di sekolah tersebut
     */
    List<PihakSekolah> findBySekolah(Sekolah sekolah);
    
    /**
     * Mencari pihak sekolah berdasarkan ID sekolah
     * @param idSekolah ID sekolah
     * @return List pihak sekolah di sekolah tersebut
     */
    List<PihakSekolah> findByIdSekolah(Integer idSekolah);
    
    /**
     * Mencari pihak sekolah berdasarkan jabatan
     * @param jabatan Jabatan di sekolah
     * @return List pihak sekolah dengan jabatan tersebut
     */
    List<PihakSekolah> findByJabatan(String jabatan);
    
    /**
     * Menghitung jumlah pihak sekolah per sekolah
     * @param idSekolah ID sekolah
     * @return Jumlah pihak sekolah di sekolah tersebut
     */
    long countBySekolah(Integer idSekolah);
}
