package org.training.microservice.tc.msorder.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.training.microservice.tc.msorder.input.rest.models.OrderDto;
import org.training.microservice.tc.msorder.services.models.Order;

@Mapper
public interface IObjectMapper {

    IObjectMapper OBJECT_MAPPER = Mappers.getMapper(IObjectMapper.class);

    Order toOrder(OrderDto order);

    OrderDto toOrderDto(Order order);

}
