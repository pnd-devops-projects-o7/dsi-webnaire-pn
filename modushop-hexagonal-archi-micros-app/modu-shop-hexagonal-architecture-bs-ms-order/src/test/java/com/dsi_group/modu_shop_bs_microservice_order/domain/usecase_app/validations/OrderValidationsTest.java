package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.validations;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLineRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteProductService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.mapper.BusinessOrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OrderValidationsTest {

    @Mock
    private OutputOrderService outputOrderService;

    @Mock
    private OutputRemoteUserService outputRemoteUserService;

    @Mock
    private BusinessOrderMapper businessOrderMapper;

    @Mock
    private OutputRemoteProductService outputRemoteProductService;

    private OrderValidations orderValidations;

    @BeforeEach
    void setUp() {
        orderValidations = new OrderValidations(outputOrderService,
                outputRemoteUserService, businessOrderMapper, outputRemoteProductService);
    }

    @Test
    void validateOrder() {
        // Prepare
        int userId = 42;
        BusinessRemoteUser remoteUser = new BusinessRemoteUser(userId, "Jean","Paul",
                "jeanpaul@gmail.com", "12345");

        BusinessOrderRequest request = new BusinessOrderRequest(userId, Set.of(
                new BusinessOrderLineRequest(1,2),new BusinessOrderLineRequest(3,4)
        ));

        Mockito.when(outputRemoteUserService.getBusinessRemoteUserById(userId)).thenReturn(remoteUser);
        Mockito.when(businessOrderMapper.toBusinessOrder(request)).thenReturn(Mockito.mock(BusinessOrder.class));
        Mockito.when(outputOrderService.createOrder(Mockito.any())).thenReturn(Mockito.mock(BusinessOrder.class));

        // Execute
        BusinessOrder result = orderValidations.validateOrder(request);

        // Verify
        Assertions.assertAll("validateOrder test", () -> {
           Assertions.assertNotNull(result);

            Mockito.verify(outputRemoteUserService).getBusinessRemoteUserById(userId);
            Mockito.verify(businessOrderMapper).toBusinessOrder(request);
            Mockito.verify(outputOrderService).createOrder(Mockito.any());
        });
    }

    @Test
    void validateOrder_shouldThrowException_whenRemoteUserIsNull() {
        // Given
        int userId = 77;
        BusinessOrderRequest request = new BusinessOrderRequest(userId,null);

        Mockito.when(outputRemoteUserService.getBusinessRemoteUserById(userId)).thenReturn(null);

        // Then
        RuntimeException runtimeException =  Assertions.assertThrows(RuntimeException.class, () ->
                orderValidations.validateOrder(request));
        Assertions.assertAll("validateOrder test", () -> {
            Assertions.assertNotNull(runtimeException);
            Mockito.verify(outputRemoteUserService).getBusinessRemoteUserById(userId);
        });
    }
}