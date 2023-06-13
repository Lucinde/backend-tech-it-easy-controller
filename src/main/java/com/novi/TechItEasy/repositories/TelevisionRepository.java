package com.novi.TechItEasy.repositories;

import com.novi.TechItEasy.models.Television;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TelevisionRepository extends JpaRepository<Television, Long> {

}


