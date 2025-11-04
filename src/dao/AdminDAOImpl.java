package dao;

import model.Admin;
import model.Sekolah;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * AdminDAO Implementation
 * Implementasi untuk operasi database Admin
 * 
 * @author EduShare Team
 */
public class AdminDAOImpl extends GenericDAOImpl<Admin, Integer> implements AdminDAO {
    
    public AdminDAOImpl() {
        super(Admin.class);
    }
    
    public AdminDAOImpl(EntityManager entityManager) {
        super(Admin.class, entityManager);
    }
    
    @Override
    public Optional<Admin> findByEmail(String email) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a WHERE a.email = :email", 
                Admin.class
            );
            query.setParameter("email", email);
            Admin admin = query.getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari admin dengan email: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Admin> findBySekolah(Sekolah sekolah) {
        return findByIdSekolah(sekolah.getIdSekolah());
    }
    
    @Override
    public List<Admin> findByIdSekolah(Integer idSekolah) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a WHERE a.sekolah.idSekolah = :idSekolah", 
                Admin.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari admin berdasarkan sekolah: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long countBySekolah(Integer idSekolah) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(a) FROM Admin a WHERE a.sekolah.idSekolah = :idSekolah", 
                Long.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung admin: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Admin> authenticate(String email, String password) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password", 
                Admin.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            Admin admin = query.getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat autentikasi admin: " + e.getMessage(), e);
        }
    }
}
