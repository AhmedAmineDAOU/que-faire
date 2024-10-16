package com.quefaire.demo.repository;
import com.quefaire.demo.entity.Event;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findByTitle(String title);
}
