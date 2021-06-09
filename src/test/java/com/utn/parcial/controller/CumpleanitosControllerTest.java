package com.utn.parcial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.parcial.model.Persona;
import com.utn.parcial.service.CumpleanitosService;
import com.utn.parcial.service.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = CumpleanitosController.class)
public class CumpleanitosControllerTest {

    private CumpleanitosService cumpleanitosService;

    private CumpleanitosController cumpleanitosController;

    //private static List<Persona> EMPTY_LIST = Collections.emptyList();


    @BeforeEach
    public void setUp() {
        cumpleanitosService = mock(CumpleanitosService.class);
        cumpleanitosController = new CumpleanitosController(cumpleanitosService);
    }


}
