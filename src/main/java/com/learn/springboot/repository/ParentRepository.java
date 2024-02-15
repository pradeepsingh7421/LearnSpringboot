package com.learn.springboot.repository;

import com.learn.springboot.Entity.Children;
import com.learn.springboot.Entity.Parent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, String> {
    Optional<Parent> findByChildren(Children lChildren);
}
