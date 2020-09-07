package Interface;

import java.util.Vector;

public class ViewController {
	private Vector<ControllerInterface> listener = new Vector<ControllerInterface>();

	public void addListener(ControllerInterface listenerObject) {
		listener.addElement(listenerObject);
	}
	
	public void removeListener(ControllerInterface listenerObject) {
		listener.removeElement(listenerObject);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void exitButtonControl() {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessButtonObjectEvent newEvent = new ProcessButtonObjectEvent(this);
			listenerCopy.processExitButtonClick(newEvent);
		}
		
	}
	
	
	
	
	
	public void errorMessage(String message) {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessErrorMessageObjectEvent newEvent = new ProcessErrorMessageObjectEvent(this);
			listenerCopy.processErrorMessage(newEvent,message);
		}
		
	}
	
	
	
	public void successMessage(String message) {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessItemAddedMessageObjectEvent newEvent = new ProcessItemAddedMessageObjectEvent(this);
			listenerCopy.processItemAddedMessage(newEvent,message);
		}
		
	}
	
	
	
	
	
	public void itemInformation(String message) {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessItemInformationObjectEvent newEvent = new ProcessItemInformationObjectEvent(this);
			listenerCopy.processItemInformation(newEvent,message);
		}
		
	}
	
	
	public void orderSubTotal(Double amount) {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessOrderSubTotalObjectEvent newEvent = new ProcessOrderSubTotalObjectEvent(this);
			listenerCopy.processOrderSubTotalMessage(newEvent,amount);
		}
		
	}
	
	
	
	public void newOrder() {
		Vector<ControllerInterface> copy;
		
		synchronized(this) {
			copy = (Vector<ControllerInterface>) listener.clone();
		}
		
		for(int i = 0; i < copy.size(); i++) {
			ControllerInterface listenerCopy = (ControllerInterface) copy.elementAt(i);
			ProcessNewOrderObjectEvent newEvent = new ProcessNewOrderObjectEvent(this);
			listenerCopy.processNewOrderButtonClick(newEvent);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
