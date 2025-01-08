package com.socaiproject.socailproject.repositry;

import com.socaiproject.socailproject.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<ProfileEntity,Long> {
}
