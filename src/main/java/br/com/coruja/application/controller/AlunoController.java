package br.com.coruja.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.application.errors.EmailJaCadastradoException;
import br.com.coruja.application.models.Aluno;
import br.com.coruja.application.repositories.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
  @Autowired
  AlunoRepository alunoRepository;

  @GetMapping
  public List<Aluno> list() {
    List<Aluno> alunos = new ArrayList<>();
    alunos = this.alunoRepository.findAll();
    return alunos;
  }

  @PostMapping
  public ResponseEntity<Aluno> save(@RequestBody Aluno data) {

    if (data == null) {
      throw new IllegalArgumentException();
    }

    Optional<Aluno> alreadyExists = this.alunoRepository.findByEmail(data.getEmail());

    if (alreadyExists.isPresent()) {
      throw new EmailJaCadastradoException();
    }

    Aluno savedAluno = this.alunoRepository.save(data);

    return ResponseEntity.status(201).body(savedAluno);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Aluno>> find(@PathVariable long id) {
    Optional<Aluno> aluno = this.alunoRepository.findById(id);

    return ResponseEntity.status(200).body(aluno);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    this.alunoRepository.deleteById(id);

    return ResponseEntity.status(200).build();
  }

  @PutMapping(value = "/{id}")
  @Transactional
  public ResponseEntity<Aluno> upate(@RequestBody Aluno data, @PathVariable long id) {
    System.out.println(data);

    if (data == null) {
      throw new IllegalArgumentException();
    }

    Optional<Aluno> aluno = this.alunoRepository.findById(id);

    if (aluno.isPresent()) {
      aluno.get().setEmail(data.getEmail());
      aluno.get().setName(data.getName());
    }

    return ResponseEntity.status(204).build();
  }
}
