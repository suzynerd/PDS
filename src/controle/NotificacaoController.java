package controle;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DaoNotificacao;

@Controller
public class NotificacaoController {
	
	@RequestMapping("/aceitarSolicitacao")
	public String aceitar(@RequestParam("idNotificacao") Integer idNotificacao, HttpSession session){
		DaoNotificacao.aceitar(idNotificacao);
		return "redirect:/perfil";
	}
}
