package com.cse208.service;

import com.cse208.Entity.Offer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OfferService {
    boolean setOneOffer(Offer offer);
    Offer getOfferByUsername(String username);
    boolean deleteOffer(Offer offer);
    boolean updateOffer(Offer offer);

    //查询所有offer
    List<Offer> getAllOffer();

    //分页查询的方法
    PageInfo<Offer> getOfferByPage(Integer page);

    //根据关键字查询
    PageInfo<Offer> getOfferByKeyword(Integer page, String keyword);
}
