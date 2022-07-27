package ru.meshgroup.testtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.meshgroup.testtask.domain.User;
import ru.meshgroup.testtask.model.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User mapRestModelToUser(UserModel userModel);

    UserModel mapUserToRestModel(User user);
}
