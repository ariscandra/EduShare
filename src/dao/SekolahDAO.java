package dao;

import model.Sekolah;
import java.util.List;

/**
 * SekolahDAO Interface
 * Interface untuk operasi database pada entity Sekolah
 * 
 * @author EduShare Team
 */
public interface SekolahDAO extends GenericDAO<Sekolah, Integer> {
    
    List<Sekolah> searchByNama(String namaSekolah);
    List<Sekolah> findByKategori(String kategori);
    List<Sekolah> findByAlamat(String alamat);
    
    /** 
     * Cari sekolah berdasarkan status kebutuhan (diajukan, diverifikasi, terpenuhi, ditolak)
     */
    List<Sekolah> findSekolahByStatusKebutuhan(String statusKebutuhan);
    
    List<Sekolah> findSekolahTerverifikasi();
    List<Sekolah> findSekolahMenungguVerifikasi();

    List<Sekolah> searchWithFilters(String kategori, String alamat, String statusKebutuhan);
    
    long countByKategori(String kategori);
    long countTerverifikasi();

    List<Sekolah> getTopSekolahByDonasi(int limit);
}
