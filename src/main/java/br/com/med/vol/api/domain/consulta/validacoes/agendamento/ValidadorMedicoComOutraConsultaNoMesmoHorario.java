package br.com.med.vol.api.domain.consulta.validacoes.agendamento;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.consulta.ConsultaRepository;
import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaMesmoHorario){
            throw new ValidacaoException("medico ja possui consulta agendada no mesmo horario");
        }
    }
}
