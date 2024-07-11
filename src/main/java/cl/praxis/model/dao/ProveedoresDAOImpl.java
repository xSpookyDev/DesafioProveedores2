package cl.praxis.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.praxis.conexion.*;
import cl.praxis.model.dto.Proveedores;

public class ProveedoresDAOImpl implements ProveedoresDAO {
    
    public void create(Proveedores p) {
      
    }
    
    public Proveedores read(int id) {
        Proveedores p = null;

        try {
            Connection c = Conexion.getCon();
            Statement s = c.createStatement();
            String sql = "SELECT id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto FROM proveedores WHERE id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                p = new Proveedores(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("rut"),
                    rs.getString("direccion"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("contacto"),
                    rs.getString("telefono_contacto")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en método read");
            e.printStackTrace();
        }

        return p;
    }

    
    public List<Proveedores> read() {
        List<Proveedores> proveedorList = new ArrayList<>();
        
        try {
        	Connection c = Conexion.getCon();
             Statement s = c.createStatement();
        		String sql = "SELECT id, nombre, rut, direccion, correo, telefono, contacto, telefono_contacto FROM proveedores";
             ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
                Proveedores p = new Proveedores(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("rut"),
                    rs.getString("direccion"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("contacto"),
                    rs.getString("telefono_contacto")
                );
                
                proveedorList.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error en método read list");
            e.printStackTrace();
        }
    
        return proveedorList;
    }
    
    public void update(Proveedores p) {
		String sql = "update proveedores set nombre = '" + p.getNombre() + "', rut = '" + p.getRut()
				+ "', direccion = '" + p.getDireccion() + "', correo = '" + p.getCorreo() + "', telefono = '"
				+ p.getTelefono() + "', contacto = '" + p.getContacto() + "', telefono_contacto = '"
				+ p.getFono_contacto() + "' where id = " + p.getId();
		try {
			Connection c = Conexion.getCon();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error en el método update()");
			e.printStackTrace();
		}
	}
    @Override
	public void delete(int id) {
		String sql = "delete from proveedores where id = " + id;
		try {
			Connection c = Conexion.getCon();
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error en el método update()");
			e.printStackTrace();
		}
	}

	
}
