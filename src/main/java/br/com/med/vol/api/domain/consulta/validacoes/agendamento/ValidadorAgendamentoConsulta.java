package br.com.med.vol.api.domain.consulta.validacoes.agendamento;

import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
