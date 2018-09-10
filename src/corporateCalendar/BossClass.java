package corporateCalendar;


/**
 This class extends the AbstractUser class.
 * It's used to give the type for the created user.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class BossClass extends AbstractUser {
	
	/**
	 * BossClass builder, used when is create an user object with the Boss
	 * type.
	 * @param userEmailID- user email.
	 */
	public BossClass(String userEmailID){
		super(userEmailID, User.BOSS);
		
	}
	
	public boolean canCreateEv() {
		return true;
	}

}
