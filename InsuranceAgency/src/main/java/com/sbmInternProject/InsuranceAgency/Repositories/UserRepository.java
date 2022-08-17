package com.sbmInternProject.InsuranceAgency.Repositories;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}