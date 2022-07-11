package com.crash.br.repository;

import com.crash.br.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import javax.swing.text.html.Option;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Quarto> findById(Long id);
}
