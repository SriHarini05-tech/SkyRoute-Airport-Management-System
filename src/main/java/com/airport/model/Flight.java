package com.airport.model;

import jakarta.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNo;
    private String source;
    private String destination;
    private String departureTime;

    private int totalSeats;
    private int remainingSeats;

    private double ticketPrice;
    private String travelDate;

    public Flight(){}

    @PrePersist
    public void setInitialSeats(){
        remainingSeats = totalSeats;
    }

    public Long getId(){ return id; }

    public String getFlightNo(){ return flightNo; }
    public void setFlightNo(String flightNo){ this.flightNo = flightNo; }

    public String getSource(){ return source; }
    public void setSource(String source){ this.source = source; }

    public String getDestination(){ return destination; }
    public void setDestination(String destination){ this.destination = destination; }

    public String getDepartureTime(){ return departureTime; }
    public void setDepartureTime(String departureTime){ this.departureTime = departureTime; }

    public int getTotalSeats(){ return totalSeats; }
    public void setTotalSeats(int totalSeats){ this.totalSeats = totalSeats; }

    public int getRemainingSeats(){ return remainingSeats; }
    public void setRemainingSeats(int remainingSeats){ this.remainingSeats = remainingSeats; }

    public double getTicketPrice(){ return ticketPrice; }
    public void setTicketPrice(double ticketPrice){ this.ticketPrice = ticketPrice; }

    public String getTravelDate(){ return travelDate; }
public void setTravelDate(String travelDate){ this.travelDate = travelDate; }
}