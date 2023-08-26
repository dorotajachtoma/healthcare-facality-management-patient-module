package com.djachtoma.model.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("address")
public class Address {

    private String city;
    private String zipCode;
    private String street;
    private String building;
    private String apartment;
}
