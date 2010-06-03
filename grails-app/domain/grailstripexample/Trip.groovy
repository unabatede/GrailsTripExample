package grailstripexample

class Trip {
  static belongsTo = [airline:Airline]

  String name
  String city
  Date startDate
  Date endDate
  String purpose
  String notes

  Airline airline
}
