package co.com.devco.usecase.venta;

import co.com.devco.model.cliente.gateways.ClienteRepository;
import co.com.devco.model.venta.Venta;
import co.com.devco.model.venta.gateways.VentaRepository;
import co.com.devco.usecase.dto.VentaDto;
import co.com.devco.usecase.exception.ExcepcionNoEncontrado;
import co.com.devco.usecase.mapper.VentaMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class VentaUseCase {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;

    public List<VentaDto> obtenerVentas(){
        List<Venta> venta = this.ventaRepository.obtenerVentas();
        return VentaMapper.toListVentaDto(venta);
    }

    public VentaDto obtenerVentaPorId(Long id){
        Venta venta = this.ventaRepository.obtenerVenta(id).
                orElseThrow(() -> new ExcepcionNoEncontrado("Venta no encontada"));
        return VentaMapper.toVentaDto(venta);
    }
}
