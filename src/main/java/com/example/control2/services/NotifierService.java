package com.example.control2.services;

import com.example.control2.models.Notifier;
import com.example.control2.models.Task;
import com.example.control2.repositories.NotifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotifierService {
    @Autowired
    NotifierRepository notifierRepository;

    public Notifier save(Notifier notifier) {
        return notifierRepository.save(notifier);
    }

    public Notifier findByUserId(Long id) {
        return notifierRepository.findByUser(id);
    }

    public void delete(Notifier notifier) {
        notifierRepository.delete(notifier);
    }

    public Notifier create(Long userid){
        Notifier notifier = new Notifier();
        notifier.setUserId(userid);
        notifier.setEnabled(false);
        notifier.setAmount(0);
        notifier.setTimeunit("day");

        return notifierRepository.save(notifier);
    }

    public int getNotificationHoursInterval(Notifier notifier) {
        String timeunit = notifier.getTimeunit();
        return switch (timeunit) {
            case "hour" -> notifier.getAmount();
            case "day" -> 24*notifier.getAmount();
            case "week" -> 24*7*notifier.getAmount();
            case "month" -> 24*7*30*notifier.getAmount();
            default -> 0;
        };
    }
}
