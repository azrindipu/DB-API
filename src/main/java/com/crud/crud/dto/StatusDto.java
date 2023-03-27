package com.crud.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    private Integer amountOfDataInserted;
    private String dbType;
    private Long amountOfData;
}
