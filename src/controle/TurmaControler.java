package controle;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoTurma;
import dominio.Perfil;
import dominio.Turma;

@Controller
public class TurmaControler {

	@RequestMapping("/turmas")
	public ModelAndView turmas(HttpSession session) throws SQLException{
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
		if (Tool.getId(session) == 2) {
			DaoTurma.criarTurma(turma, ((Perfil) session.getAttribute("perfilLogado")).getIdPerfil());
			return "redirect:/turmas";
		}else
			return "redirect:/perfil";
		
	}
	
	@RequestMapping("/minhasturmas")
	public ModelAndView minhasTurmas(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("turmas");
		model.addObject("turmas", DaoTurma.listarTurmas(Tool.getId(session)));
		return model;
	}
	
	@RequestMapping("/turma")
	public ModelAndView turma(@RequestParam("idTurma") Integer idTurma, HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("minhaTurma");
		model.addObject("turma", DaoTurma.findTurma(idTurma));
		session.setAttribute("turmaAtual", DaoTurma.findTurma(idTurma));
		return model;	
	}
	
	@RequestMapping("/turma/alunos")
	public ModelAndView alunos(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("alunos");
		model.addObject("perfis", DaoTurma.listarAlunos(Tool.getIdTurma(session)));
		model.addObject("turma", (Turma) session.getAttribute("turmaAtual"));
		return model;
	}
	
	@RequestMapping("/turma/novoAluno")
	public ModelAndView novoAluno(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("novoAluno");
		model.addObject("alunos", DaoTurma.listarAlunos(Tool.getIdTurma(session)));
		model.addObject("perfis", DaoTurma.novosAlunos(Tool.getIdTurma(session), Tool.getId(session)));
		return model;
	}
	
	@RequestMapping("/turma/adicionarAluno")
	public String salvarAluno(HttpSession session, @RequestParam("idAluno") Integer id) throws SQLException{
		DaoTurma.addAluno(Tool.getIdTurma(session), id);
		return "redirect:/turma/novoAluno";
	}
	
	@RequestMapping("/turma/removerAluno")
	public String deletarAluno(HttpSession session, @RequestParam("idAluno") Integer id) throws SQLException{
		DaoTurma.removeAluno(Tool.getIdTurma(session), id);
		return "redirect:/turma/alunos";
	}
	
}
