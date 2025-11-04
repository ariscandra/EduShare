/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panel;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Donasi;
import model.Kebutuhan;
import model.Pengguna;
import service.AuthenticationService;
import service.DonasiService;
import service.KebutuhanService;

/**
 *
 * @author ACER NITRO
 */
public class PanelDashboardDonatur extends javax.swing.JPanel {

    /**
     * Creates new form PanelDashboardDonatur
     */
    private final KebutuhanService kebutuhanService;
    private final DonasiService donasiService;
    private DefaultTableModel modelKebutuhan;
    private DefaultTableModel modelDonasi;

    public PanelDashboardDonatur(Pengguna user) {
        initComponents();
        kebutuhanService = new KebutuhanService();
        donasiService = new DonasiService();
        loadTabelKebutuhan();
        loadRiwayatDonasi();
        
        styleTable(tableKebutuhan);
        styleTable(tabelRiwayatDonasi);
    }
    
    private void styleTable(javax.swing.JTable table) {
        table.setFont(new java.awt.Font("Poppins", 0, 12));
        table.setRowHeight(28);
        table.setShowGrid(true);
        table.setGridColor(new java.awt.Color(220, 220, 220));
        table.setIntercellSpacing(new java.awt.Dimension(10, 5));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new java.awt.Font("Poppins SemiBold", 0, 13));
        table.setSelectionBackground(new java.awt.Color(66, 135, 245));
        table.setSelectionForeground(java.awt.Color.WHITE);
        table.setFillsViewportHeight(true);

        // Center alignment untuk angka & tanggal
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        int columnCount = table.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            String name = table.getColumnName(i).toLowerCase();
            if (name.contains("jumlah") || name.contains("tanggal") || name.contains("prioritas")) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }
    
    /**
     * Memuat tabel daftar kebutuhan sekolah yang diverifikasi
     */
    private void loadTabelKebutuhan() {
        modelKebutuhan = new DefaultTableModel(
            new Object[]{"Nama Sekolah", "Nama Alat", "Prioritas", "Keterangan"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableKebutuhan.setModel(modelKebutuhan);

        try {
            List<Kebutuhan> list = kebutuhanService.getKebutuhanTerverifikasi();

            modelKebutuhan.setRowCount(0);
            for (Kebutuhan k : list) {
                String namaSekolah = (k.getSekolah() != null)
                        ? k.getSekolah().getNamaSekolah() : "-";
                String prioritas = (k.getPrioritas() != null)
                        ? k.getPrioritas().name().toUpperCase() : "-";
                String deskripsi = (k.getDeskripsi() != null && !k.getDeskripsi().isEmpty())
                        ? k.getDeskripsi() : "(Tanpa deskripsi)";

                modelKebutuhan.addRow(new Object[]{
                    namaSekolah,
                    k.getNamaAlat(),
                    prioritas,
                    deskripsi
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Gagal memuat data kebutuhan: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Memuat tabel riwayat donasi berdasarkan Donatur yang login
     */
    private void loadRiwayatDonasi() {
        modelDonasi = new DefaultTableModel(
            new Object[]{"Nama Sekolah", "Nama Alat", "Jumlah Donasi", "Tanggal Donasi"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabelRiwayatDonasi.setModel(modelDonasi);

        try {
            Integer idDonatur = AuthenticationService.getCurrentUser().getIdPengguna();
            List<Donasi> list = donasiService.getRiwayatDonasiByDonatur(idDonatur);

            modelDonasi.setRowCount(0);
            for (Donasi d : list) {
                String namaSekolah = "-";
                String namaAlat = "-";

                if (d.getKebutuhan() != null) {
                    Kebutuhan k = d.getKebutuhan();
                    if (k.getSekolah() != null) {
                        namaSekolah = k.getSekolah().getNamaSekolah();
                    }
                    namaAlat = k.getNamaAlat();
                }

                String jumlah = (d.getJumlahDonasi() != null)
                        ? formatRupiah(d.getJumlahDonasi())
                        : "-";

                String tanggal = (d.getTanggalDonasi() != null)
                        ? d.getTanggalDonasi().toString()
                        : "-";

                modelDonasi.addRow(new Object[]{
                    namaSekolah, namaAlat, jumlah, tanggal
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Gagal memuat riwayat donasi: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Format BigDecimal ke format rupiah sederhana
     */
    private String formatRupiah(BigDecimal nominal) {
        if (nominal == null) return "-";
        return String.format("Rp %,d", nominal.intValue());
    }

    /**
     * Dipanggil saat panel ini ditutup agar koneksi DAO ditutup dengan aman.
     */
    public void cleanup() {
        try {
            if (donasiService != null) {
                donasiService.close();
            }
            if (kebutuhanService != null) {
                kebutuhanService.close();
            }
            System.out.println("✅ Resource PanelDashboardDonatur ditutup dengan aman.");
        } catch (Exception e) {
            System.err.println("Error saat cleanup panel donatur: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKebutuhan = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelRiwayatDonasi = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(900, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Daftar Terbaru Sekolah Yang Membutuhkan");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tableKebutuhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKebutuhan.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableKebutuhan);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 630, 310));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 670, 370));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Riwayat Donasi");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tabelRiwayatDonasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelRiwayatDonasi.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabelRiwayatDonasi);

        jPanel9.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 630, 190));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 670, 250));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 726));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabelRiwayatDonasi;
    private javax.swing.JTable tableKebutuhan;
    // End of variables declaration//GEN-END:variables
}
