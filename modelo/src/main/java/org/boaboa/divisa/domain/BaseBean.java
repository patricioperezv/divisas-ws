package org.boaboa.divisa.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Patricio A. Pérez Valverde
 */

@XmlRootElement
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 1830671565406937934L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
