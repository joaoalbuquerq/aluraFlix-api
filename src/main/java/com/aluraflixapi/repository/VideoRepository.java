package com.aluraflixapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aluraflixapi.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

}
