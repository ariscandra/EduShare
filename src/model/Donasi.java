package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity Donasi
 * Menerapkan ENCAPSULATION dengan private fields dan getter/setter
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "donasi")
public class Donasi implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULATION: Private fields dengan getter/setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donasi")
    private Integer idDonasi;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pengguna", nullable = false)
    private Donatur donatur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kebutuhan", nullable = false)
    private Kebutuhan kebutuhan;
    
    @Column(name = "tanggal_donasi", nullable = false)
    private LocalDate tanggalDonasi;
    
    @Column(name = "jumlah_donasi", nullable = false, precision = 15, scale = 2)
    private BigDecimal jumlahDonasi;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "metode_pembayaran", nullable = false)
    private MetodePembayaran metodePembayaran;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_donasi", nullable = false)
    private StatusDonasi statusDonasi;
    
     @Column(name = "total_donasi", precision = 15, scale = 2)
    private BigDecimal totalDonasi = BigDecimal.ZERO;

    public BigDecimal getTotalDonasi() {
        return totalDonasi != null ? totalDonasi : BigDecimal.ZERO;
    }

    public void setTotalDonasi(BigDecimal totalDonasi) {
        this.totalDonasi = totalDonasi;
    }
    
    // Enum untuk metode pembayaran
    public enum MetodePembayaran {
        transfer, cash, qris, lainnya
    }
    
    // Enum untuk status donasi
    public enum StatusDonasi {
        pending, berhasil, gagal, dibatalkan
    }
    
    // Constructors
    public Donasi() {
        this.tanggalDonasi = LocalDate.now();
        this.statusDonasi = StatusDonasi.pending;
    }
    
    public Donasi(Donatur donatur, Kebutuhan kebutuhan, BigDecimal jumlahDonasi, MetodePembayaran metodePembayaran) {
        this.donatur = donatur;
        this.kebutuhan = kebutuhan;
        this.jumlahDonasi = jumlahDonasi;
        this.metodePembayaran = metodePembayaran;
        this.tanggalDonasi = LocalDate.now();
        this.statusDonasi = StatusDonasi.pending;
    }
    
    // Business methods
    public void konfirmasiBerhasil() {
        if (statusDonasi == StatusDonasi.pending) {
            this.statusDonasi = StatusDonasi.berhasil;
        }
    }
    
    public void tandaiGagal() {
        if (statusDonasi == StatusDonasi.pending) {
            this.statusDonasi = StatusDonasi.gagal;
        }
    }
    
    public void batalkan() {
        if (statusDonasi == StatusDonasi.pending) {
            this.statusDonasi = StatusDonasi.dibatalkan;
        }
    }
    
    public boolean isBerhasil() {
        return statusDonasi == StatusDonasi.berhasil;
    }
    
    public String getJumlahDonasiFormatted() {
        return String.format("Rp %,.2f", jumlahDonasi);
    }
    
    // Getter and Setter
    public Integer getIdDonasi() {
        return idDonasi;
    }
    
    public void setIdDonasi(Integer idDonasi) {
        this.idDonasi = idDonasi;
    }
    
    public Donatur getDonatur() {
        return donatur;
    }
    
    public void setDonatur(Donatur donatur) {
        this.donatur = donatur;
    }
    
    public Kebutuhan getKebutuhan() {
        return kebutuhan;
    }
    
    public void setKebutuhan(Kebutuhan kebutuhan) {
        this.kebutuhan = kebutuhan;
    }
    
    public LocalDate getTanggalDonasi() {
        return tanggalDonasi;
    }
    
    public void setTanggalDonasi(LocalDate tanggalDonasi) {
        this.tanggalDonasi = tanggalDonasi;
    }
    
    public BigDecimal getJumlahDonasi() {
        return jumlahDonasi;
    }
    
    public void setJumlahDonasi(BigDecimal jumlahDonasi) {
        this.jumlahDonasi = jumlahDonasi;
    }
    
    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }
    
    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
    
    public StatusDonasi getStatusDonasi() {
        return statusDonasi;
    }
    
    public void setStatusDonasi(StatusDonasi statusDonasi) {
        this.statusDonasi = statusDonasi;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donasi)) return false;
        Donasi donasi = (Donasi) o;
        return Objects.equals(idDonasi, donasi.idDonasi);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idDonasi);
    }
    
    @Override
    public String toString() {
        return "Donasi{" +
                "idDonasi=" + idDonasi +
                ", jumlahDonasi=" + jumlahDonasi +
                ", tanggalDonasi=" + tanggalDonasi +
                ", metodePembayaran=" + metodePembayaran +
                ", statusDonasi=" + statusDonasi +
                '}';
    }
}
