/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.pinjam;

import java.awt.*;
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
public class pinjamanAngsurList extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private DefaultTableModel tableModel;
    public layout layout = null;
    public pinjamanList parent = null;
    public String id_pinjaman, id_anggota;

    /**
     * Creates new form simpananAnggotaList
     */
    public pinjamanAngsurList(String id) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        id_pinjaman = id;
        setAnggota(id);
        datatable();
    }

    private void setAnggota(String id) {
        Object[] result = null;
        Object[] result1 = null;
        try {
            String[] columnsToSelect = {"id","id_anggota","jumlah_tenor","jumlah_cicilan","ket"};
            String[] conditionColumns = {"id"};
            String[] operators = {"="};
            Object[] conditionValues = {id};
            result = dmlSql.findData("pinjaman", columnsToSelect, null, null, conditionColumns, operators, conditionValues, false, false);
            String[] columnsToSelect1 = {"id","nama"};
            String[] conditionColumns1 = {"id"};
            String[] operators1 = {"="};
            Object[] conditionValues1 = {result[1].toString()};
            result1 = dmlSql.findData("anggota", columnsToSelect1, null, null, conditionColumns1, operators1, conditionValues1, false, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id_anggota = result1[0].toString();
        lb_nama.setText(result1[1].toString());
        lb_ket.setText(result[4].toString());
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
        Object[] Baris = {"ID","NO Tenor","Tanggal","Jumlah"};
        tableModel = new DefaultTableModel(null,Baris);
        try {
            String[] columnsToSelect = {"angsur_pinjaman.id","angsur_pinjaman.no_tenor","angsur_pinjaman.tanggal","CONCAT('Rp ', REPLACE(FORMAT(angsur_pinjaman.jumlah_cicilan, 0), ',', '.'))"};
            String[] joinTable = {"pinjaman"};
            String[] joinCondition = {"pinjaman.id = angsur_pinjaman.id_pinjaman"};
            String[] conditionColumns = {"pinjaman.id"};
            String[] operators = {"="};
            Object[] conditionValues = {id_pinjaman};
            List<Object[]> results = dmlSql.selectData("angsur_pinjaman", columnsToSelect, joinTable, joinCondition, conditionColumns, operators, conditionValues, true, false);
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
        bayarButton = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lb_ket = new javax.swing.JLabel();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pinjaman Anggota");

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

        bayarButton.setBackground(new java.awt.Color(40, 167, 69));
        bayarButton.setForeground(new java.awt.Color(255, 255, 255));
        bayarButton.setText("Bayar");
        bayarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bayarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarButtonActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bayarButton)
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
                    .addComponent(bayarButton))
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

        jLabel3.setText("Keterangan Pinjaman :");

        lb_ket.setText("jLabel4");

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_ket)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lb_ket))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
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

    private void kembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliButtonActionPerformed
        pinjamanList pinjamanList = new pinjamanList();
        pinjamanList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_kembaliButtonActionPerformed

    private void bayarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarButtonActionPerformed
        pinjamanBayarForm pinjamanBayarForm = new pinjamanBayarForm(id_pinjaman, id_anggota);
        pinjamanBayarForm.layout = layout;
        pinjamanBayarForm.parent = this;
        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanBayarForm);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_bayarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayarButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembaliButton;
    private javax.swing.JLabel lb_ket;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JPanel main;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
