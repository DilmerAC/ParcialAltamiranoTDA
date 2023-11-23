/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.PropertyDAO;
import database.DbConnection;
import entities.Property;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JcarlosAd7
 */
public class PropertyControl {

    private final PropertyDAO DATA;
    private Property obj;
    private DefaultTableModel tableModel;
    public int recordsShowed;

    public PropertyControl() {
        this.DATA = new PropertyDAO();
        this.obj = new Property();
        this.recordsShowed = 0;
    }

    public DefaultTableModel list(String text, int totalPage, int pageNumber) {
        List<Property> propertiesList = new ArrayList();
        propertiesList.addAll(DATA.list(text, totalPage, pageNumber));

        String[] titles = {"Id", "Name", "Address", "State", "Characteristics", "Rent Price"};
        this.tableModel = new DefaultTableModel(null, titles);

        String[] record = new String[7];

        this.recordsShowed = 0;
        for (Property prop : propertiesList) {
            record[0] = Integer.toString(prop.getId());
            record[1] = prop.getName();
            record[2] = prop.getAddress();
            record[3] = prop.getState();
            record[4] = prop.getCharacteristics();
            record[5] = prop.getRentPrice().toString();
            this.tableModel.addRow(record);
            this.recordsShowed = this.recordsShowed + 1;
        }
        return this.tableModel;
    }

    public String insert(String name, String address, String state, String characteristics, Double rent_price) {
        if (DATA.exists(name)) {
            return "El record already exists.";
        } else {
            obj.setName(name);
            obj.setAddress(address);
            obj.setState(state);
            obj.setCharacteristics(characteristics);
            obj.setRentPrice(rent_price);
            if (DATA.insert(obj)) {
                return "OK";
            } else {
                return "Error in the record.";
            }
        }
    }

    public String update(int id, String name, String address, String state, String characteristics, Double rent_price) {
        if (DATA.exists(name)) {
            return "El record ya existe.";
        } else {
            obj.setId(id);
            obj.setName(name);
            obj.setAddress(address);
            obj.setState(state);
            obj.setCharacteristics(characteristics);
            obj.setRentPrice(rent_price);
            if (DATA.update(obj)) {
                return "OK";
            } else {
                return "Error en la actualización.";
            }

        }
    }

    public String delete(int id) {
        if (DATA.delete(id)) {
            return "OK";
        } else {
            return "Is not possible to delete the record.";
        }
    }

    public int total() {
        return DATA.total();
    }

    public int totalShowed() {
        return this.recordsShowed;
    }

    public void propertyReports() {
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        DbConnection cnn = DbConnection.getInstance();

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/reportes/RptProperties.jrxml");
            print = JasperFillManager.fillReport(report, p, cnn.connect());
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Properties Reports");
            view.setVisible(true);
        } catch (JRException e) {
            e.getMessage();
        }
    }

 
}
