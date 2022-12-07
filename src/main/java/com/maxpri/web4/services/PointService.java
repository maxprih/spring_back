package com.maxpri.web4.services;

import com.maxpri.web4.models.Point;
import com.maxpri.web4.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<Point> findAllByOwnerId(Long id) {
        return pointRepository.findAllByOwnerId(id);
    }

    @Transactional
    public Point save(Point point, Long id) {
        checkHit(point);
        point.setOwnerId(id);
        return pointRepository.save(point);
    }

    @Transactional
    public void deleteAll() {
        pointRepository.deleteAll();
    }

    public void checkHit(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        boolean hit = false;

        if ((x*x + y*y <= (r/2)*(r/2)) && x >= 0 && y >= 0) {
            hit = true; //circle check
        } else if (y>= -x/2 -r/2 && y <= 0 && x <= 0) {
            hit = true; //triangle check
        } else if (x<=r && y<=0 && x>=0 && y>=-r) {
            hit = true; //rectangle check
        }
        point.setHit(hit);
    }
}
