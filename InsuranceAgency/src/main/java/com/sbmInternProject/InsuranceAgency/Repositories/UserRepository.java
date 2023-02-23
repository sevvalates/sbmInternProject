package com.sbmInternProject.InsuranceAgency.Repositories;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
