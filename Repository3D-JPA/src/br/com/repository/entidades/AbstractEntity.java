package br.com.repository.entidades;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 8876009197813513598L;
	public abstract Long getIdentificado() ;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdentificado() == null) ? 0 : getIdentificado().hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        if (getIdentificado() == null) {
            if (other.getIdentificado() != null)
                return false;
        } else if (!getIdentificado().equals(other.getIdentificado()))
            return false;
        return true;
    }
}