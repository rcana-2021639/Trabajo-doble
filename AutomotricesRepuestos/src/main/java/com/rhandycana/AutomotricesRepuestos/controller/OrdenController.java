package com.rhandycana.AutomotricesRepuestos.controller;

import com.rhandycana.AutomotricesRepuestos.model.Orden;
import com.rhandycana.AutomotricesRepuestos.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> getAllOrdenes() {
        return ordenService.getAllOrdenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.getOrdenById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createOrden(@RequestBody Orden orden) {
        try {
            Orden nuevaOrden = ordenService.saveOrden(orden);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Orden creada correctamente");
            response.put("orden", nuevaOrden);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Long id) {
        ordenService.deleteOrden(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrden(@PathVariable Long id, @RequestBody Orden orden) {
        try {
            Orden ordenActualizada = ordenService.updateOrden(id, orden);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Orden actualizada correctamente");
            response.put("orden", ordenActualizada);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
