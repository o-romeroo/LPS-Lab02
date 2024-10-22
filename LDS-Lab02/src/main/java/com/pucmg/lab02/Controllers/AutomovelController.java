package com.pucmg.lab02.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab02.Models.Automovel;
import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Models.Usuario;
import com.pucmg.lab02.Services.AutomovelService;
import com.pucmg.lab02.Services.EmpresaService;
import com.pucmg.lab02.Services.UsuarioService;
import com.pucmg.lab02.dto.CreateAutomovelDTO;

//TODO:
@RestController
@RequestMapping("/api/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private UsuarioService usuarioService; // Serviço para gerenciar usuários
 // Serviço para gerenciar empresas

    // Endpoint para Empresa adicionar um novo veículo
    @PostMapping("/add")
    public ResponseEntity<Automovel> addAutomovel(@RequestBody CreateAutomovelDTO createAutomovelDTO) {
    // Obter empresaId do DTO
    Long empresaId = createAutomovelDTO.getEmpresaId();

    // Valida se a empresa é válida
    usuarioService.validaUsuarioEmpresa(empresaId);

    // Busca a empresa no sistema
    Usuario empresa = usuarioService.findById(empresaId);
    if (empresa == null) {
        return ResponseEntity.badRequest().body(null); // Empresa não encontrada
    }

    // Cria o automóvel e define o dono como a empresa
    Automovel automovel = createAutomovelDTO.getAutomovel();
    automovel.setDono(empresa);

    // Salva o automóvel
    automovelService.salvarAutomovel(automovel);

    return ResponseEntity.ok(automovel);
}


    // Endpoint para Empresa obter todos os seus veículos
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Automovel>> getAutomoveisByEmpresa(@PathVariable Long empresaId) {
        usuarioService.validaUsuarioEmpresa(empresaId);
        Usuario empresa = usuarioService.findById(empresaId);
        if (empresa == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Automovel> automoveis = automovelService.listarAutomoveisByDono(empresa);
        return ResponseEntity.ok(automoveis);
    }

    // Endpoint para Cliente obter todos os veículos disponíveis de todas as empresas
    @GetMapping("/available")
    public ResponseEntity<List<Automovel>> getAvailableAutomoveis() {
    // Busca todos os automóveis disponíveis
    List<Automovel> automoveisDisponiveis = automovelService.listarAutomoveisDisponiveis();
    
    // Retorna a lista de automóveis disponíveis
    return ResponseEntity.ok(automoveisDisponiveis);
}


    @DeleteMapping("/delete/{automovelId}")
    public ResponseEntity<?> deleteAutomovel(@PathVariable Long automovelId, @RequestParam Long empresaId) {
    // Valida se o usuário é uma empresa
    usuarioService.validaUsuarioEmpresa(empresaId);

    // Consulta o automóvel pelo ID
    Optional<Automovel> automovelOpt = automovelService.consultarAutomovelById(automovelId);
    
    // Verifica se o automóvel existe
    if (!automovelOpt.isPresent()) {
        return ResponseEntity.badRequest().body("Automóvel não encontrado");
    }

    // Verifica se o automóvel pertence à empresa que está tentando deletar
    Automovel automovel = automovelOpt.get();
    if (!automovel.getDono().getId().equals(empresaId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Este automóvel não pertence à empresa informada");
    }

    // Deleta o automóvel
    automovelService.deletarAutomovel(automovel.getId());

    // Retorna uma resposta de sucesso
    return ResponseEntity.ok("Automóvel deletado com sucesso");
}


    @PutMapping("/update")
    public ResponseEntity<?> updateAutomovel(@RequestBody Automovel automovel, @RequestParam Long empresaId) {
    // Valida se a empresa é válida
    usuarioService.validaUsuarioEmpresa(empresaId);

    // Verifica se o automóvel existe no banco
    Optional<Automovel> automovelExistente = automovelService.consultarAutomovelById(automovel.getId());
    if (!automovelExistente.isPresent()) {
        return ResponseEntity.badRequest().body("Automóvel não encontrado");
    }

    // Verifica se o automóvel pertence à empresa que está tentando atualizar
    if (!automovelExistente.get().getDono().getId().equals(empresaId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Este automóvel não pertence à empresa informada");
    }

    // Atualiza o automóvel
    automovelService.atualizarAutomovel(automovel);

    return ResponseEntity.ok(automovel);
}

}
