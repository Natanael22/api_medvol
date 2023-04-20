package br.com.med.vol.api.domain.paciente;

import br.com.med.vol.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
