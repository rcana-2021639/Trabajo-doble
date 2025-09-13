package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.model.Proveedor;
import java.util.List;

public interface ProveedorService {
    List<Proveedor> getAllProveedores();
    Proveedor getProveedorById(Long id);
    Proveedor saveProveedor(Proveedor proveedor);
    void deleteProveedor(Long id);
    Proveedor updateProveedor(Long id, Proveedor proveedor);
}
