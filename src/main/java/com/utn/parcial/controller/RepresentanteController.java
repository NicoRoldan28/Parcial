package com.utn.parcial.controller;

import com.utn.parcial.model.Currency;
import com.utn.parcial.model.Representante;
import com.utn.parcial.service.JugadorService;
import com.utn.parcial.service.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/representante")
public class RepresentanteController {

    @Autowired
    private RepresentanteService representanteService;
    private JugadorService jugadorService;

    @PostMapping("/")
    public void addRepresentante(@RequestBody Representante representante){
        representanteService.addRepresentante(representante);
    }

    @GetMapping()
    public List<Representante> getAll(){
        return representanteService.getAll();
    }

    @GetMapping("/{id}")
    public Representante getById(@PathVariable Integer id) throws Throwable {
        return representanteService.getById(id);
    }

    public void calcularMontoTotal() throws Throwable {

        Integer EUROS=111;
        Integer DOLARES=92;
        Integer total =0;
        Representante representante= new Representante();
        representante= this.getById(1);

        Integer cant =representante.getPesoDeLaBobeda()/1;

        Integer i=0;
        while(i<representante.getJugadores().size()){
            if(representante.getJugadores().get(i).getCurrency().getCurrency().equals("DOLARES")){
                total=EUROS*cant;
            }
            else if(representante.getJugadores().get(i).getCurrency().getCurrency().equals("DOLARES")){
                total=DOLARES*cant;
            }
            else{
                total=cant;
            }

            i++;
        }
        representante.setMontoTotal(total);
    }

}
