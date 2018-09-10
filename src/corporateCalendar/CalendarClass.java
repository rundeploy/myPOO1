package corporateCalendar;
import java.util.GregorianCalendar;

/**
 * This class implements the Calendar interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class CalendarClass implements Calendar {
	
	/**
	 * Constant used for the maximum capacity of the users vector.
	 */
	private static final int MAX = 100;
	
	/**
	 * Users vector.
	 */
	private User[] users;
	/**
	 * Variable used for the users vector counter.
	 */
	private int counter;
	
	
	/**
	 * CalendarClass builder, used when is create an calendar object type.
	 */
	public CalendarClass(){
		users = new User[MAX];
		counter = 0;
		
	}
	
	public boolean existAccount(String email){
		boolean found= false;
		int i = 0;
		while(i<counter && !found){
		      if(users[i].getEmail().equals(email))
		         found = true;
		      i++;
		}
		return found;
	}
		
	public void register(String email, String type){
		if(counter == users.length)
			resize();
		if(type.equals(User.BOSS))
			users[counter++] = new BossClass(email);
		else if (type.equals(User.STAFF))
			users[counter++] = new StaffClass(email);
		else //(type.equals(User.EXTERNAL))
			users[counter++] = new ExternalClass(email);
	}
	
	public boolean previousInviteCreatedBoss(String emailProm, String emailGuest, String eventName){
		
		User userProm = searchUser(emailProm);
		User userGuest = searchUser(emailGuest);
		Event event = userProm.getEvent(userProm, eventName);
			
		if(userGuest.hasInvite(event.getDate())){
			Invite previousInvite = userGuest.getInvite(event.getDate());
			Event previousEvent = previousInvite.getEvent();
			return previousEvent.getProm()instanceof BossClass;
		}
		else
			return false;		
	}
	
	public boolean existEvent(String emailProm, String eventName){
		User userProm = searchUser(emailProm);
		return userProm.hasEventName(eventName);
	}
	
	public boolean existDate(String emailProm, GregorianCalendar date){
		User userProm = searchUser(emailProm);
		return userProm.hasDate(date);
	}
	
	
	public boolean canCreateEvent(String emailProm){
		User userProm = searchUser(emailProm);
		return userProm.canCreateEv();
		
	}
	
	
	public void schedule(String emailProm, String eventName, GregorianCalendar date){
		
		User promoter = searchUser(emailProm);
		promoter.addEvent(promoter,eventName, date);
		
		Event event = promoter.getEvent(promoter, eventName);
		Invite invite = event.inviteUser(promoter, event, InviteClass.ACCEPTED);
		
		promoter.invite(invite);	
		
	}
	
	public boolean isInvited(String emailProm, String emailGuest, String eventName){
		User userProm = searchUser(emailProm);
		User userGuest = searchUser(emailGuest);
		Event event = userProm.getEvent(userProm, eventName);
		return userProm.isGuest(userGuest,event);
	}
	
	public boolean guestIsFree(String emailProm, String emailGuest, String eventName){
		User userProm = searchUser(emailProm);
		User userGuest = searchUser(emailGuest);
		Event event = userProm.getEvent(userProm, eventName);
		return !(userGuest.hasDate(event.getDate()));
	}
	
	public void invite(String emailProm, String emailGuest, String eventName){
		
		User userProm = searchUser(emailProm);
		User userGuest = searchUser(emailGuest);
		
		Event event = userProm.getEvent(userProm,eventName);
				
		if(userGuest.hasInvite(event.getDate())){ // Evento em colisao
			Invite previousInvite = userGuest.getInvite(event.getDate());
			Event previousEvent = previousInvite.getEvent();
			
			if(userProm instanceof BossClass && userGuest instanceof BossClass){
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.ACCEPTED);
					userProm.invite(invite);
					userGuest.setAnswer(userGuest, previousEvent, InviteClass.CONFLICT);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
			}else if(userProm instanceof BossClass && userGuest instanceof StaffClass){
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.ACCEPTED);
					userProm.invite(invite);
					userGuest.setAnswer(userGuest, previousEvent, InviteClass.CONFLICT);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
			}else if(userProm instanceof BossClass && userGuest instanceof ExternalClass){ 
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
				
			}else if(userProm instanceof StaffClass && userGuest instanceof BossClass){
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
				
			}else if(userProm instanceof StaffClass && userGuest instanceof StaffClass){
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
				
			}else{ //(userProm instanceof StaffClass && userGuest instanceof ExternalClass)
				if(previousEvent.getProm() instanceof BossClass){
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}else{ //previousEvent.getProm() instanceof StaffClass
					Invite invite = event.inviteUser(userGuest, event, InviteClass.CONFLICT);
					userProm.invite(invite);
					userGuest.addEvent(userProm,event.getName(),event.getDate());
					userGuest.invite(invite);
				}
			}
		}else{ //(!userGuest.hasInvite(event.getDate())) Nao ha evento em colisao
			if(userProm instanceof BossClass && userGuest instanceof BossClass){
				Invite invite = event.inviteUser(userGuest, event, InviteClass.ACCEPTED);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}else if(userProm instanceof BossClass && userGuest instanceof StaffClass){
				Invite invite = event.inviteUser(userGuest, event, InviteClass.ACCEPTED);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}else if(userProm instanceof BossClass && userGuest instanceof ExternalClass){ 
				Invite invite = event.inviteUser(userGuest, event, InviteClass.NO_ANSWER);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}else if(userProm instanceof StaffClass && userGuest instanceof BossClass){
				Invite invite = event.inviteUser(userGuest, event, InviteClass.NO_ANSWER);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}else if(userProm instanceof StaffClass && userGuest instanceof StaffClass){
				Invite invite = event.inviteUser(userGuest, event, InviteClass.NO_ANSWER);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}else{ //(userProm instanceof StaffClass && userGuest instanceof ExternalClass)
				Invite invite = event.inviteUser(userGuest, event, InviteClass.NO_ANSWER);
				userProm.invite(invite);
				userGuest.addEvent(userProm,event.getName(),event.getDate());
				userGuest.invite(invite);
			}
				
		}		
	}
	
	public boolean isStaff(String userEmail){
		User userGuest = searchUser(userEmail);
		return userGuest instanceof StaffClass;
	}
	
	public boolean isBoss(String userEmail){

		User userProm = searchUser(userEmail);
		return userProm instanceof BossClass;
	}
	
	public void reply(String emailGuest, String emailProm, String eventName, String answer){
		
		User userProm = searchUser(emailProm);
		User userGuest = searchUser(emailGuest);
		Event event = userProm.getEvent(userProm, eventName);
		if(answer.equalsIgnoreCase(InviteClass.ACCEPT)){
			userProm.setAnswer(userGuest,event,InviteClass.ACCEPTED);
		}
		else{// (answer.equalsIgnoreCase(REJECT)
		userProm.setAnswer(userGuest,event,InviteClass.REJECTED);
		}
	}
	
	public Event getEventByPromAndName(User userProm, String eventName){
		Event event = userProm.getEvent(userProm, eventName);
		return event;
	}
	
	public Iterator listAccounts(){
		return new IteratorUsersClass(users, counter);	
	}
		
	public Iterator listEventDetails(String emailProm, String eventName){
		User userProm = searchUser(emailProm);
		Event event = userProm.getEvent(userProm, eventName);
		return event.listEventsDetails();
	}
	
	public Iterator listEvents(String email){
		User user = searchUser(email);
		return user.listEvents();
		
	}
	
	/**
	 * Method used to resize the users vector.
	 * Replicates the size of the users vector.
	 */
	private void resize(){
		User[] tmp = new User[users.length * 2];
		for( int i = 0; i < counter; i++)
			tmp[i] = users[i];
		users = tmp;
	}
	
	/**
	 * Method used to get an user with the email given by the parameter </code>userEmail<code>.
	 * @param userEmail- user email to search
	 * @return: an user object. 
	 */
	private User searchUser(String userEmail){
		User person = null;
		int i = 0;
		
		while(i < counter && person == null){
			if(users[i].getEmail().equals(userEmail)){
				person = users[i];
			}
			i++;
		}
		return person;
	}
	

}
