package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

//TODO: RepositoryTest
