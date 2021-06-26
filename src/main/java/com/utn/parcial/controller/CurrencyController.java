package com.utn.parcial.controller;

import com.utn.parcial.model.Currency;
import com.utn.parcial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private CurrencyService currencyService;
    @Autowired
    public CurrencyController(CurrencyService currencyService){
        this.currencyService=currencyService;
    }

    @PostMapping("/")
    public ResponseEntity addCurrency(@RequestBody Currency currency){
        Currency currency1= currencyService.addCurrency(currency);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(currency1.getId())
                .toUri();
        return new ResponseEntity<>(location,(HttpStatus.CREATED));
    }

    @GetMapping()
    public List<Currency> getAll(){
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id) throws Throwable {
        return currencyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrenty(@PathVariable Integer id){
        currencyService.delete(id);
    }
}
