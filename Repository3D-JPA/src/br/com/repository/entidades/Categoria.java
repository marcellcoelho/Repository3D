package br.com.repository.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="TB_CATEGORIA")
public class Categoria extends AbstractEntity implements Comparable<Categoria>  {
	
	private static final long serialVersionUID = -1493169456436828330L;

	@Id
	@Column(name="ID_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCategoria;
	
	@Column(name="DS_NOME", nullable=false)
	private String nome;
	
	@Column(name="DS_DESCRICAO")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORIA_PAI")
	private Categoria categoriaPai;
	
	@OneToMany(mappedBy = "categoriaPai", fetch=FetchType.EAGER)
	private List<Categoria> subcategorias;
	
	@OneToMany(mappedBy = "categoria",  fetch=FetchType.EAGER )
	private List<Objeto> objetoList;
	
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
	public List<Objeto> getObjetoList() {
		return objetoList;
	}

	public void setObjetoList(List<Objeto> objetoList) {
		this.objetoList = objetoList;
	}

	@Override
	public Long getIdentificado() {
		return idCategoria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((categoriaPai == null) ? 0 : categoriaPai.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (categoriaPai == null) {
			if (other.categoriaPai != null)
				return false;
		} else if (!categoriaPai.equals(other.categoriaPai))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public int compareTo(Categoria categoria) {
		return this.getIdCategoria().compareTo(categoria.getIdCategoria());
	}

}
