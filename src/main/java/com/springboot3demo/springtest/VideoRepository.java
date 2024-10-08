package com.springboot3demo.springtest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByName(String name);

    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName,
                                                                           String partialDescription);

    @Query("select v from VideoEntity v where v.name = ?1")
    List<VideoEntity> findCustomerReport(String name);

//    @Query("select v FROM VideoEntity v " //
//            + "JOIN v.metrics m " //
//            + "JOIN m.activity a " //
//            + "JOIN v.engagement e " //
//            + "WHERE a.views < :minimumViews " //
//            + "OR e.likes < :minimumLikes")
//    List<VideoEntity> findVideosThatArentPopular( //
//                                                  @Param("minimumViews") Long minimumViews, //
//                                                  @Param("minimumLikes") Long minimumLikes);

    @Query(value = "select * from VIDEO_ENTITY where NAME = ?1",
            nativeQuery = true)
    List<VideoEntity> findCustomWithPureSql(String name);
}
