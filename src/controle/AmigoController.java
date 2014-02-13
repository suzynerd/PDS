package controle;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoAmigo;
import dominio.Perfil;

@Controller
public class AmigoController {

	@RequestMapping(value="/pessoas/adicionarAmigo", method=RequestMethod.GET)
	public String adicionarAmigo(@RequestParam("idAmigo") Integer idAmigo, HttpSession session) throws SQLException{
		DaoAmigo.insert(Tool.getIdPerfil(session) , idAmigo);
		return "redirect:/amigos";
	}
	
	@RequestMapping("/amigos")
	public ModelAndView amigos(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("amigos");
		model.addObject("amigos", DaoAmigo.getList(Tool.getIdPerfil(session)));
		return model;
	}
	
	@RequestMapping(value="/amigos/removerAmigo", method=RequestMethod.GET)
	public String removeAmigo(@RequestParam("idRelacao") Integer idRelacao) throws SQLException{
		DaoAmigo.remove(idRelacao);
		return "redirect:/amigos";
	}
}
