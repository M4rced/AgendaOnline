package co.com.agendaonline.tareas.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.com.agendaonline.tareas.appdata.AppData;
import co.com.agendaonline.tareas.models.entities.Evento;
import co.com.agendaonline.tareas.models.services.EventoService;
import co.com.agendaonline.tareas.models.services.UploadService;
import co.com.agendaonline.tareas.util.paginator.PageRender;

@Controller

@RequestMapping("/evento")
public class EventoController {
	
	private final AppData appData;
	private final EventoService eventoService;
	private UploadService uploadService;
	
	private static final String OPGEN = "EVENTO";
	
	
	public EventoController(UploadService uploadService,EventoService eventoService,AppData applicationData) {
		this.appData = applicationData;
		this.eventoService = eventoService;
		this.uploadService = uploadService;
	}
	
	@GetMapping({ "", "/", "/list", "/list/{page}" })
	public String list(@PathVariable(name = "page", required = false) Integer page, Model model) {
	
		if (page == null)
			page = 0;
		
		fillApplicationData(model,"LIST");
		
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Evento> pageEvento = eventoService.findAll(pageRequest);
		PageRender<Evento> paginator = new PageRender<>("/evento/list", pageEvento, 5);
		

		model.addAttribute("numtarea", eventoService.count());
		model.addAttribute("listtarea", pageEvento);
		model.addAttribute("paginator",paginator);
		
		model.addAttribute("actualpage", page);
		
		return "evento/list";
	}
	
	@GetMapping({ "/formcr", "/formcr/{page}" })
	public String form(@PathVariable(name = "page", required = false) Integer page, Model model) {
		Evento evento = new Evento();		
		model.addAttribute("evento",evento);
		
		if (page == null)
			page = 0;
		model.addAttribute("actualpage", page);
		
		fillApplicationData(model,"CREATE");
		
		return "evento/form";
	}
	
	@GetMapping({ "/formup/{id}", "/formup/{id}/{page}" })
	public String form(@PathVariable(name = "id") Long id, @PathVariable(name = "page", required = false) Integer page, Model model, RedirectAttributes flash) {
		if (page == null)
			page = 0;
		Evento evento = eventoService.findOne(id);
		if(evento==null) {
			flash.addFlashAttribute("error","Data not found");
			return "redirect:/evento/list/" + page;
		}
		
		model.addAttribute("evento", evento);
		
		model.addAttribute("actualpage", page);
		
		fillApplicationData(model,"UPDATE");
		
		return "evento/form";
	}
	/*
	@PostMapping("/form/{page}")
	//@Secured("ROLE_ADMIN")
	public String form(@Valid Evento evento,BindingResult result, Model model,
					   @RequestAttribute("file") MultipartFile foto1_formname,
					   @RequestParam("foto1ImageText") String foto1ImageText,
					  @RequestParam("foto1ImageTextOld") String foto1ImageTextOld,
					  @RequestAttribute("file") MultipartFile foto2_formname,
					  @RequestParam("foto2ImageText") String foto2ImageText,
					  @RequestParam("foto2ImageTextOld") String foto2ImageTextOld,

					   @PathVariable(name = "page") int page,
					   RedirectAttributes flash,
					   SessionStatus status) {
		
		boolean creating;
		
		if(evento.getId()==null) {
			fillApplicationData(model,"CREATE");
			creating = true;
		} else {
			fillApplicationData(model,"UPDATE");
			creating = false;
		}
		
		String msg = (evento.getId()==null) ? "Creation successful" : "Update successful";
		
		if(result.hasErrors()) {
			model.addAttribute("actualpage", page);
			return "evento/form";
		}
		
		if(!foto1_formname.isEmpty())
			AddUpdateImageFoto1(foto1_formname,evento);
		else {
			if(foto1ImageText.isEmpty() && !(foto1ImageTextOld.isEmpty())) {
				uploadService.delete(foto1ImageTextOld);
				evento.setFoto1(null);
			}
		}if(!foto2_formname.isEmpty())
			AddUpdateImageFoto2(foto2_formname,evento);
		else {
			if(foto2ImageText.isEmpty() && !(foto2ImageTextOld.isEmpty())) {
				uploadService.delete(foto2ImageTextOld);
				evento.setFoto2(null);
			}
		}
		eventoService.save(evento);
		status.setComplete();
		flash.addFlashAttribute("success",msg);
		
		if (creating)
			page = lastPage();
		
		return "redirect:/evento/list/" + page;
	}*/
	
