package controle;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoPerfil;
import dominio.Perfil;
import dominio.Usuario;

@Controller
public class UserController {
	
	@RequestMapping("/")
	public ModelAndView home(HttpSession session){
		ModelAndView model = new ModelAndView();
		if (session.getAttribute("perfilLogado") == null) {
			model.setViewName("index");
			model.addObject("usuario", new Usuario());
		}else
			model.setViewName("perfil");
		return model;
	}
	
	@RequestMapping("/perfil")
	public ModelAndView perfil(HttpSession session){
		ModelAndView model = new ModelAndView();
		if (session.getAttribute("perfilLogado") != null) {
			model.setViewName("perfil");
		}else
			model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping("/cadastrar")
	public String cadastro(Model model){
		model.addAttribute("perfil", new Perfil());
		return "cadastro";
	}
	
	@RequestMapping(value="/salvarPerfil", method=RequestMethod.POST)
	public String salvar(Perfil perfil, BindingResult result, Model model) throws SQLException{
		DaoPerfil.inserir(perfil);
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(Usuario user, BindingResult result, HttpSession session, Model model){
		Perfil p = DaoPerfil.logar(user);
		if(p != null)
			session.setAttribute("perfilLogado", p);
		return "forward:/perfil";
	}
	
	@RequestMapping("/perfil/sair")
	public String sair(HttpSession session){
		session.removeAttribute("perfilLogado");
		return "index";
	}
	
	@RequestMapping("/pessoas")
	public String Pessoas(Model model) throws SQLException{
		model.addAttribute("perfis", DaoPerfil.listarPerfis());
		return "pessoas";
	}
	
	
}
