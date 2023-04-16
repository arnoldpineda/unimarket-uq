package co.edu.uniquindio.unimarket.dto;

import co.edu.uniquindio.unimarket.entidades.MetodoPago;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraDTO {

    @Positive
    private int codigoUsuario;
    private MetodoPago metodoPago;
    private List<DetalleCompraDTO> detalleCompraDTO;
}