	/*
	private void AddUpdateImageFoto1(MultipartFile image, Evento evento) {
					
			if(evento.getId()!=null &&
			   evento.getId()>0 && 
			   evento.getFoto1()!=null &&
			   evento.getFoto1().length() > 0) {
			
				uploadService.delete(evento.getFoto1());
			}
			
			String uniqueName = null;
			try {
				uniqueName = uploadService.copy(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			evento.setFoto1(uniqueName);
		
	}
	private void AddUpdateImageFoto2(MultipartFile image, Evento evento) {
					
			if(evento.getId()!=null &&
				evento.getId()>0 && 
				evento.getFoto2()!=null &&
				evento.getFoto2().length() > 0) {
			
				uploadService.delete(evento.getFoto2());
			}
			
			String uniqueName = null;
			try {
				uniqueName = uploadService.copy(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			evento.setFoto2(uniqueName);
		
	}*/

	

	@GetMapping({ "/delete/{id}", "/delete/{id}/{page}" })
	public String delete(@PathVariable(name = "id") Long id,
			@PathVariable(name = "page", required = false) Integer page, RedirectAttributes flash) {
		
		if (page == null)
			page = 0;
		
		if(id>0) { 			
			Evento evento = eventoService.findOne(id);
			
			if(evento != null) {
	
				eventoService.remove(id);
			} else {
				flash.addFlashAttribute("error","Data not found");
				return "redirect:/evento/list/" + page;
			}
			
			/*if(evento.getFoto1()!=null)
				uploadService.delete(evento.getFoto1());
			if(evento.getFoto2()!=null)
				uploadService.delete(evento.getFoto2());*/

						
			flash.addFlashAttribute("success","Deletion successful");
		}
		
		return "redirect:/evento/list/" + page;
	}
	
	@GetMapping({ "/view/{id}", "/view/{id}/{page}" })
	public String view(@PathVariable(name = "id") Long id,
			@PathVariable(name = "page", required = false) Integer page, Model model, RedirectAttributes flash) {

		if (page == null)
			page = 0;
		
		if (id > 0) {
			Evento evento = eventoService.findOne(id);

			if (evento == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/evento/list/" + page;
			}

			model.addAttribute("evento", evento);
			model.addAttribute("actualpage", page);
			fillApplicationData(model, "VIEW");
			return "evento/view";
			
		}

		return "redirect:/evento/list/" + page;
	}
	
	
	@GetMapping("/viewimg/{id}/{imageField}")
	public String viewimg(@PathVariable Long id, @PathVariable String imageField, Model model, RedirectAttributes flash) {

		if (id > 0) {
			Evento evento = eventoService.findOne(id);

			if (evento == null) {
				flash.addFlashAttribute("error", "Data not found");
				return "redirect:/evento/list";
			}

			model.addAttribute("evento", evento);
			fillApplicationData(model, "VIEWIMG");
			model.addAttribute("backOption",true);
			model.addAttribute("imageField",imageField);
			
			return "evento/viewimg";
			
		}

		return "redirect:/evento/list";
	}
	
	
	
	
	private int lastPage() {
		Long nReg = eventoService.count();
		int nPag = (int) (nReg / 10);
		if (nReg % 10 == 0)
			nPag--;
		return nPag;
	}
	
	private void fillApplicationData(Model model, String screen) {
		model.addAttribute("applicationData",appData);
		model.addAttribute("optionCode",OPGEN);
		model.addAttribute("screen",screen);
	}

}

/*** creado por M4rced and G3ors ***/
