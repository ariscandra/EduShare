/*
 * Dashboard Admin Panel - Analytics & KPI Dashboard
 * Menampilkan real-time metrics dan statistik sistem donasi
 */
package view.panel;

import dao.*;
import model.*;
import service.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 * PanelDashboardAdmin
 * Dashboard analytics dengan real-time KPIs untuk Admin
 * 
 * @author EduShare Team
 */
public class PanelDashboardAdmin extends javax.swing.JPanel {

    // Service Layer Dependencies
    private final SekolahService sekolahService;
    private final DonasiService donasiService;
    private final KebutuhanService kebutuhanService;   // <-- ditambahkan
    private final KebutuhanDAO kebutuhanDAO;           // Direct DAO untuk analytics (optional)

    // Currency Formatter
    private final NumberFormat currencyFormat;

    /**
     * Creates new form PanelDashboardAdmin dengan full service integration
     */
    public PanelDashboardAdmin() {
        // Initialize services
        this.sekolahService = new SekolahService();
        this.donasiService = new DonasiService();
        this.kebutuhanService = new KebutuhanService();       // <-- ditambahkan
        this.kebutuhanDAO = new KebutuhanDAOImpl();

        // Setup currency format untuk IDR
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        initComponents();

        // Load dashboard data saat panel dibuat
        loadDashboardData();
    }

