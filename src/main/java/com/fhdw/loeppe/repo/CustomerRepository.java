package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {}

//TODO: RepositoryTest
