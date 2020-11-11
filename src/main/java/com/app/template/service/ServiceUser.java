package com.app.template.service;

import com.app.template.entity.ConfirmationToken;
import com.app.template.entity.ModelUsers;
import com.app.template.repository.RepoToken;
import com.app.template.repository.RepoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ServiceUser {

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoToken repoToken;

    @Autowired
    private ServiceEmailSender serviceEmail;

	public ModelAndView findByUsername(ModelAndView modelAndView, ModelUsers users) {
        ModelUsers existingUser = repoUser.findByEmailIdIgnoreCase(users.getEmailId());
        if(existingUser != null){
            modelAndView.addObject("message", "This Email Already Exists!");
            modelAndView.setViewName("register/error");
        }else{
            ModelUsers user = new ModelUsers();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setUsername(users.getUsername());
            user.setEmailId(users.getEmailId());
            user.setNama_lengkap(users.getNama_lengkap());
            user.setNo_tlp(users.getNo_tlp());
            user.setHak_akses("user");
            user.setStatus("belum aktif");
            user.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            repoUser.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            repoToken.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(users.getEmailId());
            mailMessage.setSubject("Verivication Registrasi!");
            mailMessage.setFrom("testingweb.gentar@gmail.com");
            mailMessage.setText("to Confirm your account, please check here : http://localhost:9009/confirm-account?token="+confirmationToken.getConfirmationToken());
            serviceEmail.sendEmail(mailMessage);
            modelAndView.addObject("emailId", users.getEmailId());
            modelAndView.setViewName("register/suksesregister");
        }
        return modelAndView;
	}
    
}
