package dao;

import model.Donatur;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * DonaturDAO Interface
 * Interface untuk operasi database pada entity Donatur
 * 
 * @author EduShare Team
 */
public interface DonaturDAO extends GenericDAO<Donatur, Integer> {
    
    /**
     * Mencari donatur berdasarkan email
     * @param email Email donatur
     * @return Optional berisi Donatur jika ditemukan
     */
    Optional<Donatur> findByEmail(String email);
    
    /**
     * Autentikasi donatur
     * @param email Email donatur
     * @param password Password donatur
     * @return Optional berisi Donatur jika credentials benar
     */
    Optional<Donatur> authenticate(String email, String password);
    
    /**
     * Mencari donatur berdasarkan nama (search)
     * @param nama Nama atau sebagian nama
     * @return List donatur yang namanya mengandung keyword
     */
    List<Donatur> searchByNama(String nama);
    
    /**
     * Menghitung total donasi yang diberikan oleh donatur
     * @param idDonatur ID donatur
     * @return Total jumlah donasi dalam BigDecimal
     */
    BigDecimal getTotalDonasi(Integer idDonatur);
    
    /**
     * Menghitung jumlah transaksi donasi yang berhasil
     * @param idDonatur ID donatur
     * @return Jumlah transaksi donasi berhasil
     */
    long getJumlahDonasiBerhasil(Integer idDonatur);
    
    /**
     * Mendapatkan top donatur (berdasarkan total donasi)
     * @param limit Jumlah maksimal donatur yang dikembalikan
     * @return List top donatur
     */
    List<Donatur> getTopDonatur(int limit);
    
    /**
     * Mendapatkan donatur yang pernah berdonasi untuk sekolah tertentu
     * @param idSekolah ID sekolah
     * @return List donatur yang pernah donasi ke sekolah tersebut
     */
    List<Donatur> findBySekolah(Integer idSekolah);
}
