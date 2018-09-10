package corporateCalendar;
import java.util.GregorianCalendar;

/**
 * This class implements the user interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public abstract class AbstractUser implements User{
	
	/**
	 * Constant used for the maximum capacity of the events and invites vector.
	 */
	private static final int MAX = 100;
	
	/**
	 * Variable used for the user email.
	 */
	private String email;	
	/**
	 * Events vector.
	 */
	private Event[] events;
	/**
	 * Variables used for the vector counters.
	 */
	private int counterEvents,counterInvites;
	/**
	 * Invites vector.
	 */
	private Invite[] invites;
	
	/**
	 * AbstractUser builder, used when is create an user object type.
	 * @param email- user email.
	 * @param type- type of the account.
	 */
	protected AbstractUser(String email, String type ){
		events = new Event[MAX];
		invites = new Invite[MAX];
		counterEvents = 0;
		counterInvites = 0;
		this.email = email;
		
	}
	
	public String getEmail(){
		return email;
	}
	
	public boolean hasEventName(String eventName){
		boolean found = false;
		int i =0;
		while(i<counterEvents && !found){
			if(events[i].getName().equals(eventName)){
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public boolean hasDate(GregorianCalendar date){
		boolean found = false;
		int i = 0;
		while( i < counterEvents && !found){
			if(events[i].getDate().equals(date)){
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public abstract boolean canCreateEv();
	
	public void addEvent(User userProm, String eventName, GregorianCalendar date){
		if(counterEvents == events.length){
			resizeEvent();
		}
		events[counterEvents++] = new EventClass(userProm, eventName, date);
	}
	
	public Event getEvent(User user, String eventName){
		int i = 0;
		Event eventFound = null;
		while(i<counterEvents && eventFound != null){
			if( events[i].getProm().equals(user) && events[i].getName().equals(eventName)){
				eventFound = events[i];
			}
			i++;
		}
		return eventFound;
	}
	
	public Event getEventByDate(GregorianCalendar date){
		int i = 0;
		Event event = null;
		while(i<counterEvents && events == null){
			if(events[i].getDate().equals(date)){
				event = events[i];
			}
			i++;
		}
		return event;
	}
	
	public boolean isGuest(User userGuest, Event event){
		boolean found = false;
		int i = 0;
		if(invites[0]!=null){ // because external users don't have invites.
			while(i < counterInvites && !found){
				if(invites[i].getUser().equals(userGuest) && invites[i].getEvent().equals(event)){
					found = true;
				}
			i++;
			}
		}
		return found;
	}
	
	public void invite(Invite invite ){
		if(counterInvites == invites.length){
			resizeInvite();
		}
		invites[counterInvites++]= invite;
			
	}
	
	public boolean hasInvite(GregorianCalendar eventDate){
		boolean found = false;
		int i =0;
		if(invites[i]!= null){ // because external users don't have invites.
			while(i < counterInvites && !found){
				if(invites[i].getEvent().getDate().equals(eventDate)){
					found = true;
				}
			i++;
			}
		}
		return found;
	}
	
	public Invite getInvite(GregorianCalendar date){
		Invite inv = null;
		int i = 0;
		while (i < counterInvites && inv == null){
			if(invites[i].getEvent().getDate().equals(date)){
				inv = invites[i];
			}
			i++;
		}
		return inv;
	}
	
	public void setAnswer(User guest, Event event, String answer){
		boolean found = false;
		int i =0;
		while(i < counterInvites && !found){
			if(invites[i].getUser().equals(guest) && invites[i].getEvent().equals(event)){
				invites[i].setDefAnswer(answer);
				found = true;
			}
			i++;
		}
	}
	
	public IteratorEventClass listEvents(){
		return new IteratorEventClass(events, counterEvents);
	}
	

	
	/**
	 * Method used to resize the events vector.
	 * Replicates the size of the events vector.
	 */
	private void resizeEvent(){
		Event[] tmp = new Event[events.length * 2];
		for( int i = 0; i < counterEvents; i++){
			tmp[i] = events[i];
		}
		events = tmp;
	}
	
	/**
	 * Method used to resize the invites vector.
	 * Replicates the size of the invites vector.
	 */
	private void resizeInvite(){
		Invite[] tmp = new Invite[invites.length * 2];
		for( int i = 0; i < counterInvites; i++){
			tmp[i] = invites[i];
		}
		invites = tmp;
	}
}
