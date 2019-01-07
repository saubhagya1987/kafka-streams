package com.kaviddiss.streamkafka.model;

import lombok.AllArgsConstructor;
// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
@AllArgsConstructor
public class Greetings {
    private long timestamp;
    private String message;
}
