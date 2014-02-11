package controle;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tools.Tool;
import dao.DaoArquivo;
import dominio.Arquivo;

@Controller
public class ArquivoController {
	
	@RequestMapping(value="arquivos")
	public ModelAndView novo(HttpSession session) throws SQLException{
		ModelAndView model = new ModelAndView("arquivos");
		model.addObject("arquivos", DaoArquivo.listarArquivos(Tool.getId(session)));
		
		return model;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException, SQLException{
		if(file != null){
			Arquivo a = new Arquivo();
			a.setNome(file.getOriginalFilename());
			a.setArquivo(file.getBytes());
			a.setTipo(file.getContentType());
			DaoArquivo.upload(a, Tool.getId(session));
		}
		
		return "redirect:/arquivos";
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public HttpEntity<byte[]> download(@RequestParam("idArquivoDownload") Integer idArquivo) throws IOException, SQLException{
				Arquivo a = DaoArquivo.download(idArquivo);
				HttpHeaders headers = new HttpHeaders();
				
				String[] tokens = a.getTipo().split("/");
				headers.setContentType(new MediaType(tokens[0], tokens[1]));
				headers.set("Content-Disposition", "attachment; filename=" + a.getNome().replace(" ", "-"));
				headers.setContentLength(a.getArquivo().length);
				return new HttpEntity<byte[]>(a.getArquivo(), headers);
	}
}
