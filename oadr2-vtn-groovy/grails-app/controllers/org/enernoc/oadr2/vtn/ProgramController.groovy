package org.enernoc.oadr2.vtn

import org.enernoc.oadr2.vtn.Program
/**
 * controller for programs
 * @author yangxiang
 *
 */
class ProgramController {
	/**
	 * Base method called to access the default page for the Programs controller
	 *
	 * @return a redirect to the programs() call as to render the default page
	 */
	def messageSource
	def index() {
		return redirect(action: "programs");
	}

	/**
	 * Default method to render the page for the Program table
	 *
	 * @return the default render page for Program display and deletion
	 */

	def programs() {
		def results = Program.listOrderByProgramName(order:"desc")
		[programList: results]
	}

	/**
	 * Called upon submission of the Create Program button
	 *
	 * @return a rendering of the Program creation form with all fields blank
	 public static Result blankProgram(){
	 return ok(views.html.newProgram.render(form(Program.class)));
	 }*/

	def blankProgram() {
		[]
	}

	def newProgram() {
		def program = new Program(params)
		def errorMessage = ""
		if (program.validate()) {
			program.save()
			flash.message = "Success"
		} else {
			flash.message = "Fail"
			program.errors.allErrors.each {
				errorMessage += messageSource.getMessage(it, null) + "</br>"
			}
			return chain(action:"blankProgram", model: [error: errorMessage])
		}
		chain(action:"programs", model: [error: errorMessage])
	}
	/**
	 *
	 * @param id - the database ID of the Program to be deleted
	 * @return a redirect to the default display page without the deleted Program
	 */


	def deleteProgram() {
		def program = Program.get(params.id)
		program.delete()
		redirect(action:"programs")
		//render(params.id)
	}

}
