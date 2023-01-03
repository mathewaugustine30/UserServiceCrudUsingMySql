package com.mathew.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String location;
    private String about;


}