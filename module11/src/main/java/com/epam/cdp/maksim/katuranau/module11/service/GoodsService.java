package com.epam.cdp.maksim.katuranau.module11.service;

import com.epam.cdp.maksim.katuranau.module11.model.Goods;

import java.util.List;

/**
 * The interface Goods service.
 */
public interface GoodsService {

    /**
     * Gets all goods.
     *
     * @param pageIndex      the page index
     * @param sortIncreasing is sort increasing
     * @return the all goods
     */
    List<Goods> getGoods(int pageIndex, boolean sortIncreasing);

    /**
     * Find goods by name list.
     *
     * @param goodsName the goods name
     * @return the list
     */
    List<Goods> findGoodsByName(String goodsName);

    /**
     * Gets all goods by user id.
     *
     * @param userId the user id
     * @return the all goods by user id
     */
    List<Goods> getAllGoodsByUserId(Long userId);

    /**
     * Remove goods by goods id and user id.
     *
     * @param goodsId the goods id
     * @param userId  the user id
     */
    void removeGoodsByGoodsIdAndUserId(Long goodsId, Long userId);

    /**
     * Remove goods by goods id.
     *
     * @param goodsId the goods id
     */
    void removeGoodsByGoodsId(Long goodsId);

    /**
     * Add goods to basket by goods id and user id.
     *
     * @param goodsId the goods id
     * @param userId  the user id
     */
    void addGoodsToBasketByGoodsIdAndUserId(Long goodsId, Long userId);

    /**
     * Create.
     *
     * @param goods the goods
     */
    void create(Goods goods);

    /**
     * Gets goods by goods id.
     *
     * @param goodsId the goods id
     * @return the goods by goods id
     */
    Goods getGoodsByGoodsId(Long goodsId);

    /**
     * Update.
     *
     * @param goods the goods
     */
    void update(Goods goods);

    /**
     * Gets page numbers.
     *
     * @return the page numbers
     */
    List<Integer> getPageNumbers();
}
