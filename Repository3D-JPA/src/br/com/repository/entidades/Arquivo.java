package br.com.repository.entidades;

import br.com.repository.enums.TipoArquivoEnum;

public class Arquivo {

	private Long idArquivo;
	
	private TipoArquivoEnum tipoArquivoEnum;
	
	private String nome;

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public TipoArquivoEnum getTipoArquivoEnum() {
		return tipoArquivoEnum;
	}

	public void setTipoArquivoEnum(TipoArquivoEnum tipoArquivoEnum) {
		this.tipoArquivoEnum = tipoArquivoEnum;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
