package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.model.Orden;
import java.util.List;

public interface OrdenService {
    List<Orden> getAllOrdenes();
    Orden getOrdenById(Long id);
    Orden saveOrden(Orden orden);
    Orden updateOrden(Long id, Orden orden);
    void deleteOrden(Long id);
}
