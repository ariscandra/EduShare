package dao;

import model.Donasi;
import model.Donatur;
import model.Kebutuhan;
import model.Sekolah;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * DonasiDAO Interface
 * Interface untuk operasi database pada entity Donasi
 * Banyak query untuk analytics dan reporting
 * 
 * @author EduShare Team
 */
public interface DonasiDAO extends GenericDAO<Donasi, Integer> {
    
    /**
     * Mencari donasi berdasarkan donatur
     * @param donatur Donatur yang melakukan donasi
     * @return List donasi dari donatur tersebut
     */
    List<Donasi> findByDonatur(Donatur donatur);
    
    /**
     * Mencari donasi berdasarkan ID donatur
     * @param idDonatur ID donatur
     * @return List donasi dari donatur tersebut
     */
    List<Donasi> findByIdDonatur(Integer idDonatur);
    
    /**
     * Mencari donasi berdasarkan kebutuhan
     * @param kebutuhan Kebutuhan yang menerima donasi
     * @return List donasi untuk kebutuhan tersebut
     */
    List<Donasi> findByKebutuhan(Kebutuhan kebutuhan);
    
    /**
     * Mencari donasi berdasarkan ID kebutuhan
     * @param idKebutuhan ID kebutuhan
     * @return List donasi untuk kebutuhan tersebut
     */
    List<Donasi> findByIdKebutuhan(Integer idKebutuhan);
    
    /**
     * Mencari donasi berdasarkan status
     * @param status Status donasi (pending, berhasil, gagal, dibatalkan)
     * @return List donasi dengan status tersebut
     */
    List<Donasi> findByStatus(String status);
    
    /**
     * Mencari donasi yang berhasil
     * @return List donasi dengan status berhasil
     */
    List<Donasi> findDonasiBerhasil();
    
    /**
     * Mencari donasi yang pending (menunggu konfirmasi)
     * @return List donasi dengan status pending
     */
    List<Donasi> findDonasiPending();
    
    /**
     * Mencari donasi berdasarkan rentang tanggal
     * @param startDate Tanggal mulai
     * @param endDate Tanggal akhir
     * @return List donasi dalam rentang tanggal
     */
    List<Donasi> findByTanggalRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Mencari donasi berdasarkan sekolah (melalui kebutuhan)
     * @param idSekolah ID sekolah
     * @return List donasi untuk sekolah tersebut
     */
    List<Donasi> findBySekolah(Integer idSekolah);
    
    /**
     * ANALYTICS: Menghitung total donasi berhasil
     * @return Total semua donasi yang berhasil
     */
    BigDecimal getTotalDonasiBerhasil();
    
    /**
     * ANALYTICS: Menghitung total donasi berhasil per donatur
     * @param idDonatur ID donatur
     * @return Total donasi dari donatur tersebut
     */
    BigDecimal getTotalDonasiByDonatur(Integer idDonatur);
    
    /**
     * ANALYTICS: Menghitung total donasi berhasil per kebutuhan
     * @param idKebutuhan ID kebutuhan
     * @return Total donasi untuk kebutuhan tersebut
     */
    BigDecimal getTotalDonasiByKebutuhan(Integer idKebutuhan);
    
    /**
     * ANALYTICS: Menghitung total donasi berhasil per sekolah
     * @param idSekolah ID sekolah
     * @return Total donasi untuk sekolah tersebut
     */
    BigDecimal getTotalDonasiBySekolah(Integer idSekolah);
    
    /**
     * ANALYTICS: Menghitung total donasi dalam periode tertentu
     * @param startDate Tanggal mulai
     * @param endDate Tanggal akhir
     * @return Total donasi dalam periode
     */
    BigDecimal getTotalDonasiByPeriode(LocalDate startDate, LocalDate endDate);
    
    /**
     * REPORTING: Mendapatkan donasi terbesar
     * @param limit Jumlah maksimal donasi yang dikembalikan
     * @return List donasi terbesar
     */
    List<Donasi> getTopDonasi(int limit);
    
    /**
     * REPORTING: Mendapatkan donasi terbaru
     * @param limit Jumlah maksimal donasi yang dikembalikan
     * @return List donasi terbaru
     */
    List<Donasi> getDonasiTerbaru(int limit);
    
    /**
     * ANALYTICS: Menghitung jumlah donasi berdasarkan status
     * @param status Status donasi
     * @return Jumlah donasi dengan status tersebut
     */
    
    List<Object[]> findRiwayatBySekolah(Integer idSekolah);

    long countByStatus(String status);
    
    /**
     * ANALYTICS: Menghitung rata-rata donasi per transaksi
     * @return Rata-rata jumlah donasi
     */
    BigDecimal getAverageDonasiAmount();
    
    /**
     * REPORTING: Statistik donasi per bulan (untuk chart)
     * @param year Tahun
     * @return Map dengan key bulan (1-12) dan value total donasi
     */
    Map<Integer, BigDecimal> getStatistikDonasiPerBulan(int year);
    
    /**
     * Konfirmasi donasi (ubah status dari pending ke berhasil)
     * @param idDonasi ID donasi
     * @return true jika berhasil dikonfirmasi
     */
    boolean konfirmasiDonasi(Integer idDonasi);
    
    List<Kebutuhan> findKebutuhanTersedia();
    /**
     * Tolak/batalkan donasi
     * @param idDonasi ID donasi
     * @param alasan Alasan penolakan
     * @return true jika berhasil dibatalkan
     */
    boolean batalkanDonasi(Integer idDonasi, String alasan);
}
