package com.lise.Model;

import com.github.javafaker.Name;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PutBody {
    private String name;
    private String email;
    private String gender;
    private String status;
}
