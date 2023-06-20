package com.project.manager.user.repository;

import com.project.manager.user.entity.AvatarFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarFileRepository extends JpaRepository<AvatarFile,Long>{

}
