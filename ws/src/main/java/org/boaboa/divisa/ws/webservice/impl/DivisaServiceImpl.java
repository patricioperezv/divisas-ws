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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pperez on 15-04-16.
 */
@WebService(endpointInterface = "org.boaboa.divisa.ws.webservice.DivisaService")
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
        Divisa divisa = null;
        try {
            if (StringUtils.isNoneBlank(nombre)) {
                divisa = this.servicioDivisa.consultarDivisaPorNombre(nombre);
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al consultar divisa: {}", e.toString());
            logger.debug("Error al consultar divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Cambio consultarCambio(@WebParam(name = "divisa1") String iso1, @WebParam(name = "divisa2") String iso2, @WebParam(name = "fecha") Date fecha) {
        Cambio cambio = null;
        if (StringUtils.isNoneBlank(iso1) && StringUtils.isNoneBlank(iso2) && fecha != null) {
            Divisa d1 = this.consultarDivisa(iso1);
            Divisa d2 = this.consultarDivisa(iso2);

            if (d1 != null && d2 != null) {
                cambio = this.servicioDivisa.consultarCambio(d1, d2, fecha);
                if (cambio == null) { // Quizas está la relación inversa
                    cambio = this.servicioDivisa.consultarCambio(d2, d1, fecha);
                    if (cambio != null) { // Si es al revés, es 1/ tasa de cambio
                        cambio.setMonto(1 / cambio.getMonto());
                    }
                }
            }
        }
        return cambio;
    }

    @Override
    public Divisa agregarDivisa(@WebParam(name = "iso") String iso, @WebParam(name = "nombre") String nombre) {
        Divisa divisa = null;
        try {
            if (StringUtils.isNoneBlank(iso) && nombre != null) {
                divisa = new Divisa();
                divisa.setIso(iso);
                divisa.setNombre(nombre);
                Divisa persistida = this.servicioDivisa.guardar(divisa);

                if (persistida != null) {
                    logger.info("Se añadio correctamente una divisa: {}", persistida);
                }
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al crear divisa: {}", e.toString());
            logger.debug("Error al crear divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Cambio agregarCambio(@WebParam(name = "iso1") String iso1, @WebParam(name = "iso2") String iso2, @WebParam(name = "monto") Double monto, @WebParam(name = "fecha") Date fecha) {
        Cambio cambio = null;
        try {
            if (StringUtils.isNoneBlank(iso1) && StringUtils.isNoneBlank(iso2) && monto != null && fecha != null) {
                Divisa d1 = this.servicioDivisa.consultarDivisa(iso1);
                Divisa d2 = this.servicioDivisa.consultarDivisa(iso2);

                if (d1 != null && d2 != null && d1 != d2) {
                    cambio = new Cambio();
                    cambio.setDivisa1(d1);
                    cambio.setDivisa2(d2);
                    cambio.setMonto(monto);
                    cambio.setFecha(fecha);

                    Cambio persistido = this.servicioDivisa.guardar(cambio);
                    if (persistido != null) {
                        logger.info("Se añadió correctamente un cambio: {}", persistido);
                    }
                }
            }
        } catch (Exception e) {
            cambio = null;
            logger.error("Error al crear cambio: {}", e.toString());
            logger.debug("Error al crear cambio: {}", e.toString(), e);
        }
        return cambio;
    }

    @Override
    public Respuesta eliminarDivisa(@WebParam(name = "iso") String iso) {
        Respuesta respuesta = new Respuesta();
        respuesta.setEstado(false);
        respuesta.setMensaje("Error al eliminar divisa :(");
        try {
            if (StringUtils.isNoneBlank(iso)) {
                Divisa divisa = this.servicioDivisa.consultarDivisa(iso);
                if (divisa != null) {
                    this.servicioDivisa.eliminar(divisa);
                    logger.info("Se eliminó correctamente la divisa: {}", divisa);
                    respuesta.setEstado(true);
                    respuesta.setMensaje(String.format("Se eliminó correctamente la divisa: {}", divisa));
                }
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al eliminar divisa :(");
            logger.error("Error al eliminar divisa: {}", e.toString());
            logger.debug("Error al eliminar divisa: {}", e.toString(), e);
        }
        return respuesta;
    }

    @Override
    public Respuesta eliminarCambio(@WebParam(name = "divisa1") String iso1, @WebParam(name = "divisa2") String iso2, @WebParam(name = "fecha") Date fecha) {
        Respuesta respuesta = new Respuesta();
        respuesta.setEstado(false);
        respuesta.setMensaje("Error al eliminar cambio :(");
        try {
            if (StringUtils.isNoneBlank(iso1) && StringUtils.isNoneBlank(iso2) && fecha != null) {
                Divisa divisa1 = this.servicioDivisa.consultarDivisa(iso1);
                Divisa divisa2 = this.servicioDivisa.consultarDivisa(iso2);
                if (divisa1 != null && divisa2 != null) {
                    Cambio cambio = this.servicioDivisa.consultarCambio(divisa1, divisa2, fecha);
                    if (cambio != null) {
                        this.servicioDivisa.eliminar(cambio);
                        logger.info("Se eliminó correctamente el cambio: {}", cambio);
                        respuesta.setEstado(true);
                        respuesta.setMensaje(String.format("Se eliminó correctamente el cambio: {}", cambio));
                    }
                }
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al eliminar cambio :(");
            logger.error("Error al eliminar cambio: {}", e.toString());
            logger.debug("Error al eliminar cambio: {}", e.toString(), e);
        }
        return respuesta;
    }

    @Override
    public List<Cambio> consultarCambios() {
        List<Cambio> cambios = new ArrayList<>();
        try {
            cambios = this.servicioDivisa.consultarCambios();
        } catch (Exception e) {
            cambios = new ArrayList<>();
            logger.error("Error al consultar cambios: {}", e.toString());
            logger.debug("Error al consultar cambios: {}", e.toString(), e);
        }
        return cambios;
    }

    @Override
    public List<Divisa> consultarDivisas() {
        List<Divisa> divisas = new ArrayList<>();
        try {
            divisas = this.servicioDivisa.consultarDivisas();
        } catch (Exception e) {
            divisas = new ArrayList<>();
            logger.error("Error al consultar divisas: {}", e.toString());
            logger.debug("Error al consultar divisas: {}", e.toString(), e);
        }
        return divisas;
    }
}
