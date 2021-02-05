package com.web.commitment.dao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.web.commitment.dto.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromId;
    private Long toId;
    private Long dataId;
    private String types;
    private Boolean isread;

    @Builder
    public Notification(Long from, Long to, Long dataId, String type) {
        this.fromId = from;
        this.toId = to;
        this.dataId = dataId;
        this.types = type;
        this.isread = false;
    }

    public boolean unread() {
        return !isread;
    }

    public void read() {
        this.isread = true;
    }
}
