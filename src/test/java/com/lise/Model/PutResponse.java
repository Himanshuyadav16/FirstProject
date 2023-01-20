package com.lise.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PutResponse {
    private  int id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
