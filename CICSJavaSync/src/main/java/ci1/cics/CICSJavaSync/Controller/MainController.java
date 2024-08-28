package ci1.cics.CICSJavaSync.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
	
	@GetMapping("/")
	public ModelAndView home(Model model) {
		model.addAttribute("pagina", "index");
		return new ModelAndView("index");
	}

	@GetMapping("/calendar")
	public ModelAndView calendar(Model model){
		model.addAttribute("pagina", "Calendar");
		return new ModelAndView("calendar");
	}

	@GetMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}

	@GetMapping("/bundle")
	public ModelAndView bundle_dash(Model model) {
		model.addAttribute("pagina", "bundle");
		return new ModelAndView("bundle");
	}

	@GetMapping("/ice_dash")
	public ModelAndView ice_dash(Model model) {
		model.addAttribute("pagina", "ice_dash");
		return new ModelAndView("ice_dashboard");
	}

	@GetMapping("/ice_form")
	public ModelAndView ice_forms() {
		return new ModelAndView("ice_form");
	}
	
	@GetMapping("/charts-chartjs")
	public ModelAndView chart(){
		return new ModelAndView("charts-chartjs");
	}

	@GetMapping("/data_tables")
	public ModelAndView data_tables() {
		return new ModelAndView("data_tables");
	}

}
