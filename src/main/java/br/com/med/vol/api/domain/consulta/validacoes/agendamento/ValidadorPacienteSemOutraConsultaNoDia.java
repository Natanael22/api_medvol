package br.com.med.vol.api.domain.consulta.validacoes.agendamento;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.consulta.ConsultaRepository;
import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("paciente ja possui uma consulta nesse dia");
        }
    }
}
