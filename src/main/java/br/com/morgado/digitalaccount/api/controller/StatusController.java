package br.com.morgado.digitalaccount.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for providing application health/status information.
 * This endpoint can be used to verify if the API is running and reachable.
 */
@RestController
@RequestMapping("/api/status")
public class StatusController {

     /**
     * Returns the current status of the API.
     *
     * @return a simple message indicating that the API is up and running
     */
    @GetMapping
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("API online");
    }
    
}
