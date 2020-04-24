package com.epam.cdp.maksim.katuranau.module11.controller;

import com.epam.cdp.maksim.katuranau.module11.model.Goods;
import com.epam.cdp.maksim.katuranau.module11.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Goods controller.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final String COOKIE_NAME = "numberOfVisits";

    private final GoodsService goodsService;

    /**
     * Instantiates a new Goods controller.
     *
     * @param goodsService the goods service
     */
    @Autowired
    public GoodsController(
            final GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * Gets goods.
     *
     * @param pageIndex      the page index
     * @param sortIncreasing the sort increasing
     * @param numberOfVisits the number of visits
     * @param response       the response
     * @param model          the model
     * @return the goods
     */
    @GetMapping
    public String getGoods(@RequestParam(value = "pageIndex", defaultValue = "1") final int pageIndex,
                           @RequestParam(value = "sortIncreasing", defaultValue = "true") final boolean sortIncreasing,
                           @CookieValue(value = COOKIE_NAME, defaultValue = "0") final Integer numberOfVisits,
                           final HttpServletResponse response, final Model model) {
        model.addAttribute("sortIncreasing", sortIncreasing);
        model.addAttribute("currentPage", pageIndex);
        model.addAttribute("pageNumbers", goodsService.getPageNumbers());
        model.addAttribute("goods", goodsService.getGoods(pageIndex, sortIncreasing));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roleList = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        model.addAttribute("rolles", roleList);
        model.addAttribute("numberOfVisits", numberOfVisits + 1);
        response.addCookie(new Cookie(COOKIE_NAME, String.valueOf(numberOfVisits + 1)));
        return "goods";
    }

    /**
     * Find goods by name string.
     *
     * @param goodsName the goods name
     * @param model     the model
     * @return the string
     */
    @PostMapping("/find")
    public String findGoodsByName(@RequestParam final String goodsName, final Model model) {
        model.addAttribute("goods", goodsService.findGoodsByName(goodsName));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roleList = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        model.addAttribute("rolles", roleList);
        return "goods";
    }

    /**
     * Remove goods by goods id.
     *
     * @param goodsId the goods id
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeGoodsByGoodsId(@RequestParam final Long goodsId) {
        goodsService.removeGoodsByGoodsId(goodsId);
    }

    /**
     * Gets goods creation page.
     *
     * @param model the model
     * @return the goods creation page
     */
    @GetMapping(value = "/create")
    public String getGoodsCreationPage(final Model model) {
        model.addAttribute("goods", new Goods());
        return "goodsCreation";
    }

    /**
     * Create goods string.
     *
     * @param goods         the goods
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping(value = "/create")
    public String createGoods(@ModelAttribute @Valid final Goods goods, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "goodsCreation";
        }
        goodsService.create(goods);
        return "redirect:/goods";
    }

    /**
     * Gets goods update page.
     *
     * @param goodsId the goods id
     * @param model   the model
     * @return the goods update page
     */
    @GetMapping(value = "/update")
    public String getGoodsUpdatePage(
            @RequestParam(value = "goodsId") final Long goodsId, final Model model) {
        model.addAttribute("goods", goodsService.getGoodsByGoodsId(goodsId));
        return "goodsUpdate";
    }

    /**
     * Update goods.
     *
     * @param goods         the goods
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping(value = "/update")
    public String updateGoods(@ModelAttribute @Valid final Goods goods, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "goodsUpdate";
        }
        goodsService.update(goods);
        return "redirect:/goods";
    }
}
