package br.com.coruja.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.application.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  public Optional<Aluno> findByEmail(String email);
}
