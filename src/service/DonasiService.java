package service;

import dao.*;
import model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DonasiService
 * Service untuk handle business logic donasi
 * Core business logic untuk sistem donasi
 * 
 * @author EduShare
 */
public class DonasiService {

    private final DonasiDAO donasiDAO;
    private final KebutuhanDAO kebutuhanDAO;
    private final DonaturDAO donaturDAO;

    public DonasiService() {
        this.donasiDAO = new DonasiDAOImpl();
        this.kebutuhanDAO = new KebutuhanDAOImpl();
        this.donaturDAO = new DonaturDAOImpl();
    }

    // =========================================================
    // 🔧 Helper untuk Dashboard / Analytics
    // =========================================================

    /** Total seluruh donasi berstatus BERHASIL (fallback ke 0) */
    public BigDecimal getTotalDonasiKeseluruhan() {
        try {
            BigDecimal total = donasiDAO.getTotalDonasiBerhasil();
            return (total != null) ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            System.err.println("Error getTotalDonasiKeseluruhan: " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    /** Top 10 donasi (untuk ranking di dashboard) */
    public List<Donasi> getTop10Donatur() {
        try {
            return donasiDAO.getTopDonasi(10);
        } catch (Exception e) {
            System.err.println("Error getTop10Donatur: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    /** Statistik total donasi per bulan pada tahun tertentu */
    public Map<Integer, BigDecimal> getStatistikBulanan(int year) {
        try {
            return donasiDAO.getStatistikDonasiPerBulan(year);
        } catch (Exception e) {
            System.err.println("Error getStatistikBulanan: " + e.getMessage());
            return java.util.Collections.emptyMap();
        }
    }

    // =========================================================
    // 💸 BUSINESS LOGIC
    // =========================================================

    public DonasiResult buatDonasi(Integer idDonatur, Integer idKebutuhan,
                                   BigDecimal jumlahDonasi, String metodePembayaran) {
        // Validasi dasar
        if (jumlahDonasi == null || jumlahDonasi.compareTo(BigDecimal.ZERO) <= 0) {
            return new DonasiResult(false, "Jumlah donasi harus lebih besar dari 0");
        }
        if (jumlahDonasi.compareTo(new BigDecimal("10000")) < 0) {
            return new DonasiResult(false, "Jumlah donasi minimal Rp 10.000");
        }

        try {
            // Validasi Donatur
            Optional<Donatur> donaturOpt = donaturDAO.findById(idDonatur);
            if (donaturOpt.isEmpty()) {
                return new DonasiResult(false, "Donatur tidak ditemukan");
            }

            // Validasi Kebutuhan
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new DonasiResult(false, "Kebutuhan tidak ditemukan");
            }

            Kebutuhan kebutuhan = kebutuhanOpt.get();

            // Status harus sudah diverifikasi & belum terpenuhi
            if (kebutuhan.getStatus() != null && kebutuhan.getStatus() != Kebutuhan.Status.diverifikasi) {
                return new DonasiResult(false, "Kebutuhan belum diverifikasi");
            }
            if (kebutuhan.getStatus() == Kebutuhan.Status.terpenuhi) {
                return new DonasiResult(false, "Kebutuhan sudah terpenuhi");
            }

            // Buat entitas donasi
            Donasi donasi = new Donasi();
            donasi.setDonatur(donaturOpt.get());
            donasi.setKebutuhan(kebutuhan);
            donasi.setJumlahDonasi(jumlahDonasi);

            // Normalisasi nama enum (UPPER_CASE)
            Donasi.MetodePembayaran mp = Donasi.MetodePembayaran.valueOf(
                    metodePembayaran == null ? "TRANSFER" : metodePembayaran.trim().toLowerCase()
            );
            donasi.setMetodePembayaran(mp);

            donasi.setTanggalDonasi(LocalDate.now());
            donasi.setStatusDonasi(Donasi.StatusDonasi.pending);

            // Simpan donasi
            donasiDAO.save(donasi);
            return new DonasiResult(true, "Donasi berhasil dibuat. Menunggu konfirmasi admin.");

        } catch (IllegalArgumentException e) {
            return new DonasiResult(false, "Metode pembayaran tidak valid");
        } catch (Exception e) {
            e.printStackTrace();
            return new DonasiResult(false, "Terjadi kesalahan: " + e.getMessage());
        }
    }

    /** Konfirmasi donasi: set BERHASIL, update progres kebutuhan & total donatur */
    public DonasiResult konfirmasiDonasi(Integer idDonasi) {
        try {
            Optional<Donasi> opt = donasiDAO.findById(idDonasi);
            if (opt.isEmpty()) return new DonasiResult(false, "Donasi tidak ditemukan");

            Donasi donasi = opt.get();
            if (donasi.getStatusDonasi() != Donasi.StatusDonasi.pending) {
                return new DonasiResult(false, "Donasi sudah dikonfirmasi sebelumnya");
            }

            // 1) Update status donasi
            donasi.setStatusDonasi(Donasi.StatusDonasi.berhasil);
            donasiDAO.update(donasi);

            // 2) Update progres kebutuhan
            Kebutuhan k = donasi.getKebutuhan();
            if (k != null) {
                BigDecimal totalKebutuhan = donasiDAO.getTotalDonasiByKebutuhan(k.getIdKebutuhan());
                if (totalKebutuhan == null) totalKebutuhan = BigDecimal.ZERO;

                k.setTotalTerkumpul(totalKebutuhan);

                if (k.getTargetDonasi() != null && totalKebutuhan.compareTo(k.getTargetDonasi()) >= 0) {
                    k.setStatus(Kebutuhan.Status.terpenuhi);
                }
                kebutuhanDAO.update(k);
            }

            // 3) Update total donasi per donatur (aggregat)
            Donatur d = donasi.getDonatur();
            if (d != null) {
                BigDecimal totalDonatur = donaturDAO.getTotalDonasi(d.getIdPengguna()); // sesuaikan getter ID
                d.setTotalDonasi(totalDonatur == null ? BigDecimal.ZERO : totalDonatur);
                donaturDAO.update(d);
            }

            return new DonasiResult(true, "Donasi berhasil dikonfirmasi!");

        } catch (Exception e) {
            e.printStackTrace();
            return new DonasiResult(false, "Error saat konfirmasi donasi: " + e.getMessage());
        }
    }

    /** Batalkan donasi → status GAGAL */
    public DonasiResult batalkanDonasi(Integer idDonasi, String alasan) {
        if (alasan == null || alasan.trim().isEmpty()) {
            return new DonasiResult(false, "Alasan pembatalan harus diisi");
        }

        try {
            Optional<Donasi> donasiOpt = donasiDAO.findById(idDonasi);
            if (donasiOpt.isEmpty()) {
                return new DonasiResult(false, "Donasi tidak ditemukan");
            }

            Donasi donasi = donasiOpt.get();
            donasi.setStatusDonasi(Donasi.StatusDonasi.gagal);
            donasiDAO.update(donasi);

            return new DonasiResult(true, "Donasi berhasil dibatalkan");

        } catch (Exception e) {
            return new DonasiResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
    * Total donasi berhasil untuk sekolah tertentu
    */
   public BigDecimal getTotalDonasiBySekolah(Integer idSekolah) {
       try {
           BigDecimal total = donasiDAO.getTotalDonasiBySekolah(idSekolah);
           return total != null ? total : BigDecimal.ZERO;
       } catch (Exception e) {
           System.err.println("Error getTotalDonasiBySekolah: " + e.getMessage());
           return BigDecimal.ZERO;
       }
   }


    // =========================================================
    // 📊 Data Access Wrappers (delegasi ke DAO)
    // =========================================================

    public List<Donasi> getRiwayatDonasi(Integer idDonatur) {
        return donasiDAO.findByIdDonatur(idDonatur);
    }

    public List<Donasi> getDonasiByKebutuhan(Integer idKebutuhan) {
        return donasiDAO.findByIdKebutuhan(idKebutuhan);
    }

    public List<Donasi> getDonasiPending() {
        return donasiDAO.findDonasiPending();
    }

    public List<Donasi> getDonasiBerhasil() {
        return donasiDAO.findDonasiBerhasil();
    }

    public List<Donasi> getAllDonasi() {
        return donasiDAO.findAll();
    }

    public BigDecimal getTotalDonasiDonatur(Integer idDonatur) {
        return donaturDAO.getTotalDonasi(idDonatur);
    }

    public BigDecimal getTotalDonasiKebutuhan(Integer idKebutuhan) {
        return donasiDAO.getTotalDonasiByKebutuhan(idKebutuhan);
    }

    public BigDecimal getTotalSemuaDonasi() {
        return donasiDAO.getTotalDonasiBerhasil();
    }

    public List<Donasi> getTopDonasi(int limit) {
        return donasiDAO.getTopDonasi(limit);
    }

    public List<Donasi> getDonasiTerbaru(int limit) {
        return donasiDAO.getDonasiTerbaru(limit);
    }

    public List<Donasi> getRiwayatDonasiByDonatur(Integer idDonatur) {
        return getRiwayatDonasi(idDonatur);
    }

    public List<Donasi> getDonasiBySekolah(Integer idSekolah) {
        return donasiDAO.findBySekolah(idSekolah);
    }

    public Optional<Donasi> getDonasiById(Integer id) {
        return donasiDAO.findById(id);
    }

    public List<Kebutuhan> getKebutuhanTersediaUntukDonasi() {
        return kebutuhanDAO.findKebutuhanTersedia();
    }

    // =========================================================
    // 🧹 Resource cleanup
    // =========================================================
    public void close() {
        try {
            if (donasiDAO instanceof DonasiDAOImpl) {
                ((DonasiDAOImpl) donasiDAO).close();
            }
            if (kebutuhanDAO instanceof KebutuhanDAOImpl) {
                ((KebutuhanDAOImpl) kebutuhanDAO).close();
            }
            if (donaturDAO instanceof DonaturDAOImpl) {
                ((DonaturDAOImpl) donaturDAO).close();
            }
            System.out.println("✅ Semua EntityManager di DonasiService berhasil ditutup.");
        } catch (Exception e) {
            System.err.println("❌ Gagal menutup resource di DonasiService: " + e.getMessage());
        }
    }

    // =========================================================
    // Result wrapper
    // =========================================================
    public static class DonasiResult {
        private final boolean success;
        private final String message;

        public DonasiResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() { return success; }

        public String getMessage() { return message; }
    }
}
