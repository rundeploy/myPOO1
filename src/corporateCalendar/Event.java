package corporateCalendar;
import java.util.GregorianCalendar;

/**
 * Interface for the Event implemented by EventClass.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor:Andre Sabino
 */
public interface Event {

	
	/**
	 * Method used to get the name of the event.
	 * @return: event name.
	 */
	String getName();
	
	/**
	 * Method used to get the date of the event.
	 * @return: date of the event.
	 */
	GregorianCalendar getDate();
	
	/**
	 * Method used to verify if the user with the email given by the parameter </code>email<code>
	 * was invited to the event with the name given by the parameter </code>eventName<code>.
	 * @param email- user's email.
	 * @param eventName- name of the event.
	 * @return: true if the user was invited and false if not.
	 */
	boolean isGuest(String email, String eventName);
	
	/**
	 * Method used to get an invite of the user given by the parameter </code>guest<code> to an
	 * event given by the parameter <event> with the default answer given by the parameter 
	 * </code>answer<code>.
	 * @param guest- guest user
	 * @param event- event of the invite.
	 * @param answer- default answer.
	 * @return: invite object.
	 */
	Invite inviteUser(User guest, Event event, String answer);
	
	/**
	 * Method used to get the promoter user of the event.
	 * @return: user object.
	 */
	User getProm();
	
	/**
	 * Method used by the guest to update the new answer given by the parameter </code>answer<code> 
	 * to the event with the name given by the parameter </code>eventName<code>.
	 * @param emailGuest- user guest email.
	 * @param eventName- name of the event.
	 * @param answer- new answer.
	 */
	void setAnswer(String emailGuest, String eventName, String answer);
	
	/**
	 * Method used to give the number of the users that </code>ACCEPTED<code> the invite.
	 * @return: number of users.
	 */
	int getNumberAcceptedAnswer();
	
	/**
	 * Method used to give the number of the users that </code>REJECTED<code> the invite.
	 * @return: number of users.
	 */
	int getNumberRejectedAnswer();
	
	/**
	 * Method used to give the number of the users that </code>CONFLICT<code> the invite.
	 * @return: number of users.
	 */
	int getNumberConflictdAnswer();
	
	/**
	 * Method used to give the number of the users that </code>NO_ANSWER<code> the invite.
	 * @return: number of users.
	 */
	int getNumberNoAnswer();
	
	/**
	 *  Method used to create an iterator for the collection of invites.
	 * @return: iterator object.
	 */
	IteratorInviteClass listEventsDetails();
}
