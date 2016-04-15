package org.boaboa.divisa.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Created by pperez on 14-04-16.
 */
@Entity
@Table(name = "cambios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"divisa1_id", "divisa2_id", "fecha"})
})
public class Cambio extends BaseBean {
    private static final long serialVersionUID = -1856027809401912852L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "divisa1_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Divisa divisa1;

    @JoinColumn(name = "divisa2_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Divisa divisa2;

    @Min(0)
    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Divisa getDivisa1() {
        return divisa1;
    }

    public void setDivisa1(Divisa divisa1) {
        this.divisa1 = divisa1;
    }

    public Divisa getDivisa2() {
        return divisa2;
    }

    public void setDivisa2(Divisa divisa2) {
        this.divisa2 = divisa2;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
