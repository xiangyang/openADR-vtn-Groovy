package org.enernoc.oadr2.vtn
/**
 * Programs linked to ven's and Events
 * @author Yang Xiang
 * 
 */
class Program {
	//@Required(message = "Must enter a valid Program Name")
	String programName;
	//@Required(message = "Must enter a valid Program URI")
	String programURI;
	//send to many to one relation; this may change in the future
	static hasMany = [ven:Ven, event:Event]
	
    static constraints = {
		programName (blank: false, unique: true)
		programURI (blank:false)
    }
}
