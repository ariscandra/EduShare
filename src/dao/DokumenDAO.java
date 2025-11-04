package dao;

import model.Dokumen;
import model.Sekolah;
import java.util.List;

/**
 * DokumenDAO Interface
 * Interface untuk operasi database pada entity Dokumen
 * 
 * @author EduShare Team
 */
public interface DokumenDAO extends GenericDAO<Dokumen, Integer> {
    
    /**
     * Mencari dokumen berdasarkan sekolah
     * @param sekolah Sekolah yang mengupload dokumen
     * @return List dokumen dari sekolah tersebut
     */
    List<Dokumen> findBySekolah(Sekolah sekolah);
    
    /**
     * Mencari dokumen berdasarkan ID sekolah
     * @param idSekolah ID sekolah
     * @return List dokumen dari sekolah tersebut
     */
    List<Dokumen> findByIdSekolah(Integer idSekolah);
    
    /**
     * Mencari dokumen berdasarkan jenis dokumen
     * @param jenisDokumen Jenis dokumen (Proposal, Laporan, dll)
     * @return List dokumen dengan jenis tersebut
     */
    List<Dokumen> findByJenisDokumen(String jenisDokumen);
    
    /**
     * Mencari dokumen berdasarkan status verifikasi
     * @param statusVerifikasi Status verifikasi (menunggu, diterima, ditolak)
     * @return List dokumen dengan status tersebut
     */
    List<Dokumen> findByStatusVerifikasi(String statusVerifikasi);
    
    /**
     * Mendapatkan dokumen yang menunggu verifikasi
     * @return List dokumen yang statusnya "menunggu"
     */
    List<Dokumen> findDokumenMenungguVerifikasi();
    
    /**
     * Mendapatkan dokumen yang sudah diverifikasi
     * @return List dokumen yang statusnya "diterima"
     */
    List<Dokumen> findDokumenTerverifikasi();
    
    /**
     * Mendapatkan dokumen yang ditolak
     * @return List dokumen yang statusnya "ditolak"
     */
    List<Dokumen> findDokumenDitolak();
    
    /**
     * Mencari dokumen berdasarkan sekolah dan status verifikasi
     * @param idSekolah ID sekolah
     * @param statusVerifikasi Status verifikasi
     * @return List dokumen yang sesuai
     */
    List<Dokumen> findBySekolahAndStatus(Integer idSekolah, String statusVerifikasi);
    
    /**
     * Menghitung jumlah dokumen menunggu verifikasi per sekolah
     * @param idSekolah ID sekolah
     * @return Jumlah dokumen menunggu verifikasi
     */
    long countMenungguVerifikasiBySekolah(Integer idSekolah);
    
    /**
     * Menghitung total dokumen per sekolah
     * @param idSekolah ID sekolah
     * @return Total dokumen dari sekolah tersebut
     */
    long countBySekolah(Integer idSekolah);
    
    /**
     * Verifikasi dokumen (terima)
     * @param idDokumen ID dokumen
     * @param catatanAdmin Catatan dari admin
     * @return true jika berhasil diverifikasi
     */
    boolean verifikasiDokumen(Integer idDokumen, String catatanAdmin);
    
    /**
     * Tolak dokumen
     * @param idDokumen ID dokumen
     * @param catatanAdmin Alasan penolakan
     * @return true jika berhasil ditolak
     */
    boolean tolakDokumen(Integer idDokumen, String catatanAdmin);
}
