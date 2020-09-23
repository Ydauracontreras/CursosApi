package ar.com.ada.api.cursos.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.entities.Usuario.TipoUsuarioEnum;
import ar.com.ada.api.cursos.model.request.CategoriaRequest;
import ar.com.ada.api.cursos.model.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
@CrossOrigin("*")
public class CategoriaController {
    // Post: recibimos, nos permite instanciar una categoria y ponerle datos.
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    UsuarioService usuarioService;

    // Crea una Categoria
    @PostMapping("/api/categorias")
    public ResponseEntity<GenericResponse> crearCategoria(Principal principal, @RequestBody Categoria categoria) {
        Usuario usuario = usuarioService.buscarPorUsername(principal.getName());
        if (usuario.getTipoUsuarioId() != TipoUsuarioEnum.STAFF) {
            // return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            // No se da informacion sobre el acceso solo se niega la entrada
            return ResponseEntity.notFound().build();
        }
        categoriaService.crearCategoria(categoria);
        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria creada con exito";
        r.id = categoria.getCategoriaId();
        return ResponseEntity.ok(r);
    }

    // Trae las categorias completas
    @GetMapping("/api/categorias")
    // @PreAuthorize("hasAuthority('CLAIM_userType_ESTUDIANTE')")
    ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    // Trae un categoria segun su id
    @GetMapping("api/categorias/{id}")
    // @PreAuthorize("hasAuthority('CLAIM_userType_STAFF')")
    ResponseEntity<Categoria> CategoriaPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.listarCategoriasById(id);
        if (categoria == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(categoria);
    }

    // Modifica Nombre y descripcion de una Categoria
    // CategoriaRequest para mapear los datos desde el front
    @PutMapping("/api/categorias/{id}")
    @PreAuthorize("@usuarioService.buscarPorUsername(principal.getUserName()).getTipoUsuarioId().getValue() == 3")
    ResponseEntity<?> editarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequest categoriaReq) {
        Categoria categoria = categoriaService.listarCategoriasById(id);
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