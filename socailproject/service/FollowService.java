package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.modal.FollowModal;

import java.util.List;

public interface FollowService {

    String insertFollow(FollowModal followModal);

    List<FollowModal>getFollowData();
}
