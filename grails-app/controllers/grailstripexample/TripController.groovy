package grailstripexample

import grails.converters.*

class TripController {

    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def trips = Trip.list()

        withFormat {
            html {
                params.max = Math.min(params.max ? params.int('max') : 10, 100)
                [tripInstanceList: Trip.list(params), tripInstanceTotal: Trip.count()]
            }
            xml { render trips as XML }
            json { render trips as JSON }
        }
    }

    def create = {
        def tripInstance = new Trip()
        tripInstance.properties = params
        withFormat {
            html {
                return [tripInstance: tripInstance]
            }
            xml {
                response.status = 201
                render ""
            }
            json {
                response.status = 201
                render ""
            }
        }
    }

    def save = {
        withFormat {
            html {
                def tripInstance = new Trip(params)
                if (tripInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.created.message', args: [message(code: 'trip.label', default: 'Trip'), tripInstance.id])}"
                    redirect(action: "show", id: tripInstance.id)
                }
                else {
                    render(view: "create", model: [tripInstance: tripInstance])
                }
            }
            xml {
                //def xml = request.XML.content()
                //def tripParams = params['trip']
                //log.error(params['trip'])
                log.error("Bla: " + request.XML.content.dump())
                def tripInstance = new Trip(request.XML.text())
  /*              def airlineInstance = Airline.get(1)
                tripInstance.airline = airlineInstance*/

                if (tripInstance.save(flush: true)) {
                    response.status = 201
                    render tripInstance as XML
                }
                else {
                    response.sendError(406)
                }
            }
            json {
                def tripInstance = new Trip(params['trip'])
                if (tripInstance.save(flush: true)) {
                    response.status = 201
                    render tripInstance as JSON
                }
                else {
                    response.sendError(406)
                }
            }
        }
    }

    def show = {
        def tripInstance = Trip.get(params.id)
        withFormat {
            html {
                if (!tripInstance) {
                    flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                    redirect(action: "list")
                }
                else {
                    [tripInstance: tripInstance]
                }
            }
            xml {
                if (tripInstance) {
                    render tripInstance as XML
                } else {
                    response.sendError(404)
                }
            }
            json {
                if (tripInstance) {
                    render tripInstance as JSON
                } else {
                    response.sendError(404)
                }
            }
        }

    }

    def edit = {
        def tripInstance = Trip.get(params.id)
        if (!tripInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tripInstance: tripInstance]
        }
    }

    def update = {
        def tripInstance = Trip.get(params.id)
        if (tripInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tripInstance.version > version) {

                    tripInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'trip.label', default: 'Trip')] as Object[], "Another user has updated this Trip while you were editing")
                    render(view: "edit", model: [tripInstance: tripInstance])
                    return
                }
            }
            tripInstance.properties = params
            if (!tripInstance.hasErrors() && tripInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'trip.label', default: 'Trip'), tripInstance.id])}"
                redirect(action: "show", id: tripInstance.id)
            }
            else {
                render(view: "edit", model: [tripInstance: tripInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tripInstance = Trip.get(params.id)
        withFormat {
            html {
                if (tripInstance) {
                    try {
                        tripInstance.delete(flush: true)
                        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                        redirect(action: "list")
                    }
                    catch (org.springframework.dao.DataIntegrityViolationException e) {
                        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                        redirect(action: "show", id: params.id)
                    }
                }
                else {
                    flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                    redirect(action: "list")
                }
            }

            xml {
                if (tripInstance) {
                    tripInstance.delete(flush: true)
                    response.sendError(200)
                }
                else {
                    response.sendError(404)
                }
            }

            json {

            }
        }

    }
}
