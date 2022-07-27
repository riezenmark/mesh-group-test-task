package ru.meshgroup.testtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.meshgroup.testtask.domain.Profile;
import ru.meshgroup.testtask.model.ProfileModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    Profile mapRestModelToProfile(ProfileModel profileModel);

    ProfileModel mapProfileToRestModel(Profile profile);
}
