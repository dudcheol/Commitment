package com.web.commitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
	
public interface NotificationDao extends JpaRepository<Notification, Long> {
	
    List<Notification> findAllByToId(Long to);

    Optional<Notification> findByFromIdAndToIdAndDataIdAndTypes(Long fromId, Long toId, Long dataId, String types);

}
