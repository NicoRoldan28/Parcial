package com.utn.parcial.service;

import com.utn.parcial.model.Representante;
import com.utn.parcial.repository.CurrencyRepository;
import com.utn.parcial.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class RepresentanteService {
    private RepresentanteRepository representanteRepository;

    @Autowired
    public RepresentanteService(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }
    public void addRepresentante(Representante representante){
        representanteRepository.save(representante);
    }

    public List<Representante> getAll(){
        return representanteRepository.findAll();
    }

    /*public Representante getById(Integer id){
        return representanteRepository.findById(id);
    }*/

    public Representante getById(Integer id) throws Throwable {
        return (Representante) representanteRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }



}
