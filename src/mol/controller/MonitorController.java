package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonitorController {
	
	@RequestMapping("homeMonitor")
	public String inicio() {
        return "monitor/index";
    }
}
