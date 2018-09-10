package corporateCalendar;

/**
 * Interface for the Iterator implemented by IteratorEventClass, or IteratorInviteClass, or
 * IteratorUserClass.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor:Andre Sabino
 */
public interface Iterator {

	/**
	 * Method used to verify if exists an next object to iterate in the users vector, or
	 * events vector or invites vector.
	 * @return
	 */
	boolean hasNext();
	
	/**
	 * Method used to initialize with zero the variable current.
	 */
	void init();
	
	/**
	 * Method used to get the next object with the type user, or event, or invite.
	 * @return: user object, or event object, or invite object.
	 */
	Object next();
	
}
