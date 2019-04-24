/**
 * This is the person class.
 * @author u1711101
 *
 */
public class Person implements Comparable<Person>{
	private String firstName;
	private String surname;
	private Boolean male; // or boolean, but it will set to false automatically
	private Boolean alive;
	// things that maybe needed
	// such as lists of people that this.person hates
	// or likes etc ?
	
	
	public Person(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}
	
	public Person(String firstName, String surname, boolean male) {
		this.firstName = firstName;
		this.surname = surname;
		this.male = male;
	}
	
	// construcotr
	public Person(String firstName, String surname, boolean male, boolean alive) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.male = male;
		this.alive = alive;
	}
	
	// list of setters and getters
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	// boolean - NullPointerException
	public Boolean isMale() {
		return male;
	}


	public void setMale(boolean male) {
		this.male = male;
	}


	public Boolean isAlive() {
		return alive;
	}

	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	
	@Override
	public int compareTo(Person p) {
		int c = this.surname.compareTo(p.getSurname());
		if (c!=0) return c;
		else return this.firstName.compareTo(p.getFirstName());
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", surname=" + surname + ", male=" + male + ", alive=" + alive + "]";
	}


	
	
	
}
