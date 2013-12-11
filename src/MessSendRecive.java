import java.util.ArrayList;


public class MessSendRecive implements Observer{
	
	private String text;
	private String textSend;
	
	private void notifObservers() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getInputText(String t) {
		// TODO Auto-generated method stub
		text = t;
		
		notifObservers();
		return textSend;
	}

	

	@Override
	public String getOutputText() {
		// TODO Auto-generated method stub
		return text;
	}
	
	
	// the list of observers to notify when something changes
		private ArrayList<Observer> observers = new ArrayList<Observer>();
		
		// add an observer to the list
		public void addObserver(Observer o){
			observers.add(o);
		}
		
		// remove an observer from the list
		public void removeObserver(Observer o){
			observers.remove(o);
		}
		
		// tell all the observers in the list that something happened
		// i e call update for each of them
		protected void notifyObservers(){
			for (Observer obj: observers){
//				obj.setString();
			}
		}
}
