package ru.meshgroup.testtask.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.testtask.model.view.ProfileView;

import javax.validation.constraints.Digits;

@Getter
@Setter
public class ProfileModel {
    @JsonView(ProfileView.Response.class)
    private Long id;

    @JsonView({ProfileView.Request.class, ProfileView.Response.class})
    @Digits(integer = 21, fraction = 2, message = "Please, provide a valid cash amount.")
    private Double cash;

    @JsonView({ProfileView.Request.class, ProfileView.Response.class})
    private UserModel user;
}
