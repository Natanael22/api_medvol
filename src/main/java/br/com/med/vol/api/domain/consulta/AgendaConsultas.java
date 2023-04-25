package br.com.med.vol.api.domain.consulta;

import br.com.med.vol.api.domain.ValidacaoException;
import br.com.med.vol.api.domain.medico.Medico;
import br.com.med.vol.api.domain.medico.MedicoRepository;
import br.com.med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id do paciente informado nao existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id do medico informado nao existe");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null,medico, paciente, dados.data());
        consultaRepository.save(consulta);
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
}
