package ic.oss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Array 순서 거꾸로 뒤집기
     * @param array
     * @return array
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

    /**
     * Array -> List 변환
     * @param array
     * @return list
     */
    public static <T> List<T> arrayToList(T[] array) {
        return Arrays.asList(array);
    }

    /**
     * List -> Array 변환
     * List와 같은 타입의 Array 객체를 생성하여 같이 넣어줄 것
     * @param list, array
     * @return array
     */
    public static <T> T[] listToArray(List<T> list, T[] array) {
        return list.toArray(array);
    }

    /**
     * List -> Set 변환
     * @param list
     * @return set
     */
    public static <T> Set<T> listToSet(List<T> list) {
        return new HashSet<>(list);
    }

    /**
     * Set -> List
     * @param set
     * @return list
     */
    public static <T> List<T> setToList(Set<T> set) {
        return new ArrayList<>(set);
    }

    /**
     * Map 객체를 Json String으로 변환
     * @param map
     * @return String
     */
    public static String mapToJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }


    /**
     * json string을 map으로 변환
     * @param json string
     * @return Map<String,Object>
     */
    public static Map<String, Object> jsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * List 정렬
     * @param list
     * @return list
     */
    public static <T extends Comparable<T>> List<T> sortList(List<T> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    /**
     * List 내 중복 제거
     * @param list
     * @return list
     */
    // Remove Duplicates from List
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    /**
     * List 또는 Set의 toString
     * @param collection (List or Set)
     * @return String
     */
    // Custom toString for List or Set
    public static <T> String collectionToString(Collection<T> collection) {
        return collection.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
