/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.pinjam;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.swing.JOptionPane;

import database.DMLSQL;
import database.DatabaseManager;
import database.UserID;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import views.layout;

/**
 *
 * @author syahrul
 */
public class pinjamanBayarForm extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private String id_anggota, id_pinjaman;
    private int tenor, cicilan, angsuran;
    public pinjamanAngsurList parent = null;
    public layout layout = null;

    /**
     * Creates new form pegawaiCreate
     */
    public pinjamanBayarForm(String id_pin, String id_ang) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        id_pinjaman = id_pin;
        id_anggota = id_ang;
        setPinjaman(id_pin);
        cicilanInput.setText(Integer.toString(cicilan));
        noTenorInput.setText(Integer.toString(angsuran));
    }

    private void setPinjaman(String id) {
        Object[] result = null;
        String angsuranStr = "";
        try {
            String[] columnsToSelect = {"id","jumlah_tenor","jumlah_cicilan"};
            String[] conditionColumns = {"id"};
            String[] operators = {"="};
            Object[] conditionValues = {id};
            result = dmlSql.findData("pinjaman", columnsToSelect, null, null, conditionColumns, operators, conditionValues, false, false);

            try {
                ResultSet result1 = dbManager.getConnection().createStatement().executeQuery("SELECT COUNT(id) FROM angsur_pinjaman WHERE id_pinjaman = '"+id_pinjaman+"'");
                if (result1.next()) angsuranStr = result1.getString(1);
                dbManager.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        angsuran = Integer.parseInt(angsuranStr) + 1;
        tenor = Integer.parseInt(result[1].toString());
        cicilan = Integer.parseInt(result[2].toString());
        if (angsuran > tenor) {
            JOptionPane.showMessageDialog(null, "Sudah Lunas. ");
            pinjamanAngsurList pinjamanAngsurList = new pinjamanAngsurList(id_pinjaman);
            pinjamanAngsurList.layout = layout;
            layout.pn_main.removeAll();
            layout.pn_main.add(pinjamanAngsurList);
            layout.pn_main.repaint();
            layout.pn_main.revalidate();
        }
    }

    private void saveData() {
        try {
            UUID uuid = UUID.randomUUID();
            String[] insertColumns = {"id","id_pegawai","id_anggota","id_pinjaman","no_tenor","tanggal","jumlah_cicilan"};
            Object[] insertValues = {uuid.toString(), UserID.getUserId(), id_anggota, id_pinjaman, angsuran,new SimpleDateFormat("yyyy-MM-dd").format(tglInput.getDate()),cicilan};
            dmlSql.insertData("angsur_pinjaman", insertColumns, insertValues);
            dbManager.close();
            print(uuid.toString(),new SimpleDateFormat("EEEE, dd MMMM yyyy",new Locale("id", "ID")).format(tglInput.getDate()));
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            pinjamanAngsurList pinjamanAngsurList = new pinjamanAngsurList(id_pinjaman);
            pinjamanAngsurList.layout = layout;
            layout.pn_main.removeAll();
            layout.pn_main.add(pinjamanAngsurList);
            layout.pn_main.repaint();
            layout.pn_main.revalidate();
            parent.datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }
    
    private void print(String id,String date) {
        try {
            String path = "./src/report/notaBayarPinjam.jasper";
            HashMap parameter = new HashMap();
            parameter.put("ID",id);
            parameter.put("DATE",date);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Gagal View Nota. "+err);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        batalButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        simpanButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tglInput = new com.toedter.calendar.JDateChooser();
        cicilanInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        noTenorInput = new javax.swing.JTextField();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));
        header.setPreferredSize(new java.awt.Dimension(676, 39));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Bayar Form");

        batalButton.setBackground(new java.awt.Color(108, 117, 125));
        batalButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batalButton.setForeground(new java.awt.Color(255, 255, 255));
        batalButton.setText("Batal");
        batalButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        batalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                batalButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                batalButtonMouseExited(evt);
            }
        });
        batalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(batalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(227, 242, 253));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 250));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(227, 242, 253));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 250));

        simpanButton.setBackground(new java.awt.Color(40, 167, 69));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("Simpan");
        simpanButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Tanggal");

        jLabel5.setText("Jumlah Cicilan");

        tglInput.setDateFormatString("yyyy-MM-dd");

        cicilanInput.setEditable(false);

        jLabel6.setText("Cicilan Ke");

        noTenorInput.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tglInput, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cicilanInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(noTenorInput)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cicilanInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noTenorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(simpanButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        saveData();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        pinjamanAngsurList pinjamanAngsurList = new pinjamanAngsurList(id_pinjaman);
        pinjamanAngsurList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanAngsurList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void batalButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseEntered
        batalButton.setBackground(new Color(90, 98, 104));
    }//GEN-LAST:event_batalButtonMouseEntered

    private void batalButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseExited
        batalButton.setBackground(new Color(108, 117, 125));
    }//GEN-LAST:event_batalButtonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalButton;
    private javax.swing.JTextField cicilanInput;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField noTenorInput;
    private javax.swing.JButton simpanButton;
    private com.toedter.calendar.JDateChooser tglInput;
    // End of variables declaration//GEN-END:variables
}
