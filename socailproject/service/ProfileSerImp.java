package com.socaiproject.socailproject.service;


import com.socaiproject.socailproject.entity.ProfileEntity;

import com.socaiproject.socailproject.modal.ProfileModal;
import com.socaiproject.socailproject.repositry.ProfileRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileSerImp implements ProfileService {

    ProfileRepo repo;

    public ProfileSerImp(ProfileRepo repo) {
        this.repo = repo;
    }

    @Override
    public String addProfile(ProfileModal modal) {

        ProfileEntity entity = new ProfileEntity();
        BeanUtils.copyProperties(modal, entity);
        repo.save(entity);
        return "data saved";
    }

    @Override
    public List<ProfileModal> getProfileData() {

        List<ProfileEntity> entityList = repo.findAll();
        List<ProfileModal> modalList = new ArrayList<>();

        for (ProfileEntity entity : entityList) {
            ProfileModal modal = new ProfileModal();
            modal.setId(entity.getId());
            modal.setUid(entity.getUid());
            modal.setImage(entity.getImage());
            modalList.add(modal);
        }
        return modalList;
    }
}
