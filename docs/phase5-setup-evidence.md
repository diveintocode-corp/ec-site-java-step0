# Phase 5 Setup Evidence

**Branch:** `feature/phase5-setup`
**Date:** 2026-06-08
**Base commit:** `94d0586` (latest `main`)
**Implementer:** cedrick012

---

## 1. Build Verification

```
./mvnw clean verify --batch-mode --no-transfer-progress
```

| Item | Result |
|---|---|
| Compilation | PASS |
| Tests run | 1 |
| Failures | 0 |
| Errors | 0 |
| BUILD | **SUCCESS** |
| Total time | ~27 s |

---

## 2. Baseline File Inventory

### Product

| Area | File | Exists? | Key methods / handlers | Related degradation IDs | Notes |
|---|---|---|---|---|---|
| Product Service | `src/main/java/com/example/ecsite/service/ProductService.java` | ✅ | `calcPriceIn()` L47, `getActiveProductViewModels()` L54, `getActiveProductDetailViewModel()` L77, `createProduct()` L110, `updateProduct()` L134, `softDelete()` L177, `findAll()` L95, `findById()` L102, `countImages()` L191, `findImagesByProductId()` L184, `getTaxRate()` L195 | P-001 – P-008 | Tax rate from `app.tax-rate` property (default 0.1) |
| Product Controller | `src/main/java/com/example/ecsite/controller/ProductController.java` | ✅ | `productList()` L34 `GET /products`, `productDetail()` L45 `GET /products/{id}` | P-001 – P-008 | — |
| Admin Product Controller | `src/main/java/com/example/ecsite/controller/admin/AdminProductController.java` | ✅ | `productList()` L44, `productDetail()` L52, `createForm()` L64, `createProduct()` L75, `editForm()` L103, `updateProduct()` L125, `deleteConfirm()` L195, `deleteProduct()` L207 | P-001 – P-008 | Max 5 images per product enforced in `updateProduct()` |
| Product list template | `src/main/resources/templates/products/list.html` | ✅ | — | T-001 – T-005 | — |
| Product detail template | `src/main/resources/templates/products/detail.html` | ✅ | — | T-001 – T-005 | — |
| Admin product templates | `src/main/resources/templates/admin/products/{list,detail,form,confirm-delete}.html` | ✅ | — | T-001 – T-005 | — |

### Cart

| Area | File | Exists? | Key methods / handlers | Related degradation IDs | Notes |
|---|---|---|---|---|---|
| Cart Service | `src/main/java/com/example/ecsite/service/CartService.java` | ✅ | `getOrCreateCart()` L48, `getCartViewModel()` L61, `addToCart()` L111, `deleteCartItem()` L134, `clearCart()` L149, `getCartItems()` L159, `getTaxRate()` L166 | C-001 – C-007 | `deleteCartItem()` verifies ownership before delete |
| Cart Controller | `src/main/java/com/example/ecsite/controller/CartController.java` | ✅ | `cartList()` L37 `GET /cart`, `addToCart()` L50 `POST /cart/add` (AJAX, `@ResponseBody`), `deleteCartItem()` L71 `POST /cart/items/{id}/delete` | C-001 – C-007 | `addToCart` returns JSON `{success, message, quantity}` |
| Cart template | `src/main/resources/templates/cart/list.html` | ✅ | — | T-001 – T-005 | — |

### Order

