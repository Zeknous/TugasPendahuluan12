/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class RepositoryPegawai implements Repository<pegawai> {
    private Connection connection;
    
    public RepositoryPegawai(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<pegawai> getAll() {
        ArrayList<pegawai> employees = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pegawai");
            while (rs.next()) {
                pegawai currentEmployee = new pegawai();
                currentEmployee.setId(rs.getInt(1));
                currentEmployee.setNama(rs.getString(2));
                currentEmployee.setPosisi(rs.getString(3));
                currentEmployee.setTglLahir(rs.getDate(4));
                
                employees.add(currentEmployee);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public int edit(pegawai updatedModel) {
        try {
            String query = "UPDATE pegawai SET nama=?, posisi=?, tgl_lahir=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, updatedModel.getNama());
            stmt.setString(2, updatedModel.getPosisi());
            stmt.setDate(3, updatedModel.getTglLahir());
            stmt.setInt(4, updatedModel.getId());
            
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int insert(pegawai newModel) {
        try {
            String query = "INSERT INTO pegawai (nama, posisi, tgl_lahir) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newModel.getNama());
            stmt.setString(2, newModel.getPosisi());
            stmt.setDate(3, newModel.getTglLahir());
            
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int idModel) {
        try {
            String query = "DELETE FROM pegawai WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, idModel);
            
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
