/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;

/**
 *
 * @author Rohmi Rahmawati
 */
public class UserID {
    private static DatabaseManager dbManager;
    private static DMLSQL dmlSql;
    private static String id,nama,email,jabatan;

    public static void setUserLogin(String kode) throws SQLException{
        dbManager = new DatabaseManager();
        dmlSql = new DMLSQL(dbManager);
        String[] columnsToSelect = {"pegawai.id","pegawai.nama","pegawai.email","jabatan.nama"};
        String[] joinTable = {"jabatan"};
        String[] joinCondition = {"pegawai.jabatan_id = jabatan.id"};
        String[] conditionColumns = {"pegawai.id"};
        String[] operators = {"="};
        Object[] conditionValues = {kode};
        Object[] result = dmlSql.findData("pegawai", columnsToSelect, joinTable, joinCondition, conditionColumns, operators, conditionValues, false, false);
        id = kode;
        nama = result[1].toString();
        email = result[2].toString();
        jabatan = result[3].toString();
    }

    public static String getUserId() {
        return id;
    }

    public static String getUserNama() {
        return nama;
    }

    public static String getUserEmail() {
        return email;
    }

    public static String getUserJabatan() {
        return jabatan;
    }
}
