package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.in.LoginResquestDTO;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaTarefa() {

        log.info(email);
        log.info(senha);

        String token = login(converterParaRequestDTO());
        log.info("iniciada a busca de tarefas");

        LocalDateTime horaFutura = LocalDateTime.now();
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(5).plusMinutes(5);
        List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas " + listaTarefas);
        listaTarefas.forEach(tarefa ->
        {
            emailService.enviaEmail(tarefa);
            log.info("email enviado para o usuario " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);
        });
        log.info("Finalizada a busca e notificação das tarefas");
    }

    public String login(LoginResquestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginResquestDTO converterParaRequestDTO() {
        return LoginResquestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }


}
