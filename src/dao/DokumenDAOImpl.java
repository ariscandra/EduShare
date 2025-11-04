package dao;

import model.Dokumen;
import model.Sekolah;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * DokumenDAO Implementation
 * Implementasi untuk operasi database dan verifikasi Dokumen
 * 
 * @author EduShare Team
 */
public class DokumenDAOImpl extends GenericDAOImpl<Dokumen, Integer> implements DokumenDAO {
    
    public DokumenDAOImpl() {
        super(Dokumen.class);
    }
    
    public DokumenDAOImpl(EntityManager entityManager) {
        super(Dokumen.class, entityManager);
    }
    
    @Override
    public List<Dokumen> findBySekolah(Sekolah sekolah) {
        return findByIdSekolah(sekolah.getIdSekolah());
    }
    
    @Override
    public List<Dokumen> findByIdSekolah(Integer idSekolah) {
        try {
            TypedQuery<Dokumen> query = entityManager.createQuery(
                "SELECT d FROM Dokumen d WHERE d.sekolah.idSekolah = :idSekolah ORDER BY d.tanggalUpload DESC", 
                Dokumen.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari dokumen berdasarkan sekolah: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Dokumen> findByJenisDokumen(String jenisDokumen) {
        try {
            TypedQuery<Dokumen> query = entityManager.createQuery(
                "SELECT d FROM Dokumen d WHERE LOWER(d.jenisDokumen) LIKE LOWER(:jenisDokumen) ORDER BY d.tanggalUpload DESC", 
                Dokumen.class
            );
            query.setParameter("jenisDokumen", "%" + jenisDokumen + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari dokumen berdasarkan jenis: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Dokumen> findByStatusVerifikasi(String statusVerifikasi) {
        try {
            TypedQuery<Dokumen> query = entityManager.createQuery(
                "SELECT d FROM Dokumen d WHERE d.statusVerifikasi = :statusVerifikasi ORDER BY d.tanggalUpload DESC", 
                Dokumen.class
            );
            query.setParameter("statusVerifikasi", Dokumen.StatusVerifikasi.valueOf(statusVerifikasi));
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari dokumen berdasarkan status: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Dokumen> findDokumenMenungguVerifikasi() {
        return findByStatusVerifikasi("menunggu");
    }
    
    @Override
    public List<Dokumen> findDokumenTerverifikasi() {
        return findByStatusVerifikasi("diterima");
    }
    
    @Override
    public List<Dokumen> findDokumenDitolak() {
        return findByStatusVerifikasi("ditolak");
    }
    
    @Override
    public List<Dokumen> findBySekolahAndStatus(Integer idSekolah, String statusVerifikasi) {
        try {
            TypedQuery<Dokumen> query = entityManager.createQuery(
                "SELECT d FROM Dokumen d " +
                "WHERE d.sekolah.idSekolah = :idSekolah AND d.statusVerifikasi = :statusVerifikasi " +
                "ORDER BY d.tanggalUpload DESC", 
                Dokumen.class
            );
            query.setParameter("idSekolah", idSekolah);
            query.setParameter("statusVerifikasi", Dokumen.StatusVerifikasi.valueOf(statusVerifikasi));
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari dokumen berdasarkan sekolah dan status: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long countMenungguVerifikasiBySekolah(Integer idSekolah) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(d) FROM Dokumen d " +
                "WHERE d.sekolah.idSekolah = :idSekolah AND d.statusVerifikasi = 'menunggu'", 
                Long.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung dokumen menunggu verifikasi: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long countBySekolah(Integer idSekolah) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(d) FROM Dokumen d WHERE d.sekolah.idSekolah = :idSekolah", 
                Long.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung dokumen sekolah: " + e.getMessage(), e);
        }
    }
    
    /**
     * BUSINESS LOGIC: Verifikasi dokumen (terima)
     */
    @Override
    public boolean verifikasiDokumen(Integer idDokumen, String catatanAdmin) {
        EntityTransaction transaction = null;
        try {
            Optional<Dokumen> dokumenOpt = findById(idDokumen);
            if (dokumenOpt.isEmpty()) {
                return false;
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Dokumen dokumen = dokumenOpt.get();
            dokumen.verifikasi(catatanAdmin);
            entityManager.merge(dokumen);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat verifikasi dokumen: " + e.getMessage(), e);
        }
    }
    
    /**
     * BUSINESS LOGIC: Tolak dokumen
     */
    @Override
    public boolean tolakDokumen(Integer idDokumen, String catatanAdmin) {
        EntityTransaction transaction = null;
        try {
            Optional<Dokumen> dokumenOpt = findById(idDokumen);
            if (dokumenOpt.isEmpty()) {
                return false;
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Dokumen dokumen = dokumenOpt.get();
            dokumen.tolak(catatanAdmin);
            entityManager.merge(dokumen);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat tolak dokumen: " + e.getMessage(), e);
        }
    }
}
