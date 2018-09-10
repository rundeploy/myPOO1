import corporateCalendar.BossClass;
import corporateCalendar.Calendar;
import corporateCalendar.CalendarClass;
import corporateCalendar.Event;
import corporateCalendar.ExternalClass;
import corporateCalendar.Invite;
import corporateCalendar.Iterator;
import corporateCalendar.StaffClass;
import corporateCalendar.User;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Users interface.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class Main {
	
	/**
	 * Enum used for the constant of the commands main.
	 */
	private enum Command{
		EXIT,REGISTER,SCHEDULE,INVITE,REPLY,LIST,ACCOUNTS,BOSS,STAFF,EXTERNAL,EVENTS,EVENTDETAILS,UNKNOWN;
	}
	
	//REGISTER outputs
	private static final String ACCOUNT_WAS_REGISTERED = "Account was registered.";
	private static final String ACCOUNT_ALREADY_EXISTS = "Account already exists."; //(1)
	private static final String UNKNOWN_ACCOUNT_TYPE = "Unknown account type."; //(2)
	
	//SCHEDULE outputs
	private static final String EVENT_SCHEDULED = "Event scheduled.";
	private static final String PROMOTER_ACCOUNT_DOES_NOT_EXIST = "Promoter account does not exist."; //(1)
	private static final String EVENT_ALREADY_EXISTS_IN_THE_ACCOUNT = "Event already exists in the account."; //(2)
	private static final String PROMOTER_IS_BUSY = "Promoter is busy."; //(3)
	private static final String USER_CANNOT_CREATE_NEW_EVENTS = "User cannot create new events."; //(4)
	
	//INVITE outputs
	private static final String GUEST_INVITED = "Guest invited.";
	private static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist."; //(1)
	private static final String EVENT_DOES_NOT_EXIST = "Event does not exist."; //(2)
	private static final String GUEST_IS_ALREADY_INVITED = "Guest is already invited."; //(3)
	private static final String GUEST_IS_ALREADY_IN_ANOTHER_EVENT = "Guest is already in another event."; //(4)
	
	//REPLY outputs
	private static final String PARTICIPATION_STATUS_UPDATED = "Participation status updated.";
	private static final String REPLY_ACCOUNT_DOES_NOT_EXIST = "Account does not exist."; //(1)
	private static final String REPLY_EVENT_DOES_NOT_EXIST = "Event does not exist."; //(2)
	private static final String USER_WAS_NOT_INVITED_TO_THIS_EVENT = "User was not invited to this event."; //(3)
	private static final String EVENT_PARTICIPATION_IS_MANDATORY = "Event participation is mandatory."; //(4)

	//LIST ACCOUNTS output
	private static final String ALL_ACCOUNTS = "All accounts:";
	
	//LIST BOSS || STAFF || EXTRENAL outputs
	private static final String BOSS_ACCOUNTS = "Boss accounts:";
	private static final String STAFF_ACCOUNTS = "Staff accounts:";
	private static final String EXTERNAL_ACCOUNTS = "External accounts:";
	private static final String UNKNOWN_COMMAND_ARGUMENT = "Unknown command argument."; //(1)
	
	//LIST EVENTDETAILS outputs
	private static final String EVENT_DETAILS = "Event details:";
	private static final String LIST_PROMOTER_ACCOUNT_DOES_NOT_EXIST = "Promoter account does not exist."; //(1)
	private static final String LIST_EVENT_DOES_NOT_EXIST = "Event does not exist."; //(2)
	
	//LIST EVENTS outputs
	private static final String ALL_EVENT_FOR_THIS_USER = "All events for this user:";
	private static final String LIST_EVENTS_PROMOTER_ACCOUNT_DOES_NOT_EXIST = "Promoter account does not exist."; //(1)
	
	//EXIT output
	private static final String EXITING = "Exiting...";
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		interpreter(in);
	}	
	
	/**
	 * Method used to translate the command given by the user to an enum.
	 * @param in- to read the users will.
	 * @return: the command.
	 */
	private static Command getCommand(Scanner in){
		try{
			String command = in.next();
			return Command.valueOf(command);
		}catch(IllegalArgumentException e){
			return Command.UNKNOWN;
		}		
	}
	
	/**
	 * Method used to the main interpreter commands.
	 * @param in- to read the users will.
	 */
	private static void interpreter(Scanner in){
		Calendar cal = new CalendarClass();
		Command command = getCommand(in);
		
		while(!command.equals(Command.EXIT)){
			switch(command){
			case REGISTER: register(in, cal);
							System.out.println();
							break;
			
			case SCHEDULE: schedule(in, cal);
							System.out.println();
							break;
							
			case INVITE: invite(in, cal);
							System.out.println();
							break;
							
			case REPLY: reply(in, cal);
							System.out.println();
							break;
			case UNKNOWN: System.out.println(UNKNOWN_COMMAND_ARGUMENT);
							System.out.println();
							break;
			
			case LIST: command = getCommand(in);
						switch(command){
						case REGISTER: // Sent a error message equal to the command UNKNOWN.
		
						case SCHEDULE: // Sent a error message equal to the command UNKNOWN
							
						case INVITE: // Sent a error message equal to the command UNKNOWN
						
						case REPLY: // Sent a error message equal to the command UNKNOWN
							
						case LIST:	// Sent a error message equal to the command UNKNOWN
							
						case UNKNOWN: System.out.println(UNKNOWN_COMMAND_ARGUMENT);
										System.out.println();
										break;	
										
						case ACCOUNTS: listAccounts(cal);
										System.out.println();
										break;
										
						case BOSS: listBoss(cal);
										System.out.println();
										break;
										
						case STAFF: listStaff(cal);
										System.out.println();
										break;
						
						case EXTERNAL: listExternal(cal);
										System.out.println();
										break;
										
						case EVENTDETAILS: listEventDetails(in, cal);
										System.out.println();									
										break;
										
						case EVENTS: listEvents(in, cal);
										System.out.println();
										break;
						
						
						default: // Do nothing.
							break;
						}
			
			
			default: // Do nothing.
				break;
			}
			command = getCommand(in);
		}
		System.out.println(EXITING);
		System.out.println();
	}
	
	/**
	 * Method used to register an user.
	 * In this method we treat all pre-conditions set out in CalendarClass.
	 * @param in- to read the users will.
	 * @param cal- our calendar object.
	 */
	private static void register(Scanner in, Calendar cal){
		String email = in.next();
		String type = in.next();
		
		if (!type.equals(User.BOSS) && !type.equals(User.STAFF) && !type.equals(User.EXTERNAL)){
			System.out.println(UNKNOWN_ACCOUNT_TYPE);
		}else if(cal.existAccount(email)){
			System.out.println(ACCOUNT_ALREADY_EXISTS);
		}else{
			cal.register(email, type);
			System.out.println(ACCOUNT_WAS_REGISTERED);
		}
			
	}
	
	/**
	 * Method used to schedule an event.
	 * In this method we treat all pre-conditions set out in CalendarClass.
	 * @param in- to read the users will.
	 * @param cal- our calendar object.
	 */
	private static void schedule(Scanner in, Calendar cal){
		String emailProm = in.next();
		String eventName = in.nextLine();
		int year = in.nextInt();
		int month = in.nextInt();
		int day = in.nextInt();
		int hour = in.nextInt();
		GregorianCalendar date = new GregorianCalendar(year,month,day,hour,0);
		
		if(!cal.existAccount(emailProm)){
			System.out.println(PROMOTER_ACCOUNT_DOES_NOT_EXIST);
		}
		else if(cal.existEvent(emailProm, eventName)){
			System.out.println(EVENT_ALREADY_EXISTS_IN_THE_ACCOUNT);
		}
		else if(cal.existDate(emailProm, date)){
			System.out.println(PROMOTER_IS_BUSY);
		}
		else if(!cal.canCreateEvent(emailProm)){
			System.out.println(USER_CANNOT_CREATE_NEW_EVENTS);
		}
		else{
			cal.schedule(emailProm, eventName, date);
			System.out.println(EVENT_SCHEDULED);
		}
	}
		
		/**
		 * Method used to invite an user for an event.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param in- to read the users will.
		 * @param cal- our calendar object.
		 */
		private static void invite(Scanner in, Calendar cal){
			String emailProm = in.next();
			String emailGuest = in.next();
			String eventName = in.nextLine();
			
			if(!(cal.existAccount(emailProm)) || !(cal.existAccount(emailGuest))){
				System.out.println(ACCOUNT_DOES_NOT_EXIST);
			}
			else if(!cal.existEvent(emailProm, eventName)){
				System.out.println(EVENT_DOES_NOT_EXIST);
			}
			else if(cal.isInvited(emailProm,emailGuest, eventName)){
				System.out.println(GUEST_IS_ALREADY_INVITED);
			}
			else if(cal.previousInviteCreatedBoss(emailProm,emailGuest,eventName)&&
					(!cal.guestIsFree(emailProm, emailGuest, eventName)) && (cal.isBoss(emailProm))){
				System.out.println(GUEST_IS_ALREADY_IN_ANOTHER_EVENT);
			}
			else{
				cal.invite(emailProm, emailGuest, eventName);
				System.out.println(GUEST_INVITED);
			}
		}
		
		/**
		 * Method used by the guest to give a new answer for the invite.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param in- to read the users will.
		 * @param cal- our calendar object.
		 */
		private static void reply(Scanner in, Calendar cal){
			String emailGuest = in.next();
			String emailProm = in.next();
			String eventName = in.nextLine();
			String answer = in.next();
			
			if(!(cal.existAccount(emailGuest)) || !(cal.existAccount(emailProm))){
				System.out.println(REPLY_ACCOUNT_DOES_NOT_EXIST);
			}
			else if(!cal.existEvent(emailProm, eventName)){
				System.out.println(REPLY_EVENT_DOES_NOT_EXIST);
			}
			else if(!cal.isInvited(emailProm, emailGuest, eventName)){
				System.out.println(USER_WAS_NOT_INVITED_TO_THIS_EVENT);
			}
			else if((cal.isStaff(emailGuest)|| cal.isBoss(emailGuest))&& cal.isBoss(emailProm)){
				System.out.println(EVENT_PARTICIPATION_IS_MANDATORY);
			}
			else{
				cal.reply(emailGuest, emailProm, eventName, answer);
				System.out.println(PARTICIPATION_STATUS_UPDATED);
			}
		}
	
		/**
		 * Method used to list all accounts created.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param cal- our calendar object.
		 */
		private static void listAccounts(Calendar cal){
			System.out.println(ALL_ACCOUNTS);
			Iterator it = cal.listAccounts();
			while(it.hasNext()){
				User usr = (User)it.next();
				if(usr instanceof BossClass){
					System.out.println(usr.getEmail() + " " + User.BOSS);
				}
				else if(usr instanceof StaffClass){
					System.out.println(usr.getEmail() + " " + User.STAFF);
				}else if(usr instanceof ExternalClass){
					System.out.println(usr.getEmail() + " " + User.EXTERNAL);
				}
				
			}
		}
		
		/**
		 * Method used to list all </code>BOSS<code> accounts.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param cal- our calendar object.
		 */
		private static void listBoss(Calendar cal){
			System.out.println(BOSS_ACCOUNTS);
			Iterator it = cal.listAccounts();
			while(it.hasNext()){				
				User usr = (User)it.next();
				if(usr instanceof BossClass){
					System.out.println(usr.getEmail());
				}
			}
		}
		
		/**
		 * Method used to list all </code>STAFF<code> accounts.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param cal- our calendar object.
		 */
		private static void listStaff(Calendar cal){
			System.out.println(STAFF_ACCOUNTS);
			Iterator it = cal.listAccounts();
			while(it.hasNext()){				
				User usr = (User)it.next();
				if(usr instanceof StaffClass){
					System.out.println(usr.getEmail());
				}
			}
		}
		
		/** 
		 * Method used to list all </code>EXTERNAL<code> accounts.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param cal- our calendar object.
		 */
		private static void listExternal(Calendar cal){
			System.out.println(EXTERNAL_ACCOUNTS);
			Iterator it = cal.listAccounts();
			while(it.hasNext()){				
				User usr = (User)it.next();
				if(usr instanceof ExternalClass){
					System.out.println(usr.getEmail());
				}
			}
		}
	
		/** 
		 * Method used to list the details of the event given by the user.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param in- to read the users will.
		 * @param cal- our calendar object.
		 */
		private static void listEventDetails(Scanner in, Calendar cal){
			String emailProm = in.next();
			String eventName = in.nextLine();
			
			if(!cal.existAccount(emailProm)){
				System.out.println(LIST_PROMOTER_ACCOUNT_DOES_NOT_EXIST);
			}
			else if(!cal.existEvent(emailProm ,eventName)){
				System.out.println(LIST_EVENT_DOES_NOT_EXIST);
			}
			else{
				System.out.println(EVENT_DETAILS);
				Iterator it = cal.listEventDetails(emailProm, eventName);
				int counter = 0;
				while(it.hasNext()){
					Invite invite = (Invite) it.next();
					GregorianCalendar date = invite.getEvent().getDate();
					int year = date.get(java.util.Calendar.YEAR);
					int month = date.get(java.util.Calendar.MONTH);
					int day = date.get(java.util.Calendar.DAY_OF_MONTH);
					int hour = date.get(java.util.Calendar.HOUR_OF_DAY);
					if(counter == 0){
						System.out.println(invite.getUser().getEmail() + invite.getEvent().getName() + " " + year + " " + month + " " + day + " " + hour);
						System.out.println(invite.getUser().getEmail() + " " +  invite.getAnswer());
						counter++;
					}else{
						System.out.println(invite.getUser().getEmail() + " " +  invite.getAnswer());
					}
				}
			}
		}
	
		/** 
		 * Method used to list all the events of the user with the email given by the user.
		 * In this method we treat all pre-conditions set out in CalendarClass.
		 * @param in- to read the users will.
		 * @param cal- our calendar object.
		 */
		private static void listEvents(Scanner in, Calendar cal){
			String email = in.next();
			
			if(!cal.existAccount(email)){
				System.out.println(LIST_EVENTS_PROMOTER_ACCOUNT_DOES_NOT_EXIST);
			}
			else{
				System.out.println(ALL_EVENT_FOR_THIS_USER);
				Iterator it = cal.listEvents(email);
				while(it.hasNext()){
					Event event = (Event)it.next();
					String eventName = event.getName();
					User userProm = event.getProm();
					Event eventProm = cal.getEventByPromAndName(userProm,eventName);
					
					GregorianCalendar date = event.getDate();
					int year = date.get(java.util.Calendar.YEAR);
					int month = date.get(java.util.Calendar.MONTH);
					int day = date.get(java.util.Calendar.DAY_OF_MONTH);
					int hour = date.get(java.util.Calendar.HOUR_OF_DAY);
					System.out.println("(" + event.getProm().getEmail() + ")" + event.getName() + " " + year + " " + month + " " + day + " " + hour +" "+ 
							eventProm.getNumberAcceptedAnswer() + " " + eventProm.getNumberRejectedAnswer()+ " " + eventProm.getNumberConflictdAnswer()
							+ " " + eventProm.getNumberNoAnswer());
				}
					
				
			}
		}
}
