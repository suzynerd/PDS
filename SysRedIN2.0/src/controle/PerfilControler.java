package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfilControler {

	@RequestMapping("/perfil")
	public ModelAndView perfil(){
		ModelAndView model = new ModelAndView("user/main");
		return model;
	}
	
	public ModelAndView turmas(){
		ModelAndView model = new ModelAndView("user/turmas");
		return model;
	}
}
