package corporateCalendar;

/**
 * This class implements the Invite interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class InviteClass implements Invite{
	
	/**
	 * Constant used for the state </code>NO_ANSWER<code>.
	 */
	public static final String NO_ANSWER = "NO_ANSWER";
	/**
	 * Constant used for the state </code>ACCEPTED<code>.
	 */
	public static final String ACCEPTED = "ACCEPTED";
	/**
	 * Constant used for the state </code>REJECTED<code>.
	 */
	public static final String REJECTED = "REJECTED";
	/**
	 * Constant used for the state </code>CONFLICT<code>.
	 */
	public static final String CONFLICT = "CONFLICT";
	
	/**
	 * Constant used when a user </code>ACCEPT<code> the invite.
	 */
	public static final String ACCEPT = "ACCEPT";
	
	/**
	 *  Variable used to keep an user.
	 */
	private User user;
	/**
	 * Variable used to keep an event.
	 */
	private Event event;
	/**
	 * Variable used for the answer given by the user.
	 */
	private String answer;
	
	/**
	 * InviteClass builder, used when is create an invite object type.
	 * @param user- invited user.
	 * @param event- event of the invite
	 * @param answer- the default answer of the invite.
	 */
	public InviteClass(User user, Event event, String answer){
		this.user = user;
		this.event = event;
		this.answer = answer;
	}
	
	public String getAnswer(){
		return answer;
	}
	
	public User getUser(){
		return user;
	}
	
	public Event getEvent(){
		return event;
	}
	
	public void setDefAnswer(String answer){
		this.answer = answer;
	}

}
