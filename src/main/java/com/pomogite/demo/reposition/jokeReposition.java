package com.pomogite.demo.reposition;

import com.pomogite.demo.model.joke;

import org.springframework.data.jpa.repository.JpaRepository;

public interface jokeReposition extends JpaRepository<joke, Long> {

}
