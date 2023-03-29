package br.com.senac.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Estudante;
import br.com.senac.repository.EstudanteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudanteService {

	private EstudanteRepository estudanteRepository;

	public ResponseEntity<Estudante> buscarEstudantePorId(Long id) {
		if (estudanteRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	public ResponseEntity<List<Estudante>> buscarTodosEstudantes() {
		List listaEstudantes = estudanteRepository.findAll();

		if (listaEstudantes != null) {
			return ResponseEntity.status(HttpStatus.OK).body(listaEstudantes);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	public ResponseEntity<Estudante> cadastrarEstudante(Estudante estudante) {
		Estudante est = estudanteRepository.save(estudante);
		return ResponseEntity.status(HttpStatus.CREATED).body(est);
	}

	public ResponseEntity<Estudante> atualizarEstudante(Long id, Estudante estudante) {
		if (estudanteRepository.existsById(id)) {
			Estudante est = estudanteRepository.save(estudante);
			return ResponseEntity.status(HttpStatus.OK).body(est);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	public ResponseEntity<String> removerEstudante(Long id) {
		if (estudanteRepository.existsById(id)) {
			estudanteRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removido com louvor!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NULL");
	}
	
	public Page<Estudante> buscaEstudantePorPaginacao(PageRequest page){
		return estudanteRepository.findAll(page);
	}
}
