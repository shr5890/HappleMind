package framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {
public static void main(String[] args) {
    Map<Integer,Demo> map=new HashMap<Integer, Demo>();
    Demo d1= new Demo(1,"Test Case 1","Test Scenario 1",true);
    Demo d2= new Demo(1,"Test Case 2","Test Scenario 2",true);
    Demo d3= new Demo(1,"Test Case 3","Test Scenario 3",true);
    Demo d4= new Demo(1,"Test Case 4","Test Scenario 4",true);
    //adding values to map
    map.put(d1.getScenarioNo(), d1);
    map.put(d2.getScenarioNo(), d2);
    map.put(d3.getScenarioNo(), d3);
    map.put(d4.getScenarioNo(), d4);
    //retrieving values from map
    Set<Integer> keySet= map.keySet();
    for(int i:keySet){
        System.out.println(map.get(i));
    }
    //searching key on map
    System.out.println(map.containsKey(d1.getScenarioNo()));
    //searching value on map
    System.out.println(map.containsValue(d1));
}
}
