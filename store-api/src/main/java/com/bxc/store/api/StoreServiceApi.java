package com.bxc.store.api;

import java.util.Date;

public interface StoreServiceApi {

    public int selectVersion(String supplierId, String goodsId);

    public int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy,
                                         Date updateTime);

    public int selectStoreCount(String supplierId, String goodsId);


}
