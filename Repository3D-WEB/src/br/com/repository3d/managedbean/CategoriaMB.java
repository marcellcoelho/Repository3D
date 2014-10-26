package br.com.repository3d.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.repository.entidades.Categoria;
import br.com.repository3d.service.CategoriaService;

@ViewScoped
@ManagedBean(name="categoriaMB")
public class CategoriaMB implements Serializable {
	
	private static final long serialVersionUID = 199085770373746989L;
	
	@EJB
	protected CategoriaService categoriaService;
	
	private Categoria categoriaPai;
	
	private Categoria categoria;
	
	private TreeNode raiz;
	
	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categoriaPai = new Categoria();
		getAllRaizes();
	}
	
	public void cadastrarCategoria() {
		categoria.setCategoriaRaiz(true);
		categoriaService.salvar(categoria);
		categoria = new Categoria();
		getAllRaizes();
		RequestContext.getCurrentInstance().update("formList");
		RequestContext.getCurrentInstance().execute("PF('dlgCategoria').hide()");
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful") );
	}
	
	public void cadastrarSubcategoria() {
		categoria.setCategoriaPai(categoriaPai);
		categoriaService.salvar(categoria);
		categoriaPai = new Categoria();
		categoria = new Categoria();
        getAllRaizes();
		RequestContext.getCurrentInstance().update("formList");
		RequestContext.getCurrentInstance().execute("PF('dlgSubcategoria').hide()");
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful") );
	}
	
	public void getAllRaizes() {
		List<Categoria> categoriasRaizes = categoriaService.getAllRaizes();
		this.raiz = new DefaultTreeNode("Raiz", null);
		adicionarNos(categoriasRaizes, this.raiz);
	}
	
	private void adicionarNos(List<Categoria> categorias, TreeNode pai) {
		for (Categoria categoria : categorias) {
			TreeNode no = new DefaultTreeNode(categoria, pai);
			adicionarNos(categoria.getSubcategorias(), no);
		}
	}
	
	public void abrirModalCadastroSubcategoria(Categoria categoria) {
		this.categoriaPai = categoria;
		RequestContext.getCurrentInstance().update("subcategoriaDlg");
		RequestContext.getCurrentInstance().execute("PF('dlgSubcategoria').show()");
	}
	
	public void abrirModalCadastroObjeto(Categoria categoria) {
		this.categoriaPai = categoria;
		RequestContext.getCurrentInstance().update("subcategoriaDlg");
		RequestContext.getCurrentInstance().execute("PF('dlgSubcategoria').show()");
	}

	// *************************SETs & GETs ***********************/
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public TreeNode getRaiz() {
		return raiz;
	}
	
	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

}
