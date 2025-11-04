package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donatur")
@DiscriminatorValue("Donatur")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class Donatur extends Pengguna implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = true)
    private Sekolah sekolah;

    // ✅ pakai BigDecimal, bukan String
    @Column(name = "total_donasi", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalDonasi = BigDecimal.ZERO;

    @OneToMany(mappedBy = "donatur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donasi> daftarDonasi = new ArrayList<>();

    public Donatur() {
        super();
    }

    public Donatur(String nama, String email, String password, String noHp, String alamat, Sekolah sekolah) {
        super(nama, email, password, noHp, alamat);
        this.sekolah = sekolah;
        this.totalDonasi = BigDecimal.ZERO;
    }

    @Override
    public String getRole() {
        return "Donatur";
    }

    @Override
    public String getInfoLengkap() {
        String infoSekolah = (sekolah != null) ? sekolah.getNamaSekolah() : "Tidak ada sekolah";
        return super.getInfoLengkap() + String.format(
                ", Role: Donatur, Total Donasi: %s, Sekolah: %s",
                getTotalDonasiFormatted(), infoSekolah);
    }

    public void tambahDonasi(Donasi donasi) {
        daftarDonasi.add(donasi);
        donasi.setDonatur(this);
    }

    public Sekolah getSekolah() { return sekolah; }
    public void setSekolah(Sekolah sekolah) { this.sekolah = sekolah; }

    // ✅ BigDecimal getter/setter
    public BigDecimal getTotalDonasi() {
        return totalDonasi != null ? totalDonasi : BigDecimal.ZERO;
    }
    public void setTotalDonasi(BigDecimal totalDonasi) {
        this.totalDonasi = (totalDonasi != null) ? totalDonasi : BigDecimal.ZERO;
    }

    // 👉 helper utk tampilan
    @Transient
    public String getTotalDonasiFormatted() {
        return String.format("Rp %,d", getTotalDonasi().setScale(0, java.math.RoundingMode.DOWN).intValue());
    }

    public List<Donasi> getDaftarDonasi() { return daftarDonasi; }
    public void setDaftarDonasi(List<Donasi> daftarDonasi) { this.daftarDonasi = daftarDonasi; }

    @Override
    public String toString() {
        return "Donatur{" +
                "idPengguna=" + getIdPengguna() +
                ", nama='" + getNama() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", totalDonasi=" + totalDonasi +
                ", sekolah=" + (sekolah != null ? sekolah.getNamaSekolah() : "null") +
                '}';
    }
}
