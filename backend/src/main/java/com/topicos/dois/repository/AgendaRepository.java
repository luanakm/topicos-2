package com.topicos.dois.repository;

import com.topicos.dois.entity.Agenda;
import com.topicos.dois.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
