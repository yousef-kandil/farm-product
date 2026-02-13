package com.mazra3ty.store.utils.shared;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ObjectMapperUtils {

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true); // مهم في الـ update ويعتبر أفضل Practice
    }

    private ObjectMapperUtils() {
        // Utility class — prevent instantiation
    }

    // تحويل من DTO -> Entity أو العكس
    public static <D, T> D map(final T source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    // Update على Object موجود بدون ما يلغي قيم non-null
    public static void map(Object source, Object destination) {
        modelMapper.map(source, destination);
    }

    // يعمل mapping ل List كاملة
    public static <D, T> List<D> mapAll(final Collection<T> sourceList, Class<D> destinationClass) {
        return sourceList.stream()
                .map(entity -> map(entity, destinationClass))
                .toList();
    }
}
