package com.app.template.service;

import com.app.template.entity.ConfirmationToken;
import com.app.template.entity.ModelUsers;
import com.app.template.repository.RepoToken;
import com.app.template.repository.RepoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ServiceToken {

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoToken repoToken;

	public ModelAndView findbyToken(ModelAndView modelAndView, String confirmationToken) {
        ConfirmationToken token = repoToken.findByConfirmationToken(confirmationToken);
        if(token != null){
            ModelUsers users = repoUser.findByEmailIdIgnoreCase(token.getUser().getEmailId());
            users.setEnabled(1);
            repoUser.save(users);
            modelAndView.setViewName("register/accountVerified");
        }else{
            modelAndView.addObject("message", "The link is invalid or broken");
            modelAndView.setViewName("register/error");
        }
        return modelAndView;
	}
    
}
