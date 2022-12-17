package com.example.intersectioncontrol.repository;

import com.example.intersectioncontrol.entity.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntersectionRepository extends JpaRepository<Intersection, Long> {
}