| Area | File | Exists? | Key methods / handlers | Related degradation IDs | Notes |
|---|---|---|---|---|---|
| Order Service | `src/main/java/com/example/ecsite/service/OrderService.java` | ✅ | `getOrderConfirmViewModel()` L60, `completeOrder()` L108, `getOrderHistory()` L181, `cancelOrder()` L221, `findAll()` L235, `findById()` L242, `findOrderItems()` L249, `findOrderItemViewModels()` L256, `updateOrder()` L276, `getTotalShipped()` L283, `buildFullAddress()` L288 | O-001 – O-006 | `orderNo` = UUID 12-char uppercase; cancel only if `status == "pending"` |
| Order Controller | `src/main/java/com/example/ecsite/controller/OrderController.java` | ✅ | `orderConfirm()` L42 `GET /order/confirm`, `orderComplete()` L58 `POST /order/complete` | O-001 – O-006 | Redirects to `/profile/edit` if no profile; to `/cart` if cart empty |
| Order History Controller | `src/main/java/com/example/ecsite/controller/OrderHistoryController.java` | ✅ | `orderHistory()` L34 `GET /order-history`, `cancelOrder()` L46 `POST /order-history/{id}/cancel` | O-001 – O-006 | — |
| Admin Order Controller | `src/main/java/com/example/ecsite/controller/admin/AdminOrderController.java` | ✅ | `orderList()` L34, `orderDetail()` L42, `editForm()` L53, `updateOrder()` L73 | O-001 – O-006 | — |
| Order confirm template | `src/main/resources/templates/order/confirm.html` | ✅ | — | T-001 – T-005 | — |
| Order complete template | `src/main/resources/templates/order/complete.html` | ✅ | — | T-001 – T-005 | — |
| Order history template | `src/main/resources/templates/order-history/list.html` | ✅ | — | T-001 – T-005 | — |
| Admin order templates | `src/main/resources/templates/admin/orders/{list,detail,form}.html` | ✅ | — | T-001 – T-005 | — |

### Profile

| Area | File | Exists? | Key methods / handlers | Related degradation IDs | Notes |
|---|---|---|---|---|---|
| Profile Service | `src/main/java/com/example/ecsite/service/ProfileService.java` | ✅ | `findByUserId()` L25, `saveProfile()` L33, `updateProfile()` L60 | PR-001 – PR-004 | `saveProfile()` upserts (insert if not exists, update if exists) |
| Profile Controller | `src/main/java/com/example/ecsite/controller/ProfileController.java` | ✅ | `editProfile()` L39 `GET /profile/edit`, `saveProfile()` L60 `POST /profile/edit` | PR-001 – PR-004 | Redirects to `/products` on success; `PREFECTURE_LIST` constant (47 prefectures) |
| Profile Form | `src/main/java/com/example/ecsite/form/ProfileForm.java` | ✅ | Fields: `name` (`@NotBlank @Size(max=64)`), `postalCode` (`@Pattern(\\d{7})`), `prefecture` (`@NotBlank`), `address1` (`@NotBlank @Size(max=128)`), `address2` (`@Size(max=128)` optional), `telno` (`@Pattern(\\d{1,11})`) | PR-001 – PR-004 | — |
| Profile template | `src/main/resources/templates/profile/edit.html` | ✅ | — | T-001 – T-005 | — |

### Common / Safety

| Item | Status | Notes |
|---|---|---|
| No compile errors | ✅ | Build passes clean |
| No test failures | ✅ | 1/1 tests pass |
| No behavior changes | ✅ | No code modified |
| No refactoring | ✅ | No code modified |
| Tax calculation intact | ✅ | `calcPriceIn()` uses `BigDecimal.multiply(1.0 + taxRate).intValue()` |
| Order total intact | ✅ | `subtotal + (int)(subtotal * taxRate)` |
| Ownership checks intact | ✅ | `deleteCartItem()` verifies `cart.userId == currentUser` |
| Cancel guard intact | ✅ | `cancelOrder()` only allows `status == "pending"` |
| Security config unchanged | ✅ | Dual filter chains untouched |
| No secrets committed | ✅ | — |

---

## 3. Degradation ID → Target Map

| ID Group | Count | Target area |
|---|---|---|
| P-001 – P-008 | 8 | `ProductService`, `ProductController`, `AdminProductController`, product templates |
| C-001 – C-007 | 7 | `CartService`, `CartController`, cart template |
| O-001 – O-006 | 6 | `OrderService`, `OrderController`, `OrderHistoryController`, `AdminOrderController`, order templates |
| PR-001 – PR-004 | 4 | `ProfileService`, `ProfileController`, `ProfileForm`, profile template |
| T-001 – T-005 | 5 | Thymeleaf templates across all areas |
| COMMON-001 – COMMON-012 | 12 | Cross-cutting safety constraints (no compile errors, no behavior change, etc.) |
| **Total** | **42** | — |

---

## 4. Startup Verification

Application starts successfully with default (H2) profile:

```
./mvnw spring-boot:run
```

