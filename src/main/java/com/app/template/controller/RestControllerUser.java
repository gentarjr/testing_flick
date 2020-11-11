package com.app.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.template.entity.ModelUsers;
import com.app.template.service.ServiceUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerUser {

    public static final Logger Logger = LoggerFactory.getLogger(RestController.class);
    
    @Autowired
    private ServiceUser service;

    @RequestMapping(value = "/insertUsers", method = RequestMethod.POST)
    public <T> ResponseEntity<T> insertUsers(@RequestBody ModelUsers data){
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<T> response = null;
        try{
            map = service.insertUsers(data);
            response = new ResponseEntity<T>((T)map, HttpStatus.OK);
        }catch(Exception e){
            Logger.error(e.getMessage(), e);
            e.printStackTrace();
            response = new ResponseEntity<T>((T)e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelUsers>> listUsers(){
        Page<ModelUsers> page = service.listAllUsrs();
        List<ModelUsers> list = page.getContent();
        return new ResponseEntity<List<ModelUsers>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/detailUsers", method = RequestMethod.POST)
    public <T> ResponseEntity<T> detailUsers(@RequestBody Map<String, Object> data){
        ResponseEntity<T> response = null;
        String username = (String) data.get("username");
        List<ModelUsers> result = new ArrayList<ModelUsers>();
        try{
            result = service.detailUsers(username);
            response = new ResponseEntity<T>((T)result, HttpStatus.OK);
        }catch(Exception e){
            Logger.error(e.getMessage(), e);
            e.printStackTrace();
            response = new ResponseEntity<>((T) e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/updateAkses", method = RequestMethod.POST)
    public<T> ResponseEntity<T> updateAkses(@RequestBody Map<String, Object> data){
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<T> response = null;
        String hak_akses = (String) data.get("hak_akses");
        String status = (String) data.get("status");
        String username = (String) data.get("username");
        try{
            map = service.updateAkses(hak_akses, status, username);
            response = new ResponseEntity<T>((T)map, HttpStatus.OK);
        }catch(Exception e){
            Logger.error(e.getMessage(), e);
            e.printStackTrace();
            response = new ResponseEntity<T>((T)e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
