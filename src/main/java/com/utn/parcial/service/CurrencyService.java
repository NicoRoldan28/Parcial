package com.utn.parcial.service;

import com.utn.parcial.model.Currency;
import com.utn.parcial.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public void addCurrency(Currency currency){
        currencyRepository.save(currency);
    }
    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id) throws Throwable {
        return (Currency) currencyRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
    public void delete(Integer id){
        currencyRepository.delete(id);
    }

}
