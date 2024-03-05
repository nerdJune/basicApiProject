package io.jh.main.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public final class ConvertUtility {
    private static final ObjectMapper mapper =
            new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static Sort convertToPageRequestSort(final String sortStr) {
        if (sortStr == null || sortStr.isBlank()) return Sort.unsorted();

        String[] words = sortStr.split(",");
        List<Pair<String, String>> conditions = new ArrayList<Pair<String, String>>();
        for (int idx = 0; idx < words.length; idx += 2) {
            conditions.add(Pair.of(words[idx], words[idx + 1]));
        }
        List<Sort.Order> orders =
                conditions.stream()
                        .map(
                                condition ->
                                        new Sort.Order(
                                                Sort.Direction.fromString(condition.getSecond()),
                                                condition.getFirst()))
                        .collect(Collectors.toList());
        return Sort.by(orders);
    }

    public static <T> T readValue(String data, Class<T> type) {
        try {
            return mapper.readValue(data, type);
        } catch (Exception exception) {
            return null;
        }
    }

    public static <T> T convertValue(Object data, Class<T> type) {
        return mapper.convertValue(data, type);
    }

    public static <T> T convertValueWithTypeReference(Object data, TypeReference<T> typeReference) {
        return mapper.convertValue(data, typeReference);
    }
}
