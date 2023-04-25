package br.com.med.vol.api.domain.consulta.validacoes.agendamento;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if(domingo ||antesAberturaClinica || depoisFechamentoClinica){
            throw new ValidacaoException("consulta fora do horario de funcionamento");
        }
    }
}
