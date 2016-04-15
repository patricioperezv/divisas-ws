package org.boaboa.divisa.ws.webservice.impl;

import org.apache.commons.lang3.StringUtils;
import org.boaboa.divisa.domain.Cambio;
import org.boaboa.divisa.domain.Divisa;
import org.boaboa.divisa.domain.Respuesta;
import org.boaboa.divisa.service.ServicioDivisa;
import org.boaboa.divisa.ws.webservice.DivisaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pperez on 15-04-16.
 */
@WebService(endpointInterface = "org.boaboa.divisa.ws.webservice.Divisa")
@Service
public class DivisaServiceImpl implements Serializable, DivisaService {
    private static final long serialVersionUID = -2242598309522768573L;
    private static final Logger logger = LoggerFactory.getLogger(DivisaServiceImpl.class);



    @Inject
    private ServicioDivisa servicioDivisa;

    @Override
    public Divisa consultarDivisa(@WebParam(name = "iso") String iso) {
        Divisa divisa = null;
        try {
            if (StringUtils.isNoneBlank(iso)) {
                divisa = this.servicioDivisa.consultarDivisa(iso);
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al consultar divisa: {}", e.toString());
            logger.debug("Error al consultar divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Divisa consultarDivisaPorNombre(@WebParam(name = "nombre") String nombre) {
        return null;
    }

    @Override
    public Cambio consultarCambio(@WebParam(name = "divisa1") String iso1, @WebParam(name = "divisa1") String iso2, @WebParam(name = "fecha") Date fecha) {
        return null;
    }

    @Override
    public Respuesta agregarDivisa(@WebParam(name = "iso") String iso, @WebParam(name = "nombre") String nombre) {
        return null;
    }

    @Override
    public Respuesta agregarCambio(@WebParam(name = "iso1") String iso1, @WebParam(name = "iso2") String iso2, @WebParam(name = "monto") Double monto, @WebParam(name = "fecha") Date fecha) {
        return null;
    }
}
