
package com.pi_final.pi_7_e_9.controller;

import com.pi_final.pi_7_e_9.data.ClienteEntity;
import com.pi_final.pi_7_e_9.data.FuncionarioEntity;
import com.pi_final.pi_7_e_9.data.SolicitacaoEntity;
import com.pi_final.pi_7_e_9.service.ClienteService;
import com.pi_final.pi_7_e_9.service.FuncionarioService;
import com.pi_final.pi_7_e_9.service.SolicitacaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MvcController {
    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    ClienteService clienteService;
    
    @Autowired
    SolicitacaoService solicitacaoService;
    
    /******************************Funcionários****************************************/
    
    @GetMapping("/")
    public String paginaPrincipal(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }
    
    @GetMapping("/tela_funcionario")
    public String paginaFuncionario(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("funcionario", new FuncionarioEntity());
        model.addAttribute("css", tema);
        return "tela_funcionario";
    }

    @GetMapping("/listaFuncionario")
    public String listarFuncionarios(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("listarFuncionarios", funcionarioService.listarTodosFuncionarios());
        model.addAttribute("css", tema);
        return "lista_funcionarios";
    }
    
    
    @GetMapping("/criarFuncionarioForm") 
    public String criarFuncionarioForm(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) { 
        FuncionarioEntity func = new FuncionarioEntity(); 
        model.addAttribute("funcionario", func); 
        model.addAttribute("css", tema);
        return "tela_funcionario";
    }
    
    @PostMapping("/salvarFuncionario") 
    public String salvarFuncionario(@Valid @ModelAttribute("funcionario") FuncionarioEntity func, BindingResult result) { 
        
        if (result.hasErrors()) { 
            return "tela_funcionario"; 
        }
        if (func.getId()==null) {
            funcionarioService.criarFuncionario(func); 
        } else {
            funcionarioService.atualizarFuncionario(func.getId(), func);
        } 
        return "redirect:/listaFuncionario"; 
    } 

    @GetMapping("/atualizarFuncionario/{id}") 
    public String atualizarFuncionarioForm(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, 
            @PathVariable(value = "id") Integer id, Model model) { 
        FuncionarioEntity func = funcionarioService.getFuncionarioId(id); 
        model.addAttribute("funcionario", func); 
        model.addAttribute("css", tema);
        return "atualizar_tela_funcionario"; 
    }    
    
    @DeleteMapping("/deletarFuncionario/{id}")
     public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.ok().build();
     }
     
     /******************************Clientes****************************************/
     
   
    @GetMapping("/tela_cliente")
    public String paginaCliente(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        model.addAttribute("css", tema);
        return "tela_cliente";
    }

    @GetMapping("/listaCliente")
    public String listaCliente(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("listarClientes", clienteService.listarTodosClientes());
        model.addAttribute("css", tema);
        return "lista_clientes";
    }
    
    
    @GetMapping("/criarCliente") 
    public String criarCliente(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) { 
        ClienteEntity client = new ClienteEntity(); 
        model.addAttribute("cliente", client); 
        model.addAttribute("css", tema);
        return "tela_cliente";
    }
    
    @PostMapping("/salvarCliente") 
    public String salvarCliente(@Valid @ModelAttribute("cliente") ClienteEntity client, BindingResult result) { 
        
        if (result.hasErrors()) { 
            return "tela_cliente"; 
        }
        if (client.getId()==null) {
            clienteService.criarCliente(client); 
        } else {
            clienteService.atualizarCliente(client.getId(), client);
        } 
        return "redirect:/listaCliente"; 
    } 

    @GetMapping("/atualizarCliente/{id}") 
    public String atualizarCliente(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema,
            @PathVariable(value = "id") Integer id, Model model) { 
        ClienteEntity func = clienteService.getClienteId(id); 
        model.addAttribute("cliente", func); 
        model.addAttribute("css", tema);
        return "atualizar_tela_cliente"; 
    }    
    
    @DeleteMapping("/deletarCliente/{id}")
     public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.ok().build();
     }
     
     /******************************Solicitacoes****************************************/

     
     @GetMapping("/tela_solicitacao")
    public String paginaSolicitacao(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("solicitacao", new SolicitacaoEntity());
        model.addAttribute("css", tema);
        return "tela_solicitacao";
    }

    @GetMapping("/listaSolicitacao")
    public String listaSolicitacao(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) {
        model.addAttribute("listarSolicitacoes", solicitacaoService.listarTodasSolicitacoes());
        model.addAttribute("css", tema);
        return "lista_solicitacoes";
    }
    
    
    @GetMapping("/criarSolicitacao") 
    public String criarSolicitacao(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, Model model) { 
        SolicitacaoEntity sol = new SolicitacaoEntity(); 
        model.addAttribute("solicitacao", sol); 
        model.addAttribute("css", tema);
        return "tela_solicitacao";
    }
    
    @PostMapping("/salvarSolicitacao") 
    public String salvarSolicitacao(@Valid @ModelAttribute("solicitacao") SolicitacaoEntity sol, BindingResult result) { 
        
        if (result.hasErrors()) { 
            return "tela_solicitacao"; 
        }
        if (sol.getId()==null) {
            solicitacaoService.criarSolicitacao(sol); 
        } else {
            solicitacaoService.atualizarSolicitacao(sol.getId(), sol);
        } 
        return "redirect:/listaSolicitacao"; 
    } 

    @GetMapping("/atualizarSolicitacao/{id}") 
    public String atualizarSolicitacao(@CookieValue(name="pref-estilo", defaultValue="Claro")String tema, @PathVariable(value = "id") Integer id, Model model) { 
        SolicitacaoEntity sol = solicitacaoService.getSolicitacaoId(id); 
        model.addAttribute("solicitacao", sol); 
        model.addAttribute("css", tema);
        return "atualizar_tela_solicitacao"; 
    }    
    
    @DeleteMapping("/deletarSolicitacao/{id}")
     public ResponseEntity<Void> deletarSolicitacao(@PathVariable Integer id) {
        solicitacaoService.deletarSolicitacao(id);
        return ResponseEntity.ok().build();
     }
     
      /**************************************Alternar tema*************************************/
    
    @PostMapping("/mudarTema")
    public String mudarTema(@CookieValue(name = "pref-estilo", defaultValue = "Escuro") String temaAtual, HttpServletResponse response, HttpServletRequest request) {
        String novoTema = temaAtual.equals("Escuro") ? "Claro" : "Escuro";
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", novoTema);
        cookiePrefEstilo.setDomain("localhost");
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(86400);
        response.addCookie(cookiePrefEstilo);
        String referer = request.getHeader("Referer");
      
        return "redirect:" + (referer != null ? referer : "/index");
    }
}
