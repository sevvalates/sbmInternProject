package com.sbmInternProject.InsuranceAgency.Repositories;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
}
