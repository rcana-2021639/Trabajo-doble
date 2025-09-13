package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.exceptiones.ResourceNotFoundException;
import com.rhandycana.AutomotricesRepuestos.model.Orden;
import com.rhandycana.AutomotricesRepuestos.model.Cliente;
import com.rhandycana.AutomotricesRepuestos.repository.OrdenRepository;
import com.rhandycana.AutomotricesRepuestos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden getOrdenById(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    @Override
    public Orden saveOrden(Orden orden) {
        validarOrden(orden);
        return ordenRepository.save(orden);
    }

    @Override
    public void deleteOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public Orden updateOrden(Long id, Orden orden) {
        Orden existingOrden = ordenRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        validarOrden(orden);
        orden.setId(id);
        return ordenRepository.save(orden);
    }

    private void validarOrden(Orden orden) {
        if (orden.getCustomer() == null || orden.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Se requiere un cliente válido");
        }

        Cliente cliente = clienteRepository.findById(orden.getCustomer().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + orden.getCustomer().getId()));

        if (orden.getOrderDate() == null) {
            throw new IllegalArgumentException("La fecha de la orden es obligatoria");
        }

        if (orden.getTotalAmount() == null || orden.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor que cero");
        }

        if (orden.getStatus() == null || orden.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la orden es obligatorio");
        }

        String status = orden.getStatus().toUpperCase();
        if (!status.equals("PENDIENTE") && !status.equals("PROCESANDO") && 
            !status.equals("COMPLETADA") && !status.equals("CANCELADA")) {
            throw new IllegalArgumentException("Estado de orden no válido. Estados permitidos: PENDIENTE, PROCESANDO, COMPLETADA, CANCELADA");
        }
    }
}
