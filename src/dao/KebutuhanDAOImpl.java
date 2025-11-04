package dao;

import model.Kebutuhan;
import model.Sekolah;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * KebutuhanDAO Implementation
 * Implementasi dengan filtering dan tracking
 * 
 * @author EduShare Team
 */
public class KebutuhanDAOImpl extends GenericDAOImpl<Kebutuhan, Integer> implements KebutuhanDAO {

    public KebutuhanDAOImpl() {
        super(Kebutuhan.class);
    }

    public KebutuhanDAOImpl(EntityManager entityManager) {
        super(Kebutuhan.class, entityManager);
    }

    // 🔹 Cari kebutuhan berdasarkan sekolah
    @Override
    public List<Kebutuhan> findBySekolah(Sekolah sekolah) {
        return findByIdSekolah(sekolah.getIdSekolah());
    }

    @Override
    public List<Kebutuhan> findByIdSekolah(Integer idSekolah) {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.sekolah.idSekolah = :idSekolah ORDER BY k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari kebutuhan berdasarkan sekolah: " + e.getMessage(), e);
        }
    }

    // 🔹 Cari berdasarkan status (aman dari error case-sensitive)
    @Override
    public List<Kebutuhan> findByStatus(String status) {
        try {
            // konversi aman ke Enum (tanpa error case-sensitive)
            Kebutuhan.Status statusEnum = Kebutuhan.Status.valueOf(status.trim().toLowerCase());
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.status = :status ORDER BY k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            query.setParameter("status", statusEnum);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status '" + status + "' tidak valid. Gunakan: diajukan, diverifikasi, ditolak, terpenuhi.", e);
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari kebutuhan berdasarkan status: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> findByPrioritas(String prioritas) {
        try {
            Kebutuhan.Prioritas prioritasEnum = Kebutuhan.Prioritas.valueOf(prioritas.trim().toLowerCase());
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.prioritas = :prioritas ORDER BY k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            query.setParameter("prioritas", prioritasEnum);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari kebutuhan berdasarkan prioritas: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> findKebutuhanTerverifikasi() {
        return findByStatus("diverifikasi");
    }

    @Override
    public List<Kebutuhan> findKebutuhanMenungguVerifikasi() {
        return findByStatus("diajukan");
    }

    @Override
    public List<Kebutuhan> findKebutuhanAktif() {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.status != 'terpenuhi' ORDER BY k.prioritas, k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari kebutuhan aktif: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> searchByNamaItem(String namaItem) {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE LOWER(k.namaAlat) LIKE LOWER(:namaItem)",
                Kebutuhan.class
            );
            query.setParameter("namaItem", "%" + namaItem + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat search kebutuhan: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> searchWithFilters(Integer idSekolah, String status, String prioritas) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT k FROM Kebutuhan k WHERE 1=1");

            if (idSekolah != null) {
                jpql.append(" AND k.sekolah.idSekolah = :idSekolah");
            }
            if (status != null && !status.isEmpty()) {
                jpql.append(" AND k.status = :status");
            }
            if (prioritas != null && !prioritas.isEmpty()) {
                jpql.append(" AND k.prioritas = :prioritas");
            }

            jpql.append(" ORDER BY k.tanggalPengajuan DESC");

            TypedQuery<Kebutuhan> query = entityManager.createQuery(jpql.toString(), Kebutuhan.class);

            if (idSekolah != null) {
                query.setParameter("idSekolah", idSekolah);
            }
            if (status != null && !status.isEmpty()) {
                query.setParameter("status", Kebutuhan.Status.valueOf(status.trim().toLowerCase()));
            }
            if (prioritas != null && !prioritas.isEmpty()) {
                query.setParameter("prioritas", Kebutuhan.Prioritas.valueOf(prioritas.trim().toLowerCase()));
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat search dengan filter: " + e.getMessage(), e);
        }
    }

    @Override
    public BigDecimal getTotalTargetDana(Integer idSekolah) {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTotalDanaTerkumpul(Integer idKebutuhan) {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) FROM Donasi d " +
                "WHERE d.kebutuhan.idKebutuhan = :idKebutuhan AND d.statusDonasi = 'berhasil'",
                BigDecimal.class
            );
            query.setParameter("idKebutuhan", idKebutuhan);
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung dana terkumpul: " + e.getMessage(), e);
        }
    }

    @Override
    public double getPersentaseProgress(Integer idKebutuhan) {
        return 0.0;
    }

    @Override
    public long countByStatusAndSekolah(Integer idSekolah, String status) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(k) FROM Kebutuhan k WHERE k.sekolah.idSekolah = :idSekolah AND k.status = :status",
                Long.class
            );
            query.setParameter("idSekolah", idSekolah);
            query.setParameter("status", Kebutuhan.Status.valueOf(status.trim().toLowerCase()));
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung kebutuhan: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> findKebutuhanPrioritasTinggi() {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.prioritas = 'tinggi' AND k.status != 'terpenuhi' ORDER BY k.tanggalPengajuan",
                Kebutuhan.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari kebutuhan prioritas tinggi: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Kebutuhan> findKebutuhanTersedia() {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k " +
                "WHERE k.status <> 'terpenuhi' " +
                "ORDER BY k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil kebutuhan tersedia: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Integer idKebutuhan) {
        try {
            entityManager.getTransaction().begin();
            Kebutuhan kebutuhan = entityManager.find(Kebutuhan.class, idKebutuhan);
            if (kebutuhan != null) {
                entityManager.remove(kebutuhan);
                entityManager.getTransaction().commit();
                return true;
            } else {
                entityManager.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error saat menghapus kebutuhan: " + e.getMessage());
            return false;
        }
    }
    
    public int verifikasiBySekolah(Integer idSekolah) {
        try {
            entityManager.getTransaction().begin();
            int updated = entityManager.createQuery(
                "UPDATE Kebutuhan k SET k.status = 'diverifikasi' WHERE k.sekolah.idSekolah = :idSekolah"
            ).setParameter("idSekolah", idSekolah)
             .executeUpdate();
            entityManager.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error saat memverifikasi kebutuhan sekolah: " + e.getMessage(), e);
        }
    }
}
