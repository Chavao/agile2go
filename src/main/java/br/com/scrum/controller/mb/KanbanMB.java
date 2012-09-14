package br.com.scrum.controller.mb;
//package br.com.scrum.controller.mb;
//
//import java.io.Serializable;
//
//import javax.enterprise.context.RequestScoped;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//
//import org.primefaces.event.DashboardReorderEvent;
//import org.primefaces.model.DashboardColumn;
//import org.primefaces.model.DashboardModel;
//import org.primefaces.model.DefaultDashboardColumn;
//import org.primefaces.model.DefaultDashboardModel;
//
//@Named
//@RequestScoped
//public class KanbanMB extends BaseBean implements Serializable {
//
//	private DashboardModel taskBoard;
//	
//	public KanbanMB() {		
//		taskBoard = new DefaultDashboardModel();
//		DashboardColumn column1 = new DefaultDashboardColumn();
//		DashboardColumn column2 = new DefaultDashboardColumn();
//		DashboardColumn column3 = new DefaultDashboardColumn();
//		
//		column1.addWidget("sports");
//		column1.addWidget("finance");
//		column2.addWidget("lifestyle");
//		column2.addWidget("weather");
//		column3.addWidget("politics");
//		
//		taskBoard.addColumn(column1);
//		taskBoard.addColumn(column2);
//		taskBoard.addColumn(column3);
//
//	}	
//	
//	public void handleReorder(DashboardReorderEvent event) {  
//		FacesMessage message = new FacesMessage();  
//		message.setSeverity(FacesMessage.SEVERITY_INFO);  
//		message.setSummary("Reordered: " + event.getWidgetId());  
//		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  
//
//		addMessage(message);  
//	}  
//	
//	private void addMessage(FacesMessage message) {  
//        FacesContext.getCurrentInstance().addMessage(null, message);  
//    }  
//
//	public DashboardModel getTaskBoard() {
//		return taskBoard;
//	}
//
//	private static final long serialVersionUID = -4240901561070389364L;
//
//}
