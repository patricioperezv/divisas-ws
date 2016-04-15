package org.boaboa.divisa.domain;

import javax.persistence.*;

/**
 * Created by pperez on 14-04-16.
 */
@Entity
@Table(name = "divisas")
public class Divisa extends BaseBean {

    private static final long serialVersionUID = 8555574128849109015L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iso", nullable = false, unique = true)
    private String iso;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
