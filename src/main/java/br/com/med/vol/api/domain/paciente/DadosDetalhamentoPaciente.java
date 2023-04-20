package br.com.med.vol.api.paciente;

import br.com.med.vol.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
