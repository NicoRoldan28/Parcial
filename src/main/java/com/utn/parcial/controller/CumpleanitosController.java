package com.utn.parcial.controller;

import com.utn.parcial.model.Cumpleanitos;
import com.utn.parcial.service.CumpleanitosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cumpleanitos")

public class CumpleanitosController {

    CumpleanitosService cumpleanitosService;

    @Autowired
    public CumpleanitosController(CumpleanitosService cumpleanitosService){
        this.cumpleanitosService=cumpleanitosService;
    }

    @PostMapping("/")
    public ResponseEntity newCumpleanitos(@RequestBody Cumpleanitos cumpleanitos){
        Cumpleanitos newCumpleanitos = cumpleanitosService.addCumpleanitos(cumpleanitos);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCumpleanitos.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Cumpleanitos>>  getCumpleanitos(Pageable pageable){
        Page page = cumpleanitosService.allCumpleanitos(pageable);
        return response(page);
    }

    private ResponseEntity response(Page page) {

        HttpStatus httpStatus = page.getContent().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.
                status(httpStatus).
                header("X-Total-Count", Long.toString(page.getTotalElements()))
                .header("X-Total-Pages", Long.toString(page.getTotalPages()))
                .body(page.getContent());
    }

    @GetMapping("/{id}")
    public Cumpleanitos getCumpleanitosById(@PathVariable Integer id){
        return cumpleanitosService.getCumpleanitosById(id);
    }

    @PutMapping("/{id}/persona/{persona_id}")
    public void addPersonaToCumpleanitos(@PathVariable Integer id,@PathVariable Integer persona_id){
        cumpleanitosService.addPersonaToCumpleanitos(id,persona_id);
    }

    @PutMapping("/{idCumpleanios}")
    public Float pagarFiesta(@PathVariable Integer id) throws IOException, InterruptedException {
        Float result= cumpleanitosService.pagarFiesta(id);
        return result;
    }

}
