package com.app.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class ModelUsers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user_id;

    private String emailId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    private String username;

    @Column(name = "nama_lengkap")
    private String nama_lengkap;

    @Column(name = "no_telp")
    private String no_tlp;

    @Column(name = "hak_akses")
    private String hak_akses;

    @Column(name = "status")
    private String status;

    private String password;

    @Column(name= "enabled", length = 1, nullable = false)
    private int enabled;

    public ModelUsers(){
        this.enabled = 0;
    }
}
