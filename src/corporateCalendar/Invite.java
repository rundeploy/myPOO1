package corporateCalendar;

/**
 * Interface for the Invite implemented by InviteClass.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor:Andre Sabino
 */
public interface Invite {

	/**
	 * Method used to get the answer given to the invite.
	 * @return: the current answer.
	 */
	String getAnswer();
	
	/**
	 * Method used to get the user that was invited.
	 * @return: user object
	 */
	User getUser();
	
	/**
	 * Method to get the event of the invite.
	 * @return: event object.
	 */
	Event getEvent();
	
	/**
	 * Method used to set a final answer to the invite.
	 * @param answer- final answer.
	 */
	void setDefAnswer(String answer);
}
