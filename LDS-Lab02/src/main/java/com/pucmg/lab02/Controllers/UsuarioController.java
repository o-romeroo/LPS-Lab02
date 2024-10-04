package com.pucmg.lab02.Controllers;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab02.Models.Banco;
import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Models.EntidadeEmpregadora;
import com.pucmg.lab02.Models.Usuario;
import com.pucmg.lab02.Services.BancoService;
import com.pucmg.lab02.Services.ClienteService;
import com.pucmg.lab02.Services.EmpresaService;
import com.pucmg.lab02.Services.EntidadeEmpregadoraService;
import com.pucmg.lab02.Services.UsuarioService;
import com.pucmg.lab02.dto.CreateBancoDTO;
import com.pucmg.lab02.dto.CreateClienteDTO;
import com.pucmg.lab02.dto.CreateEmpresaDTO;
import com.pucmg.lab02.dto.LoginData;
import com.pucmg.lab02.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    
    @Autowired
    private UsuarioService usuarioService; // Serviço para gerenciar usuários

    @Autowired
    private EmpresaService empresaService; // Serviço para gerenciar empresas

    @Autowired
    private ClienteService clienteService; // Serviço para gerenciar clientes

    @Autowired
    private EntidadeEmpregadoraService entidadeEmpregadoraService; // Serviço para gerenciar entidadeEmpregadora

    @Autowired
    private BancoService bancoService; // Serviço para gerenciar bancos

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginData loginData, BindingResult bindingResult) {
    // Valida os dados de entrada
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    // Autenticação do usuário
    Optional<Usuario> usuarioOpt = usuarioService.findByUsernameAndPassword(loginData.getLogin(), loginData.getSenha());

    // Verifica se o usuário foi encontrado
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        
        // Verifica se o usuário é uma instância de Cliente ou Empresa
        if (usuario instanceof Cliente) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario.getId(), "CLIENTE"));
        } else if (usuario instanceof Empresa) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario.getId(), "EMPRESA"));
        } else {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario.getId(), "USUARIO"));
        }
    } else {
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}



    @PostMapping("/createCliente")
    public ResponseEntity<?> createUserCliente(@Valid @RequestBody CreateClienteDTO newUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    try {
        // 1. Cria um novo Cliente com os dados de Usuario e Cliente
        Cliente cliente = new Cliente(newUser.getCpf(), newUser.getRg(), newUser.getEntidadesEmpregadoras());
        cliente.setNome(newUser.getUsuario().getNome());
        cliente.setEndereco(newUser.getUsuario().getEndereco());
        cliente.setLogin(newUser.getUsuario().getLogin());
        cliente.setSenha(newUser.getUsuario().getSenha());

        // 2. Salva o Cliente no banco de dados para gerar o ID
        Cliente createdUser = clienteService.create(cliente);

        // 3. Vincula o ID do cliente às Entidades Empregadoras
        for (EntidadeEmpregadora entidade : newUser.getEntidadesEmpregadoras()) {
            entidade.setCliente(createdUser); // Associa a entidade ao cliente recém-criado
            // Salva a entidade empregadora (isso pode ser feito no serviço ou em um repositório específico)
            entidadeEmpregadoraService.save(entidade);
        }

        // 4. Retorna o ID do cliente recém-criado
        return ResponseEntity.ok(new UsuarioResponseDTO(createdUser.getId()));
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
    }
}

@PostMapping("/createEmpresa")
public ResponseEntity<?> createUserEmpresa(@Valid @RequestBody CreateEmpresaDTO newUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        // Retorna os erros de validação
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    try {
        // Extrai os dados do objeto Usuario no DTO
        Usuario usuario = newUser.getUsuario();

        // Cria uma nova Empresa com os dados de Usuario e o CNPJ
        Empresa empresa = new Empresa(newUser.getCnpj());
        empresa.setNome(usuario.getNome());
        empresa.setEndereco(usuario.getEndereco());
        empresa.setLogin(usuario.getLogin());
        empresa.setSenha(usuario.getSenha());

        // Salva a empresa através do empresaService
        Empresa createdUser = empresaService.save(empresa);
        
        return ResponseEntity.ok(new UsuarioResponseDTO(createdUser.getId()));
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
    }
}

    @PostMapping("/createBanco")
    public ResponseEntity<?> createUserBanco(@Valid @RequestBody CreateBancoDTO newUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        // Retorna os erros de validação
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    try {
        // Extrai os dados do objeto Usuario no DTO
        Usuario usuario = newUser.getUsuario();

        // Cria uma nova instância de Banco com os dados de Usuario e o CNPJ
        Banco banco = new Banco(newUser.getCnpj());
        banco.setNome(usuario.getNome());
        banco.setEndereco(usuario.getEndereco());
        banco.setLogin(usuario.getLogin());
        banco.setSenha(usuario.getSenha());

        // Salva o banco através do bancoService
        Banco createdUser = bancoService.save(banco);
        
        return ResponseEntity.ok(new UsuarioResponseDTO(createdUser.getId()));
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
    }
}

    @GetMapping("/allEmpresa")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/allBanco")
    public ResponseEntity<List<Banco>> getAllBancos() {
        List<Banco> bancos = bancoService.findAll();
        return ResponseEntity.ok(bancos);
    }

}
