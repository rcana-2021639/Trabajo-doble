package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.exceptiones.ResourceNotFoundException;
import com.rhandycana.AutomotricesRepuestos.model.Cliente;
import com.rhandycana.AutomotricesRepuestos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        validarCliente(cliente);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getName() == null || cliente.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

        if (cliente.getEmail() != null && !cliente.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }

        if (cliente.getPhone() != null && !cliente.getPhone().matches("\\d{8,12}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener entre 8 y 12 dígitos");
        }
    }
}
