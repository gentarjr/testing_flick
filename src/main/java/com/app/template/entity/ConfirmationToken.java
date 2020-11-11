package com.app.template.entity;

import java.util.*;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class ConfirmationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private long tokenid;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = ModelUsers.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private ModelUsers user;

    public ConfirmationToken(ModelUsers user){
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public ConfirmationToken(){
        
    }
}
