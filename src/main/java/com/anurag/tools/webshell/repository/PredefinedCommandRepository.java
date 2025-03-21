package com.anurag.tools.webshell.repository;

import com.anurag.tools.webshell.entity.PredefinedCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredefinedCommandRepository extends JpaRepository<PredefinedCommandEntity, Long> {
}
