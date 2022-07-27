package ru.meshgroup.testtask.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.testtask.model.view.PhoneView;

import javax.validation.constraints.Size;

@Getter
@Setter
public class PhoneModel {
    @JsonView(PhoneView.Response.class)
    private Long id;

    @JsonView({PhoneView.Request.class, PhoneView.Response.class})
    @Size(min = 5, max = 15, message = "Please, provide a correct phone.")
    private String value;

    @JsonView({PhoneView.Request.class, PhoneView.Response.class})
    private UserModel user;
}
