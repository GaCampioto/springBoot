package br.com.gcampioto.listavip.controller;

import br.com.gcampioto.listavip.model.Convidado;
import br.com.gcampioto.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
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
        enviarEmail(convidado.getNome(), convidado.getEmail());
        return "redirect:/listaConvidados";
    }

    @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
    public String deletar(@PathVariable("id") String id){
        Long idLong = Long.valueOf(id);
        convidadoRepository.delete(idLong);
        return "redirect:/listaConvidados";
    }

    private void enviarEmail(String nome, String email){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8000/email/send")
                .queryParam("name", nome)
                .queryParam("email", email);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);
    }
}
