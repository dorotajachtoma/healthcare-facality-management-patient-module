package com.djachtoma.model.phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("phoneNumber")
public class PhoneNumber {

    private String number;
}
