package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    Cliente saveCliente(Cliente cliente);
    void deleteCliente(Long id);
    Cliente updateCliente(Long id, Cliente cliente);
}
