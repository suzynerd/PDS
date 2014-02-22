package controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoPerfil;

@Controller
public class BuscaController {
	
	@RequestMapping("/busca/Pessoas")
	public ModelAndView buscaPessoas(){
		ModelAndView model = new ModelAndView("busca/pessoas");
		model.addObject("perfis", DaoPerfil.getList(Tool.getSessionID()));
		return model;
	}
}
