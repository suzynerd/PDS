package tools;

import javax.servlet.http.HttpSession;

import dominio.Perfil;
import dominio.Turma;

public class PerfilTool {
	public static PerfilTool perfilTool;
	
	public static synchronized PerfilTool getInstance(){
		if (perfilTool == null)
			perfilTool = new PerfilTool();
		return perfilTool;
	}
	
	public static Integer getId(HttpSession session){
		return ((Perfil) session.getAttribute("perfilLogado")).getIdPerfil();
	}
	
	public static Integer getIdTurma(HttpSession session){
		return ((Turma) session.getAttribute("turmaAtual")).getId();
	}
}
