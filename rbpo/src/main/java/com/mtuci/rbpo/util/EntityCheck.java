package com.mtuci.rbpo.util;

import java.util.List;
import java.util.Optional;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class EntityCheck {
    
    public static <T> T getEntityOrThrowOptional(Optional<T> optionalEntity, String errorMessage) {
        return optionalEntity.orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    public static <T> T getEntityOrThrowList(List<T> listEntity, String errorMesssage){
        if(listEntity.isEmpty()){
            throw new IllegalArgumentException(errorMesssage);
        }
        return listEntity.get(0);
    }
}
