package com.octo.repository;


import com.octo.domain.enums.Level;
import com.octo.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String>, JpaSpecificationExecutor<Video> {
@Query(value = "SELECT DISTINCT ID, DESCRIPTION, DURATION, LEVEL, TITLE, URL FROM VIDEO\n" +
        "LEFT JOIN VIDEO_TAGS\n" +
        "ON VIDEO_TAGS.VIDEO_ID = VIDEO.ID\n" +
        "WHERE  ((VIDEO.LEVEL=:level ) AND (VIDEO.LEVEL=:level OR VIDEO_TAGS.TAGS IN :tags ))   OR  VIDEO_TAGS.TAGS IN :tags ",nativeQuery = true)

    public List<Video> getVideosByLevelAndTag(@Param("tags") List<String> tags, @Param("level") String level);
}
