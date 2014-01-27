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
	public ModelAndView cadastro(){
		ModelAndView model = new ModelAndView();
		model.setViewName("cadastro");
		model.addObject("perfil", new Perfil());
		return model;
	}
	
	@RequestMapping(value="/salvarPerfil", method=RequestMethod.POST)
	public ModelAndView salvar(Perfil perfil, BindingResult result) throws SQLException{
		DaoPerfil.inserir(perfil);
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(Usuario user, HttpSession session){
		ModelAndView model = new ModelAndView();
		Perfil p = DaoPerfil.logar(user);
		if(p != null){
			session.setAttribute("perfilLogado", p);
			model.setViewName("perfil");
		}else
			model.setViewName("index");
		return model;
	}
	
	@RequestMapping("/perfil/sair")
	public ModelAndView sair(HttpSession session){
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		session.removeAttribute("perfilLogado");
		return model;
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView Pessoas() throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("pessoas");
		model.addObject("perfis", DaoPerfil.listarPerfis());
		return model;
	}
	
	
}
