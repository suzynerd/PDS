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
import dao.DaoNotificacao;
import dao.DaoTurma;
import dominio.Notificacao;
import dominio.Perfil;
import dominio.Turma;

@Controller
public class TurmaControler {

	@RequestMapping("/turmas")
	public ModelAndView turmas(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("User/Turmas");
		model.addObject("turmas", DaoTurma.getList(Tool.getTipoPerfil(session), Tool.getIdPerfil(session)));
		return model;
	}
	
	@RequestMapping("/turmas/entrar")
	public String entrarTurma(HttpSession session, @RequestParam("idTurma") Integer idTurma){
		DaoNotificacao.insert(new Notificacao(Tool.getIdPerfil(session), idTurma));
		return "/turmas";
	}
	
	@RequestMapping("/novaturma")
	public ModelAndView novaTurma(){
		ModelAndView model = new ModelAndView("User/NovaTurma");
		model.addObject("turma", new Turma());
		return model;
	}
	
	@RequestMapping(value="/salvarturma", method=RequestMethod.GET)
	public String salvarTurma(@ModelAttribute Turma turma, HttpSession session) throws SQLException{
		if (Tool.getTipoPerfil(session) == 2) {
			DaoTurma.insertTurma(turma, Tool.getIdPerfil(session));
			return "redirect:/turmas";
		}else
			return "redirect:/perfil";
		
	}
	
	@RequestMapping("/minhasturmas")
	public ModelAndView minhasTurmas(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("turmas");
		model.addObject("turmas", DaoTurma.getListProfessor(Tool.getIdPerfil(session)));
		return model;
	}
	
	@RequestMapping("/turma")
	public ModelAndView turma(@RequestParam("idTurma") Integer idTurma, HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("Turma/Main");
		model.addObject("turma", DaoTurma.findTurma(idTurma));
		session.setAttribute("turmaAtual", DaoTurma.findTurma(idTurma));
		return model;	
	}
	
	@RequestMapping("/turma/alunos")
	public ModelAndView alunos(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("Turma/Membros");
		model.addObject("perfis", DaoTurma.getListMembros(Tool.getIdTurma(session)));
		model.addObject("turma", (Turma) session.getAttribute("turmaAtual"));
		return model;
	}
	
	@RequestMapping("/turma/novoAluno")
	public ModelAndView novoAluno(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("Turma/NovoAluno");
		model.addObject("alunos", DaoTurma.getListMembros(Tool.getIdTurma(session)));
		model.addObject("perfis", DaoTurma.getListAlunos(Tool.getIdTurma(session), Tool.getIdPerfil(session)));
		return model;
	}
	
	@RequestMapping("/turma/adicionarAluno")
	public String salvarAluno(HttpSession session, @RequestParam("idAluno") Integer id) throws SQLException{
		DaoTurma.insertAluno(Tool.getIdTurma(session), id);
		return "redirect:/turma/novoAluno";
	}
	
	@RequestMapping("/turma/removerAluno")
	public String deletarAluno(HttpSession session, @RequestParam("idAluno") Integer id) throws SQLException{
		DaoTurma.removeAluno(Tool.getIdTurma(session), id);
		return "redirect:/turma/alunos";
	}
	
}
