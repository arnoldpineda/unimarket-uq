package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.entidades.Moderador;
import co.edu.uniquindio.unimarket.repositorios.ModeradorRepo;
import co.edu.uniquindio.unimarket.servicios.excepcion.ObjetoNoEncontradoException;
import co.edu.uniquindio.unimarket.servicios.interfaces.ModeradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;


    @Override
    public Moderador obtener(int codigoModerador) throws Exception {

        //Devuelve un moderador dado su codigo
        Optional<Moderador> moderador = moderadorRepo.findById(codigoModerador);

        if(moderador.isEmpty()){
            throw new ObjetoNoEncontradoException("El código "+codigoModerador+" no está asociado a ningún moderador");
        }
        return moderador.get();
    }
}
