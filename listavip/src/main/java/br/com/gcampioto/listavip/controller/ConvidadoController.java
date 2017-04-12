package br.com.gcampioto.listavip.controller;

import br.com.gcampioto.listavip.model.Convidado;
import br.com.gcampioto.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by gcampioto on 11/04/17.
 */
@Controller
public class ConvidadoController {

    @Autowired
    ConvidadoRepository convidadoRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/listaConvidados")
    public String listaConvidados(Model model){
        List<Convidado> convidados = convidadoRepository.findAll();

        model.addAttribute("convidados", convidados);
        return "listaConvidados";
    }
}
