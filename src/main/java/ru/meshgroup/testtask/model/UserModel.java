package ru.meshgroup.testtask.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.testtask.model.view.PhoneView;
import ru.meshgroup.testtask.model.view.ProfileView;
import ru.meshgroup.testtask.model.view.UserView;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserModel {
    @JsonView({
            UserView.Response.class,
            ProfileView.Request.class, ProfileView.Response.class,
            PhoneView.Request.class, PhoneView.Response.class
    })
    private Long id;

    @JsonView({UserView.Request.class, UserView.Response.class})
    @NotBlank(message = "Please, specify a name.")
    private String name;

    @JsonView({UserView.Request.class, UserView.Response.class})
    @Min(value = 1, message = "Please, specify a correct age.")
    @Max(value = 255, message = "Please, specify a correct age.")
    private Integer age;

    @JsonView({UserView.Request.class, UserView.Response.class})
    @Email(message = "Please provide a valid email address.")
    @NotBlank(message = "Please, provide an email.")
    private String email;
}
