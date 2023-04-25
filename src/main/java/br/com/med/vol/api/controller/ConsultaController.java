package br.com.med.vol.api.controller;


import br.com.med.vol.api.domain.consulta.AgendaConsultas;
import br.com.med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.vol.api.domain.consulta.DadosCancelamentoConsulta;
import br.com.med.vol.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){

        return ResponseEntity.noContent().build();
    }
}
