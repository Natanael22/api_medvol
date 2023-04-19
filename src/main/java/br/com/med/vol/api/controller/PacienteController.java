package br.com.med.vol.api.controller;

import br.com.med.vol.api.paciente.DadosCadastroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrar(DadosCadastroPaciente paciente){
        System.out.println(paciente);
    }
}
