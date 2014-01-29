package controle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dominio.Turma;

@Controller
public class TurmaControler {
	private List<Turma> turmas = new ArrayList<>();

	@RequestMapping("/turmas")
	public ModelAndView turmas(){
		ModelAndView model = new ModelAndView("turmas");
		model.addObject("turmas", turmas);
		return model;
	}
	
	@RequestMapping("/novaturma")
	public ModelAndView novaTurma(){
		ModelAndView model = new ModelAndView("novaturma");
		return model;
	}
	
	@RequestMapping(value="/salvarturma", method=RequestMethod.GET)
	public ModelAndView salvarTurma(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao){
		turmas.add(new Turma(nome, descricao));
		ModelAndView model = new ModelAndView("turmas");
		model.addObject("turmas", turmas);
		return model;
	}
	
}
