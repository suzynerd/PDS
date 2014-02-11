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
	
	public static Integer getId(HttpSession session){
		return ((Perfil) session.getAttribute("perfilLogado")).getIdPerfil();
	}
	
	public static Integer getIdTurma(HttpSession session){
		return ((Turma) session.getAttribute("turmaAtual")).getId();
	}
}
