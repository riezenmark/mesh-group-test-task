package ru.meshgroup.testtask.service.tool;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StandardMapper<T> {
    public T copyFieldsIgnoringId(T source, T target) {
        BeanUtils.copyProperties(source, target, "id");
        return target;
    }
}
