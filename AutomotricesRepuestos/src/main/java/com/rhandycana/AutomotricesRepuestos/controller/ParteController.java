package com.rhandycana.AutomotricesRepuestos.controller;

import com.rhandycana.AutomotricesRepuestos.model.Parte;
import com.rhandycana.AutomotricesRepuestos.service.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partes")
public class ParteController {

    @Autowired
    private ParteService parteService;

    @GetMapping
    public List<Parte> getAllPartes() {
        return parteService.getAllPartes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parte> getParteById(@PathVariable Long id) {
        return ResponseEntity.ok(parteService.getParteById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createParte(@RequestBody Parte parte) {
        try {
            Parte nuevaParte = parteService.saveParte(parte);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Parte creada correctamente");
            response.put("parte", nuevaParte);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParte(@PathVariable Long id) {
        parteService.deleteParte(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public String updateParte(@PathVariable Long id, @RequestBody Parte parte) {
        Parte result = parteService.updateParte(id, parte);
        if (result == null) {
            return "Parte no encontrada";
        }
        return "Parte actualizada correctamente";
    }
}
