package com.socaiproject.socailproject.controler;


import com.socaiproject.socailproject.modal.PostModal;
import com.socaiproject.socailproject.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostControler {

    PostService service;

    public PostControler(PostService service) {
        this.service = service;
    }

    @GetMapping("post")
    List<PostModal>getPostData(){
            return service.getPostData();
    }

    @PostMapping("post")
    String createPost(@RequestBody PostModal postModal){
        service.createPost(postModal);
        return "post created";
    }


}
