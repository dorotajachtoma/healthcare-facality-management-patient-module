package com.djachtoma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("patient")
public class Patient implements Serializable {

    @Id
    private String id;
    private String name;
    private String surname;
    private LocalDateTime dateOfBirth;
    private Gender gender;


}
