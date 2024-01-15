package br.com.alura.clinic.manager.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query(""" 
            SELECT m 
            FROM Medico m 
            WHERE m.ativo = true
            AND m.especialidade = :especialidade 
            AND NOT EXISTS 
                (
                    SELECT 1
                    FROM Consulta c
                    WHERE c.medico.id = m.id
                    AND c.data = :data
                    AND c.motivoCancelamento IS NULL
                )
            ORDER BY rand()
            LIMIT 1 """)
    Medico escolherMedicoAleatorioAtivoEspecialidadeDataLivre(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE m.id = :id """)
    Boolean findAtivoById(Long id);
}
