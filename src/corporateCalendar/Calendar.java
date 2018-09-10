package corporateCalendar;

import java.util.GregorianCalendar;

/**
 * Interface for the Calendar implemented by CalendarClass.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor:Andre Sabino
 */
public interface Calendar {
	
	/**
	 * Method to verify if was registered an user with the email
	 * given in the parameter </code>email<code>.
	 * @param email- user email.
	 * @return: true if exist the user and false if not.
	 */
	boolean existAccount(String email);
	/**
	 * Method to register an user with the email given by the parameter
	 * </code>email<code> and the type given by the parameter </code>type<code>.
	 * @param email- email user.
	 * @param type- type of the user account.
	 * @pre: !existAccount(userEmail);
	 * @pre: use only the three existent user's type
	 */
	void register(String email, String type);
	
	/**
	 * Method to verify if the previous invite with the same date of the event with the name
	 * given by the parameter </code>eventName<code> was created by a Promoter with the type 
	 * </code>BOSS<code>.
	 * @param emailProm- user promoter email.
	 * @param emailGuest- user guest email.
	 * @param eventName- name of the event.
	 * @return: true if the event was created by a </code>BOSS<code>.
	 */
	boolean previousInviteCreatedBoss(String emailProm, String emailGuest, String eventName);
	
	/**
	 * Method used to verify if exist an event created with the promoter email
	 * given by the parameter </code>emailProm<code> and with the event name given
	 * by the parameter </code>eventName<code>.
	 * @param emailProm- user promoter email.
	 * @param eventName- name of the event.
	 * @return: true if exist the event and false if not.
	 */
	boolean existEvent(String emailProm, String eventName);
	/**
	 * Method used to verify if exist an event created with the promoter email
	 * given by the parameter </code>emailProm<code> and with the event date given
	 * by the parameter </code>date<code>.
	 * @param emailProm- user promoter email.
	 * @param date- date of the event.
	 * @return: true if exist the event and false if not.
	 */
	boolean existDate(String emailProm, GregorianCalendar date);
	/**
	 * Method to verify if the promoter user given by the parameter </code>emailProm<code>
	 * can create an event.
	 * @param emailProm- user promoter email.
	 * @return: true if can create an event and false if not.
	 */
	boolean canCreateEvent(String emailProm);
	/**
	 * Method used to schedule an event with the promoter user email given by the
	 * parameter </code>emailProm<code> and with the name and date event given by
	 * the parameters </code>eventName,date<code>.
	 * @param emailProm- user promoter email.
	 * @param eventName- name of the event.
	 * @param date- date of the event.
	 * @pre: existAccount(userEmail);
	 * @pre: !userProm.hasEventName(String eventName);
	 * @pre: !userProm instanceof EXTERNAL
	 */
	void schedule(String emailProm, String eventName, GregorianCalendar date);
	
	/**
	 * Method used to verify if an user was invited to an event with the promoter user email
	 * given by the parameter </code>emailProm<code> and with the event name given by the
	 * parameter </code>eventName<code>.
	 * @param emailProm- user promoter email.
	 * @param emailGuest- user guest email.
	 * @param eventName- name of the event.
	 * @return: true if the user</code>emailGuest<code> was invited to the event and false
	 * 			if not.
	 */
	boolean isInvited(String emailProm, String emailGuest, String eventName);
	/**
	 * Method used to verify if the guest free to participate in the event.
	 * @param emailProm- user promoter email.
	 * @param emailGuest- user guest email.
	 * @param eventName- name of the event.
	 * @return: true if the guest is free and false if not.
	 */
	boolean guestIsFree(String emailProm, String emailGuest, String eventName);
	/**
	 * Method to invite an user with the email given by the parameter </code>emailProm<code>
	 * to an event with the name given by the parameter </code>eventName<code> created by the
	 * promoter user email given by the parameter </code>emailProm<code>.
	 * @param emailProm- user promoter email.
	 * @param emailGuest- user guest email.
	 * @param eventName- name of the event.
	 * @pre: existAccount(userProm) && existAccount(userInv);
	 * @pre: userProm.hasEventName(String eventName);
	 * @pre: userProm.isGuest(User userGuest, Event event);
	 * @pre: fail if user is already invited by another <code>BOSS</code> 
	 */
	void invite(String emailProm, String emailGuest, String eventName);
	
	/**
	 * Method used to verify if the account type of the guest user is </code>STAFF<code>.
	 * @param userEmail- user email.
	 * @return: true if the account type is </code>STAFF<code> and false if not.
	 */
	boolean isStaff(String emailGuest);
	/**
	 * Method used to verify if the account type of the promoter user is </code>BOSS<code>.
	 * @param userEmail- user email.
	 * @return: true if the account type is </code>BOSS<code> and false if not.
	 */
	boolean isBoss(String userEmail);
	/**
	 * Method used by the user guest to give a new answer to an invite of the event with the
	 * name given by the parameter </code>eventName<code> and the promoter user email given by
	 * the parameter </code>emailProm<code>.
	 * @param emailGuest- user guest email.
	 * @param emailProm- user promoter email.
	 * @param eventName- name of the event.
	 * @param answer- new answer for the invite.
	 * @pre: existAccount(userProm)&& existAccount(userInv);
	 * @pre: userProm.existEvent(eventName)
	 * @pre: if promotor is <code>BOSS</code>, user <code>STAFF</code> accepts automatiaclly because
	 * "Event participation is mandatory" and user <code>EXTERNAL</code> can make a choise.
	 * @pre: invited user must belong to the inventation list
	 */
	void reply(String emailGuest, String emailProm, String eventName, String answer);
	
	Event getEventByPromAndName(User userProm, String eventName);
	
	/**
	 * Method used to create an iterator for the collection of users.
	 * @return: iterator object 
	 */
	
	Iterator listAccounts();
	
	/**
	 * Method used to get the iterator of the invites of the event with the name given by
	 * the parameter </code>eventName<code> and with the user promoter email given by the
	 * parameter </code>emailProm<code>.
	 * @param emailProm- - user promoter email.
	 * @param eventName- name of the event.
	 * @return: iterator object
	 * @pre: existAccount(userProm);
	 * @pre: userProm.hasEventName(String eventName);;
	 */
	Iterator listEventDetails(String emailProm, String eventName);
	
	/**
	 * Method used to get the iterator for user's collection of events.
	 * @param email- user email.
	 * @return: iterator object.
	 * @pre: existAccount(userProm);
	 */
	Iterator listEvents(String email);

}
