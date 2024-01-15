package br.com.alura.clinic.manager.controller;

import br.com.alura.clinic.manager.domain.medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired // Injeção de dependência
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional // Transação ativa com banco de dados
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(dadosCadastroMedico);
        medicoRepository.save(medico);

        return ResponseEntity.created(
                uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri())
                .body(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable pageable) {
		// Para sobrescrever o padrão da paginação, pode-se decorar o tipo do parâmetro com
		// @PageableDefault, definindo as propriedades desejadas (page, size, sort...)
		var medicos = medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);

		return ResponseEntity.ok(medicos);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedico.id());
		medico.atualizar(dadosAtualizacaoMedico);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

}
