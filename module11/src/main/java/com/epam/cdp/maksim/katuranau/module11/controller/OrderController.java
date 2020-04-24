package com.epam.cdp.maksim.katuranau.module11.controller;

import com.epam.cdp.maksim.katuranau.module11.service.OrderService;
import com.epam.cdp.maksim.katuranau.module11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * The type Order controller.
 */
@Controller
@Validated
@RequestMapping("/order")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    /**
     * Instantiates a new Order controller.
     *
     * @param userService  the user service
     * @param orderService the order service
     */
    @Autowired
    public OrderController(final UserService userService, final OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * Gets orders.
     *
     * @param model the model
     * @return the orders
     */
    @GetMapping
    public String getOrders(final Model model) {
        model.addAttribute("listOrders", orderService.getListOrder(getUserId()));
        return "orders";
    }

    /**
     * Submit order string.
     *
     * @return the string
     */
    @GetMapping("/submit")
    public String submitOrder() {
        orderService.submitOrder(getUserId());
        return "redirect:/order";
    }

    /**
     * Update order status string.
     *
     * @param orderId the order id
     * @param model   the model
     * @return the string
     */
    @GetMapping("/{orderId}/update")
    public String updateOrderStatus(@PathVariable @Min(value = 0, message = "order id should not be negative") final Long orderId, final Model model) {
        model.addAttribute("order", orderService.getOrderById(orderId));
        model.addAttribute("statusList", orderService.getListStatus());
        return "orderUpdate";
    }

    /**
     * Update status submit string.
     *
     * @param orderId  the order id
     * @param statusId the status id
     * @return the string
     */
    @PostMapping("/update")
    public String updateStatusSubmit(
            @RequestParam @Min(value = 0, message = "order id should not be negative") final Long orderId,
            @RequestParam @Min(value = 0, message = "status id should not be negative") final Long statusId) {
        orderService.updateOrderStatus(orderId, statusId);
        return "redirect:/order";
    }

    /**
     * Delete order by id.
     *
     * @param orderId the order id
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(
            @RequestParam @Min(value = 0, message = "order id should not be negative") final Long orderId) {
        orderService.removeOrder(orderId);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserIdByLogin(authentication.getName());
    }
}
