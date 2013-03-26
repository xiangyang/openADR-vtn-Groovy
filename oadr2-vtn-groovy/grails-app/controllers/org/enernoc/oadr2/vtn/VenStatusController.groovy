package org.enernoc.oadr2.vtn

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.enernoc.oadr2.vtn.VenStatus;
import org.enernoc.oadr2.vtn.Event;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.enernoc.oadr2.vtn.VenStatus;
import org.enernoc.oadr2.vtn.Program;

import org.enernoc.open.oadr2.model.EiEvent;



/**
 * Controller to handle the XMPP and HTTP services as well as the VENStatuses
 * 
 * @author Yang Xiang
 *
 */
public class VenStatusController {
    	

	
    /**
     * Base method called to access the default page for the VENStatuses controller
     *  
     * @return a redirect to the venStatuses() call as to render the default page
     */
	def index() {
        return redirect(action: "venStatuses");
    }
    
	/**
	 * Default method to render the page for the VENStatus table
	 * 
	 * @param program - The program specific to the Events to be displayed
	 * @return the default rendered page for VENStatus display and deletion
	 */
//	@SuppressWarnings("unchecked")
 //   @Transactional
	def venStatuses() {
	    //def eventList = Event.list()
		def venStatuses = VenStatus.findAllWhere(eventID: params.eventID) 
		def	eventList = Event.executeQuery("SELECT distinct e.eventID FROM Event e")
		[venStatusList: venStatuses, eventList: eventList, currentEventID: params.eventID]
	}
	

	
	/**
	 * Deletes a VENStatus based on the active program and the ID of the VENStatus
	 * 
	 * @param program - the selected program
	 * @param id - the database ID of the VENStatus to be deleted
	 * @return a redirect to the VENStatus default page without the deleted VENStatus
	 */
//	@Transactional
	def deleteStatus(){
		def venStatus = VenStatus.get(params.id)
		venStatus.delete()
		redirect(action:"venStatuses", params:[program: params.program])
	  //  JPA.em().remove(JPA.em().find(VENStatus.class, id));
	   //return redirect(routes.VENStatuses.venStatuses(program));
	}
}
