package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.entity.FollowEntity;
import com.socaiproject.socailproject.modal.FollowModal;
import com.socaiproject.socailproject.repositry.FollowRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FollowSerImp implements FollowService{

    FollowRepo repo;

    public FollowSerImp(FollowRepo repo) {
        this.repo = repo;
    }

    @Override
    public String insertFollow(FollowModal followModal) {

        FollowEntity followEntity=new FollowEntity();
        BeanUtils.copyProperties(followModal,followEntity);
        repo.save(followEntity);

        return "data saved success";
    }

    @Override
    public List<FollowModal> getFollowData() {

        List<FollowEntity>followEntityList=repo.findAll();
        List<FollowModal>followModalList=new ArrayList<>();

        for (FollowEntity entity:followEntityList){
            FollowModal modal=new FollowModal();
            modal.setId(entity.getId());
            modal.setUid(entity.getUid());
            modal.setCount(entity.getCount());
            modal.setUserName(entity.getUserName()); 
            modal.setFollowUser(entity.getFollowUser());
            followModalList.add(modal);
        }
        return followModalList;
    }
}
