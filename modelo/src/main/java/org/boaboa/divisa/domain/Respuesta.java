package org.boaboa.divisa.domain;

/**
 * Created by pperez on 15-04-16.
 */
public class Respuesta extends BaseBean {
    private static final long serialVersionUID = 5152521570683753318L;

    private boolean estado = false;

    private String mensaje;

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
