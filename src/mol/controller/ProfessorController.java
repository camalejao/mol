package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorController {
	@RequestMapping("homeProfessor")
	public String inicio() {
        return "professor/index";
    }
}
