package com.utn.parcial.service;

import com.utn.parcial.api.ApiCallServiceDolar;
import com.utn.parcial.api.ApiCallServiceEuro;
import com.utn.parcial.model.*;
import com.utn.parcial.repository.CumpleanitosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class CumpleanitosService {

    CumpleanitosRepository cumpleanitosRepository;
    PersonaService personaService;
    ApiCallServiceEuro apiCallServiceEuro;
    ApiCallServiceDolar apiCallServiceDolar;

    private static final Integer precioCumpleanito= 25000;

    @Autowired
    public CumpleanitosService(CumpleanitosRepository cumpleanitosRepository, PersonaService personaService, ApiCallServiceEuro apiCallServiceEuro,ApiCallServiceDolar apiCallServiceDolar ){
        this.cumpleanitosRepository = cumpleanitosRepository;
        this.personaService= personaService;
        this.apiCallServiceEuro = apiCallServiceEuro;
        this.apiCallServiceDolar = apiCallServiceDolar;
    }

    public Cumpleanitos addCumpleanitos(Cumpleanitos cumpleanitos){
        return cumpleanitosRepository.save(cumpleanitos);
    }

    public List getAll(Pageable pageable){
        return cumpleanitosRepository.findAll();
    }

    public Page allCumpleanitos(Pageable pageable) {
        return cumpleanitosRepository.findAll(pageable);
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

    public Float pagarFiesta(Integer idCumpleanitos) throws IOException, InterruptedException {
        Float result=null;
        Cumpleanitos newCumpleanitos= getCumpleanitosById(idCumpleanitos);

        for ( Persona c: newCumpleanitos.getInvitados()){
            if(c instanceof Jugador){
                result =this.Convertion(((Jugador) c).getCurrency().getTypeCurrency());
            }
        }
        return result;
    }

    public Float Convertion(TypeCurrency typeCurrency) throws IOException, InterruptedException {

        Float result =null;

        if(typeCurrency.getDescripcion().equals("EURO")){
            result = precioCumpleanito/ apiCallServiceEuro.getEuro().getVenta();
        }
        else if (typeCurrency.getDescripcion().equals("DOLAR"))
        {
            result = precioCumpleanito/ apiCallServiceDolar.getDolar().getVenta();
        }

        return result;
    }
}
