package com.cse208.mapper;

import com.cse208.Entity.Offer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OfferMapper {
    // 插入 offer
    boolean setOneOffer(Offer offer);

    // 筛选 offer by username, 多条则由offerId升序排列
    Offer getOfferByUsername(String username);

    // 根据 uName 和 offerId 来删除 offer
    boolean deleteOffer(Offer offer);

    // 更新 offer
    boolean updateOffer(Offer offer);

    // 获取所有offer信息
    List<Offer> getAllOffer();

    List<Offer> getOfferByInput(String keyword);
}
