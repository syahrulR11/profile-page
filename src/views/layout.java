/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.DatabaseManager;
import database.UserID;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import views.anggota.anggotaList;
import views.jabatan.jabatanList;
import views.jenisPinjaman.jenisPinjamanList;
import views.jenisSimpanan.jenisSimpananList;
import views.pegawai.pegawaiList;
import views.transaksi.pinjam.pinjamanList;
import views.transaksi.simpan.simpananList;

/**
 *
 * @author syahrul
 */
public class layout extends javax.swing.JFrame {
    private DatabaseManager dbManager;

    /**
     * Creates new form beranda
     */
    public layout() {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        lb_nama.setText(UserID.getUserNama());
        lb_jabatan.setText(UserID.getUserJabatan());

        // sidebar
        // menu home
        ImageIcon iconHome = new ImageIcon(getClass().getResource("/icon/kennel30.png"));
        sideBarItem menuHome = new sideBarItem(iconHome, false, null, "Home", (ActionEvent e) -> {
            pn_main.removeAll();
            pn_main.add(new beranda());
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data jabatan
        ImageIcon iconJabatan = new ImageIcon(getClass().getResource("/icon/job30.png"));
        sideBarItem menuJabatan = new sideBarItem(null, true, iconJabatan, "Data Jabatan", (ActionEvent e) -> {
            jabatanList jabatanList = new jabatanList();
            jabatanList.layout = this;
            pn_main.removeAll();
            pn_main.add(jabatanList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data pegawai
        ImageIcon iconPegawai = new ImageIcon(getClass().getResource("/icon/absent30.png"));
        sideBarItem menuPegawai = new sideBarItem(null, true, iconPegawai, "Data Pegawai", (ActionEvent e) -> {
            pegawaiList pegawaiList = new pegawaiList();
            pegawaiList.layout = this;
            pn_main.removeAll();
            pn_main.add(pegawaiList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data jenis pinjaman
        ImageIcon iconJenisPinjaman = new ImageIcon(getClass().getResource("/icon/settings30.png"));
        sideBarItem menuJenisPinjaman = new sideBarItem(null, true, iconJenisPinjaman, "Data Jenis Pinjaman", (ActionEvent e) -> {
            jenisPinjamanList jenisPinjamanList = new jenisPinjamanList();
            jenisPinjamanList.layout = this;
            pn_main.removeAll();
            pn_main.add(jenisPinjamanList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data jenis simpanan
        ImageIcon iconJenisSimpanan = new ImageIcon(getClass().getResource("/icon/settings30.png"));
        sideBarItem menuJenisSimpanan = new sideBarItem(null, true, iconJenisSimpanan, "Data Jenis Simpanan", (ActionEvent e) -> {
            jenisSimpananList jenisSimpananList = new jenisSimpananList();
            jenisSimpananList.layout = this;
            pn_main.removeAll();
            pn_main.add(jenisSimpananList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data anggota
        ImageIcon iconAnggota = new ImageIcon(getClass().getResource("/icon/absent30.png"));
        sideBarItem menuAnggota = new sideBarItem(null, true, iconAnggota, "Data Anggota", (ActionEvent e) -> {
            anggotaList anggotaList = new anggotaList();
            anggotaList.layout = this;
            pn_main.removeAll();
            pn_main.add(anggotaList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data master
        ImageIcon iconMaster = new ImageIcon(getClass().getResource("/icon/server30.png"));
        sideBarItem menuMaster = new sideBarItem(iconMaster, false, null, "Data Master", null,
            menuJabatan, menuPegawai, menuJenisPinjaman, menuJenisSimpanan, menuAnggota);

        // menu data simpanan
        ImageIcon iconSimpanan = new ImageIcon(getClass().getResource("/icon/investment30.png"));
        sideBarItem menuSimpanan = new sideBarItem(null, true, iconSimpanan, "Data Simpanan", (ActionEvent e) -> {
            simpananList simpananList = new simpananList();
            simpananList.layout = this;
            pn_main.removeAll();
            pn_main.add(simpananList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data pinjaman
        ImageIcon iconPinjaman = new ImageIcon(getClass().getResource("/icon/dollar30.png"));
        sideBarItem menuPinjaman = new sideBarItem(null, true, iconPinjaman, "Data Pinjaman", (ActionEvent e) -> {
            pinjamanList pinjamanList = new pinjamanList();
            pinjamanList.layout = this;
            pn_main.removeAll();
            pn_main.add(pinjamanList);
            pn_main.repaint();
            pn_main.revalidate();
        });
        // menu data transaksi
        ImageIcon iconTransaksi = new ImageIcon(getClass().getResource("/icon/economy30.png"));
        sideBarItem menuTransaksi = new sideBarItem(iconTransaksi, false, null, "Data Transaksi", null,
            menuSimpanan, menuPinjaman);

        // menu report anggota
        ImageIcon iconReport = new ImageIcon(getClass().getResource("/icon/invoices30.png"));
        sideBarItem menuReportAnggota = new sideBarItem(null, true, iconReport, "Report Anggota", (ActionEvent e) -> {
            try {
                String path = "./src/report/laporanAnggota.jasper";
                HashMap parameter = new HashMap();
                JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
                JasperViewer.viewReport(print, false);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Gagal View Report. "+err);
            }
        });
        sideBarItem menuReportPegawai = new sideBarItem(null, true, iconReport, "Report Pegawai", (ActionEvent e) -> {
            try {
                String path = "./src/report/laporanPegawai.jasper";
                HashMap parameter = new HashMap();
                JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
                JasperViewer.viewReport(print, false);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Gagal View Report. "+err);
            }
        });
        sideBarItem menuReportPinjamanAnggota = new sideBarItem(null, true, iconReport, "Report Pinjaman", (ActionEvent e) -> {
            try {
                String path = "./src/report/laporanPinjamanAnggota.jasper";
                HashMap parameter = new HashMap();
                JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
                JasperViewer.viewReport(print, false);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Gagal View Report. "+err);
            }
        });
        sideBarItem menuReportSimpananAnggota = new sideBarItem(null, true, iconReport, "Report Simpanan", (ActionEvent e) -> {
            try {
                String path = "./src/report/reportSimpananAnggota.jasper";
                HashMap parameter = new HashMap();
                JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
                JasperViewer.viewReport(print, false);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Gagal View Report. "+err);
            }
        });
        // menu data report
        sideBarItem menuReport = new sideBarItem(iconReport, false, null, "Report", null,
            menuReportAnggota,menuReportPegawai,menuReportPinjamanAnggota,menuReportSimpananAnggota);

        // initialize menu
        addMenu(menuHome, menuMaster, menuTransaksi, menuReport);
    }

    private void addMenu(sideBarItem... menu) {
        for (sideBarItem menu1 : menu) {
            pn_sidebar.add(menu1);
            ArrayList<sideBarItem> sbMenu = menu1.getSubMenu();
            for (sideBarItem m : sbMenu) {
                addMenu(m);
            }
        }
        pn_sidebar.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_topbar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lb_jabatan = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        pn_sidebar = new javax.swing.JPanel();
        pn_main = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KSP Idol");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pn_topbar.setBackground(new java.awt.Color(12, 28, 76));
        pn_topbar.setPreferredSize(new java.awt.Dimension(920, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo1_navy80.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(220, 53, 69));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cross-sign60.png"))); // NOI18N
        jButton1.setToolTipText("Logout");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lb_jabatan.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lb_jabatan.setText("jabatan");

        lb_nama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lb_nama.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lb_nama.setText("nama");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_jabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_jabatan)
                        .addGap(6, 6, 6))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_topbarLayout = new javax.swing.GroupLayout(pn_topbar);
        pn_topbar.setLayout(pn_topbarLayout);
        pn_topbarLayout.setHorizontalGroup(
            pn_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_topbarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 619, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_topbarLayout.setVerticalGroup(
            pn_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_topbarLayout.createSequentialGroup()
                .addGroup(pn_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_topbarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_topbarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addGap(16, 16, 16))
        );

        getContentPane().add(pn_topbar, java.awt.BorderLayout.PAGE_START);

        pn_sidebar.setBackground(new java.awt.Color(52, 58, 64));
        pn_sidebar.setPreferredSize(new java.awt.Dimension(220, 400));
        pn_sidebar.setLayout(new javax.swing.BoxLayout(pn_sidebar, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(pn_sidebar, java.awt.BorderLayout.LINE_START);

        pn_main.setBackground(new java.awt.Color(227, 242, 253));
        pn_main.setPreferredSize(new java.awt.Dimension(700, 400));
        pn_main.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pn_main, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pn_main.add(new beranda());
        pn_main.setPreferredSize(getMaximumSize());
        pn_main.repaint();
        pn_main.revalidate();
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin ingin keluar?", "logout", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            this.setVisible(false);
            new login().setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new layout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_jabatan;
    private javax.swing.JLabel lb_nama;
    public javax.swing.JPanel pn_main;
    private javax.swing.JPanel pn_sidebar;
    private javax.swing.JPanel pn_topbar;
    // End of variables declaration//GEN-END:variables
}
