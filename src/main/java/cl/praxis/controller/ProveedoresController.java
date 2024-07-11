package cl.praxis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.praxis.model.dao.ProveedoresDAO;
import cl.praxis.model.dao.ProveedoresDAOImpl;
import cl.praxis.model.dto.Proveedores;

@WebServlet("/")
public class ProveedoresController extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    public ProveedoresController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProveedoresDAOImpl pDAO = new ProveedoresDAOImpl();
        List<Proveedores> proveedores = null;
        
        try {
            proveedores = pDAO.read();
            request.setAttribute("proveedores", proveedores);
            getServletContext().getRequestDispatcher("/views/proveedores.jsp").forward(request, response);
            
           
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de proveedores: " + e.getMessage());
            e.printStackTrace();
         
            
        }
int id = Integer.parseInt(request.getParameter("id"));
        
       
        ProveedoresDAO proveedoresDAO = new ProveedoresDAOImpl();
        proveedoresDAO.delete(id);
        
       
        response.sendRedirect(request.getContextPath() + "/proveedores.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String action = request.getServletPath();
    	    
    	    switch (action) {
    	        case "/":
    	            actualizarProveedor(request, response);
    	            break;
    	        default:
    	            doGet(request, response);
    	            break;
    	    }
    	}

    
    	private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    int id = Integer.parseInt(request.getParameter("id"));
    	    String nombre = request.getParameter("nombre");
    	    String rut = request.getParameter("rut");
    	    String direccion = request.getParameter("direccion");
    	    String correo = request.getParameter("correo");
    	    String telefono = request.getParameter("telefono");
    	    String contacto = request.getParameter("contacto");
    	    String fonoContacto = request.getParameter("fono_contacto");
    	    
    	    Proveedores proveedor = new Proveedores(id, nombre, rut, direccion, correo, telefono, contacto, fonoContacto);
    	    
    	    ProveedoresDAOImpl pDAO = new ProveedoresDAOImpl();
    	    
    	    try {
    	        pDAO.update(proveedor);
    	        response.sendRedirect(request.getContextPath() + "/");
    	    } catch (Exception e) {
    	        System.out.println("Error al actualizar proveedor: " + e.getMessage());
    	        e.printStackTrace();
    	      
    	    }
    }
}
