package co.com.devco.usecase.cliente;

import co.com.devco.model.cliente.Cliente;
import co.com.devco.model.cliente.gateways.ClienteRepository;
import co.com.devco.usecase.dto.ClienteDto;
import co.com.devco.usecase.exception.ExcepcionDuplicidad;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import co.com.devco.usecase.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static co.com.devco.model.utils.ValidadorArgumento.validarEmail;
import static co.com.devco.usecase.utils.Messages.*;

@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteRepository clienteGateway;

    public List<ClienteDto> obtenerClientes(){
        List<Cliente> cliente = clienteGateway.obtenerClientes();
        return ClienteMapper.toListClienteDto(cliente);
    }

    public Cliente crearCliente(Cliente cliente){
        validarExistenciaPreviaCliente(cliente);
        return clienteGateway.guardarCliente(cliente);
    }

    private void validarExistenciaPreviaCliente(Cliente cliente){
        boolean existe = this.clienteGateway.existeIdentificacion(cliente.getNumeroIdentificacion());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    public Cliente obtenerCliente(Long id){
        return this.clienteGateway.obtenerCliente(id).orElseThrow(
                () -> new ExcepcionNoEncontrado(CLIENTE_NO_ENCONTRADO)
        );
    }

    public Cliente modificarCliente(Cliente clienteModificado){
        Cliente clienteActualizado = validarActualizacionCliente(clienteModificado);
        return this.clienteGateway.guardarCliente(clienteActualizado);
    }

    private Cliente validarActualizacionCliente(Cliente clienteModificado){
        Cliente clienteActual = this.obtenerCliente(clienteModificado.getId());

        clienteActual.setNombre(obtenerCampoActualizado(clienteModificado.getNombre(), clienteActual.getNombre()));
        clienteActual.setTelefono(obtenerCampoActualizado(clienteModificado.getTelefono(), clienteActual.getTelefono()));
        validarEmail(clienteModificado.getEmail(), EMAIL_INVALIDO);
        clienteActual.setEmail(obtenerCampoActualizado(clienteModificado.getEmail(), clienteActual.getEmail()));
        clienteActual.setDireccion(clienteModificado.getDireccion());

        return clienteActual;
    }

    private String obtenerCampoActualizado(String valorNuevo, String valorActual){
        return (valorNuevo.isBlank()) ? valorActual : valorNuevo;
    }

    public Cliente desactivarCliente(Long id){
        Cliente cliente = this.obtenerCliente(id);
        cliente.cambiarEstado();
        return clienteGateway.guardarCliente(cliente);
    }

}
