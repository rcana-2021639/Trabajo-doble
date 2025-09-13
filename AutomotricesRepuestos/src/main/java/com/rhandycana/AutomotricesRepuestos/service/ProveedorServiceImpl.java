package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.exceptiones.ResourceNotFoundException;
import com.rhandycana.AutomotricesRepuestos.model.Proveedor;
import com.rhandycana.AutomotricesRepuestos.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        validarProveedor(proveedor);
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor updateProveedor(Long id, Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));

        validarProveedor(proveedor);
        proveedor.setId(id);
        return proveedorRepository.save(proveedor);
    }

    private void validarProveedor(Proveedor proveedor) {
        if (proveedor.getName() == null || proveedor.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del proveedor es obligatorio");
        }

        if (proveedor.getEmail() != null && !proveedor.getEmail().isEmpty() && 
            !proveedor.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }

        if (proveedor.getPhone() != null && !proveedor.getPhone().isEmpty() && 
            !proveedor.getPhone().matches("\\d{8,12}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener entre 8 y 12 dígitos");
        }

        if (proveedor.getContactPerson() != null && 
            proveedor.getContactPerson().trim().length() < 3) {
            throw new IllegalArgumentException("El nombre de la persona de contacto debe tener al menos 3 caracteres");
        }
    }
}
