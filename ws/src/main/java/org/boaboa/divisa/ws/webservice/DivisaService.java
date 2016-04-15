package org.boaboa.divisa.ws.webservice;

import org.boaboa.divisa.domain.Cambio;
import org.boaboa.divisa.domain.Divisa;
import org.boaboa.divisa.domain.Respuesta;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

/**
 * Created by pperez on 15-04-16.
 */
@WebService
public interface DivisaService {

    Divisa consultarDivisa(@WebParam(name = "iso") String iso);

    Divisa consultarDivisaPorNombre(@WebParam(name = "nombre") String nombre);

    Cambio consultarCambio(@WebParam(name = "divisa1") String iso1, @WebParam(name = "divisa2") String iso2, @WebParam(name = "fecha") Date fecha);

    Divisa agregarDivisa(@WebParam(name = "iso") String iso, @WebParam(name = "nombre") String nombre);

    Cambio agregarCambio(@WebParam(name = "iso1") String iso1, @WebParam(name = "iso2") String iso2, @WebParam(name = "monto") Double monto, @WebParam(name = "fecha") Date fecha);

    Respuesta eliminarDivisa(@WebParam(name = "iso") String iso);

    Respuesta eliminarCambio(@WebParam(name = "divisa1") String iso1, @WebParam(name = "divisa2") String iso2, @WebParam(name = "fecha") Date fecha);

    // TODO: Agregar funciones de eliminar y actualizar cambios y divisas
}
