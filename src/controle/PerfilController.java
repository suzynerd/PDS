package controle;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoPerfil;
import dao.DaoTipo;
import dominio.Perfil;
import dominio.Usuario;

@Controller
public class PerfilController {
	
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
		if (session.getAttribute("perfilLogado") == null) {
			model.setViewName("index");
		}else
			model.setViewName("perfil");
		
		return model;
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("cadastro");
		model.addObject("perfil", new Perfil());
		model.addObject("tipos", DaoTipo.listarTipos());
		return model;
	}
	
	@RequestMapping(value="/salvarPerfil", method=RequestMethod.POST)
	public String criarPerfil(@RequestParam("tipoPerfil") Integer idTipo, Perfil perfil, BindingResult result) throws SQLException{
		perfil.setIdTipoPerfil(idTipo);
		DaoPerfil.insert(perfil);
		return "redirect:/";
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(Usuario user, HttpSession session){
		ModelAndView model = new ModelAndView();
		Perfil p = DaoPerfil.autenticar(user);
		if(user.getEmail() != null && user.getSenha() != null && p != null){
			session.setAttribute("perfilLogado", p);
			model.setViewName("perfil");
		}else{
			if(session.getAttribute("perfilLogado") == null)
				model.setViewName("index");
			else
				model.setViewName("perfil");
		}
		return model;
	}
	
	@RequestMapping("/perfil/sair")
	public String sair(HttpSession session){
		session.removeAttribute("perfilLogado");
		return "redirect:/";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView Pessoas(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("pessoas");
		System.out.println(Tool.getIdPerfil(session));
		model.addObject("perfis", DaoPerfil.getList(Tool.getIdPerfil(session)));
		return model;
	}
	
	
}
