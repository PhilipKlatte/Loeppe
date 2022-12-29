package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {}

//TODO: RepositoryTest
