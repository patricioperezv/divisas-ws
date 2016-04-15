package org.boaboa.divisa.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.boaboa.divisa.domain.Cambio;
import org.boaboa.divisa.domain.Divisa;
import org.boaboa.divisa.repository.CambioRepository;
import org.boaboa.divisa.repository.DivisaRepository;
import org.boaboa.divisa.service.ServicioDivisa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pperez on 14-04-16.
 */
@Service
public class ServicioDivisaImpl implements Serializable, ServicioDivisa {
    private static final long serialVersionUID = 9152239317111653703L;
    private static final Logger logger = LoggerFactory.getLogger(ServicioDivisaImpl.class);

    @Inject
    private DivisaRepository divisaRepository;
    @Inject
    private CambioRepository cambioRepository;

    @Override
    public Divisa consultarDivisa(Long id) {
        Divisa divisa = null;
        try {
            if (id != null) {
                divisa = this.divisaRepository.findOne(id);
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al consultar divisa: {}", e.toString());
            logger.debug("Error al consultar divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Divisa consultarDivisa(String iso) {
        Divisa divisa = null;
        try {
            if (StringUtils.isNoneBlank(iso)) {
                divisa = this.divisaRepository.findByIso(iso);
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al consultar divisa: {}", e.toString());
            logger.debug("Error al consultar divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Divisa consultarDivisaPorNombre(String nombre) {
        Divisa divisa = null;
        try {
            if (StringUtils.isNoneBlank(nombre)) {
                divisa = this.divisaRepository.findByNombre(nombre);
            }
        } catch (Exception e) {
            divisa = null;
            logger.error("Error al consultar divisa: {}", e.toString());
            logger.debug("Error al consultar divisa: {}", e.toString(), e);
        }
        return divisa;
    }

    @Override
    public Cambio consultarCambio(Long id) {
        Cambio cambio = null;
        try {
            if (id != null) {
                cambio = this.cambioRepository.findOne(id);
            }
        } catch (Exception e) {
            cambio = null;
            logger.error("Error al consultar cambio: {}", e.toString());
            logger.debug("Error al consultar cambio: {}", e.toString(), e);
        }
        return cambio;
    }

    @Override
    public Cambio consultarCambio(Divisa d1, Divisa d2, Date fecha) {
        Cambio cambio = null;
        try {
            if (d1 != null && d2 != null && fecha != null) {
                cambio = this.cambioRepository.findByDivisa1AndDivisa2AndFecha(d1, d2, fecha);
            }
        } catch (Exception e) {
            cambio = null;
            logger.error("Error al consultar cambio: {}", e.toString());
            logger.debug("Error al consultar cambio: {}", e.toString(), e);
        }
        return cambio;
    }

    @Override
    @Transactional
    public Divisa guardar(Divisa divisa) {
        Divisa persistida = null;
        try {
            if (divisa != null) {
                persistida = this.divisaRepository.save(divisa);
                if (persistida != null) {
                    logger.info("Se ha persistido: {}", persistida);
                }
            }
        } catch (Exception e) {
            persistida = null;
            logger.error("Error al persistir divisa: {}", e.toString());
            logger.debug("Error al persistir divisa: {}", e.toString(), divisa);
        }
        return persistida;
    }

    @Override
    @Transactional
    public Cambio guardar(Cambio cambio) {
        Cambio persistido = null;
        try {
            if (cambio != null) {
                persistido = this.cambioRepository.save(cambio);
                if (persistido != null) {
                    logger.info("Se ha persistido: {}", persistido);
                }
            }
        } catch (Exception e) {
            persistido = null;
            logger.error("Error al persistir cambio: {}", e.toString());
            logger.debug("Error al persistir cambio: {}", e.toString(), e, cambio);
        }
        return persistido;
    }

    @Override
    @Transactional
    public boolean eliminar(Divisa divisa) {
        boolean resultado = false;
        try {
            if (divisa != null) {
                this.divisaRepository.delete(divisa);
                logger.info("Se ha eliminado: {}", divisa);
                resultado = true;
            }
        } catch (Exception e) {
            resultado = false;
            logger.error("Error al eliminar divisa: {}", e.toString());
            logger.debug("Error al eliminar divisa: {}", e.toString(), e, divisa);
        }
        return resultado;
    }

    @Override
    @Transactional
    public boolean eliminar(Cambio cambio) {
        boolean resultado = false;
        try {
            if (cambio != null) {
                this.cambioRepository.delete(cambio);
                logger.info("Se ha eliminado: {}", cambio);
                resultado = true;
            }
        } catch (Exception e) {
            resultado = false;
            logger.error("Error al eliminar cambio: {}", e.toString());
            logger.debug("Error al eliminar cambio: {}", e.toString(), e, cambio);
        }
        return resultado;
    }
}
