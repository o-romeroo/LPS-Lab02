package com.pucmg.lab02.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab02.Models.Automovel;
import com.pucmg.lab02.Models.Banco;
import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.Contrato;
import com.pucmg.lab02.Models.Contrato.StatusContrato;
import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Services.AutomovelService;
import com.pucmg.lab02.Services.BancoService;
import com.pucmg.lab02.Services.ClienteService;
import com.pucmg.lab02.Services.ContratoService;
import com.pucmg.lab02.Services.EmpresaService;
import com.pucmg.lab02.Services.UsuarioService;
import com.pucmg.lab02.dto.ContratoDataDTO;
import com.pucmg.lab02.dto.ContratoResponseDTO;

//TODO:
@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private BancoService bancoService;

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para Cliente criar um novo contrato
    @PostMapping("/create")
    public ResponseEntity<?> createContrato(@RequestBody ContratoDataDTO contratoData) {
    try {
        // Obtenha o cliente e automóvel
        Long clienteId = contratoData.getClienteId();
        Long automovelId = contratoData.getAutomovelId();

        // Valida o cliente
        usuarioService.validaUsuarioCliente(clienteId);

        // Busca Cliente e Automóvel
        Cliente cliente = clienteService.findById(clienteId);
        Automovel automovel = automovelService.consultarAutomovelById(automovelId)
                                    .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

        Empresa empresa = (Empresa) automovel.getDono();

        // Verifica se há um banco associado ao contrato
        Banco banco = null;
        if (contratoData.getBancoId() != null) {
            banco = bancoService.findById(contratoData.getBancoId());
            if (banco == null) {
                return ResponseEntity.badRequest().body("Banco não encontrado");
            }
        }

        // Cria o contrato
        Contrato contrato = contratoService.criarContrato(
                Optional.of(automovel),
                cliente,
                empresa,
                contratoData.getQuantidadeDias(),
                contratoData.getTipoRegistro(),
                contratoData.getTipoContrato(),
                banco
        );

        return ResponseEntity.ok(contrato);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Erro ao processar a requisição: " + e.getMessage());
    }
}
    

    @GetMapping("/empresa/{empresaId}/status/{status}")
    public ResponseEntity<List<ContratoResponseDTO>> getContratosByStatus(@PathVariable Long empresaId, @PathVariable StatusContrato status) {
    // Valida se o usuário é uma empresa
    usuarioService.validaUsuarioEmpresa(empresaId);
    
    // Busca a empresa
    Empresa empresa = empresaService.findById(empresaId);
    if (empresa == null) {
        return ResponseEntity.badRequest().build();
    }

    // Busca os contratos da empresa pelo status
    List<Contrato> contratos = contratoService.findContratosByEmpresaAndStatus(empresa, status);

    // Converte a lista de contratos para a lista de DTOs
    List<ContratoResponseDTO> contratoResponseDTOs = contratos.stream()
        .map(ContratoResponseDTO::new) // Converte cada contrato para ContratoResponseDTO
        .collect(Collectors.toList());

    // Retorna a lista de DTOs
    return ResponseEntity.ok(contratoResponseDTOs);
}


    // Endpoint para Empresa aprovar um contrato
    @PostMapping("/{contratoId}/approve")
    public ResponseEntity<Void> approveContrato(@PathVariable Long contratoId, @RequestParam Long empresaId) {
    usuarioService.validaUsuarioEmpresa(empresaId); // Valida o Empresa
    contratoService.aprovarContrato(contratoId); // Aprova o contrato
    contratoService.definirStatusContrato(contratoId); // Define o status do contrato
    return ResponseEntity.ok().build();
}


    // Endpoint para Empresa rejeitar um contrato
    @PostMapping("/{contratoId}/reject")
    public ResponseEntity<Void> rejectContrato(@PathVariable Long contratoId, @RequestParam Long empresaId) {
        usuarioService.validaUsuarioEmpresa(empresaId);
        contratoService.rejeitarContrato(contratoId);
        contratoService.definirStatusContrato(contratoId);
        return ResponseEntity.ok().build();
    }

    // Endpoint para Cliente obter seus contratos
    @GetMapping("/cliente/{clienteId}/status/{status}")
    public ResponseEntity<List<ContratoResponseDTO>> getContratosByClienteAndStatus(@PathVariable Long clienteId, @PathVariable String status) {
    // Valida se o usuário é um cliente
    usuarioService.validaUsuarioCliente(clienteId);
    
    // Busca o cliente
    Cliente cliente = clienteService.findById(clienteId);
    if (cliente == null) {
        return ResponseEntity.badRequest().build();
    }

    // Busca os contratos do cliente pelo status
    List<Contrato> contratos = contratoService.findContratosByClienteAndStatus(cliente, status);

    // Converte a lista de contratos para a lista de DTOs
    List<ContratoResponseDTO> contratoResponseDTOs = contratos.stream()
        .map(ContratoResponseDTO::new) // Converte cada contrato para ContratoResponseDTO
        .collect(Collectors.toList());

    // Retorna a lista de DTOs
    return ResponseEntity.ok(contratoResponseDTOs);
}

}
