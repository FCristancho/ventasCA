package co.com.devco.usecase.mapper;

import co.com.devco.model.cliente.Cliente;
import co.com.devco.usecase.dto.ClienteDto;

import java.util.List;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteDto toClienteDto(Cliente cliente){
        return ClienteDto.builder()
                .id(cliente.getId())
                .numeroIdentificacion(cliente.getNumeroIdentificacion())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .email(cliente.getEmail())
                .direccion(cliente.getDireccion())
                .activo(cliente.isActivo())
                .build();
    }

    public static List<ClienteDto> toListClienteDto(List<Cliente> clientes){
        return clientes.stream().map(ClienteMapper::toClienteDto).toList();
    }
}
