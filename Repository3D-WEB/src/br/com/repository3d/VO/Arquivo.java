package br.com.repository3d.VO;

import org.primefaces.model.UploadedFile;

import br.com.repository.enums.TipoArquivoEnum;

public class Arquivo {

	private Long idArquivo;
	
	private TipoArquivoEnum tipoArquivoEnum;
	
	private String nome;
	
	private UploadedFile file;

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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
}
