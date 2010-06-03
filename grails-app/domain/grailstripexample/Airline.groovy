package grailstripexample

class Airline {
  static constraints = {
    name(blank:false, maxSize:100)
    url(url:true)
    frequentFlyer(blank:true)
    notes(maxSize:1500)
  }

  static hasMany = [trip:Trip]

  String name
  String url
  String frequentFlyer
  String notes

  String toString() {
    return name
  }
}