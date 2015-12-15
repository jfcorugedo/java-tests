package es.juan.utils.collections;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ListTest {

	@Test
	public void testSplitList() {
		List<Double> list = new ArrayList<Double>();
		list.add(1d);
		list.add(2d);
		list.add(3d);
		list.add(4d);
		list.add(5d);
		list.add(6d);
		
		assertThat(list.subList(0, 3)).containsExactly(1d,2d,3d);
		assertThat(list.subList(3, 6)).containsExactly(4d,5d,6d);
		
	}
}
