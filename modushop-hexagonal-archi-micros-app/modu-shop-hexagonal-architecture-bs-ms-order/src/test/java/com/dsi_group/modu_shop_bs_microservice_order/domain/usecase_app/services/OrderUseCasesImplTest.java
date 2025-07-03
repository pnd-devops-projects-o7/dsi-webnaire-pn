package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLineRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.input.InputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.validations.OrderValidations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class OrderUseCasesImplTest {
    @Mock
    private OrderValidations orderValidations;

    @Mock
    private OutputRemoteUserService outputRemoteUserService;

    @Mock
    private OutputOrderService outputOrderService;

    private InputOrderService inputOrderService;

    @BeforeEach
    void setUp() {
        inputOrderService = new OrderUseCasesImpl(orderValidations, outputRemoteUserService, outputOrderService);
    }

    @Test
    void createOrderTest() {
        // Prepare
        BusinessOrderRequest request = new BusinessOrderRequest(1,
                Set.of(Mockito.mock(BusinessOrderLineRequest.class)));
        BusinessOrder expectedOrder = Mockito.mock(BusinessOrder.class);
        Mockito.when(orderValidations.validateOrder(request)).thenReturn(expectedOrder);

        // Execute
        BusinessOrder result = inputOrderService.createOrder(request);

        // Verify
        Assertions.assertAll("create order test", () -> {
            Assertions.assertNotNull(result);
            Mockito.verify(orderValidations, Mockito.times(1))
                    .validateOrder(request);
        });
    }

    @Test
    void getAllOrders() {
        // Prepare
        BusinessOrder order = Mockito.mock(BusinessOrder.class);
        order.setUserId(1);
        Mockito.when(outputOrderService.getAllOrders()).thenReturn(List.of(order));

        // Execute
        Collection<BusinessOrder> result = inputOrderService.getAllOrders();

        // Verify
        Assertions.assertAll("getAllOrders test", () -> {
            Assertions.assertNotNull(result);
            Mockito.verify(outputOrderService, Mockito.atLeast(1)).getAllOrders();
        });
    }

    @Test
    void getAllOrdersByClientId() {
        // Prepare
        int userId = 7;
        BusinessRemoteUser remoteUser = Mockito.mock(BusinessRemoteUser.class);
        BusinessOrder order = Mockito.mock(BusinessOrder.class);
        order.setUserId(userId);

        // Execute
        Mockito.when(outputRemoteUserService.getBusinessRemoteUserById(userId)).thenReturn(remoteUser);
        Mockito.when(outputOrderService.getAllOrdersByClientId(userId)).thenReturn(List.of(order));
        Collection<BusinessOrder> result = inputOrderService.getAllOrdersByClientId(userId);

        // Verify
        Assertions.assertAll("getAllOrdersByClientId test", () -> {
            Assertions.assertNotNull(result);
            Mockito.verify(outputRemoteUserService, Mockito.times(1))
                    .getBusinessRemoteUserById(userId);
            Mockito.verify(outputRemoteUserService).getBusinessRemoteUserById(userId);
            Mockito.verify(outputOrderService).getAllOrdersByClientId(userId);
            Mockito.verify(outputRemoteUserService, Mockito.atLeastOnce())
                    .getBusinessRemoteUserById(userId);
        });
    }

    @Test
    void getAllOrdersByClientIdTestShouldThrowExceptionWhenClientIdIsNull() {
        // Prepare
        int unknownUserId = 42;
        //Execute
        Mockito.when(outputRemoteUserService.getBusinessRemoteUserById(unknownUserId))
                .thenReturn(null);
        RuntimeException exception = Assertions.assertThrows(RemoteServiceUnreachableException.class, () -> {
            inputOrderService.getAllOrdersByClientId(unknownUserId);
        });
        // Verify
        Assertions.assertAll("getAllOrdersByClientId test", () -> {
            Assertions.assertNotNull(exception);
            Assertions.assertTrue(exception.getMessage().contains("Remote user not found"));
            Mockito.verify(outputRemoteUserService, Mockito.atLeastOnce()).getBusinessRemoteUserById(unknownUserId);
        });
    }
}