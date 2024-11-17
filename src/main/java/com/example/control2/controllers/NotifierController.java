package com.example.control2.controllers;

import com.example.control2.models.Notifier;
import com.example.control2.services.NotifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifiers")
public class NotifierController {
    @Autowired
    NotifierService notifierService;

    @PutMapping("/update")
    public ResponseEntity<Notifier> update(@RequestBody Notifier notifier) {
        notifierService.update(notifier);
        return ResponseEntity.ok(notifier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notifier> getNotifierByUserid(@PathVariable Long id){
        Notifier notifier = notifierService.findByUserId(id);
        if (notifier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notifier);
    }

}
