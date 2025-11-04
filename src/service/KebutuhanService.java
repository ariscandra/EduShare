package service;

import dao.*;
import model.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * KebutuhanService
 * Service untuk handle business logic kebutuhan sekolah
 * 
 * @author EduShare
 */
public class KebutuhanService {
    
    private final KebutuhanDAO kebutuhanDAO;
    private final SekolahDAO sekolahDAO;
    
    public KebutuhanService() {
        this.kebutuhanDAO = new KebutuhanDAOImpl();
        this.sekolahDAO = new SekolahDAOImpl();
    }
    
    /**
     * BUSINESS LOGIC: Tambah kebutuhan baru (untuk pihak sekolah)
     */
    public OperationResult tambahKebutuhan(Integer idSekolah, String namaAlat, Integer jumlah,
                                            String prioritas, String deskripsi,
                                            BigDecimal totalKebutuhan, BigDecimal targetDonasi) {
        // Validasi input dasar
        if (namaAlat == null || namaAlat.trim().isEmpty()) {
            return new OperationResult(false, "Nama alat tidak boleh kosong");
        }
        if (jumlah == null || jumlah <= 0) {
            return new OperationResult(false, "Jumlah harus lebih dari 0");
        }
        if (prioritas == null || prioritas.trim().isEmpty()) {
            return new OperationResult(false, "Prioritas harus dipilih");
        }
        if (totalKebutuhan == null || totalKebutuhan.compareTo(BigDecimal.ZERO) <= 0) {
            return new OperationResult(false, "Total kebutuhan harus lebih dari 0");
        }
        if (targetDonasi == null || targetDonasi.compareTo(BigDecimal.ZERO) <= 0) {
            return new OperationResult(false, "Target donasi harus lebih dari 0");
        }

        try {
            // Validasi sekolah
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isEmpty()) {
                return new OperationResult(false, "Sekolah tidak ditemukan");
            }
            
            Sekolah sekolah = sekolahOpt.get();

            // Buat kebutuhan baru
            Kebutuhan kebutuhan = new Kebutuhan();
            kebutuhan.setSekolah(sekolah);
            kebutuhan.setNamaAlat(namaAlat);
            kebutuhan.setJumlah(jumlah);
            kebutuhan.setPrioritas(Kebutuhan.Prioritas.valueOf(prioritas));
            kebutuhan.setDeskripsi(deskripsi);
            kebutuhan.setTotalKebutuhan(totalKebutuhan);
            kebutuhan.setTargetDonasi(targetDonasi);
            kebutuhan.setStatus(Kebutuhan.Status.diajukan);
            
            kebutuhanDAO.save(kebutuhan);
            
            return new OperationResult(true, "Kebutuhan berhasil diajukan");
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * BUSINESS LOGIC: Update kebutuhan
     */
    public OperationResult updateKebutuhan(Integer idKebutuhan, String namaAlat, Integer jumlah,
                                            String prioritas, String deskripsi,
                                            BigDecimal totalKebutuhan, BigDecimal targetDonasi) {
        // Validasi input dasar
        if (namaAlat == null || namaAlat.trim().isEmpty()) {
            return new OperationResult(false, "Nama alat tidak boleh kosong");
        }
        if (jumlah == null || jumlah <= 0) {
            return new OperationResult(false, "Jumlah harus lebih dari 0");
        }

        try {
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new OperationResult(false, "Kebutuhan tidak ditemukan");
            }
            
            Kebutuhan kebutuhan = kebutuhanOpt.get();
            
            // Hanya bisa update kebutuhan yang belum diverifikasi
            if (kebutuhan.getStatus() != Kebutuhan.Status.diajukan) {
                return new OperationResult(false, "Tidak bisa update kebutuhan yang sudah diproses");
            }
            
            kebutuhan.setNamaAlat(namaAlat);
            kebutuhan.setJumlah(jumlah);
            if (prioritas != null && !prioritas.isEmpty()) {
                kebutuhan.setPrioritas(Kebutuhan.Prioritas.valueOf(prioritas));
            }
            kebutuhan.setDeskripsi(deskripsi);
            
            if (totalKebutuhan != null) kebutuhan.setTotalKebutuhan(totalKebutuhan);
            if (targetDonasi != null) kebutuhan.setTargetDonasi(targetDonasi);
            
            kebutuhanDAO.update(kebutuhan);
            
            return new OperationResult(true, "Kebutuhan berhasil diupdate");
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    // ====== BUSINESS LOGIC LAINNYA (tidak diubah) ======
    public OperationResult verifikasiKebutuhan(Integer idKebutuhan) {
        try {
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new OperationResult(false, "Kebutuhan tidak ditemukan");
            }
            
            Kebutuhan kebutuhan = kebutuhanOpt.get();
            if (kebutuhan.getStatus() != Kebutuhan.Status.diajukan) {
                return new OperationResult(false, "Kebutuhan sudah diverifikasi sebelumnya");
            }
            
            kebutuhan.verifikasi();
            kebutuhanDAO.update(kebutuhan);
            
            return new OperationResult(true, "Kebutuhan berhasil diverifikasi");
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    public OperationResult tolakKebutuhan(Integer idKebutuhan) {
        try {
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new OperationResult(false, "Kebutuhan tidak ditemukan");
            }
            
            Kebutuhan kebutuhan = kebutuhanOpt.get();
            if (kebutuhan.getStatus() != Kebutuhan.Status.diajukan) {
                return new OperationResult(false, "Kebutuhan sudah diverifikasi sebelumnya");
            }
            
            kebutuhan.tolak();
            kebutuhanDAO.update(kebutuhan);
            
            return new OperationResult(true, "Kebutuhan berhasil ditolak");
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    public OperationResult tandaiTerpenuhi(Integer idKebutuhan) {
        try {
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new OperationResult(false, "Kebutuhan tidak ditemukan");
            }
            
            Kebutuhan kebutuhan = kebutuhanOpt.get();
            if (kebutuhan.getStatus() != Kebutuhan.Status.diverifikasi) {
                return new OperationResult(false, "Hanya kebutuhan terverifikasi yang bisa ditandai terpenuhi");
            }
            
            kebutuhan.tandaiTerpenuhi();
            kebutuhanDAO.update(kebutuhan);
            
            return new OperationResult(true, "Kebutuhan berhasil ditandai terpenuhi");
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    public List<Kebutuhan> getKebutuhanBySekolah(Integer idSekolah) {
        return kebutuhanDAO.findByIdSekolah(idSekolah);
    }

    public List<Kebutuhan> getKebutuhanTerverifikasi() {
        return kebutuhanDAO.findKebutuhanTerverifikasi();
    }

    public List<Kebutuhan> getKebutuhanMenungguVerifikasi() {
        return kebutuhanDAO.findKebutuhanMenungguVerifikasi();
    }

    public List<Kebutuhan> getKebutuhanAktif() {
        return kebutuhanDAO.findKebutuhanAktif();
    }

    public List<Kebutuhan> getKebutuhanPrioritasTinggi() {
        return kebutuhanDAO.findKebutuhanPrioritasTinggi();
    }

    public List<Kebutuhan> searchKebutuhan(String namaAlat) {
        return kebutuhanDAO.searchByNamaItem(namaAlat);
    }

    public List<Kebutuhan> filterKebutuhan(Integer idSekolah, String status, String prioritas) {
        return kebutuhanDAO.searchWithFilters(idSekolah, status, prioritas);
    }

    public Optional<Kebutuhan> getKebutuhanById(Integer id) {
        return kebutuhanDAO.findById(id);
    }

    public OperationResult deleteKebutuhan(Integer idKebutuhan) {
        try {
            Optional<Kebutuhan> kebutuhanOpt = kebutuhanDAO.findById(idKebutuhan);
            if (kebutuhanOpt.isEmpty()) {
                return new OperationResult(false, "Kebutuhan tidak ditemukan");
            }
            
            Kebutuhan kebutuhan = kebutuhanOpt.get();
            if (!kebutuhan.getDaftarDonasi().isEmpty()) {
                return new OperationResult(false, "Tidak bisa menghapus kebutuhan yang sudah ada donasi");
            }
            
            kebutuhanDAO.deleteById(idKebutuhan);
            return new OperationResult(true, "Kebutuhan berhasil dihapus");
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    public static class OperationResult {
        private final boolean success;
        private final String message;
        
        public OperationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
    /**
    * Hitung persentase kebutuhan terpenuhi
    * Rumus: (jumlah kebutuhan status=terpenuhi / total kebutuhan) * 100
    */
   public double getPersentaseKebutuhanTerpenuhi() {
       try {
           List<Kebutuhan> semuaKebutuhan = kebutuhanDAO.findAll();
           if (semuaKebutuhan == null || semuaKebutuhan.isEmpty()) {
               return 0.0;
           }

           long terpenuhi = semuaKebutuhan.stream()
                   .filter(k -> k.getStatus() == Kebutuhan.Status.terpenuhi)
                   .count();

           return (terpenuhi * 100.0) / semuaKebutuhan.size();
       } catch (Exception e) {
           System.err.println("Error menghitung persentase kebutuhan terpenuhi: " + e.getMessage());
           return 0.0;
       }
   }
   
   public BigDecimal getTotalKebutuhanDiajukan() {
        try {
            List<Kebutuhan> daftar = kebutuhanDAO.findAll();
            if (daftar == null || daftar.isEmpty()) return BigDecimal.ZERO;

            BigDecimal total = BigDecimal.ZERO;
            for (Kebutuhan k : daftar) {
                if (k.getStatus() == Kebutuhan.Status.diajukan && k.getTotalKebutuhan() != null) {
                    total = total.add(k.getTotalKebutuhan());
                }
            }
            return total;
        } catch (Exception e) {
            System.err.println("Error getTotalKebutuhanDiajukan: " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }

   /**
    * Total kebutuhan diajukan berdasarkan sekolah
    */
   public BigDecimal getTotalKebutuhanDiajukanBySekolah(Integer idSekolah) {
       try {
           List<Kebutuhan> daftar = kebutuhanDAO.findByIdSekolah(idSekolah);
           if (daftar == null || daftar.isEmpty()) return BigDecimal.ZERO;

           BigDecimal total = BigDecimal.ZERO;
           for (Kebutuhan k : daftar) {
               if (k.getTotalKebutuhan() != null) {
                   total = total.add(k.getTotalKebutuhan());
               }
           }
           return total;
       } catch (Exception e) {
           System.err.println("Error getTotalKebutuhanDiajukanBySekolah: " + e.getMessage());
           return BigDecimal.ZERO;
       }
   }

    
    public void close() {
        if (kebutuhanDAO instanceof KebutuhanDAOImpl dao) dao.close();
        if (sekolahDAO instanceof SekolahDAOImpl dao) dao.close();
    }
}
