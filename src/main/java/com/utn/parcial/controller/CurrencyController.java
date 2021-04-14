package com.utn.parcial.controller;

import com.utn.parcial.model.Currency;
import com.utn.parcial.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/")
    public void addCurrency(@RequestBody Currency currency){
        currencyService.addCurrency(currency);
    }

    @GetMapping()
    public List<Currency> getAll(){
        return currencyService.getAll();
    }
    @GetMapping()
    public Currency getById(@PathVariable Integer id) throws Throwable {
        return currencyService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCurrenty(@PathVariable Integer id){
        currencyService.delete(id);
    }

}
