package dao;

import model.PihakSekolah;
import model.Sekolah;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * PihakSekolahDAO Implementation
 * Implementasi untuk operasi database PihakSekolah
 * 
 * @author EduShare Team
 */
public class PihakSekolahDAOImpl extends GenericDAOImpl<PihakSekolah, Integer> implements PihakSekolahDAO {
    
    public PihakSekolahDAOImpl() {
        super(PihakSekolah.class);
    }
    
    public PihakSekolahDAOImpl(EntityManager entityManager) {
        super(PihakSekolah.class, entityManager);
    }
    
    @Override
    public Optional<PihakSekolah> findByEmail(String email) {
        try {
            TypedQuery<PihakSekolah> query = entityManager.createQuery(
                "SELECT ps FROM PihakSekolah ps WHERE ps.email = :email", 
                PihakSekolah.class
            );
            query.setParameter("email", email);
            PihakSekolah pihakSekolah = query.getSingleResult();
            return Optional.of(pihakSekolah);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari pihak sekolah dengan email: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<PihakSekolah> authenticate(String email, String password) {
        try {
            TypedQuery<PihakSekolah> query = entityManager.createQuery(
                "SELECT ps FROM PihakSekolah ps WHERE ps.email = :email AND ps.password = :password", 
                PihakSekolah.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            PihakSekolah pihakSekolah = query.getSingleResult();
            return Optional.of(pihakSekolah);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat autentikasi pihak sekolah: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<PihakSekolah> findBySekolah(Sekolah sekolah) {
        return findByIdSekolah(sekolah.getIdSekolah());
    }
    
    @Override
    public List<PihakSekolah> findByIdSekolah(Integer idSekolah) {
        try {
            TypedQuery<PihakSekolah> query = entityManager.createQuery(
                "SELECT ps FROM PihakSekolah ps WHERE ps.sekolah.idSekolah = :idSekolah", 
                PihakSekolah.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari pihak sekolah berdasarkan sekolah: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<PihakSekolah> findByJabatan(String jabatan) {
        try {
            TypedQuery<PihakSekolah> query = entityManager.createQuery(
                "SELECT ps FROM PihakSekolah ps WHERE LOWER(ps.jabatan) LIKE LOWER(:jabatan)", 
                PihakSekolah.class
            );
            query.setParameter("jabatan", "%" + jabatan + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari pihak sekolah berdasarkan jabatan: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long countBySekolah(Integer idSekolah) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(ps) FROM PihakSekolah ps WHERE ps.sekolah.idSekolah = :idSekolah", 
                Long.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung pihak sekolah: " + e.getMessage(), e);
        }
    }
}
