package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlunoController {
	
	@RequestMapping("homeAluno")
	public String inicio() {
        return "aluno/index";
    }
}
