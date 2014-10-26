package br.com.repository3d.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.repository.entidades.Categoria;
import br.com.repository.entidades.Objeto;
import br.com.repository3d.commom.ConversationBaseBean;
import br.com.repository3d.service.ObjetoService;

@Named
@ConversationScoped
public class ObjetoMB extends ConversationBaseBean {

	private static final long serialVersionUID = -5513605706796139506L;

	@EJB
	protected ObjetoService objetoService;
	
	private Objeto objeto;
	
	private Categoria categoria;
	
	private List<Objeto> objetoList;
	
	@PostConstruct
	public void init() {
		objeto = new Objeto();
		categoria = new Categoria();
		objetoList = objetoService.getAllOrderByNome();
	}
	
	public String cadastrarObjeto() {
		objeto.setCategoria(categoria);
		objetoService.salvar(objeto);
		objetoList = objetoService.getAllOrderByNome();
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful") );
        endConversation();
		return "listarObjeto?faces-redirect=true";
	}
	
	public String getAllDaCategoria(Categoria categoria) {
		objetoList = objetoService.getAllDaCategoria(categoria);
		return "/pages/objeto/listarObjeto?faces-redirect=true";
	}
	
	// *************************SETs & GETs ***********************/
	
	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Objeto> getObjetoList() {
		return objetoList;
	}

	public void setObjetoList(List<Objeto> objetoList) {
		this.objetoList = objetoList;
	}
	
}
