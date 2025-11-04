    package dao;

    import model.Donatur;
    import jakarta.persistence.*;
    import java.math.BigDecimal;
    import java.util.List;
    import java.util.Optional;

    /**
     * DonaturDAO Implementation
     * Implementasi dengan analytics dan reporting queries
     * 
     * @author EduShare Team
     */
    public class DonaturDAOImpl extends GenericDAOImpl<Donatur, Integer> implements DonaturDAO {

        public DonaturDAOImpl() {
            super(Donatur.class);
        }

        public DonaturDAOImpl(EntityManager entityManager) {
            super(Donatur.class, entityManager);
        }

        @Override
        public Optional<Donatur> findByEmail(String email) {
            try {
                TypedQuery<Donatur> query = entityManager.createQuery(
                    "SELECT d FROM Donatur d WHERE d.email = :email", 
                    Donatur.class
                );
                query.setParameter("email", email);
                Donatur donatur = query.getSingleResult();
                return Optional.of(donatur);
            } catch (NoResultException e) {
                return Optional.empty();
            } catch (Exception e) {
                throw new RuntimeException("Error saat mencari donatur dengan email: " + e.getMessage(), e);
            }
        }

        @Override
        public Optional<Donatur> authenticate(String email, String password) {
            try {
                TypedQuery<Donatur> query = entityManager.createQuery(
                    "SELECT d FROM Donatur d WHERE d.email = :email AND d.password = :password", 
                    Donatur.class
                );
                query.setParameter("email", email);
                query.setParameter("password", password);
                Donatur donatur = query.getSingleResult();
                return Optional.of(donatur);
            } catch (NoResultException e) {
                return Optional.empty();
            } catch (Exception e) {
                throw new RuntimeException("Error saat autentikasi donatur: " + e.getMessage(), e);
            }
        }

        @Override
        public List<Donatur> searchByNama(String nama) {
            try {
                TypedQuery<Donatur> query = entityManager.createQuery(
                    "SELECT d FROM Donatur d WHERE LOWER(d.nama) LIKE LOWER(:nama)", 
                    Donatur.class
                );
                query.setParameter("nama", "%" + nama + "%");
                return query.getResultList();
            } catch (Exception e) {
                throw new RuntimeException("Error saat search donatur: " + e.getMessage(), e);
            }
        }

        /**
         * ANALYTICS: Menghitung total donasi yang diberikan
         * Menggunakan JOIN dengan tabel Donasi dan aggregate function SUM
         */
        @Override
        public BigDecimal getTotalDonasi(Integer idDonatur) {
            try {
                TypedQuery<BigDecimal> query = entityManager.createQuery(
                    "SELECT COALESCE(SUM(don.jumlahDonasi), 0) " +
                    "FROM Donasi don " +
                    "WHERE don.donatur.idPengguna = :idDonatur " +
                    "AND don.statusDonasi = 'berhasil'", 
                    BigDecimal.class
                );
                query.setParameter("idDonatur", idDonatur);
                BigDecimal total = query.getSingleResult();
                return total != null ? total : BigDecimal.ZERO;
            } catch (Exception e) {
                throw new RuntimeException("Error saat menghitung total donasi: " + e.getMessage(), e);
            }
        }

        /**
         * ANALYTICS: Menghitung jumlah transaksi donasi berhasil
         */
        @Override
        public long getJumlahDonasiBerhasil(Integer idDonatur) {
            try {
                TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(don) FROM Donasi don " +
                    "WHERE don.donatur.idPengguna = :idDonatur " +
                    "AND don.statusDonasi = 'berhasil'", 
                    Long.class
                );
                query.setParameter("idDonatur", idDonatur);
                return query.getSingleResult();
            } catch (Exception e) {
                throw new RuntimeException("Error saat menghitung jumlah donasi: " + e.getMessage(), e);
            }
        }

        /**
         * REPORTING: Mendapatkan top donatur berdasarkan total donasi
         * Query kompleks dengan JOIN, GROUP BY, dan ORDER BY
         */
        @Override
        public List<Donatur> getTopDonatur(int limit) {
            try {
                TypedQuery<Donatur> query = entityManager.createQuery(
                    "SELECT d FROM Donatur d " +
                    "LEFT JOIN d.daftarDonasi don " +
                    "WHERE don.statusDonasi = 'berhasil' " +
                    "GROUP BY d.idPengguna " +
                    "ORDER BY SUM(don.jumlahDonasi) DESC", 
                    Donatur.class
                );
                query.setMaxResults(limit);
                return query.getResultList();
            } catch (Exception e) {
                throw new RuntimeException("Error saat mengambil top donatur: " + e.getMessage(), e);
            }
        }

        /**
         * Query donatur yang pernah berdonasi ke sekolah tertentu
         * Menggunakan DISTINCT untuk menghindari duplikasi
         */
        @Override
        public List<Donatur> findBySekolah(Integer idSekolah) {
            try {
                TypedQuery<Donatur> query = entityManager.createQuery(
                    "SELECT DISTINCT d FROM Donatur d " +
                    "JOIN d.daftarDonasi don " +
                    "JOIN don.kebutuhan k " +
                    "WHERE k.sekolah.idSekolah = :idSekolah " +
                    "AND don.statusDonasi = 'berhasil'", 
                    Donatur.class
                );
                query.setParameter("idSekolah", idSekolah);
                return query.getResultList();
            } catch (Exception e) {
                throw new RuntimeException("Error saat mencari donatur berdasarkan sekolah: " + e.getMessage(), e);
            }
        }
    }
