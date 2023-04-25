package br.com.med.vol.api.domain.consulta;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import br.com.med.vol.api.domain.medico.Medico;
import br.com.med.vol.api.domain.medico.MedicoRepository;
import br.com.med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id do paciente informado nao existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id do medico informado nao existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("nao existe medico disponivel para essa data");
        }

        var consulta = new Consulta(null,medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("especialidade e obrigatorio quando medico nao for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("id da consulta informado nao existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
