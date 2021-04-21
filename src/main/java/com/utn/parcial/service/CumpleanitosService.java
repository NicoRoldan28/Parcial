package com.utn.parcial.service;

import com.utn.parcial.model.Cumpleanitos;
import com.utn.parcial.model.Jugador;
import com.utn.parcial.model.Persona;
import com.utn.parcial.model.Representante;
import com.utn.parcial.repository.CumpleanitosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CumpleanitosService {

    CumpleanitosRepository cumpleanitosRepository;
    PersonaService personaService;

    @Autowired
    public CumpleanitosService(CumpleanitosRepository cumpleanitosRepository,PersonaService personaService){
        this.cumpleanitosRepository = cumpleanitosRepository;
        this.personaService= personaService;
    }

    public void addCumpleanitos(Cumpleanitos cumpleanitos){
        cumpleanitosRepository.save(cumpleanitos);
    }

    public List getAll(){
        return cumpleanitosRepository.findAll();
    }

    public Cumpleanitos getCumpleanitosById(Integer id){
        return cumpleanitosRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void addPersonaToCumpleanitos(Integer id, Integer persona_id){
        Cumpleanitos cumpleanitos= getCumpleanitosById(id);
        Persona persona= personaService.getById(persona_id);

        if(!(persona instanceof Persona) || !(cumpleanitos instanceof Cumpleanitos) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(cumpleanitos.getInvitados().size()<=10){
            cumpleanitos.getInvitados().add(persona);
            cumpleanitosRepository.save(cumpleanitos);
        }
        else
        {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
