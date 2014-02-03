package tools;

import javax.servlet.http.HttpSession;

import dominio.Perfil;

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
}
