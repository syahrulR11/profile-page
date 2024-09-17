/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.simpan;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import database.DMLSQL;
import database.DatabaseManager;
import views.layout;

/**
 *
 * @author syahrul
 */
public class simpananAnggotaList extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private DefaultTableModel tableModel;
    public layout layout = null;
    public simpananList parent = null;
    public String id_anggota,nama_anggota,email_anggota;

    /**
     * Creates new form simpananAnggotaList
     */
    public simpananAnggotaList(String id) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        id_anggota = id;
        setAnggota(id);
        datatable();
    }

    private void setAnggota(String id) {
        String[] columnsToSelect = {"id","nama","email"};
        String[] conditionColumns = {"id"};
        String[] operators = {"="};
        Object[] conditionValues = {id};
        Object[] result = null;
        String nominal = "0";
        try {
            result = dmlSql.findData("anggota", columnsToSelect, null, null, conditionColumns, operators, conditionValues, false,false);
            ResultSet result1 = dbManager.getConnection().createStatement().executeQuery("SELECT SUM(jumlah_simpan) FROM simpanan WHERE id_anggota = '"+id_anggota+"'");
            if (result1.next()) nominal = result1.getString(1);
            dbManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nama_anggota = result[1].toString();
        email_anggota = result[2].toString();

        lb_nama.setText(nama_anggota);
        lb_nominal.setText(nominal);
    }

    private static class EvenRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Mengubah background baris genap menjadi light gray
            if (row % 2 == 0) {
                renderer.setBackground(new Color(248, 249, 250));
                renderer.setForeground(new Color(52, 58, 64));
            } else {
                renderer.setBackground(new Color(227, 242, 253));
                renderer.setForeground(new Color(52, 58, 64));
            }

            return renderer;
        }
    }

    public void datatable() {
        Object[] Baris = {"ID","Jenis Simpanan","Tipe","Keterangan","Tanggal","Jumlah"};
        tableModel = new DefaultTableModel(null,Baris);
        try {
            String[] columnsToSelect = {"simpanan.id","jenis_simpanan.nama","simpanan.tipe","simpanan.ket","simpanan.tanggal","simpanan.jumlah_simpan"};
            String[] joinTable = {"jenis_simpanan"};
            String[] joinCondition = {"simpanan.id_jenis_simpanan = jenis_simpanan.id"};
            List<Object[]> results = dmlSql.selectData("simpanan", columnsToSelect, joinTable, joinCondition, null, null, null, true,false);
            for (Object[] row : results) {
                tableModel.addRow(row);
            }
            table.setModel(tableModel);
            dbManager.close();

            TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setBackground(new Color(0, 123, 255)); // Mengubah background header
                    label.setForeground(Color.WHITE); // Mengubah warna teks header
                    label.setFont(new Font("Arial", Font.BOLD, 14)); // Mengubah font header
                    label.setHorizontalAlignment(SwingConstants.LEADING); // Mengubah perataan teks header
                    return label;
                }
            };

            // Menerapkan custom header renderer ke setiap kolom
            for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }

            table.setDefaultRenderer(Object.class, new EvenRowRenderer());

            TableColumnModel columnModel = table.getColumnModel();
            TableColumn column = columnModel.getColumn(0); // Index of the column to hide
            column.setPreferredWidth(0);
            column.setMinWidth(0);
            column.setMaxWidth(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
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

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        kembaliButton = new javax.swing.JButton();
        ambilButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lb_nominal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Simpanan Anggota");

        lb_nama.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_nama.setText("nama");

        kembaliButton.setBackground(new java.awt.Color(108, 117, 125));
        kembaliButton.setForeground(new java.awt.Color(255, 255, 255));
        kembaliButton.setText("Kembali");
        kembaliButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kembaliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliButtonActionPerformed(evt);
            }
        });

        ambilButton.setBackground(new java.awt.Color(40, 167, 69));
        ambilButton.setForeground(new java.awt.Color(255, 255, 255));
        ambilButton.setText("Ambil");
        ambilButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ambilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ambilButtonActionPerformed(evt);
            }
        });

        simpanButton.setBackground(new java.awt.Color(0, 123, 255));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("Simpan");
        simpanButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(simpanButton)
                .addGap(18, 18, 18)
                .addComponent(ambilButton)
                .addGap(18, 18, 18)
                .addComponent(kembaliButton)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembaliButton)
                    .addComponent(ambilButton)
                    .addComponent(simpanButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        main.setBackground(new java.awt.Color(227, 242, 253));

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        jLabel2.setText("Jumlah Simpanan :");

        lb_nominal.setText("nominal");

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_nominal)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lb_nominal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        simpananSimpanForm simpananSimpanForm = new simpananSimpanForm(id_anggota);
        simpananSimpanForm.layout = layout;
        simpananSimpanForm.parent = this;

        layout.pn_main.removeAll();
        layout.pn_main.add(simpananSimpanForm);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void kembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliButtonActionPerformed
        simpananList simpananList = new simpananList();
        simpananList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(simpananList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_kembaliButtonActionPerformed

    private void ambilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ambilButtonActionPerformed
        simpananAmbilForm simpananAmbilForm = new simpananAmbilForm(id_anggota);
        simpananAmbilForm.layout = layout;
        simpananAmbilForm.parent = this;

        layout.pn_main.removeAll();
        layout.pn_main.add(simpananAmbilForm);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_ambilButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ambilButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembaliButton;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_nominal;
    private javax.swing.JPanel main;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
