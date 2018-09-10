package corporateCalendar;

/**
 * This class implements the Iterator interface.
 * This class contains all the method implementation.
 * @author Sara Trafaria nº41693(P3) e Cristian Mitul nº41751(P7)
 * @Professor: Andre Sabino
 */
public class IteratorEventClass implements Iterator {

	/**
	 * Events vector.
	 */
	private Event[] events;
	/**
	 * Variable used for the events vector counter.
	 */
	private int counter;
	/**
	 * Variable used for the events vector current counter.
	 */
	private int current;
	
	/**
	 * IteratorEventClass builder, used when is create an iterator object type.
	 * @param events- events vector of the user.
	 * @param counter- counter of the events of the user.
	 */
	public IteratorEventClass(Event[] events, int counter){
		this.events = events;
		this.counter = counter;
		this.init();
	}
	
	public boolean hasNext(){
		return (current >= 0) && (current < counter);
	}
	
	public void init(){
		current = 0;
	}
	
	public Object next(){
		Event nextEvent = events[current++];
		return nextEvent;	
	}
		
}