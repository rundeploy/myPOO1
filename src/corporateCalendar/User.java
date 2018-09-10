package corporateCalendar;
import java.util.GregorianCalendar;


/**
 * Interface for the Users implemented by UserClass.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor:Andre Sabino
 */
public interface User {
	
	/**
	 * Constant for <code>BOSS</code> type account.
	 */
	static final String BOSS = "BOSS";
	/**
	 * Constant for <code>STAFF</code> type account.
	 */
	static final String STAFF = "STAFF";
	/**
	 * Constant for <code>EXTERNAL</code> type account
	 */
	static final String EXTERNAL = "EXTERNAL";

	/**
	 * Method used to get the user's email.
	 * @return
	 */
	String getEmail();
	/**
	 * Method used to verify if exist an event with the given name.
	 * @param eventName- event name.
	 * @return true if the event exist false if not.
	 */
	boolean hasEventName(String eventName);
	/**
	 * Method used to verify if exist an event with the given date.
	 * @param date- event date.
	 * @return true if the event exist false if not.
	 */
	boolean hasDate(GregorianCalendar date);
	/**
	 * Method used to verify if an user can create an event.
	 * This method is implemented on BossClass, StaffClass and ExternalClass.
	 * @return true if the user can creat an event and false if not.
	 */
	boolean canCreateEv();
	/**
	 * Method used to add an event in the user event vector.
	 * Add an event with the name event given by </code> eventName<code>
	 * and with the date given by </code>date<code>.
	 * @param userProm- Promoter of the event.
	 * @param eventName- name of the event to add.
	 * @param date- date of the event to add.
	 */
	void addEvent(User userProm,String eventName,GregorianCalendar date);
	/**
	 * Method used to get an event of a user given in the parameter </code>user<code>
	 * and with a name of event given by the parameter </code>eventName<code>.
	 * @param user- Promoter of the event.
	 * @param eventName- name of the event.
	 * @return: an Event with the name and the user given.
	 */
	Event getEvent(User user, String eventName);
	/**
	 * Method used to get an event with the date given by the parameter </code>date<code>
	 * @param date- date of the event.
	 * @return: an event with the given date.
	 */
	Event getEventByDate(GregorianCalendar date);
	/**
	 * Method used to verify if an user given by the parameter </code>userGuest<code>
	 * was invited to an event given by the parameter </code>event<code>.
	 * @param userGuest- user to verify if was invited to the event.
	 * @param event- event to verify if the user given was invited.
	 * @return: true if the user was invited to the event and false if not.
	 */
	boolean isGuest(User userGuest, Event event);

	/**
	 * Method used to add an invite to the invite vector.
	 * @param invite- invite to add.
	 */
	void invite(Invite invite);
	/**
	 * Method used to update the user answer given to an invite of an event.
	 * @param emailGuest- invitee email
	 * @param event- event of the invite.
	 * @param answer- new answer
	 */
	void setAnswer(User emailGuest, Event event, String answer);
	/**
	 * Method used to verify if the user was invited to an event with the date given by
	 * the parameter </code>eventDate<code>.
	 * @param eventDate- date of the event. 
	 * @return: true if the user was invited and false if not.
	 */
	boolean hasInvite(GregorianCalendar eventDate);
	/** 
	 * Method used to get an invite with the date given by the parameter </code>date<code>.
	 * @param date- date of the event.
	 * @return: the invite with the given date.
	 */
	Invite getInvite(GregorianCalendar date);
	
	/**
	 * Method used to create an iterator for the collection of events.
	 * @return: iterator object.
	 */
	IteratorEventClass listEvents();
	
	
}
