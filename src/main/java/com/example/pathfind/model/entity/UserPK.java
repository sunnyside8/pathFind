package com.example.pathfind.model.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserPK implements Serializable {
    private Long id;
    private String username;
}
