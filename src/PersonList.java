import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonList {
	private List<Person> list;
	
	public PersonList() {
		list = new ArrayList<Person>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean add(Person p) {
		if(check(p))
			return false;
		else 
			list.add(p);
		return true;
	}
	
	public Person get(int index) {
		return list.get(index);
	}
	
	private boolean check(Person p) {
		if(list.size() < 5) {
			for(Person person : list) {
				if(p.getFirstName().equals(person.getFirstName()) && 
						p.getSurname().equals(person.getSurname()))
					return true;
			}
		}
		else {
			sort();
			int min = 0;
			int max = size() - 1;
			while (max >= min) {
				int middle = (min + max) / 2;
				Person tempPerson = list.get(middle);
				int n = tempPerson.getSurname().compareTo(p.getSurname());
				if (n > 0) {
					max = middle - 1;
				} else if (n < 0) {
					min = middle + 1;
				} else {
					int m = tempPerson.getFirstName().compareTo(p.getFirstName());
					if (m > 0) {
						max = middle - 1;
					} else if (m < 0) {
						min = middle + 1;
					} else {
						return true;
					}
				}

			}
		}
		return false;
	}
	
	public void sort() {
		Collections.sort(list);
	}
	
	// 
	public int index(String firstName, String lastName) {
		int min = 0;
		int max = size() - 1;
		while (max >= min) {
			int middle = (min + max) / 2;
			Person tempPerson = list.get(middle);
			int n = tempPerson.getSurname().compareTo(lastName);
			if (n > 0) {
				max = middle - 1;
			} else if (n < 0) {
				min = middle + 1;
			} else {
				int m = tempPerson.getFirstName().compareTo(firstName);
				if (m > 0) {
					max = middle - 1;
				} else if (m < 0) {
					min = middle + 1;
				} else {
					return middle;
				}
			}

		}
		return -1;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < size(); i++) {
			s += i + ". " + list.get(i) + "\n";
		}
		return s;
	}
}
