package com.ly.application.entity;

import jakarta.persistence.Transient;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors
public class OperationLog implements Serializable {

    private Long id;

    private String url;

    private String requestData;

    private String responseData;

    private LocalDateTime createTime;

    private Long userId;

    private String userName;

    private Long useTime;

    private boolean hasException;

    private String exceptionData;

}
