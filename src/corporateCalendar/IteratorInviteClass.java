package corporateCalendar;

/**
 * This class implements the Iterator interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class IteratorInviteClass implements Iterator {
	
	/**
	 * Invites vector.
	 */
	private Invite[] invites;
	/**
	 * Variable used for the invites vector counter.
	 */
	private int counter;
	/**
	 * Variable used for the invites vector current counter.
	 */
	private int current;
	
	/** IteratorInviteClass builder, used when is create an iterator object type.
	 * @param invites- invites vector of the user or the event.
	 * @param counter- counter of the invites of the user or the event.
	 */
	public IteratorInviteClass(Invite[] invites, int counter){
		this.invites = invites;
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
		Invite nextInvite = invites[current++];
		return nextInvite;		
	}
	
}
