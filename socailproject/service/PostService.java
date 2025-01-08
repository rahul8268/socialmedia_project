package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.modal.PostModal;

import java.util.List;

public interface PostService {

    String createPost(PostModal postModal);
    List<PostModal>getPostData();
}
