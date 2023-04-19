package br.com.med.vol.api.paciente;

import br.com.med.vol.api.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
