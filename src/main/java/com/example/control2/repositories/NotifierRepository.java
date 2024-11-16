package com.example.control2.repositories;

import com.example.control2.models.Notifier;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifierRepository {
    void save(Notifier notifier);
    Notifier findByUser(Long userid);
    Notifier delete(Notifier notifier);
}
