package br.com.repository3d.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleService {
	private StringBuffer retornoDoComando;
	private StringBuffer erroDoComando;
	private boolean teveErro;

	public void executaComando(String comando) {
		String retorno;
		retornoDoComando = new StringBuffer();
		erroDoComando = new StringBuffer();

		try {
			Process processo = Runtime.getRuntime().exec(comando);

			InputStream streamEntrada = processo.getInputStream();
			InputStream streamErro = processo.getErrorStream();
			BufferedReader saidaDoProcesso;

			saidaDoProcesso = new BufferedReader(new InputStreamReader(
					streamEntrada));
			while ((retorno = saidaDoProcesso.readLine()) != null) {
				retornoDoComando.append(retorno + "\r\n");
			}

			saidaDoProcesso = new BufferedReader(new InputStreamReader(
					streamErro));
			while ((retorno = saidaDoProcesso.readLine()) != null) {
				erroDoComando.append(retorno + "\r\n");
			}

			processo.waitFor();

			saidaDoProcesso.close();
			streamErro.close();
			streamEntrada.close();
			processo.getOutputStream().close();
			teveErro = false;

		} catch (IOException mensagemDeErro) {
			teveErro = true;
			erroDoComando.append(mensagemDeErro.toString());
		} catch (InterruptedException mensagemDeErro) {
			teveErro = true;
			erroDoComando.append(mensagemDeErro.toString());
		}
	}

	public String PegaRetorno() {
		if (retornoDoComando.length() > 0) {
			return retornoDoComando.toString();
		} else {
			return null;
		}
	}

	public String PegaErros() {
		if (erroDoComando.length() > 0) {
			return erroDoComando.toString();
		} else {
			return null;
		}
	}

	public boolean isTeveErro() {
		return teveErro;
	}

	public void setTeveErro(boolean teveErro) {
		this.teveErro = teveErro;
	}

}
