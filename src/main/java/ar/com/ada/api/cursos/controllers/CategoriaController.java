package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class CategoriaController {
    // Post: recibimos, nos permite instanciar una categoria y ponerle datos.
    @Autowired
    CategoriaService categoriaService;

    // Crear una Categoria
    @PostMapping("/categorias")
    ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {

        categoriaService.crearCategoria(categoria);
        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria creada con exito";
        r.id = categoria.getCategoriaId();
        return ResponseEntity.ok(r);
    }

    @GetMapping("/categorias")
    ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}