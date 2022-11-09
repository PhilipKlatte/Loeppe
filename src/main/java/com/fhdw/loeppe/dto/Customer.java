package com.fhdw.loeppe.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@Data
public class Customer {

    private long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String address;
}
