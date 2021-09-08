package com.brewery.beerinventoryservice.services.listener;

import brewery.model.BeerOrderDto;
import brewery.model.events.AllocateOrderRequest;
import brewery.model.events.AllocateOrderResult;
import com.brewery.beerinventoryservice.config.RabbitConfig;
import com.brewery.beerinventoryservice.services.AllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final RabbitTemplate rabbitTemplate;
    private final AllocationService allocationService;


    @RabbitListener(queues = RabbitConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(final AllocateOrderRequest allocateOrderRequest) {

        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();

        BeerOrderDto beerOrderDto = allocateOrderRequest.getBeerOrderDto();

        Boolean allocationResult = allocationService.allocateOrder(beerOrderDto);

        builder.beerOrderDto(beerOrderDto)
                .allocationError(false);

        try {
            if (allocationResult) {
                builder.pendingInventory(false);
            } else
                builder.pendingInventory(true);
        } catch (Exception e) {
            builder.allocationError(true);
        }

        rabbitTemplate.convertAndSend(RabbitConfig.ALLOCATE_ORDER_RESULT_QUEUE, builder.build());


    }

}
