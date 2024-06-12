package com.pomogite.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="joke1")
@Table(name="joke")

public class joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jokeid")
    private Long id;

    @Column(name="joketext")
    private String textJoke;

    @CreationTimestamp
    @Column(name="jokeinclude")
    private LocalDateTime include;

    @UpdateTimestamp
    @Column(name="jokechange")
    private LocalDateTime change;

}
