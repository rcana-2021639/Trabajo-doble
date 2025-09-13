package com.rhandycana.AutomotricesRepuestos.service;

import com.rhandycana.AutomotricesRepuestos.model.Parte;
import java.util.List;

public interface ParteService {
    List<Parte> getAllPartes();
    Parte getParteById(Long id);
    Parte saveParte(Parte parte);
    void deleteParte(Long id);
    Parte updateParte(Long id, Parte parte);
}
