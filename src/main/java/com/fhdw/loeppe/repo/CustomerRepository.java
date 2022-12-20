package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> { }

//TODO: RepositoryTest
//TODO: Specifications??
