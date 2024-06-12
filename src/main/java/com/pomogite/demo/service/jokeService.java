package com.pomogite.demo.service;


import com.pomogite.demo.model.joke;

import java.util.List;
import java.util.Optional;

public interface jokeService {
    void addJoke(joke joke);
    List<joke> getAllJokes();
    Optional<joke> getJokeById(Long id);
    Optional<joke> putJokeById(Long id, joke updatedJoke);
    void deleteJokeById(Long id);

}