Expected log lines confirming startup:
```
HikariPool-1 - Added connection conn0: url=jdbc:h2:file:./data/ecsitedb ...
Tomcat started on port 8080 (http) with context path '/'
Started EcSiteApplication in X.XXX seconds
```

H2 console available at: `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:file:./data/ecsitedb`

---

## 5. Baseline Flow Checklist (Build + Code Inspection Only)

> **Note:** Status "Build-verified" means the route handler compiles and loads into the Spring context. HTTP-level execution was not performed at setup time.

| # | Flow | Expected result | Status |
|---|---|---|---|
| 1 | `GET /products` | Product list renders (empty if no seed data) | ✅ Build-verified |
| 2 | `GET /products/{id}` | Product detail renders; 404 if deleted/missing | ✅ Build-verified |
| 3 | `POST /cart/add` (AJAX) | Returns `{success:true, message:"カートに追加しました。", quantity:N}` | ✅ Build-verified |
| 4 | `GET /cart` | Cart view renders with items, totalEx, totalIn, taxRate | ✅ Build-verified |
| 5 | `POST /cart/items/{id}/delete` | Removes item; redirects to `/cart` | ✅ Build-verified |
| 6 | `GET /order/confirm` | Shows confirm view; redirects to `/profile/edit` if no profile | ✅ Build-verified |
| 7 | `POST /order/complete` | Creates order record + order items; clears cart; shows complete view | ✅ Build-verified |
| 8 | `GET /profile/edit` | Shows edit form with existing values prefilled if profile exists | ✅ Build-verified |
| 9 | `POST /profile/edit` | Upserts profile; redirects to `/products` on success | ✅ Build-verified |

---

## 6. Discrepancies from Instructor Guide

Three areas where the current codebase diverges from the guide's assumed "standard state."

### O-002: `completeOrder()` already a massive method

**Guide's standard state:** *"order registration, order details generation, cart contents retrieval, and cart deletion are organized into certain units"*

**Actual state:** `completeOrder()` (L108–L176, 69 lines) already does all of the following in a single method with no private method extraction:
- Subtotal calculation (loop over cart items)
- Order record creation
- Order items creation (second loop)
- Cart clearing
- Completion ViewModel generation

The guide classifies O-002 as "Additional deterioration," but the baseline already resembles the intended degraded state. Phase 5 will need to make it **further** unreadable (e.g., deepen nesting, lengthen expressions) rather than collapsing separate methods into one.

---

### O-003 + C-003: Abbreviated names already present

**Guide's classification:** Both O-003 and C-003 are classified as "Additional deterioration" — rename domain-specific variables to vague names.

**Actual state:** The codebase already uses abbreviated names the guide expects to introduce:

| Variable | File | Guide expectation | Reality |
|---|---|---|---|
| `ci` (CartItem loop var) | `CartService.getCartViewModel()`, `OrderService.completeOrder()` | Classified as new degradation | Already present |
| `oi` (OrderItem loop var) | `OrderService.completeOrder()`, `getOrderHistory()`, `findOrderItemViewModels()` | Classified as new degradation | Already present |
| `vm` (ViewModel local var) | `CartService`, `OrderService` (multiple methods) | Classified as new degradation in C-003 | Already present (same pattern flagged as existing in P-001) |

Phase 5 for O-003 and C-003 should focus on the **remaining** clear names (`cartItems`, `orderItems`, `product`, `subtotal`, `totalEx`, `totalIn`) rather than names already abbreviated.

---

### Inconsistency in `vm` classification

The guide flags `vm` in `ProductService.getActiveProductViewModels()` as an **existing improvement target** (P-001), but treats the same `vm` pattern in `CartService` and `OrderService` as **additional deterioration** (C-003, O-003). In practice, `vm` is already present across all three services and should be treated uniformly as an existing improvement target.

---

## 7. Blockers for Phase 5 Implementation

No hard blockers. One item requires attention before implementing O-002:

- **O-002 starting point:** `completeOrder()` is already a single large method. Phase 5 implementer should not attempt to first "organize into units" and then re-degrade — apply degradation directly to the existing method (deeper nesting, longer expressions, merged loops where possible) without restructuring first.

All other targets are clean and ready for Phase 5 degradation as specified.
