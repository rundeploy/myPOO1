package corporateCalendar;

/**
 * This class extends the AbstractUser class.
 * It's used to give the type for the created user.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class ExternalClass extends AbstractUser {
	
	
	/**
	 * ExternalClass builder, used when is create an user object with the External
	 * type.
	 * @param userEmailID- user email.
	 */
	public ExternalClass(String userEmailID){
		super(userEmailID, User.EXTERNAL);
		
	}
	
	public boolean canCreateEv() {
		return false;
	}
}
