package corporateCalendar;
import java.util.GregorianCalendar;

/**
 * This class implements the Event interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class EventClass implements Event {
	
	/**
	 * Constant used for the maximum capacity of the invites vector.
	 */
	private static final int MAX = 100;
	
	/**
	 * Invites vector.
	 */
	private Invite[] invites;
	/**
	 * Variable used for the invites vector counter.
	 */
	private int counter;
	/**
	 * Variable used to save the Promoter user object.
	 */
	private User userProm;
	/**
	 * Variable used for the name of the event.
	 */
	private String eventName;
	/**
	 * Variable used for the date of the event.
	 */
	private GregorianCalendar date;
	
	/**
	 * EventClass builder, used when is create an event object type.
	 * @param user- Promoter user.
	 * @param eventName- name of the event.
	 * @param date- date of the event.
	 */
	public EventClass(User user, String eventName, GregorianCalendar date){
		userProm = user;
		this.eventName = eventName;
		this.date = date;
		counter = 0;
		invites = new Invite[MAX];
	}
	
	public String getName(){
		return eventName;
	}
	
	public GregorianCalendar getDate(){
		return date;
	}
	
	public boolean isGuest(String email, String eventName){
		boolean found = false;
		int i = 0;
		while(i<counter && !found){
			if(invites[i].getUser().getEmail().equals(email) && invites[i].getEvent().getName().equals(eventName)){
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public Invite inviteUser(User guest, Event event, String answer){
		if(counter == invites.length){
			resize();
		}
		invites[counter] = new InviteClass(guest, event, answer);
		Invite invite = invites[counter];
		counter++;
		return invite;
	}
	
	public User getProm(){
		
		return userProm;
	}
	
	
	public void setAnswer(String emailGuest,String eventName, String answer){
		boolean found = false;
		int i = 0;
		while( i < counter && !found){
			if(invites[i].getUser().getEmail().equals(emailGuest) && invites[i].getEvent().getName().equals(eventName)){
				found = true;
				invites[i].setDefAnswer(answer);
			}
			i++;
		}
	}

	public int getNumberAcceptedAnswer(){
		int numberAnswer = 0;
		for (int i = 0; i<counter; i++){
			if(invites[i].getAnswer().equals(InviteClass.ACCEPTED)){
				numberAnswer++;
			}
		}
		return numberAnswer;
	}
	
	public int getNumberRejectedAnswer(){
		int numberAnswer = 0;
		for (int i = 0; i<counter; i++){
			if(invites[i].getAnswer().equals(InviteClass.REJECTED)){
				numberAnswer++;
			}
		}
		return numberAnswer;
	}
	
	public int getNumberConflictdAnswer(){
		int numberAnswer = 0;
		for (int i = 0; i<counter; i++){
			if(invites[i].getAnswer().equals(InviteClass.CONFLICT)){
				numberAnswer++;
			}
		}
		return numberAnswer;
	}
	
	public int getNumberNoAnswer(){
		int numberAnswer = 0;
		for (int i = 0; i<counter; i++){
			if(invites[i].getAnswer().equals(InviteClass.NO_ANSWER)){
				numberAnswer++;
			}
		}
		return numberAnswer;
	}
	
	public IteratorInviteClass listEventsDetails(){
		return new IteratorInviteClass(invites, counter);
	}
	
	/**
	 * Method used to resize the invites vector.
	 * Replicates the size of the invites vector.
	 */
	private void resize(){
		Invite[] tmp = new Invite[invites.length * 2];
		for (int i = 0; i < counter; i++){
			tmp[i] = invites[i];
		}
		invites = tmp;
	}
}
