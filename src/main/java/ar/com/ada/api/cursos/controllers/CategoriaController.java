package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.model.CategoriaRequest;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class CategoriaController {
    // Post: recibimos, nos permite instanciar una categoria y ponerle datos.
    @Autowired
    CategoriaService categoriaService;

    // Crea una Categoria
    @PostMapping("/categorias")
    ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {
        categoriaService.crearCategoria(categoria);
        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria creada con exito";
        r.id = categoria.getCategoriaId();
        return ResponseEntity.ok(r);
    }

    // Trae las categorias completas
    @GetMapping("/categorias")
    ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    // Trae un categoria segun su id
    @GetMapping("/categorias/{id}")
    ResponseEntity<Categoria> CategoriaPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.listarCategoriasById(id);
        if (categoria == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(categoria);
    }

    // Modifica Nombre y descripcion de una Categoria
    @PutMapping("/categorias/{id}")
    ResponseEntity<?> editarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequest categoriaReq) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null)
            return ResponseEntity.badRequest().build();
        else
            categoria.setNombre(categoriaReq.nombre);
        categoria.setDescripcion(categoriaReq.descripcion);

        GenericResponse r = new GenericResponse();
        boolean resultado = categoriaService.crearCategoria(categoria);
        if (resultado) {
            r.isOk = true;
            r.id = categoria.getCategoriaId();
            r.message = "Modificaste una categoria con éxito.";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "No pudiste Modificar el esta categoria";
            return ResponseEntity.badRequest().body(r);
        }

    }

}