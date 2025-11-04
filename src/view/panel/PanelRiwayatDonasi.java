/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panel;

import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Admin;
import model.Donasi;
import model.Donatur;
import model.Pengguna;
import model.PihakSekolah;
import service.AuthenticationService;
import service.DonasiService;

/**
 *
 * @author ACER NITRO
 */
public class PanelRiwayatDonasi extends javax.swing.JPanel {

   private final DonasiService donasiService;
    private final DefaultTableModel modelDonasi;
    private final Pengguna penggunaLogin;

    public PanelRiwayatDonasi() {
        initComponents();
        this.donasiService = new DonasiService();
        this.penggunaLogin = AuthenticationService.getCurrentUser();

        modelDonasi = new DefaultTableModel(
            new Object[]{"Nama Sekolah", "Kebutuhan", "Jumlah", "Tanggal", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRiwayatDonasi.setModel(modelDonasi);

        styleTable();
        loadRiwayatDonasi();
    }

    /**
     * Styling tabel (modern clean style)
     */
    private void styleTable() {
        tblRiwayatDonasi.setRowHeight(28);
        tblRiwayatDonasi.setShowGrid(true);
        tblRiwayatDonasi.setGridColor(new Color(220, 220, 220));
        tblRiwayatDonasi.setIntercellSpacing(new java.awt.Dimension(10, 5));
        tblRiwayatDonasi.getTableHeader().setReorderingAllowed(false);
        tblRiwayatDonasi.getTableHeader().setFont(new java.awt.Font("Poppins SemiBold", 0, 13));
        tblRiwayatDonasi.setFont(new java.awt.Font("Poppins", 0, 12));
        tblRiwayatDonasi.setSelectionBackground(new Color(66, 135, 245));
        tblRiwayatDonasi.setSelectionForeground(Color.WHITE);
        tblRiwayatDonasi.setFillsViewportHeight(true);

        // lebar kolom
        TableColumnModel columnModel = tblRiwayatDonasi.getColumnModel();
        int[] widths = {160, 160, 100, 100, 100};
        for (int i = 0; i < widths.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(widths[i]);
        }

        // center alignment untuk angka & status
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblRiwayatDonasi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblRiwayatDonasi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblRiwayatDonasi.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    /**
     * Load riwayat donasi menyesuaikan role pengguna
     */
    private void loadRiwayatDonasi() {
        try {
            modelDonasi.setRowCount(0);

            Object user = AuthenticationService.getCurrentUser();
            System.out.println("🔍 Logged-in user: " + (user != null ? user.getClass().getSimpleName() : "null"));

            List<Donasi> list = null;

            if (user instanceof PihakSekolah sekolahLogin) {
                // ambil ID sekolah dari PihakSekolah
                list = donasiService.getDonasiBySekolah(sekolahLogin.getIdSekolah());
                jLabel14.setText("Riwayat Donasi Masuk ke Sekolah Anda");
            } 
            else if (user instanceof Donatur donaturLogin) {
                list = donasiService.getRiwayatDonasiByDonatur(donaturLogin.getIdPengguna());
                jLabel14.setText("Riwayat Donasi Anda");
            } 
            else if (user instanceof Admin) {
                jLabel14.setText("Riwayat Donasi (Admin View)");
                // opsional: list = donasiService.getAllDonasi();
            } 
            else {
                jLabel14.setText("Riwayat Donasi Tidak Dikenali");
                JOptionPane.showMessageDialog(this,
                        "Tipe pengguna tidak dikenali. Silakan login ulang.",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (list == null || list.isEmpty()) {
                kosongLabel.setVisible(true);
                kosongLabel.setText("Belum ada data donasi untuk sekolah ini.");
                tblRiwayatDonasi.setVisible(true);
                return;
            }

            kosongLabel.setVisible(false);

            for (Donasi d : list) {
                String namaSekolah = (d.getKebutuhan() != null && d.getKebutuhan().getSekolah() != null)
                        ? d.getKebutuhan().getSekolah().getNamaSekolah() : "-";
                String namaKebutuhan = (d.getKebutuhan() != null)
                        ? d.getKebutuhan().getNamaAlat() : "-";
                String jumlah = (d.getJumlahDonasi() != null)
                        ? String.format("Rp %,d", d.getJumlahDonasi().intValue()) : "-";
                String tanggal = (d.getTanggalDonasi() != null)
                        ? d.getTanggalDonasi().toString() : "-";
                String status = (d.getStatusDonasi() != null)
                        ? d.getStatusDonasi().toString().toUpperCase() : "-";

                modelDonasi.addRow(new Object[]{namaSekolah, namaKebutuhan, jumlah, tanggal, status});
            }

            System.out.println("✅ Data donasi berhasil dimuat: " + list.size() + " entri");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat riwayat donasi: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRiwayatDonasi = new javax.swing.JTable();
        kosongLabel = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Riwayat Donasi");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tblRiwayatDonasi.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRiwayatDonasi.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblRiwayatDonasi);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 630, 540));

        kosongLabel.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        kosongLabel.setForeground(new java.awt.Color(51, 51, 51));
        kosongLabel.setText("Belum ada yang donasi ke sekolah kamu");
        jPanel8.add(kosongLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel kosongLabel;
    private javax.swing.JTable tblRiwayatDonasi;
    // End of variables declaration//GEN-END:variables
}
