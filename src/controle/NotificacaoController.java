package controle;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoNotificacao;

@Controller
public class NotificacaoController {
	
	@RequestMapping("/aceitarSolicitacao")
	public String aceitar(@RequestParam("idNotificacao") Integer idNotificacao, HttpSession session){
		DaoNotificacao.aceitar(idNotificacao);
		return "redirect:/perfil";
	}
	
	@RequestMapping("/recusarSolicitacao")
	public String recusar(@RequestParam("idNotificacao") Integer idNotificacao, HttpSession session){
		DaoNotificacao.remove(idNotificacao);
		return "redirect:/perfil";
	}
	
	@RequestMapping("/turma/Notificacoes")
	public ModelAndView notificacao(HttpSession session){
		ModelAndView model = new ModelAndView("Turma/Notificacao");
		model.addObject("nots", DaoNotificacao.getList(Tool.getIdTurma(session)));
		return model;
	}
}
