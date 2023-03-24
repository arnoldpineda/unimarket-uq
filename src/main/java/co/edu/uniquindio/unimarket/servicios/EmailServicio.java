package co.edu.uniquindio.unimarket.servicios;

import co.edu.uniquindio.unimarket.dto.EmailDTO;

public interface EmailServicio {
    String enviarEmail(EmailDTO emailDTO);
}
