package org.wzl.videocenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class VideoCenterApplicationTests {

    @Test
    void contextLoads() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        System.out.println((List<Integer>)map.values());
        
    }

}
