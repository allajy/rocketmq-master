package com.bxc.store.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.bxc.store.api.StoreServiceApi;
import com.bxc.store.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: Bixc
 * @date: 2024/07/15 10:36
 **/
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class StoreServiceProvider implements StoreServiceApi {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public int selectVersion(String supplierId, String goodsId) {
        return storeMapper.selectVersion(supplierId,goodsId);
    }

    @Override
    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime) {
        return storeMapper.updateStoreCountByVersion(version,supplierId,goodsId,updateBy,updateTime);
    }

    @Override
    public int selectStoreCount(String supplierId, String goodsId) {
        return storeMapper.selectStoreCount(supplierId, goodsId);
    }
}
