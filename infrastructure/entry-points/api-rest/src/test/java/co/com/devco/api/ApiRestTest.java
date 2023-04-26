package co.com.devco.api;

import co.com.devco.usecase.cliente.ClienteUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiRest.class)
class ApiRestTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteUseCase useCase;

    @Test
    void testObtenerCliente() throws Exception {
        when(useCase.obtenerCliente(anyLong())).thenReturn(Datos.CLIENTE);

        mvc.perform(get("/api/clientes/1").contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Felipe"));
    }

}