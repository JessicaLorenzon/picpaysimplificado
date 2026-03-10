package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transferencia.Transferencia;
import com.picpaysimplificado.domain.transferencia.dto.NotificacaoDTO;
import com.picpaysimplificado.domain.usuario.Usuario;
import com.picpaysimplificado.exceptions.NotificacaoForaDoArException;
import com.picpaysimplificado.exceptions.SaldoInsuficienteException;
import com.picpaysimplificado.exceptions.TransferenciaBloqueadaException;
import com.picpaysimplificado.exceptions.TransferenciaImpedidaException;
import com.picpaysimplificado.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Transferencia transferir(Transferencia transferencia) {
        Usuario remetente = usuarioService.buscaUsuario(transferencia.getIdRemetente());
        Usuario destinatario = usuarioService.buscaUsuario(transferencia.getIdDestinatario());

        if (!(usuarioService.podeTransferir(remetente))) throw new TransferenciaImpedidaException();

        if (!(usuarioService.temSaldoSuficiente(remetente, transferencia.getQuantia())))
            throw new SaldoInsuficienteException();

        if (!estaAutorizado()) throw new TransferenciaBloqueadaException();

        Transferencia novaTransferencia = new Transferencia();
        novaTransferencia.setQuantia(transferencia.getQuantia());
        novaTransferencia.setIdRemetente(remetente.getId());
        novaTransferencia.setIdDestinatario(destinatario.getId());
        novaTransferencia.setDataTransferencia(LocalDateTime.now());

        remetente.debitar(transferencia.getQuantia());
        destinatario.creditar(transferencia.getQuantia());

        enviaNotificacao(remetente, "Transação realizada com sucesso");
        enviaNotificacao(destinatario, "Transação recebida com sucesso");

        return transferenciaRepository.save(novaTransferencia);
    }

    public Boolean estaAutorizado() {
        String url = "https://util.devi.tools/api/v2/authorize";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map data = (Map) response.getBody().get("data");
            return (Boolean) data.get("authorization");
        } else return false;
    }

    public void enviaNotificacao(Usuario usuario, String mensagem) {
        String email = usuario.getEmail();

        NotificacaoDTO notificacaoRequest = new NotificacaoDTO(email, mensagem);

        ResponseEntity<String> response = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificacaoRequest, String.class);

        if (!(response.getStatusCode() == HttpStatus.OK)) {
            System.out.println("erro ao enviar notificação");
            throw new NotificacaoForaDoArException();
        }
    }
}
