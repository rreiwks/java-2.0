package com.pomogite.demo.service;

import com.pomogite.demo.model.joke;
import com.pomogite.demo.reposition.jokeReposition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class jokeServiceImp implements jokeService{
    private final jokeReposition jokeReposition;

    @Override
    public void addJoke(joke joke) {
        jokeReposition.save(joke);

    }

    @Override
    public List<joke> getAllJokes() {
        return jokeReposition.findAll();
    }

    @Override
    public Optional<joke> getJokeById(Long id) {
        return jokeReposition.findById(id);
    }

    @Override
    public Optional<joke> putJokeById(Long id, joke updatedJoke) {
        Optional<joke> existingJoke = jokeReposition.findById(id);
        if (existingJoke.isPresent()) {
            joke jokeToUpdate = existingJoke.get();
            jokeToUpdate.setTextJoke(updatedJoke.getTextJoke());
            jokeReposition.save(jokeToUpdate);
        }
        return existingJoke;
    }
    @Override
    public void deleteJokeById(Long id) {
        jokeReposition.deleteById(id);
    }
}