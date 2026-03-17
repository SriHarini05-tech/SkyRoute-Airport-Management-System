package com.airport.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;

    private String username;

    private int tickets;

    private String seatNumbers;

    private double totalPrice;

    private String travelDate;

    // ✅ Used only for form (NOT DB)
    @Transient
    private Long flightId;

    // ✅ Actual DB relation
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Booking(){}

    public Long getId(){ return id; }

    public String getPassengerName(){ return passengerName; }
    public void setPassengerName(String passengerName){ this.passengerName = passengerName; }

    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }

    public int getTickets(){ return tickets; }
    public void setTickets(int tickets){ this.tickets = tickets; }

    public String getSeatNumbers(){ return seatNumbers; }
    public void setSeatNumbers(String seatNumbers){ this.seatNumbers = seatNumbers; }

    public double getTotalPrice(){ return totalPrice; }
    public void setTotalPrice(double totalPrice){ this.totalPrice = totalPrice; }

    public String getTravelDate(){ return travelDate; }
    public void setTravelDate(String travelDate){ this.travelDate = travelDate; }

    public Long getFlightId(){ return flightId; }
    public void setFlightId(Long flightId){ this.flightId = flightId; }

    public Flight getFlight(){ return flight; }
    public void setFlight(Flight flight){ this.flight = flight; }
}