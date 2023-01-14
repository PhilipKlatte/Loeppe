package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.entity.ArticleQuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleQuantityRepository extends JpaRepository<ArticleQuantityEntity, UUID> {}
