package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	private List<MultipartFile> arquivos = new ArrayList<>();
	
	@RequestMapping(value="arquivos")
	public ModelAndView novo(){
		ModelAndView model = new ModelAndView("arquivos");
		model.addObject("arquivos", arquivos);
		
		return model;
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file){
		if(file != null){
			arquivos.add(file);
		}
		
		return "forward:/arquivos";
	}
	
	@RequestMapping(value="arquivo", method=RequestMethod.GET)
	public HttpEntity<byte[]> download(@RequestParam("nome") String fileName) 
			throws IOException{
		for(MultipartFile file : arquivos){
			if(file.getOriginalFilename().equals(fileName)){
				byte[] bytes = file.getBytes();
				HttpHeaders headers = new HttpHeaders();
				String[] tokens = file.getContentType().split("/");
				MediaType fileType = new MediaType(tokens[0], tokens[1]);
				headers.setContentType(fileType);
				headers.setContentLength(bytes.length);
				
				return new HttpEntity<byte[]>(bytes, headers);
			}
		}
		
		return null;
	}
}
