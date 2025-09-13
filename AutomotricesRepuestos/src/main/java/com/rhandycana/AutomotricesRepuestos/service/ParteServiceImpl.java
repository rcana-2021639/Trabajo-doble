package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.exceptiones.ResourceNotFoundException;
import com.rhandycana.AutomotricesRepuestos.model.Parte;
import com.rhandycana.AutomotricesRepuestos.repository.ParteRepository;
import com.rhandycana.AutomotricesRepuestos.repository.CategoriaRepository;
import com.rhandycana.AutomotricesRepuestos.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteServiceImpl implements ParteService {

    @Autowired
    private ParteRepository parteRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Parte> getAllPartes() {
        return parteRepository.findAll();
    }

    @Override
    public Parte getParteById(Long id) {
        return parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada"));
    }

    @Override
    public Parte saveParte(Parte parte) {
        // Validar que la categoría existe
        if (parte.getCategory() == null || parte.getCategory().getId() == null) {
            throw new ResourceNotFoundException("Se requiere una categoría válida");
        }
        
        categoriaRepository.findById(parte.getCategory().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + parte.getCategory().getId()));

        // Validar que el proveedor existe
        if (parte.getSupplier() == null || parte.getSupplier().getId() == null) {
            throw new ResourceNotFoundException("Se requiere un proveedor válido");
        }
        
        proveedorRepository.findById(parte.getSupplier().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + parte.getSupplier().getId()));

        // Validar campos obligatorios
        if (parte.getName() == null || parte.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la parte es obligatorio");
        }
        
        if (parte.getPartNumber() == null || parte.getPartNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de parte es obligatorio");
        }
        
        if (parte.getPrice() == null || parte.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        
        if (parte.getStock() == null || parte.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        return parteRepository.save(parte);
    }

    @Override
    public void deleteParte(Long id) {
        parteRepository.deleteById(id);
    }

    @Override
    public Parte updateParte(Long id, Parte parte) {
        Parte existingParte = parteRepository.findById(id).orElse(null);
        if (existingParte != null) {
            parte.setId(id);
            return parteRepository.save(parte);
        }
        return null;
    }
}
