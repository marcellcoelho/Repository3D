package br.com.repository3d.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.repository.entidades.Categoria;
import br.com.repository.entidades.Objeto;
import br.com.repository3d.repository.ObjetoRepository;

@WebService
@Stateless
public class ObjetoService {

	@EJB
	private ObjetoRepository objetoRepository;

	@WebMethod(exclude=true)
	public Objeto salvar(Objeto objeto) {
		return objetoRepository.salvar(objeto);
	}
	
	@WebMethod(exclude=true)
	public List<Objeto> getAll() {
		return objetoRepository.getAll(Objeto.class);
	}

	@WebMethod(exclude=true)
	public List<Objeto> getAllDaCategoria(Categoria categoria) {
		return objetoRepository.getAllDaCategoria(categoria);
	}
	
	@WebMethod
	public List<Objeto> getAllObjetosPorIdCategoria(Long idCategoria) {
		return objetoRepository.getAllObjetosPorIdCategoria(idCategoria);
	}
	
	@WebMethod
	public Objeto getObjetoPorID(Long idObjeto) {
		return objetoRepository.getObjetoPorID(idObjeto);
	}
	
	@WebMethod(exclude=true)
	public List<Objeto> getAllOrderByNome() {
		return objetoRepository.getAllOrderByNome();
	}

	@WebMethod(exclude=true)
	public String convertX3dParaX3DOM(InputStream fileX3D) {
		String caminho = "c:\\x3d\\";
		salvarFileX3D(fileX3D, caminho);
		return extrairTagX3DdeHtml(caminho);  
	}

	@WebMethod(exclude=true)
	private String extrairTagX3DdeHtml(String caminho) {
		String TagX3D = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminho +"x3dom.html"));
			while(br.ready()){  
				TagX3D += br.readLine();  
			}  

			TagX3D = TagX3D.replaceAll("  ", "");

			Pattern pattern = Pattern.compile("\\<\\s*{0,}x3d\\s*{0,}.*\\<\\s{0,}/\\s{0,}x3d\\>", Pattern.CASE_INSENSITIVE);  
			Matcher matcher2 = pattern.matcher(TagX3D); 

			while(matcher2.find()){
				TagX3D = matcher2.group();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TagX3D;
	}

	@WebMethod(exclude=true)
	private void salvarFileX3D(InputStream fileX3D, String caminho) {
		String fileX3dParaSalva = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fileX3D));
			while (br.ready()) {
				fileX3dParaSalva += br.readLine();
			}
			br.close();
			FileWriter fw = new FileWriter(caminho + "x3d.x3d");
			fw.write(fileX3dParaSalva);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ConsoleService console = new ConsoleService();
		console.executaComando("cmd.exe /c " + caminho + "convertX3dToHtml.bat");
	}

}
