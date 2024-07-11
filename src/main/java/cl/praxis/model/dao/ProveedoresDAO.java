package cl.praxis.model.dao;

import java.util.List;

import cl.praxis.model.dto.Proveedores;

public interface ProveedoresDAO {
	  void create(Proveedores p);
	  Proveedores read(int id);
	  List<Proveedores> read();
	  void update(Proveedores proveedor);
	  void delete(int id);

}
