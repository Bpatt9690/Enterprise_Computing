package Interface;

import java.util.EventListener;

public interface ControllerInterface extends EventListener {
	
	public void processButtonClick(ProcessButtonObjectEvent e);
	

}
