package framework;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class Sample {
public static void main(String args[]){
//	Hashtable[][] arr = new Hashtable[2][]	

	Table<String, String, Integer> table = HashBasedTable.create();

	for (int i = 0; i < 10; i++) {
	    table.put("abc", "def", i);
	}
}
}
