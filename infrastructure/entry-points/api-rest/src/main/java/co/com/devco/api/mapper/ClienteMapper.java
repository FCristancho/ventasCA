package co.com.devco.api.mapper;

import co.com.devco.api.dto.ClienteDto;
import co.com.devco.model.cliente.Cliente;
import lombok.AllArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClienteMapper {

    private final ObjectMapper mapper;

    public Cliente clienteDtoToCliente(ClienteDto clienteDto){
        return  mapper.mapBuilder(clienteDto, Cliente.class).toBuilder()
                .numeroIdentificacion(clienteDto.getNumeroIdentificacion())
                .nombre(clienteDto.getNombre())
                .telefono(clienteDto.getTelefono())
                .email(clienteDto.getEmail())
                .direccion(clienteDto.getDireccion())
                .build();
    }
}
