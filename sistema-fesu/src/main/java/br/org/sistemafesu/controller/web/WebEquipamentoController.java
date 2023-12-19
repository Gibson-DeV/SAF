package br.org.sistemafesu.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.service.EquipamentoService;
import br.org.sistemafesu.service.LocacaoService;

@Controller
@RequestMapping("/equipamentos")
public class WebEquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private LocacaoService locacaoService;



    @GetMapping()
    public String listarEquipamentos(Model model){
        model.addAttribute("listaEquipamentos", equipamentoService.getAll());
        model.addAttribute("equipamento", new Equipamento());
        return "lista-equipamento";
    }

    @PostMapping()
    public String cadastrarEquipamento(Equipamento equipamento){
        equipamentoService.save(equipamento);
        return "redirect:/equipamentos";
    }

    @DeleteMapping("{id}")
    public String deletarEquipamento(@PathVariable("id") Long id){
           equipamentoService.deleteLocacao(id);
           equipamentoService.deleteById(id);
        return "redirect:/equipamentos";
    }

}
