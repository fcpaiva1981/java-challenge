package br.com.fcpaiva.javachallenge.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class EntityOutputConvert {
    private static ObjectMapper instance = null;

    public static ObjectMapper getInstance() {
        if(instance == null){
            instance = new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        }
        return instance;
    }

    private EntityOutputConvert() {
    }

    public static <T, K> K convert(T source, Class<K> targetClass){
        ObjectMapper mapper = EntityOutputConvert.getInstance();
        return mapper.convertValue(source, targetClass);

    }

    public static <T, K> List<K> convertList(List<T> sourceList, Class<K> targetClass) {

        ObjectMapper mapper = EntityOutputConvert.getInstance();
        TypeReference<List<K>> typeReference = new TypeReference<List<K>>() {};
        return mapper.convertValue(sourceList, typeReference);
    }
}
