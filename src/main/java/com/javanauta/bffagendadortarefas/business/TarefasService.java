package com.javanauta.bffagendadortarefas.business;



import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.client.TerefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TerefasClient terefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTO){
        return terefasClient.gravarTarefas(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataIncial,
                                                                     LocalDateTime dataFinal,
                                                                     String token){
        return terefasClient.buscarListaDeTarefasPorPeriodo(dataIncial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token){
      return terefasClient.buscarListaDeTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token){
        terefasClient.deletaTarefaPorId(id, token);
    }


    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum statusNotificacaoEnum, String id, String token){

        return terefasClient.alteraStatusNotificacao(statusNotificacaoEnum, id, token);

    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token){
        return terefasClient.updateTarefas(dto, id, token);
    }

}
