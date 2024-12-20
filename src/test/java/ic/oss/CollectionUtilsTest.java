package ic.oss;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsTest {

    @Test
    public void testReverseArray() {
        String[] array = {"a", "b", "c", "d"};
        String[] reverseArray = {"d", "c", "b", "a"};
//        String[] reverseMethodArray = CollectionUtils.reverseArray(array);
        for (int i = 0; i < reverseArray.length; i++) {
            assertEquals(reverseArray[i], CollectionUtils.reverseArray(array)[i]);
//            assertEquals(reverseArray[i], reverseMethodArray[i]);
        }
    }

    @Test
    public void testArrayToList() {
        String[] array = {"a", "b", "c", "a"};
        List<String> list = CollectionUtils.arrayToList(array);
        assertEquals(4, list.size());
        assertEquals("a", list.get(0));
    }

    @Test
    public void testListToSet() {
        List<String> list = Arrays.asList("a", "b", "c", "a");
        Set<String> set = CollectionUtils.listToSet(list);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testSetToList() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        List<String> list = CollectionUtils.setToList(set);
        assertEquals(3, list.size());
    }

    @Test
    public void testSortList() {
        List<Integer> numbers = Arrays.asList(4, 2, 5, 1, 2);
        List<Integer> sortedNumbers = CollectionUtils.sortList(numbers);
        assertEquals(Arrays.asList(1, 2, 2, 4, 5), sortedNumbers);
    }

    @Test
    public void testRemoveDuplicates() {
        List<Integer> numbers = Arrays.asList(4, 2, 5, 1, 2);
        List<Integer> noDupList = CollectionUtils.removeDuplicates(numbers);
        assertEquals(Arrays.asList(4, 2, 5, 1), noDupList);
    }

    @Test
    public void testMapToJsonAndJsonToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", 30);

        String json = CollectionUtils.mapToJson(map);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"John\""));
        assertTrue(json.contains("\"age\":30"));

        Map<String, Object> newMap = CollectionUtils.jsonToMap(json);
        assertEquals("John", newMap.get("name"));
        assertEquals(30, newMap.get("age"));
    }

    @Test
    public void testCollectionToString() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = CollectionUtils.collectionToString(list);
        assertEquals("[a, b, c]", result);
    }
}