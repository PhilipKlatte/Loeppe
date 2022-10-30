package com.fhdw.loeppe.repo;

import com.fhdw.loeppe.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {}

//TODO: RepositoryTest
