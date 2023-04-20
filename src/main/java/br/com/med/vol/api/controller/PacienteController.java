package br.com.med.vol.api.controller;

import br.com.med.vol.api.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente paciente){

        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarTodos(@PageableDefault(page=0,size=10,sort = {"nome"}) Pageable paginacao){
        //return repository.findAll(paginacao).map(DadosListagemPaciente::new);
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizarPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){
        //repository.deleteById(id);
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
