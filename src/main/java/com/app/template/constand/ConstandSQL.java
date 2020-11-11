package com.app.template.constand;

public class ConstandSQL {
    
    public static final String detailUsers = "SELECT * FROM USERS WHERE USERNAME = :p_username";

    public static final String updateAkses = "UPDATE USERS SET HAK_AKSES = :p_hak_akses, STATUS = :p_status WHERE USERNAME = :p_username ";
    
}