    /**
     * Load semua data dashboard - KPIs, statistik, top donations
     */
    private void loadDashboardData() {
        try {
            // Load KPI metrics
            loadKPIMetrics();

            // Load monthly statistics
            loadMonthlyStatistics();

            // Load top donations table
            loadTableManajemenSekolah();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading dashboard data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Load KPI Metrics - Total Sekolah, Total Donasi, Fulfillment Rate
     */
    private void loadKPIMetrics() {
        try {
            // KPI 1️⃣ Total Sekolah Terverifikasi
            int totalSekolah = sekolahService.getTotalSekolahTerverifikasi(); // <-- sebelumnya tidak ada
            jLabel11.setText(String.valueOf(totalSekolah));

            // KPI 2️⃣ Total Donasi
            BigDecimal totalDonasi = donasiService.getTotalDonasiKeseluruhan();
            jLabel9.setText(totalDonasi != null ? formatLargeNumber(totalDonasi) : currencyFormat.format(0));

            // KPI 3️⃣ Fulfillment Rate
            double fulfillmentRate = kebutuhanService.getPersentaseKebutuhanTerpenuhi(); // <-- sebelumnya pakai var yg belum dideklarasi
            jLabel12.setText(String.format("%.1f%%", fulfillmentRate));

        } catch (Exception e) {
            System.err.println("Error loading KPI metrics: " + e.getMessage());
            jLabel11.setText("0");
            jLabel9.setText(currencyFormat.format(0));
            jLabel12.setText("0%");
        }
    }

    /**
     * Load Monthly Statistics - Grafik Donasi Bulanan
     */
    private void loadMonthlyStatistics() {
        try {
            int currentYear = LocalDate.now().getYear();
            Map<Integer, BigDecimal> monthlyStats = donasiService.getStatistikBulanan(currentYear);

            // Calculate total untuk tahun ini
            BigDecimal yearlyTotal = BigDecimal.ZERO;
            if (monthlyStats != null) {
                for (BigDecimal monthlyAmount : monthlyStats.values()) {
                    if (monthlyAmount != null) {
                        yearlyTotal = yearlyTotal.add(monthlyAmount);
                    }
                }
            }

            // Update yearly total label
            jLabel13.setText(currencyFormat.format(yearlyTotal));
            jLabel7.setText("Januari - Desember " + currentYear);

        } catch (Exception e) {
            System.err.println("Error loading monthly statistics: " + e.getMessage());
            jLabel13.setText(currencyFormat.format(0));
        }
    }

    /**
     * Load Top Donations Table - 10 Donasi Terbesar
     */
    private void loadTableManajemenSekolah() {
        try {
            // Setup table model
            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"No", "Nama Sekolah", "Lokasi", "Status Verifikasi", "Nama Kebutuhan"},
                    0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // tabel hanya baca
                }
            };

            // Ambil daftar sekolah dari service
            List<Sekolah> daftarSekolah = sekolahService.getAllSekolah(); // pastikan method ini ada di SekolahService

            int no = 1;
            if (daftarSekolah != null && !daftarSekolah.isEmpty()) {
                for (Sekolah sekolah : daftarSekolah) {
                    String namaSekolah = sekolah.getNamaSekolah() != null ? sekolah.getNamaSekolah() : "-";
                    String lokasi = sekolah.getAlamatSekolah()!= null ? sekolah.getAlamatSekolah()  : "-";
                    String status = sekolah.getStatusVerifikasi() != null ? sekolah.getStatusVerifikasi() : "Menunggu";

                    // Ambil satu nama kebutuhan (misal kebutuhan terbaru atau prioritas tinggi)
                    String namaKebutuhan = "-";
                    try {
                        List<Kebutuhan> kebutuhanList = kebutuhanService.getKebutuhanBySekolah(sekolah.getIdSekolah());
                        if (kebutuhanList != null && !kebutuhanList.isEmpty()) {
                            // Ambil kebutuhan pertama atau prioritas tinggi
                            Kebutuhan kebutuhan = kebutuhanList.get(0);
                            if (kebutuhan.getNamaAlat() != null) {
                                namaKebutuhan = kebutuhan.getNamaAlat();
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Error mengambil kebutuhan sekolah: " + ex.getMessage());
                    }

                    // Tambahkan baris ke tabel
                    model.addRow(new Object[]{
                            no++,
                            namaSekolah,
                            lokasi,
                            status,
                            namaKebutuhan
                    });
                }
            }

            // Pasang model ke tabel GUI
            tableSkolah.setModel(model);

            // Konfigurasi tampilan tabel
            tableSkolah.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableSkolah.getTableHeader().setReorderingAllowed(false);

            // Atur lebar kolom agar proporsional
            if (tableSkolah.getColumnModel().getColumnCount() > 0) {
                tableSkolah.getColumnModel().getColumn(0).setPreferredWidth(40);   // No
                tableSkolah.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nama Sekolah
                tableSkolah.getColumnModel().getColumn(2).setPreferredWidth(200);  // Lokasi
                tableSkolah.getColumnModel().getColumn(3).setPreferredWidth(120);  // Status
                tableSkolah.getColumnModel().getColumn(4).setPreferredWidth(200);  // Nama Kebutuhan
            }

        } catch (Exception e) {
            System.err.println("Error loading data manajemen sekolah: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Format large numbers dengan K, M, B notation
     * Example: 5_080_000 -> "Rp 5.08M"
     */
    private String formatLargeNumber(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            return currencyFormat.format(0);
        }

        double value = amount.doubleValue();

        if (value >= 1_000_000_000) {
            return String.format("Rp %.2fB", value / 1_000_000_000d);
        } else if (value >= 1_000_000) {
            return String.format("Rp %.2fM", value / 1_000_000d);
        } else if (value >= 1_000) {
            return String.format("Rp %.2fK", value / 1_000d);
        } else {
            return currencyFormat.format(amount);
        }
    }

    /**
     * Refresh dashboard data - dipanggil saat panel ditampilkan
     */
    public void refreshDashboard() {
        SwingUtilities.invokeLater(this::loadDashboardData);
    }

    /**
     * Cleanup resources saat panel tidak digunakan
     * Aman bila service tidak implement close()
     */
    public void cleanup() {
        try {
            if (sekolahService instanceof AutoCloseable ac) ac.close();
            if (donasiService instanceof AutoCloseable ac) ac.close();
            if (kebutuhanService instanceof AutoCloseable ac) ac.close();
            if (kebutuhanDAO instanceof AutoCloseable ac) ac.close();
        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSkolah = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Total Sekolah Terverifikasi");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("1,234");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 40));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 110));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Total Donasi Terkumpul");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Rp 5,08M");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 40));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 220, 110));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Kebutuhan Terpenuhi");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("75%");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 40));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 210, 110));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Januari - Desember 2025");
        jPanel8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel13.setFont(new java.awt.Font("Poppins SemiBold", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Rp 1,234,567,890");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 40));

        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Grafik Donasi Bulanan");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 670, 170));

        tableSkolah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableSkolah);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 630, 253));

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Manajemen sekolah terbaru");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableSkolah;
    // End of variables declaration//GEN-END:variables
}
