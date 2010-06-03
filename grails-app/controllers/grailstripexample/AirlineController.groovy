package grailstripexample

class AirlineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [airlineInstanceList: Airline.list(params), airlineInstanceTotal: Airline.count()]
    }

    def create = {
        def airlineInstance = new Airline()
        airlineInstance.properties = params
        return [airlineInstance: airlineInstance]
    }

    def save = {
        def airlineInstance = new Airline(params)
        if (airlineInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'airline.label', default: 'Airline'), airlineInstance.id])}"
            redirect(action: "show", id: airlineInstance.id)
        }
        else {
            render(view: "create", model: [airlineInstance: airlineInstance])
        }
    }

    def show = {
        def airlineInstance = Airline.get(params.id)
        if (!airlineInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
            redirect(action: "list")
        }
        else {
            [airlineInstance: airlineInstance]
        }
    }

    def edit = {
        def airlineInstance = Airline.get(params.id)
        if (!airlineInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [airlineInstance: airlineInstance]
        }
    }

    def update = {
        def airlineInstance = Airline.get(params.id)
        if (airlineInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (airlineInstance.version > version) {

                    airlineInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'airline.label', default: 'Airline')] as Object[], "Another user has updated this Airline while you were editing")
                    render(view: "edit", model: [airlineInstance: airlineInstance])
                    return
                }
            }
            airlineInstance.properties = params
            if (!airlineInstance.hasErrors() && airlineInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'airline.label', default: 'Airline'), airlineInstance.id])}"
                redirect(action: "show", id: airlineInstance.id)
            }
            else {
                render(view: "edit", model: [airlineInstance: airlineInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def airlineInstance = Airline.get(params.id)
        if (airlineInstance) {
            try {
                airlineInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'airline.label', default: 'Airline'), params.id])}"
            redirect(action: "list")
        }
    }
}
