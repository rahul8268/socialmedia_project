package com.socaiproject.socailproject.controler;

import com.socaiproject.socailproject.modal.FollowModal;
import com.socaiproject.socailproject.service.FollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FollowController {
    FollowService service;


    public FollowController(FollowService service) {
        this.service = service;
    }
     @PostMapping("follow")
    public String inserFollow(@RequestBody FollowModal modal){
        service.insertFollow(modal);
        return "data saved";
    }
     @GetMapping("follow")
    public List<FollowModal>getFollowes(){
        return service.getFollowData();
    }
}
