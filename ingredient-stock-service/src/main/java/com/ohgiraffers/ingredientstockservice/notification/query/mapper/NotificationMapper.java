package com.ohgiraffers.ingredientstockservice.notification.query.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ohgiraffers.ingredientstockservice.notification.query.dto.response.NotificationDTO;

@Mapper
public interface NotificationMapper {

    List<NotificationDTO> getNotificationsByUserNo(long userNo);
}
