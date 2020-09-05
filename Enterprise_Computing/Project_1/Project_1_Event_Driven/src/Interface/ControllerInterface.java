package Interface;

import java.util.EventListener;

public interface ControllerInterface extends EventListener {
	
	public void processExitButtonClick(ProcessButtonObjectEvent e);
	public void processNewOrderButtonClick(ProcessNewOrderObjectEvent e);
	public void processFinishOrderButtonClick(ProcessFinishOrderObjectEvent e);
	public void processViewOrderButtonClick(ProcessViewOrderObjectEvent e);
	public void processConfirmItemButtonClick(ProcessConfirmItemObjectEvent e);
	public void processProcessIButtonClick(ProcessProcessItemObjectEvent e);
	public void processErrorMessage(ProcessErrorMessageObjectEvent e, String message);
	public void processItemAddedMessage(ProcessItemAddedMessageObjectEvent e, String message);
	public void processOrderSubTotalMessage(ProcessOrderSubTotalObjectEvent e, Double amount);
	
	public void processItemInformation(ProcessItemInformationObjectEvent e, String messasge);

}
