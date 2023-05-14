package com.djachtoma.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("Patient")
public class Patient implements Serializable {

    private String id;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private Gender gender;


}
