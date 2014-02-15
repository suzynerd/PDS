package tools;

import javax.servlet.http.HttpSession;

import dominio.Perfil;
import dominio.Turma;

public class Tool {
	public static Tool Tool;
	
	public static synchronized Tool getInstance(){
		if (Tool == null)
			Tool = new Tool();
		return Tool;
	}
	
	public static Integer getIdPerfil(HttpSession session){
		return ((Perfil) session.getAttribute("perfilLogado")).getId();
	}
	
	public static Integer getIdTurma(HttpSession session){
		return ((Turma) session.getAttribute("turmaAtual")).getId();
	}
	
	public static Integer getTipoPerfil(HttpSession session){
		return ((Perfil) session.getAttribute("perfilLogado")).getIdTipoPerfil();
	}
}
