package tools;

import org.springframework.security.core.context.SecurityContextHolder;

import dominio.Perfil;

public class Tool {
	
	public static Perfil getSessionObject(){
		return (Perfil) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}
	
	public static Integer getSessionID(){
		return getSessionObject().getId();
	}
}
