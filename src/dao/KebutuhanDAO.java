package dao;

import java.math.BigDecimal;
import java.util.List;
import model.Kebutuhan;
import model.Sekolah;

public interface KebutuhanDAO extends GenericDAO<Kebutuhan, Integer> {

    List<Kebutuhan> findBySekolah(Sekolah sekolah);

    List<Kebutuhan> findByIdSekolah(Integer idSekolah);

    List<Kebutuhan> findByStatus(String status);

    List<Kebutuhan> findByPrioritas(String prioritas);

    List<Kebutuhan> findKebutuhanTerverifikasi();

    List<Kebutuhan> findKebutuhanMenungguVerifikasi();

    List<Kebutuhan> findKebutuhanAktif();

    List<Kebutuhan> searchByNamaItem(String namaItem);

    List<Kebutuhan> searchWithFilters(Integer idSekolah, String status, String prioritas);

    BigDecimal getTotalTargetDana(Integer idSekolah);

    BigDecimal getTotalDanaTerkumpul(Integer idKebutuhan);

    double getPersentaseProgress(Integer idKebutuhan);

    long countByStatusAndSekolah(Integer idSekolah, String status);
    
    boolean delete(Integer idKebutuhan);

    List<Kebutuhan> findKebutuhanPrioritasTinggi();

    int verifikasiBySekolah(Integer idSekolah);

    // ✅ Tambahan baru
    /**
     * Mengambil daftar kebutuhan yang masih tersedia untuk didonasi
     * (status != 'terpenuhi')
     */
    List<Kebutuhan> findKebutuhanTersedia();
}
