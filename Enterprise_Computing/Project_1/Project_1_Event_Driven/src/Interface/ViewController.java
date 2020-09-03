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
	
	
	
	
	
	
	
	
	
	
	
}
