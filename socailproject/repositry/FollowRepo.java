package com.socaiproject.socailproject.repositry;

import com.socaiproject.socailproject.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepo extends JpaRepository<FollowEntity,Long> {
}
