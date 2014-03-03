package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoPerfil;

@Controller
public class PerfilControler {

	@RequestMapping("/perfil")
	public ModelAndView perfil(){
		ModelAndView model = new ModelAndView("user/main");
		return model;
	}
	
	@RequestMapping("/perfil/preferencias")
	public ModelAndView preferencias(){
		ModelAndView model = new ModelAndView("user/preferencias");
		return model;
	}
	
	@RequestMapping("/perfil/alterarDados")
	public String alterarDados(@RequestParam("nome") String nome, @RequestParam("email") String email){
		DaoPerfil.update(nome, email);
		
		return "/";
	}
	public ModelAndView turmas(){
		ModelAndView model = new ModelAndView("user/turmas");
		return model;
	}
}
