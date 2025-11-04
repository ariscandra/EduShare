package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity Kebutuhan
 * Menyimpan daftar alat/barang yang diajukan oleh sekolah.
 * Sudah disesuaikan dengan DonasiService (mendukung target & total donasi).
 * 
 * @author EduShare
 */
@Entity
@Table(name = "kebutuhan")
public class Kebutuhan implements Serializable {

    private static final long serialVersionUID = 1L;

    // ==============================
    // 🔒 ENCAPSULATION: Private fields
    // ==============================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kebutuhan")
    private Integer idKebutuhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", referencedColumnName = "id_sekolah", nullable = false)
    private Sekolah sekolah;

    @Column(name = "nama_alat", nullable = false, length = 100)
    private String namaAlat;

    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioritas", nullable = false)
    private Prioritas prioritas;

    @Column(name = "deskripsi", columnDefinition = "TEXT")
    private String deskripsi;

    @Column(name = "tanggal_pengajuan", nullable = false)
    private LocalDate tanggalPengajuan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "total_kebutuhan", precision = 15, scale = 2)
    private BigDecimal totalKebutuhan = BigDecimal.ZERO;
    
    // 🎯 Target donasi untuk kebutuhan ini
    @Column(name = "target_donasi", precision = 15, scale = 2)
    private BigDecimal targetDonasi = BigDecimal.ZERO;

    // 💰 Total donasi yang sudah terkumpul
    @Column(name = "total_terkumpul", precision = 15, scale = 2)
    private BigDecimal totalTerkumpul = BigDecimal.ZERO;

    // ==============================
    // 🔗 RELASI
    // ==============================

    @OneToMany(mappedBy = "kebutuhan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donasi> daftarDonasi = new ArrayList<>();

    // ==============================
    // 📊 ENUM
    // ==============================

    public enum Prioritas {
        tinggi, sedang, rendah
    }

    public enum Status {
        diajukan, diverifikasi, ditolak, terpenuhi
    }

    // ==============================
    // 🧩 CONSTRUCTOR
    // ==============================

    public Kebutuhan() {
        this.tanggalPengajuan = LocalDate.now();
        this.status = Status.diajukan;
    }

    public Kebutuhan(Sekolah sekolah, String namaAlat, Integer jumlah, Prioritas prioritas, String deskripsi, BigDecimal targetDonasi) {
        this.sekolah = sekolah;
        this.namaAlat = namaAlat;
        this.jumlah = jumlah;
        this.prioritas = prioritas;
        this.deskripsi = deskripsi;
        this.targetDonasi = targetDonasi;
        this.tanggalPengajuan = LocalDate.now();
        this.status = Status.diajukan;
    }

    // ==============================
    // ⚙️ BUSINESS LOGIC
    // ==============================

    public void tambahDonasi(Donasi donasi) {
        daftarDonasi.add(donasi);
        donasi.setKebutuhan(this);
        updateTotalDonasi(); // auto update total setiap kali donasi masuk
    }

    /**
     * Mengupdate total donasi berdasarkan daftarDonasi.
     */
    public void updateTotalDonasi() {
        BigDecimal total = BigDecimal.ZERO;
        for (Donasi d : daftarDonasi) {
            if (d.getStatusDonasi() == Donasi.StatusDonasi.berhasil) {
                total = total.add(d.getJumlahDonasi());
            }
        }
        this.totalTerkumpul = total;
    }

    public boolean isDiverifikasi() {
        return status == Status.diverifikasi;
    }

    /**
     * Mengecek apakah kebutuhan sudah terpenuhi berdasarkan status atau target tercapai.
     */
    public boolean isTerpenuhi() {
        if (status == Status.terpenuhi) return true;
        if (targetDonasi != null && totalTerkumpul != null) {
            return totalTerkumpul.compareTo(targetDonasi) >= 0;
        }
        return false;
    }

    public void verifikasi() {
        if (status == Status.diajukan) {
            this.status = Status.diverifikasi;
        }
    }

    public void tolak() {
        if (status == Status.diajukan) {
            this.status = Status.ditolak;
        }
    }

    public void tandaiTerpenuhi() {
        if (status == Status.diverifikasi) {
            this.status = Status.terpenuhi;
        }
    }

    // ==============================
    // 🧾 GETTER & SETTER
    // ==============================

    public Integer getIdKebutuhan() {
        return idKebutuhan;
    }

    public void setIdKebutuhan(Integer idKebutuhan) {
        this.idKebutuhan = idKebutuhan;
    }

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlat = namaAlat;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Prioritas getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(Prioritas prioritas) {
        this.prioritas = prioritas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(LocalDate tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Donasi> getDaftarDonasi() {
        return daftarDonasi;
    }

    public void setDaftarDonasi(List<Donasi> daftarDonasi) {
        this.daftarDonasi = daftarDonasi;
    }
    
    public BigDecimal getTotalKebutuhan() {
        return totalKebutuhan;
    }

    public void setTotalKebutuhan(BigDecimal totalKebutuhan) {
        this.totalKebutuhan = totalKebutuhan;
    }


    public BigDecimal getTargetDonasi() {
        return targetDonasi;
    }

    public void setTargetDonasi(BigDecimal targetDonasi) {
        this.targetDonasi = targetDonasi;
    }

    public BigDecimal getTotalTerkumpul() {
        return totalTerkumpul;
    }

    public void setTotalTerkumpul(BigDecimal totalTerkumpul) {
        this.totalTerkumpul = totalTerkumpul;
    }

    // ==============================
    // 🧮 UTILITIES
    // ==============================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kebutuhan)) return false;
        Kebutuhan kebutuhan = (Kebutuhan) o;
        return Objects.equals(idKebutuhan, kebutuhan.idKebutuhan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKebutuhan);
    }

    @Override
    public String toString() {
        return "Kebutuhan{" +
                "idKebutuhan=" + idKebutuhan +
                ", namaAlat='" + namaAlat + '\'' +
                ", jumlah=" + jumlah +
                ", prioritas=" + prioritas +
                ", status=" + status +
                ", targetDonasi=" + targetDonasi +
                ", totalTerkumpul=" + totalTerkumpul +
                ", tanggalPengajuan=" + tanggalPengajuan +
                '}';
    }
}
