package com.cse208.service;

import com.cse208.Entity.Offer;
import com.cse208.mapper.OfferMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    OfferMapper offerMapper;

    @Override
    public boolean setOneOffer(Offer offer) {
        return offerMapper.setOneOffer(offer);
    }

    @Override
    public Offer getOfferByUsername(String username){ return offerMapper.getOfferByUsername(username); }

    @Override
    public boolean deleteOffer(Offer offer) {
        return offerMapper.deleteOffer(offer);
    }

    @Override
    public boolean updateOffer(Offer offer) {
        return offerMapper.updateOffer(offer);
    }

    @Override
    public List<Offer> getAllOffer() {
        return offerMapper.getAllOffer();
    }

    @Override
    public PageInfo<Offer> getOfferByPage(Integer page) {
        //开启分页支持
        PageHelper.startPage(page, 3);
        //调用查询所有
        List<Offer> list = offerMapper.getAllOffer();
        //获取分页的相关信息
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    @Override
    public PageInfo<Offer> getOfferByKeyword(Integer page, String keyword) {
        //开启分页支持
        PageHelper.startPage(page, 3);
        //调用查询所有
        List<Offer> list = offerMapper.getOfferByInput(keyword);
        //获取分页的相关信息
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }
}
