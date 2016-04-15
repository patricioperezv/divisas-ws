package org.boaboa.divisa.repository;

import org.boaboa.divisa.domain.Cambio;
import org.boaboa.divisa.domain.Divisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by pperez on 14-04-16.
 */
public interface CambioRepository extends JpaRepository<Cambio, Long> {

    List<Cambio> findAllByOrderByFechaAscDivisa1IsoAscDivisa2IsoAsc();

    Cambio findByDivisa1AndDivisa2AndFecha(Divisa divisa1, Divisa divisa2, Date fecha);
}
