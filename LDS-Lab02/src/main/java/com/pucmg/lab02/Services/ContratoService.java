package com.pucmg.lab02.Services;

import com.pucmg.lab02.Models.Automovel;
import com.pucmg.lab02.Models.Banco;
import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.Contrato;
import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Models.Contrato.StatusContrato;
import com.pucmg.lab02.Models.Contrato.TipoContrato;
import com.pucmg.lab02.Models.Contrato.TipoRegistro;
import com.pucmg.lab02.Repositories.ContratoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;


    // o contrato é criado com base na interaçao do cliente ao querer alugar um veículo
    @Transactional
    public Contrato criarContrato(Optional<Automovel> automovel, Cliente cliente, Empresa empresa, int quantidadeDias, TipoRegistro tipoRegistro, TipoContrato tipoContrato, Banco banco) {
        Contrato contrato = new Contrato(automovel, cliente, empresa, quantidadeDias, tipoRegistro, tipoContrato);
        contrato.setStatusContrato(StatusContrato.PENDENTE); // contrato inicia com status PENDENTE, aguardando resposta da empresa
        contrato.setValor(quantidadeDias * automovel.orElseThrow(() -> new RuntimeException("Automóvel não encontrado")).getPrecoDiaria());
       
        //somente serão setados se for um emprestimo, caso contrario esses atributos serão nulos
        if (contrato.isEmprestimo()){
            contrato.setBanco(banco);
            contrato.setJuros(0.1*contrato.getValor());
            contrato.setValor(contrato.getValor() + contrato.getJuros());
        }
        
        return contratoRepository.save(contrato);
    }

    public void aprovarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        contrato.setAprovacaoEmpresa(true);
        contratoRepository.save(contrato);
    }

    public void rejeitarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        contrato.setAprovacaoEmpresa(false);
        contratoRepository.save(contrato);
    }

    // a empresa responde se aprova ou não o contrato e o status é atualizado
    public void definirStatusContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

        if (contrato.getAprovacaoEmpresa()) {
            contrato.setStatusContrato(StatusContrato.ATIVO);
            ajustarDataContrato(contrato);
           
        } else {
            contrato.setStatusContrato(StatusContrato.CANCELADO);
        }

        contratoRepository.save(contrato);
    }

    public void ajustarDataContrato(Contrato contrato) {
        LocalDateTime agora = LocalDateTime.now();  // Horário atual
        LocalTime limite = LocalTime.of(16, 0);     // Limite das 16h

        if (agora.toLocalTime().isAfter(limite)) {
            // Se for após as 16h, define para o próximo dia às 8h
            LocalDateTime amanhaAsOito = agora.plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);
            contrato.setDataInicio(amanhaAsOito);
            contrato.setDataTermino(amanhaAsOito.plusDays(contrato.getQuantidadeDias()));
        } else {
            // Se for antes das 16h, define como o horário atual
            contrato.setDataInicio(agora.truncatedTo(ChronoUnit.SECONDS));  // Remove nanosegundos para precisão
            contrato.setDataTermino(agora.plusDays(contrato.getQuantidadeDias()).truncatedTo(ChronoUnit.SECONDS));
        }
    }

    @Scheduled(cron = "0 1 8,16 * * ?")  // Executa às 08:01 e 16:01 todos os dias
    @Transactional
    public void verificarEFinalizarContratos() {
        // Buscar todos os contratos com status ATIVO
        List<Contrato> contratosAtivos = contratoRepository.findAllByStatusContrato(StatusContrato.ATIVO);

        // Iterar sobre todos os contratos ativos
        for (Contrato contrato : contratosAtivos) {
            // Verificar se a data de término é anterior à data atual
            if (contrato.getDataTermino().isBefore(LocalDateTime.now())) {
                // Se for, mudar o status do contrato para FINALIZADO
                contrato.setStatusContrato(StatusContrato.FINALIZADO);
                contratoRepository.save(contrato); // Atualizar no banco de dados
            }
        }
    }


    public Contrato visualizarContrato(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
    }

    public void salvarContrato(Contrato contrato) {
        contratoRepository.save(contrato);
    }


    @Transactional
    public List<Contrato> findContratosByEmpresaAndStatus(Empresa empresa, StatusContrato status) {
        return contratoRepository.findByEmpresaAndStatusContrato(empresa, status);
    }

    @Transactional
    public List<Contrato> findContratosByClienteAndStatus(Cliente cliente, String status) {
        StatusContrato statusContrato = StatusContrato.valueOf(status.toUpperCase());
        return contratoRepository.findByClienteAndStatusContrato(cliente, statusContrato);
    }

   
}
