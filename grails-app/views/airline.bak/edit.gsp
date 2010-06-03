
<%@ page import="grailstripexample.Airline" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'airline.label', default: 'Airline')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${airlineInstance}">
            <div class="errors">
                <g:renderErrors bean="${airlineInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${airlineInstance?.id}" />
                <g:hiddenField name="version" value="${airlineInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="airline.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="100" value="${airlineInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="url"><g:message code="airline.url.label" default="Url" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'url', 'errors')}">
                                    <g:textField name="url" value="${airlineInstance?.url}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="frequentFlyer"><g:message code="airline.frequentFlyer.label" default="Frequent Flyer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'frequentFlyer', 'errors')}">
                                    <g:textField name="frequentFlyer" value="${airlineInstance?.frequentFlyer}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="airline.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'notes', 'errors')}">
                                    <g:textArea name="notes" cols="40" rows="5" value="${airlineInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="trip"><g:message code="airline.trip.label" default="Trip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: airlineInstance, field: 'trip', 'errors')}">
                                    <g:select name="trip" from="${grailstripexample.Trip.list()}" multiple="yes" optionKey="id" size="5" value="${airlineInstance?.trip*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
