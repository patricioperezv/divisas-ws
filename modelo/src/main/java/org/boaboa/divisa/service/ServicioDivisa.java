package org.boaboa.divisa.service;

import org.boaboa.divisa.domain.Cambio;
import org.boaboa.divisa.domain.Divisa;

import java.util.Date;

/**
 * Created by pperez on 14-04-16.
 */
public interface ServicioDivisa {

    Divisa consultarDivisa(Long id);

    Divisa consultarDivisa(String iso);

    Divisa consultarDivisaPorNombre(String nombre);

    Cambio consultarCambio(Long id);

    Cambio consultarCambio(Divisa d1, Divisa d2, Date fecha);

    Divisa guardar(Divisa divisa);

    Cambio guardar(Cambio cambio);

    boolean eliminar(Divisa divisa);

    boolean eliminar(Cambio cambio);
}
