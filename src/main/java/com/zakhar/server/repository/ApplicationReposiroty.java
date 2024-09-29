package com.zakhar.server.repository;

import com.zakhar.server.entity.Applications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationReposiroty extends JpaRepository<Applications, Long> {
}
