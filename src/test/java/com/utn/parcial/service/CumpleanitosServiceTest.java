package com.utn.parcial.service;

import com.utn.parcial.api.ApiCallServiceDolar;
import com.utn.parcial.api.ApiCallServiceEuro;
import com.utn.parcial.model.Cumpleanitos;
import com.utn.parcial.repository.CumpleanitosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.utn.parcial.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CumpleanitosServiceTest {

    @InjectMocks
    private CumpleanitosService cumpleanitosService;

    @Mock
    private CumpleanitosRepository cumpleanitosRepository;

    @Mock
    private PersonaService personaService;

    @Mock
    private ApiCallServiceEuro apiCallServiceEuro;

    @Mock
    private ApiCallServiceDolar apiCallServiceDolar;

    @Test
    void shouldSaveCumpleanitos(){

        Cumpleanitos cumpleanitos= aCumpleanitos();

        Mockito.when(cumpleanitosRepository.save(cumpleanitos)).thenReturn(cumpleanitos);

        assertNotNull(cumpleanitos);

        cumpleanitosRepository.save(cumpleanitos);
    }

    @Test
    void getCumpleanitosById(){
        Cumpleanitos cumpleanitos= aCumpleanitos();
        Mockito.when(cumpleanitosRepository.findById(cumpleanitos.getId())).thenReturn(Optional.of(cumpleanitos));

        Cumpleanitos response = cumpleanitosService.getCumpleanitosById(1);
        assertNotNull(response);
        assertEquals(cumpleanitos, response);
    }

    @Test
    void shouldGetAll() {

        Pageable pageable = aPageable();
        Page<Cumpleanitos> page = aCumpleanitosPage();

        when(cumpleanitosRepository.findAll(pageable)).thenReturn(page);

        Page<Cumpleanitos> u = cumpleanitosService.allCumpleanitos(pageable);

        assertEquals(page.getContent().get(0).getId(),u.getContent().get(0).getId());
    }





}
