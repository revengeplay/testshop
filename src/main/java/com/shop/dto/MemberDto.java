package com.shop.dto;


import lombok.Getter;
import lombok.Setter;



/**
 * A DTO for the {@link com.shop.entity.Member} entity
 */
@Getter @Setter
public class MemberDto {
    private String name;
    private String email;
    private String password;
    private String address;
}
