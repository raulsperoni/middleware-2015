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

    public int getCantidadDeMensajes() {
        return config.getMensajes().size();
    }


}
