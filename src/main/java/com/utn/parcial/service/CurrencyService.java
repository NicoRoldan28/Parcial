package com.utn.parcial.service;

import com.utn.parcial.model.Currency;
import com.utn.parcial.model.Persona;
import com.utn.parcial.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency addCurrency(Currency currency){
        return currencyRepository.save(currency);
    }

    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id){
        return currencyRepository.findById(id)
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<?> delete(Integer id) {
        try {
            Currency currency= getById(id);
            currencyRepository.deleteById(id);
            return new ResponseEntity<>("Se ha eliminado la currency con éxito.", HttpStatus.OK);
        } catch (
                DataAccessException e) {
            return new ResponseEntity<>("La currency con el id " + id + " es inexistente.", HttpStatus.NOT_FOUND);
        }
    }
}
