package controle;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoAmigo;
import dao.DaoPerfil;
import dominio.Perfil;

@Controller
public class AmigoController {

	@RequestMapping(value="/pessoas/adicionarAmigo", method=RequestMethod.GET)
	public String addAmigo(@RequestParam("idAmigo") Integer idAmigo, HttpSession session) throws SQLException{
		Perfil p = (Perfil) session.getAttribute("perfilLogado");
		DaoAmigo.adicionarAmigo(p.getIdPerfil() , idAmigo);
		return "redirect:/amigos";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView Pessoas() throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("pessoas");
		model.addObject("perfis", DaoPerfil.listarPerfis());
		return model;
	}
	
	@RequestMapping("/amigos")
	public ModelAndView amigos(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("amigos");
		model.addObject("perfis", DaoAmigo.listarAmigo( ((Perfil) session.getAttribute("perfilLogado")).getIdPerfil() ));
		return model;
	}
}
