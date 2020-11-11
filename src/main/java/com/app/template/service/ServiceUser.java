package com.app.template.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.template.entity.ConfirmationToken;
import com.app.template.entity.ModelUsers;
import com.app.template.repository.RepoToken;
import com.app.template.repository.RepoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        ModelUsers existingEmail = repoUser.findByEmailIdIgnoreCase(users.getEmailId());
        ModelUsers existingUsername = repoUser.findByUsernameIgnoreCase(users.getUsername());
        if (existingEmail != null) {
            modelAndView.addObject("message", "This Email " + users.getEmailId() + " Already Exists!");
            modelAndView.setViewName("register/error");
        } else if (existingUsername != null) {
            modelAndView.addObject("message", "This Username " + users.getUsername() + " Already Exists!");
            modelAndView.setViewName("register/error");
        } else {
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
            mailMessage
                    .setText("to Confirm your account, please check here : http://localhost:9009/confirm-account?token="
                            + confirmationToken.getConfirmationToken());
            serviceEmail.sendEmail(mailMessage);
            modelAndView.addObject("emailId", users.getEmailId());
            modelAndView.setViewName("register/suksesregister");
        }
        return modelAndView;
    }

    public Map<String, Object> insertUsers(ModelUsers data) {
        Map<String, Object> map = new HashMap<>();
        ModelUsers existingEmail = repoUser.findByEmailIdIgnoreCase(data.getEmailId());
        ModelUsers existingUsername = repoUser.findByUsernameIgnoreCase(data.getUsername());
        if (existingEmail != null) {
            map.put("msg", "This Email "+data.getEmailId()+" Already Exists!");
        } else if (existingUsername != null) {
            map.put("msg", "This Username "+data.getUsername()+" Already Exists!");
        } else {
            ModelUsers user = new ModelUsers();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setUsername(data.getUsername());
            user.setEmailId(data.getEmailId());
            user.setNama_lengkap(data.getNama_lengkap());
            user.setNo_tlp(data.getNo_tlp());
            user.setHak_akses("user");
            user.setStatus("belum aktif");
            user.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
            repoUser.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            repoToken.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(data.getEmailId());
            mailMessage.setSubject("Verivication Registrasi!");
            mailMessage.setFrom("testingweb.gentar@gmail.com");
            mailMessage
                    .setText("to Confirm your account, please check here : http://localhost:9009/confirm-account?token="
                            + confirmationToken.getConfirmationToken());
            serviceEmail.sendEmail(mailMessage);
            map.put("message", "User Berhasil di input");
        }
        return map;
    }

	public Page<ModelUsers> listAllUsrs() {
        Pageable pageable = PageRequest.of(0, 1000);
        Page<ModelUsers> page = repoUser.findAll(pageable);
        return page;
	}

	public List<ModelUsers> detailUsers(String username) {
        List<ModelUsers> result = new ArrayList<ModelUsers>();
        result = repoUser.detailUsers(username);
		return result;
	}

	public Map<String, Object> updateAkses(String hak_akses, String status, String username) {
        Map<String, Object> map = new HashMap<>();
        repoUser.updateAkses(hak_akses, status, username);
        map.put("message", "data berhasil diupdate");
		return map;
	}
}
