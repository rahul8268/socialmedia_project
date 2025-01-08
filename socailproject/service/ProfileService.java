package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.modal.ProfileModal;

import java.util.List;

public interface ProfileService {
    String addProfile(ProfileModal modal);
    List<ProfileModal>getProfileData();
}
