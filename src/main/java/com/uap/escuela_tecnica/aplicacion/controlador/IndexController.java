package com.uap.escuela_tecnica.aplicacion.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/index", "/inicio", "/home"})
    public String redirigirApi() {
        return "redirect:/docs/api/swagger-ui.html";
    }
}