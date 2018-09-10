package corporateCalendar;

/**
 * This class extends the AbstractUser class.
 * It's used to give the type for the created user.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class StaffClass extends AbstractUser{
	
	/**
	 * StaffClass builder, used when is create an user object with the Staff
	 * type.
	 * @param userEmailID- user email.
	 */
	public StaffClass(String userEmailID){
		super(userEmailID, User.STAFF);
	}	
	
	public boolean canCreateEv() {
		return true;
	}

}
