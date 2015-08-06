package es.juan.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
 
import static org.assertj.core.api.Assertions.*;

public class LambdaTest {

	@Test
	public void testFilterList() {
		List<DummyBean> beans = getTestList();
	
		
		beans.stream().filter((d) -> d.getAge() > 33).mapToInt(d -> d.getAge()).max();
	}
	
	@Test
	public void testAddElements() {
		List<Integer> storedIds = new ArrayList<Integer>();
		storedIds.add(1);
		storedIds.add(2);
		storedIds.add(3);
		storedIds.add(4);
		
		List<DummyBean> receivedBeans = new ArrayList<DummyBean>();
		receivedBeans.add(new DummyBean().setAge(1));
		receivedBeans.add(new DummyBean().setAge(4));
		receivedBeans.add(new DummyBean().setAge(3));
		receivedBeans.add(new DummyBean());
		
		List<Integer> removedIds = new ArrayList<Integer>();
		
		//Create new elements
		receivedBeans.forEach( i -> updateSection(i));
		
		//Delete missing elements
		List<Integer> recivedIds = receivedBeans.stream().map(d -> d.getAge()).collect(Collectors.toList());
		storedIds.stream().filter(i -> !recivedIds.contains(i)).forEach((i) -> removeElement(i, removedIds));
		
		assertThat(receivedBeans.get(3).getAge()).isEqualTo(5);
		assertThat(removedIds).hasSize(1);
		assertThat(removedIds.get(0)).isEqualTo(2);
	}

	private void removeElement(Integer i, List<Integer> removeIds) {
		removeIds.add(i);
	}

	private void updateSection(DummyBean i) {
		//if id == null
		//Create fragments
		//	1- Create fragment
		//  2- Create Section with list of IDs
		//Create section
		if(i.getAge() == 0) {
			i.setAge(5);
		}
		//else checkFragments
		//Create associated fragments
		//...
	}

	private List<DummyBean> getTestList() {
		List<DummyBean> testList = new ArrayList<DummyBean>();
		testList.add(new DummyBean().setAge(32).setName("Juan"));
		testList.add(new DummyBean().setAge(33).setName("Isaac"));
		return testList;
	}
}

class DummyBean {
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public DummyBean setName(String name) {
		this.name = name;
		return this;
	}
	public int getAge() {
		return age;
	}
	public DummyBean setAge(int age) {
		this.age = age;
		return this;
	}
	
	
}
