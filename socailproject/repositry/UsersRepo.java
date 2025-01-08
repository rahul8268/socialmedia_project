package com.socaiproject.socailproject.repositry;

import com.socaiproject.socailproject.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity,Long>{
}
