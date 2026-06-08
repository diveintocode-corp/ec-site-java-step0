package com.example.ecsite.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecsite.entity.Cart;
import com.example.ecsite.entity.CartItem;
import com.example.ecsite.entity.Order;
import com.example.ecsite.entity.OrderItem;
import com.example.ecsite.entity.Product;
import com.example.ecsite.entity.ProductImage;
import com.example.ecsite.entity.Profile;
import com.example.ecsite.repository.CartItemRepository;
import com.example.ecsite.repository.CartRepository;
import com.example.ecsite.repository.OrderItemRepository;
import com.example.ecsite.repository.OrderRepository;
import com.example.ecsite.repository.ProductImageRepository;
import com.example.ecsite.repository.ProductRepository;
import com.example.ecsite.viewmodel.OrderCompleteViewModel;
import com.example.ecsite.viewmodel.OrderConfirmViewModel;
import com.example.ecsite.viewmodel.OrderHistoryItemViewModel;
import com.example.ecsite.viewmodel.OrderItemViewModel;

/**
 * 注文関連のビジネスロジックを提供するサービスクラス。
 */
@Service
public class OrderService {

    @Value("${app.tax-rate:0.1}")
    private double taxRate;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
            ProductRepository productRepository, ProductImageRepository productImageRepository,
            CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * 注文確認画面用のデータを取得する。
     */
    public OrderConfirmViewModel getOrderConfirmViewModel(Long userId, Profile profile) {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartItem> list = (cart != null)
                ? cartItemRepository.findByCartId(cart.getCartId())
                : List.of();

        List<OrderItemViewModel> data = new ArrayList<>();
        int subtotal = 0;
        for (CartItem ci : list) {
            Product p = productRepository.findById(ci.getProductId());
            if (p == null)
                continue;
            List<ProductImage> images = productImageRepository.findByProductId(p.getProductId());
            String url = images.isEmpty() ? null : images.get(0).getImagePath();
            int priceEx = p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())).intValue();
            int priceIn = p.getPrice()
                    .multiply(BigDecimal.valueOf(1.0 + taxRate))
                    .multiply(BigDecimal.valueOf(ci.getQuantity()))
                    .intValue();
            OrderItemViewModel vm = new OrderItemViewModel();
            vm.setProductName(p.getName());
            vm.setImageUrl(url);
            vm.setQuantity(ci.getQuantity());
            vm.setUnitPrice(p.getPrice().intValue());
            vm.setPriceIn(priceIn);
            data.add(vm);
            subtotal += priceEx;
        }

        int tax = (int) (subtotal * taxRate);
        int total = subtotal + tax;
        String address = buildFullAddress(profile.getPrefecture(), profile.getAddress1(), profile.getAddress2());

        OrderConfirmViewModel vm = new OrderConfirmViewModel();
        vm.setProfile(profile);
        vm.setAddress(address);
        vm.setOrderItems(data);
        vm.setSubtotal(subtotal);
        vm.setTax(tax);
        vm.setTotal(total);
        vm.setTaxRate((int) (taxRate * 100));
        return vm;
    }

    /**
     * 注文を確定する。カートを空にして注文完了用データを返す。
     */
    @Transactional
    public OrderCompleteViewModel completeOrder(Long userId, Profile profile) {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartItem> list = (cart != null)
                ? cartItemRepository.findByCartId(cart.getCartId())
                : List.of();
        if (!list.isEmpty()) {
            int subtotal = 0;
            for (CartItem ci : list) {
                Product p = productRepository.findById(ci.getProductId());
                if (p != null) {
                    subtotal += p.getPrice().intValue() * ci.getQuantity();
                }
            }
            int tax = (int) (subtotal * taxRate);
            int total = subtotal + tax;

            String orderNo = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
            Order order = new Order();
            order.setUserId(userId);
            order.setOrderNo(orderNo);
            order.setShipName(profile.getName());
            order.setShipPostalCode(profile.getPostalCode());
            order.setShipPrefecture(profile.getPrefecture());
            order.setShipAddress1(profile.getAddress1());
            order.setShipAddress2(profile.getAddress2());
            order.setShipTelno(profile.getTelno());
            order.setSubtotalPrice(subtotal);
            order.setTax(tax);
            order.setTotalPrice(total);
            order.setStatus("pending");
            orderRepository.insert(order);

            List<OrderItemViewModel> data = new ArrayList<>();
            for (CartItem ci : list) {
                Product p = productRepository.findById(ci.getProductId());
                if (p == null)
                    continue;
                OrderItem oi = new OrderItem();
                oi.setOrderId(order.getOrderId());
                oi.setProductId(p.getProductId());
                oi.setUnitPrice(p.getPrice().intValue());
                oi.setQuantity(ci.getQuantity());
                orderItemRepository.insert(oi);

                int priceIn = (int) (oi.getUnitPrice() * (1 + taxRate));
                OrderItemViewModel vm = new OrderItemViewModel();
                vm.setProductName(p.getName());
                vm.setQuantity(ci.getQuantity());
                vm.setUnitPrice(oi.getUnitPrice());
                vm.setPriceIn(priceIn);
                data.add(vm);
            }

            cartItemRepository.deleteByCartId(cart.getCartId());

            String address = buildFullAddress(profile.getPrefecture(), profile.getAddress1(), profile.getAddress2());
            OrderCompleteViewModel vm = new OrderCompleteViewModel();
            vm.setOrder(order);
            vm.setAddress(address);
            vm.setOrderItems(data);
            vm.setTaxRate((int) (taxRate * 100));
            return vm;
        } else {
            return null;
        }
    }

    /**
     * ユーザーの注文履歴を取得する。
     */
    public List<OrderHistoryItemViewModel> getOrderHistory(Long userId) {
        List<Order> list = orderRepository.findByUserId(userId);
        List<OrderHistoryItemViewModel> result = new ArrayList<>();
        for (Order order : list) {
            List<OrderItem> data = orderItemRepository.findByOrderId(order.getOrderId());
            List<OrderItemViewModel> vms = new ArrayList<>();
            for (OrderItem oi : data) {
                Product p = productRepository.findById(oi.getProductId());
                List<ProductImage> images = p != null
                        ? productImageRepository.findByProductId(p.getProductId())
                        : List.of();
                String url = images.isEmpty() ? null : images.get(0).getImagePath();
                int priceIn = (int) (oi.getUnitPrice() * (1 + taxRate));
                OrderItemViewModel vm = new OrderItemViewModel();
                vm.setProductName(p != null ? p.getName() : "（削除済み商品）");
                vm.setImageUrl(url);
                vm.setUnitPrice(oi.getUnitPrice());
                vm.setQuantity(oi.getQuantity());
                vm.setPriceIn(priceIn);
                vms.add(vm);
            }
            OrderHistoryItemViewModel hvm = new OrderHistoryItemViewModel();
            hvm.setOrderId(order.getOrderId());
            hvm.setOrderNo(order.getOrderNo());
            hvm.setStatusDisplay(order.getStatusDisplay());
            hvm.setCreatedAt(order.getCreatedAt());
            hvm.setItems(vms);
            hvm.setSubtotal(order.getSubtotalPrice());
            hvm.setTax(order.getTax());
            hvm.setTotal(order.getTotalPrice());
            hvm.setCanCancel("pending".equals(order.getStatus()));
            result.add(hvm);
        }
        return result;
    }

    /**
     * 注文をキャンセルする（pending の場合のみ）。
     */
    @Transactional
    public boolean cancelOrder(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            if (order.getUserId().equals(userId)) {
                if ("pending".equals(order.getStatus())) {
                    order.setStatus("canceled");
                    orderRepository.update(order);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 全注文一覧を取得する（管理者用）。
     */
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * 注文をIDで取得する（管理者用）。
     */
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    /**
     * 注文の注文アイテム一覧を取得する（管理者用）。
     */
    public List<OrderItem> findOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    /**
     * 注文アイテムを ViewModel のリストで取得する（管理者用）。
     */
    public List<OrderItemViewModel> findOrderItemViewModels(Long orderId) {
        List<OrderItem> list = orderItemRepository.findByOrderId(orderId);
        List<OrderItemViewModel> result = new ArrayList<>();
        for (OrderItem oi : list) {
            Product p = productRepository.findById(oi.getProductId());
            int priceIn = (int) (oi.getUnitPrice() * (1 + taxRate));
            OrderItemViewModel vm = new OrderItemViewModel();
            vm.setProductName(p != null ? p.getName() : "（削除済み商品）");
            vm.setQuantity(oi.getQuantity());
            vm.setUnitPrice(oi.getUnitPrice());
            vm.setPriceIn(priceIn);
            result.add(vm);
        }
        return result;
    }

    /**
     * 注文を更新する（管理者用）。
     */
    @Transactional
    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    /**
     * ユーザーの出荷済み合計金額を取得する（管理者用）。
     */
    public int getTotalShipped(Long userId) {
        Integer total = orderRepository.sumTotalPriceShippedByUserId(userId);
        return total != null ? total : 0;
    }

    private String buildFullAddress(String prefecture, String address1, String address2) {
        return prefecture + address1 + (address2 != null ? address2 : "");
    }
}
