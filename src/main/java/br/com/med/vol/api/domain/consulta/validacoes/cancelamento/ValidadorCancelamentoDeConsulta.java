package br.com.med.vol.api.domain.consulta.validacoes.cancelamento;

import br.com.med.vol.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
