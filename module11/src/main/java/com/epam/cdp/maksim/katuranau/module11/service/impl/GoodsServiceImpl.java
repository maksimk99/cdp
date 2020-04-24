package com.epam.cdp.maksim.katuranau.module11.service.impl;

import com.epam.cdp.maksim.katuranau.module11.dao.GoodsDao;
import com.epam.cdp.maksim.katuranau.module11.exception.InternalServerException;
import com.epam.cdp.maksim.katuranau.module11.exception.ValidationException;
import com.epam.cdp.maksim.katuranau.module11.model.Goods;
import com.epam.cdp.maksim.katuranau.module11.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsDao goodsDao;
    private final MessageSourceService messageSourceService;

    @Autowired
    public GoodsServiceImpl(final GoodsDao goodsDao, final MessageSourceService messageSourceService) {
        this.goodsDao = goodsDao;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public List<Goods> getGoods(int pageIndex, final boolean sortIncreasing) {
        int pageSize = Integer.parseInt(messageSourceService.getLocaleMessage("page.size"));
        pageIndex--;
        int amountOfGoods = goodsDao.getAmountOfGoods().intValue();
        if ((pageIndex) * pageSize > amountOfGoods) {
            pageIndex = 0;
        }
        List<Goods> goodsList = goodsDao.getGoods(pageSize, pageIndex * pageSize);
        if (!sortIncreasing) {
            goodsList.sort(Comparator.comparing(Goods::getId).reversed());
        }
        return goodsList;
    }

    @Override
    public List<Goods> findGoodsByName(final String goodsName) {
        return goodsDao.findGoodsByName(goodsName);
    }

    @Override
    public List<Goods> getAllGoodsByUserId(final Long userId) {
        return goodsDao.getAllGoodsByUserId(userId);
    }

    @Override
    public void removeGoodsByGoodsIdAndUserId(final Long goodsId, final Long userId) {
        if (!goodsDao.isExistByGoodsIdAndUserId(goodsId, userId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.errorExistingOfGoods"));
        }
        if (!goodsDao.removeGoodsByGoodsIdAndUserId(goodsId, userId)) {
            throw new InternalServerException(
                    messageSourceService.getLocaleMessage("goods.errorRemovingFromBasket"));
        }
    }

    @Override
    public void removeGoodsByGoodsId(final Long goodsId) {
        if (!goodsDao.isExist(goodsId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.errorExistingOfGoods"));
        }
        if (!goodsDao.removeGoodsByGoodsId(goodsId)) {
            throw new InternalServerException(
                    messageSourceService.getLocaleMessage("goods.errorRemovingGoods"));
        }
    }

    @Override
    public void addGoodsToBasketByGoodsIdAndUserId(final Long goodsId, final Long userId) {
        if (!goodsDao.isExist(goodsId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.errorExistingOfGoods"));
        }
        if (goodsDao.isExistByGoodsIdAndUserId(goodsId, userId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.alreadyExistsInTheBasket"));
        }
        if (!goodsDao.addGoodsToBasketByGoodsIdAndUserId(goodsId, userId)) {
            throw new InternalServerException(
                    messageSourceService.getLocaleMessage("goods.errorAddingInToBasket"));
        }
    }

    @Override
    public void create(Goods goods) {
        goodsDao.create(goods);
    }

    @Override
    public Goods getGoodsByGoodsId(final Long goodsId) {
        if (!goodsDao.isExist(goodsId)) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.errorExistingOfGoods"));
        }
        return goodsDao.getGoodsByGoodsId(goodsId);
    }

    @Override
    public void update(final Goods goods) {
        if (!goodsDao.isExist(goods.getId())) {
            throw new ValidationException(
                    messageSourceService.getLocaleMessage("goods.errorExistingOfGoods"));
        }
        if (!goodsDao.update(goods)) {
            throw new InternalServerException(
                    messageSourceService.getLocaleMessage("goods.errorUpdatingGoods"));
        }
    }

    @Override
    public List<Integer> getPageNumbers() {
        int totalPages = (int) Math.ceil(goodsDao.getAmountOfGoods() /
                Integer.parseInt(messageSourceService.getLocaleMessage("page.size")));
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }
}
