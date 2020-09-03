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
	
	
	
	
	
	
	
	
	
}
