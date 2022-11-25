package com.maxpri.web4.controllers;

import com.maxpri.web4.models.Point;
import com.maxpri.web4.models.User;
import com.maxpri.web4.services.PointService;
import com.maxpri.web4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author max_pri
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/points")
public class PointsController {

    private final PointService pointService;
    private final UserService userService;

    @Autowired
    public PointsController(PointService pointService, UserService userService) {
        this.pointService = pointService;
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Point>> getPoints(@RequestHeader HttpHeaders headers) {
        String username = headers.get("username").get(0);
        User user = userService.findByUsername(username).orElse(null);
        return new ResponseEntity<>(pointService.findAllByOwnerId(user.getId()), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Point> addPoint(@RequestBody Point point, @RequestHeader HttpHeaders headers) {
        String username = headers.get("username").get(0);
        User user = userService.findByUsername(username).orElse(null);
        return new ResponseEntity<>(pointService.save(point, user.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteAll() {
        pointService.deleteAll();
    }
}
