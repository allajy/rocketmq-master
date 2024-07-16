package com.bxc.paya.mapper;

import com.bxc.paya.entity.BrokerMessageLog;

public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);
}