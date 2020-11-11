package com.app.template.service;

import com.app.template.entity.ConfirmationToken;
import com.app.template.entity.ModelUsers;
import com.app.template.repository.RepoToken;
import com.app.template.repository.RepoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdmin {
    
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoToken repoToken;

    @Autowired
    private ServiceEmailSender serviceEmail;

    public Page<ModelUsers> listAllUser(){
        Pageable pageable = PageRequest.of(0, 1000);
        Page<ModelUsers> page = repoUser.findAll(pageable);
        return page;
    }

	public ModelUsers getUsers(Long idUsers) {
        ModelUsers users = repoUser.findById(idUsers).get();
        return users;
    }
    
    public void save(ModelUsers users){
        ModelUsers user = new ModelUsers();
        user.setUsername(users.getUsername());
        user.setEmailId(users.getEmailId());
        user.setNama_lengkap(users.getNama_lengkap());
        user.setNo_tlp(users.getNo_tlp());
        user.setHak_akses("user");
        user.setStatus(users.getStatus());
        user.setHak_akses(users.getHak_akses());
        user.setPassword(users.getPassword());
        repoUser.save(users);
        ConfirmationToken confirmationToken = new ConfirmationToken(users);
        if(users.getStatus().equals("belum_aktif")){
            repoToken.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(users.getEmailId());
            mailMessage.setSubject("Status Dibatalkan!");
            mailMessage.setFrom("testingweb.gentar@gmail.com");
            mailMessage.setText("Mohon Maaf status registrasi anda dibatalkan, Silahkan Registrasi Ulang");
            serviceEmail.sendEmail(mailMessage);
        }else{
            repoToken.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(users.getEmailId());
            mailMessage.setSubject("Status Dikonfirmasi!");
            mailMessage.setFrom("testingweb.gentar@gmail.com");
            mailMessage.setText("Status Anda Berhasil Dikonfirmasi");
            serviceEmail.sendEmail(mailMessage);
        }
    }
}
