/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panel;

import dao.KebutuhanDAO;
import dao.KebutuhanDAOImpl;
import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Kebutuhan;
import model.Sekolah;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import service.KebutuhanService;
import service.DonasiService;


/**
 *
 * @author ACER NITRO
 */
public class PanelDashboardSekolah extends javax.swing.JPanel {

    private final Sekolah sekolahLogin;
    private final DefaultTableModel tableModel;
    private final KebutuhanService kebutuhanService;
    private final DonasiService donasiService;
    private final NumberFormat currencyFormat;


    /**
     * Creates new form PanelDashboardSekolah
     */
    public PanelDashboardSekolah(Sekolah sekolahLogin) {
        initComponents();
        this.sekolahLogin = sekolahLogin;

        // ====== MODEL TABEL ======
        tableModel = new DefaultTableModel(
                new Object[]{"Nama Kebutuhan", "Jumlah", "Status Donasi"}, 0
        );
        tblKebutuhan.setModel(tableModel);

        // ====== STYLING TABEL ======
        tblKebutuhan.setRowHeight(28);
        tblKebutuhan.setShowGrid(true);
        tblKebutuhan.setGridColor(new Color(220, 220, 220));
        tblKebutuhan.setIntercellSpacing(new java.awt.Dimension(10, 5));
        tblKebutuhan.getTableHeader().setReorderingAllowed(false);
        tblKebutuhan.getTableHeader().setFont(new java.awt.Font("Poppins SemiBold", 0, 13));
        tblKebutuhan.setFont(new java.awt.Font("Poppins", 0, 12));
        tblKebutuhan.setSelectionBackground(new Color(66, 135, 245));
        tblKebutuhan.setSelectionForeground(Color.WHITE);
        tblKebutuhan.setFillsViewportHeight(true);

        // ====== ATUR LEBAR KOLOM ======
        int[] columnWidths = {250, 100, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            tblKebutuhan.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        
        jLabel10.setText("Total Kebutuhan Diajukan");
        jLabel8.setText("Jumlah Donasi Terkumpul");
        jLabel5.setText("Status Verifikasi");

        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 24));
        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 24));
        jLabel12.setFont(new java.awt.Font("Poppins SemiBold", 0, 24));


        // ====== ALIGNMENT KOLOM ======
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKebutuhan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblKebutuhan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        this.kebutuhanService = new KebutuhanService();
        this.donasiService = new DonasiService();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // ====== LOAD DATA ======
        loadDataKebutuhan();
        loadDashboardData();
    }

    
    private void loadDataKebutuhan() {
        try {
            KebutuhanDAO kebutuhanDAO = new KebutuhanDAOImpl();
            List<Kebutuhan> daftarKebutuhan = kebutuhanDAO.findBySekolah(sekolahLogin);

            DefaultTableModel model = (DefaultTableModel) tblKebutuhan.getModel();
            model.setRowCount(0);

            for (Kebutuhan k : daftarKebutuhan) {
                model.addRow(new Object[]{
                    k.getNamaAlat(),
                    k.getJumlah() + " unit",
                    k.getStatus().toString().toLowerCase()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data kebutuhan: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDashboardData() {
        try {
            Integer idSekolah = sekolahLogin.getIdSekolah();

            // === 1️⃣ Total Kebutuhan Diajukan untuk sekolah ini ===
            BigDecimal totalKebutuhanDiajukan = kebutuhanService.getTotalKebutuhanDiajukanBySekolah(idSekolah);
            jLabel11.setText(formatLargeNumber(totalKebutuhanDiajukan));

            // === 2️⃣ Total Donasi Terkumpul untuk sekolah ini ===
            BigDecimal totalDonasi = donasiService.getTotalDonasiBySekolah(idSekolah);
            jLabel9.setText(formatLargeNumber(totalDonasi));

            // === 3️⃣ Status Verifikasi Sekolah ===
            String status = sekolahLogin.getStatusVerifikasi() != null
                    ? sekolahLogin.getStatusVerifikasi()
                    : "-";
            jLabel12.setText(status);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data dashboard: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
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
        tblKebutuhan = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Kebutuhan Alat Belajar");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tblKebutuhan.setModel(new javax.swing.table.DefaultTableModel(
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
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblKebutuhan);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 630, 410));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKebutuhan;
    // End of variables declaration//GEN-END:variables
}
