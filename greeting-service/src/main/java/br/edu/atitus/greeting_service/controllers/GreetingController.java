package br.edu.atitus.greeting_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.atitus.greeting_service.configs.GreetingConfig;

import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingConfig config;
    
    public GreetingController(GreetingConfig config) {
        this.config = config;
    }

    private String validName(String name){
        if(name == null || name.isEmpty()){
            name = config.getDefaultName();
        }
        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }

    @GetMapping({"", "/"})
    public String getGreeting(@RequestParam(required = false) String name){
        return validName(name);
    }
    @GetMapping("/{name}")
    public String getGreetingName(@PathVariable(required = false) String name){
       return validName(name);
    }
    @PostMapping()
    public ResponseEntity postGreeting(@RequestBody Map<String, String> body){
        String name = body.get("name");
        return ResponseEntity.status(201).body(validName(name));
    }
}
