package br.com.gcampioto.listavip.controller;

import br.com.gcampioto.listavip.model.Convidado;
import br.com.gcampioto.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Convidado convidado){
        convidadoRepository.save(convidado);
        return "redirect:/listaConvidados";
    }

    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public String deletar(@PathVariable("id") String id){
        Long idLong = Long.valueOf(id);
        convidadoRepository.delete(idLong);
        return "redirect:/listaConvidados";
    }
}
