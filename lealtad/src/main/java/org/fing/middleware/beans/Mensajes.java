package org.fing.middleware.beans;

import org.fing.middleware.servlet.Config;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by raul on 06/09/15.
 */
@ManagedBean
public class Mensajes implements Serializable {

    Config config = Config.getInstance();

    Long idUsuarioConsulta;
    Usuario usuario;

    public int getCantidadDeMensajes() {
        return config.getMensajes().size();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuarioConsulta() {
        return idUsuarioConsulta;
    }

    public void setIdUsuarioConsulta(Long idUsuarioConsulta) {
        this.idUsuarioConsulta = idUsuarioConsulta;
        this.usuario = config.getMensajes().get(idUsuarioConsulta);
    }
}
