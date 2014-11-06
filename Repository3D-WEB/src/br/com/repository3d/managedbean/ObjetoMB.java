package br.com.repository3d.managedbean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.repository.entidades.Categoria;
import br.com.repository.entidades.Objeto;
import br.com.repository.enums.TipoArquivoEnum;
import br.com.repository3d.VO.Arquivo;
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
	
	private UploadedFile file;
	
	private List<Arquivo> arquivoList;
	
	@PostConstruct
	public void init() {
		objeto = new Objeto();
		categoria = new Categoria();
		arquivoList = new ArrayList<Arquivo>();
		objetoList = objetoService.getAllOrderByNome();
	}
	
	public String cadastrarObjeto() {
		try {
			objeto.setObjeto(objetoService.convertX3dParaX3DOM(arquivoList.get(0).getFile().getInputstream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	 public void handleFileUpload(FileUploadEvent event) {
	        Arquivo arquivo = new Arquivo();
			arquivo.setNome(event.getFile().getFileName());
			arquivo.setTipoArquivoEnum(TipoArquivoEnum.X3D);
			arquivo.setFile(event.getFile());
			arquivoList.add(arquivo);
			RequestContext.getCurrentInstance().update("form:arquivoList");
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Arquivo> getArquivoList() {
		return arquivoList;
	}

	public void setArquivoList(List<Arquivo> arquivoList) {
		this.arquivoList = arquivoList;
	}
	
}
