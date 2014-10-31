package br.com.repository.enums;

public enum TipoArquivoEnum {
	X3D('X'), Imagem('I'), Audio('A');
	
    private Character value;
    
    private TipoArquivoEnum(Character value) {
    	this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

	public Character getValue() {
		return value;
	}
}
