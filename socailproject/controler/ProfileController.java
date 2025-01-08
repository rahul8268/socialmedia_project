package com.socaiproject.socailproject.controler;

import com.socaiproject.socailproject.modal.ProfileModal;
import com.socaiproject.socailproject.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

    ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("data")
    List<ProfileModal>getdata(){
        return service.getProfileData();
    }

    @PostMapping("data")
    String insertProfile( @RequestBody  ProfileModal modal){
      return service.addProfile(modal);

    }
}
