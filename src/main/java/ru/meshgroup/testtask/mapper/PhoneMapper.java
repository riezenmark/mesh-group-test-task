package ru.meshgroup.testtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.meshgroup.testtask.domain.Phone;
import ru.meshgroup.testtask.model.PhoneModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {
    Phone mapRestModelToPhone(PhoneModel phoneModel);

    PhoneModel mapPhoneToRestModel(Phone phone);
}
