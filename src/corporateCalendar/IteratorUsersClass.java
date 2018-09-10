package corporateCalendar;

/**
 * This class implements the Iterator interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class IteratorUsersClass implements Iterator {

	/**
	 * Users vector.
	 */
	private User[] users;
	/**
	 * Variable used for the users vector counter.
	 */
	private int counter;
	/**
	 * Variable used for the users vector current counter.
	 */
	private int current;
	
	/**
	 * IteratorUserClass builder, used when is create an iterator object type.
	 * @param users- users vector of the CalendarClass.
	 * @param counter- counter of the users of the CalendarClass.
	 */
	public IteratorUsersClass(User[] users, int counter){
		this.users = users;
		this.counter = counter;
		this.init();
	}
	
	public boolean hasNext(){
		return (current >= 0) && (current < counter);
	}
	
	public void init(){
		current = 0;
	}
	
	public Object next(){
		User nextUser = users[current++];
		return nextUser;
	}
		
}
