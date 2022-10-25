package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.Entity.CustomerEntity;
import com.fhdw.loeppe.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {}

//TODO: RepositoryTest
