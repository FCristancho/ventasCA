package co.com.devco.api;

import co.com.devco.api.dto.ClienteDto;
import co.com.devco.api.mapper.ClienteMapper;
import co.com.devco.model.cliente.Cliente;
import co.com.devco.usecase.cliente.ClienteUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private static final String OK = "Ok";
    private static final String CREACION_CORRECTA = "Cliente creado correctamente";
    private static final String DESACTIVACION_CORRECTA = "Cliente desactivado correctamente";


    private final ClienteUseCase useCase;
    private final ClienteMapper mapper;


    @GetMapping()
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> respuesta = useCase.obtenerClientes();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id){
        Cliente respuesta = useCase.obtenerCliente(id);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDto clienteDto){
        Cliente cliente = mapper.clienteDtoToCliente(clienteDto);
        Cliente respuesta =useCase.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> modificarCliente(@RequestBody Cliente cliente, @PathVariable Long id){
        cliente.setId(id);
        Cliente respuesta = useCase.modificarCliente(cliente);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> desactivarCliente(@PathVariable Long id){
        Cliente respuesta = useCase.desactivarCliente(id);
        return ResponseEntity.ok(respuesta);
    }
}
