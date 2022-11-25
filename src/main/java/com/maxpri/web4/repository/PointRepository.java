package com.maxpri.web4.repository;

import com.maxpri.web4.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author max_pri
 */
@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findAllByR(double r);
    List<Point> findAllByOwnerId(Long id);
}
