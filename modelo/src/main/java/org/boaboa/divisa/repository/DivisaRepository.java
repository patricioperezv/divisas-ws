package org.boaboa.divisa.repository;

import org.boaboa.divisa.domain.Divisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by pperez on 14-04-16.
 */
public interface DivisaRepository extends JpaRepository<Divisa, Long> {

    Divisa findByNombre(String nombre);

    Divisa findByIsoIgnoreCase(String iso);

    List<Divisa> findAllByOrderByIsoAsc();
}
