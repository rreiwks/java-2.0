package com.pomogite.demo.controller;

import com.pomogite.demo.model.joke;
import com.pomogite.demo.service.jokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/joke")
@RequiredArgsConstructor
public class jokeController {

    private final jokeService jokeService;

    @PostMapping
    ResponseEntity<Void> addJoke(@RequestBody joke jokeModel){
        jokeService.addJoke(jokeModel);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    ResponseEntity<List<joke>> getAllJoke(){
        return ResponseEntity.ok(jokeService.getAllJokes());

    }


    @GetMapping("/{id}")
    ResponseEntity<joke> getJokeById(@PathVariable Long id){
        Optional<joke> jokeOptional = jokeService.getJokeById(id);
        return jokeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<joke> updateJokeById(@PathVariable Long id, @RequestBody joke updatedJoke) {
        Optional<joke> updatedJokeOptional = jokeService.putJokeById(id, updatedJoke);
        return updatedJokeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJokeById(@PathVariable Long id){
        jokeService.deleteJokeById(id);
        return ResponseEntity.ok().build();
    }
}