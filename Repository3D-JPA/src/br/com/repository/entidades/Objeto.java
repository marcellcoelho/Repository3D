package br.com.repository.entidades;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_OBJETO")
public class Objeto extends AbstractEntity  {
	
	private static final long serialVersionUID = -5444577034450119188L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_OBJETO")
	private Long idObjeto;
	
	@Column(name="DS_NOME")
	private String nome;
		
	@Lob
	@Column(name="BL_OBJETO")
	private String objeto;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORIA")
	private Categoria categoria;
	
	public Long getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public Long getIdentificado() {
		return idObjeto;
	}
}
