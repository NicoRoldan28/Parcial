package com.utn.parcial.controller;

import com.utn.parcial.model.Cumpleanitos;
import com.utn.parcial.service.CumpleanitosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addCumpleanitos(@RequestBody Cumpleanitos cumpleanitos){
        cumpleanitosService.addCumpleanitos(cumpleanitos);
    }
    @GetMapping
    public List getCumpleanitos(){
        return cumpleanitosService.getAll();
    }
    @GetMapping("/{id}")
    public Cumpleanitos getCumpleanitosById(@PathVariable Integer id){
        return cumpleanitosService.getCumpleanitosById(id);
    }
    @PutMapping("/{id}/persona/{persona_id}")
    public void addPersonaToCumpleanitos(@PathVariable Integer id,@PathVariable Integer persona_id){
        cumpleanitosService.addPersonaToCumpleanitos(id,persona_id);
    }





}
