package ic.oss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param array
     * @return
     * @param <T>
     */
    public static <T> T[] reverseArray(T[] array) {
        if (array == null || array.length <= 1) return array;

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left ++;
            right --;
        }

        return array;
    }

    // Array to List
    public static <T> List<T> arrayToList(T[] array) {
        return Arrays.asList(array);
    }

    // List to Array
    public static <T> T[] listToArray(List<T> list, T[] arrayType) {
        return list.toArray(arrayType);
    }

    // List to Set (중복 제거)
    public static <T> Set<T> listToSet(List<T> list) {
        return new HashSet<>(list);
    }

    // Set to List
    public static <T> List<T> setToList(Set<T> set) {
        return new ArrayList<>(set);
    }

    // Map to JSON String
    public static String mapToJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    // JSON String to Map
    public static Map<String, Object> jsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // Sort List
    public static <T extends Comparable<T>> List<T> sortList(List<T> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    // Remove Duplicates from List
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    // Custom toString for List or Set
    public static <T> String collectionToString(Collection<T> collection) {
        return collection.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
