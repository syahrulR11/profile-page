/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.jenisPinjaman;

import java.awt.Color;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.JOptionPane;

import database.DMLSQL;
import database.DatabaseManager;
import views.layout;

/**
 *
 * @author syahrul
 */
public class jenisPinjamanForm extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private String id = null;
    public jenisPinjamanList parent = null;
    public layout layout = null;

    /**
     * Creates new form pegawaiCreate
     */
    public jenisPinjamanForm(String id) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        if (id != null) {
            this.id = id;
            setData(id);
        } else {
            hapusButton.setVisible(false);
        }
    }

    private void setData(String id) {
        String[] columnsToSelect = {"nama"};
        String[] conditionColumns = {"id"};
        String[] operators = {"="};
        Object[] conditionValues = {id};
        Object[] result;
        try {
            result = dmlSql.findData("jenis_pinjaman", columnsToSelect, null, null, conditionColumns, operators, conditionValues, false,false);
            namaInput.setText((String)result[0]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void saveData() {
        try {
            if (this.id != null) {
                String[] insertColumns = {"nama"};
                Object[] insertValues = {namaInput.getText()};
                dmlSql.updateData("jenis_pinjaman", insertColumns, insertValues, this.id);
                dbManager.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
                jenisPinjamanList jenisPinjamanList = new jenisPinjamanList();
                jenisPinjamanList.layout = layout;
                layout.pn_main.removeAll();
                layout.pn_main.add(jenisPinjamanList);
                layout.pn_main.repaint();
                layout.pn_main.revalidate();
            } else {
                UUID uuid = UUID.randomUUID();
                String[] insertColumns = {"id","nama"};
                Object[] insertValues = {uuid.toString(), namaInput.getText()};
                dmlSql.insertData("jenis_pinjaman", insertColumns, insertValues);
                dbManager.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
                jenisPinjamanList jenisPinjamanList = new jenisPinjamanList();
                jenisPinjamanList.layout = layout;
                layout.pn_main.removeAll();
                layout.pn_main.add(jenisPinjamanList);
                layout.pn_main.repaint();
                layout.pn_main.revalidate();
            }
            parent.datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        namaInput = new javax.swing.JTextField();
        simpanButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));
        header.setPreferredSize(new java.awt.Dimension(676, 39));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Jenis Pinjaman Form");

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
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(227, 242, 253));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 150));

        jLabel2.setText("Nama");

        simpanButton.setBackground(new java.awt.Color(40, 167, 69));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("Simpan");
        simpanButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        hapusButton.setBackground(new java.awt.Color(220, 53, 69));
        hapusButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusButton.setText("Hapus");
        hapusButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(namaInput, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                    .addComponent(hapusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(simpanButton)
                .addGap(18, 18, 18)
                .addComponent(hapusButton)
                .addContainerGap(38, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        saveData();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin ingin hapus?", "hapus", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                dmlSql.deleteData("jabatan", "id", this.id);
                dbManager.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
                jenisPinjamanList jenisPinjamanList = new jenisPinjamanList();
                jenisPinjamanList.layout = layout;
                layout.pn_main.removeAll();
                layout.pn_main.add(jenisPinjamanList);
                layout.pn_main.repaint();
                layout.pn_main.revalidate();
                parent.datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
            }
        }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        jenisPinjamanList pegawaiList = new jenisPinjamanList();
        pegawaiList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pegawaiList);
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
    private javax.swing.JButton hapusButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField namaInput;
    private javax.swing.JButton simpanButton;
    // End of variables declaration//GEN-END:variables
}