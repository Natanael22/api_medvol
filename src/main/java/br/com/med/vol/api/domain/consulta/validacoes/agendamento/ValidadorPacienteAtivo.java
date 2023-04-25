package br.com.med.vol.api.domain.consulta.validacoes.agendamento;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import br.com.med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("consulta nao pode ser agendada com paciente inativo");
        }
    }
}
