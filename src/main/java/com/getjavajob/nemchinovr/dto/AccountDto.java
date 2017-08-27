package com.getjavajob.nemchinovr.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
}