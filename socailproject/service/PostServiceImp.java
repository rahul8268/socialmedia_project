package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.entity.PostEntity;
import com.socaiproject.socailproject.modal.PostModal;
import com.socaiproject.socailproject.repositry.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImp implements PostService{
    PostRepo postRepo;

    public PostServiceImp(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public String createPost(PostModal postModal) {
        PostEntity postEntity=new PostEntity();
        BeanUtils.copyProperties(postModal,postEntity);
        postRepo.save(postEntity);
        return "post created";
    }

    @Override
    public List<PostModal> getPostData() {

       List<PostEntity>postEntityList=postRepo.findAll();
       List<PostModal>postModalList=new ArrayList<>();
       for (PostEntity entity:postEntityList){
           PostModal modal=new PostModal();
          modal.setId(entity.getId());
          modal.setUid(entity.getUid());
          modal.setCaption(entity.getCaption());
          modal.setImage(entity.getImage());
         modal.setUserName(entity.getUserName());

          postModalList.add(modal);


       }


        return postModalList;
    }
}
