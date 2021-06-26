package com.utn.parcial.controller;

import com.utn.parcial.api.ApiCallServiceDolar;
import com.utn.parcial.api.ApiCallServiceEuro;
import com.utn.parcial.api.DolarResponse;
import com.utn.parcial.api.EuroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiCallServiceDolar apiCallServiceDolar;

    @Autowired
    private ApiCallServiceEuro apiCallServiceEuro;

    @GetMapping("dolar")
    public DolarResponse getDolarResponse() throws IOException, InterruptedException {
        return apiCallServiceDolar.getDolar();
    }

    @GetMapping("/euro")
    public EuroResponse getEuroResponse() throws IOException, InterruptedException {
        return apiCallServiceEuro.getEuro();
    }
}
