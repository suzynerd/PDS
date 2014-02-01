package controle;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoTurma;
import dominio.Perfil;
import dominio.Turma;

@Controller
public class TurmaControler {

	@RequestMapping("/turmas")
	public ModelAndView turmas() throws SQLException{
		ModelAndView model = new ModelAndView("turmas");
		model.addObject("turmas", DaoTurma.listarTurmas());
		return model;
	}
	
	@RequestMapping("/novaturma")
	public ModelAndView novaTurma(){
		ModelAndView model = new ModelAndView("novaturma");
		model.addObject("turma", new Turma());
		return model;
	}
	
	@RequestMapping(value="/salvarturma", method=RequestMethod.GET)
	public String salvarTurma(@ModelAttribute Turma turma, HttpSession session) throws SQLException{
		DaoTurma.criarTurma(turma, ((Perfil) session.getAttribute("perfilLogado")).getIdPerfil());
		return "redirect:/turmas";
	}
	
}
