/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DbConnection;
import entities.Property;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import datos.interfaces.CrudPaginationInterface;
/**
 *
 * @author Dilmer
 */
public class PropertyDAO implements CrudPaginationInterface<Property> {
    private final DbConnection CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropertyDAO(){
        CON=DbConnection.getInstance();
    }
    
    
    @Override
    public List<Property> list(String text,int totalPage,int pageNumber) {
        List<Property> records=new ArrayList();
        try {
            ps=CON.connect().prepareStatement("SELECT id, name, address, characteristics, state, rent_price FROM property WHERE name LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + text +"%");            
            ps.setInt(2, (pageNumber-1)*totalPage);
            ps.setInt(3, totalPage);
            rs=ps.executeQuery();
            while(rs.next()){
                records.add(new Property(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.disconnect();
        }
        return records;
    }
   

    @Override
    public boolean insert(Property obj) {
        resp=false;
        try {
            ps=CON.connect().prepareStatement("INSERT INTO property (name, address, characteristics, state, rent_price) VALUES (?,?,?,?,?)");
            ps.setString(1,obj.getName());
            ps.setString(2, obj.getAddress());
            ps.setString(3, obj.getCharacteristics());
            ps.setString(4, obj.getState());
            ps.setDouble(5, obj.getRentPrice());
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.disconnect();
        }
        return resp;
    }

    @Override
    public boolean update(Property obj) {
        resp=false;
        try {
            ps=CON.connect().prepareStatement("UPDATE property SET name=?, address=?, characteristics=?, state=?, rent_price=? WHERE id=?");
            ps.setString(1,obj.getName());
            ps.setString(2, obj.getAddress());
            ps.setString(3, obj.getCharacteristics());
            ps.setString(4, obj.getState());
            ps.setDouble(5, obj.getRentPrice());
            ps.setInt(6, obj.getId());
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.disconnect();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRecords=0;
        try {
            ps=CON.connect().prepareStatement("SELECT COUNT(id) FROM property");            
            rs=ps.executeQuery();
            
            while(rs.next()){
                totalRecords=rs.getInt("COUNT(id)");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.disconnect();
        }
        return totalRecords;
    }

    @Override
    public boolean exists(String texto) {
        resp=false;
        try {
            ps=CON.connect().prepareStatement("SELECT name FROM property WHERE name=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.disconnect();
        }
        return resp;
    }


    @Override
    public boolean delete(int id) {
        resp=false;
        try {
            ps=CON.connect().prepareStatement("DELETE FROM property WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.disconnect();
        }
        return resp;
    }
}
