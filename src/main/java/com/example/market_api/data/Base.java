package com.example.market_api.data;


import com.example.market_api.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Base {

    private Long id;
    private Long updatedAt;
    private Long createdAt;
    @LastModifiedBy
    private String updatedBy;
    @CreatedBy
    private String createdBy;

    private Map<String, Object> customData;

    public Base(Long id) {
        this.id = id;
    }

    public Base(Long id, LocalDateTime updatedAt, LocalDateTime createdAt, String updatedBy,String createdBy) {
        this.id = id;
        this.updatedAt = DateUtil.localDateTimeToMilliseconds(updatedAt);
        this.createdAt = DateUtil.localDateTimeToMilliseconds(createdAt);
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
    }

    public Base(Long id,LocalDateTime createdAt,String createdBy) {
        this.id = id;
        this.createdAt = DateUtil.localDateTimeToMilliseconds(createdAt);
        this.createdBy = createdBy;
    }
}