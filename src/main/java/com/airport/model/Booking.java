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

    @ManyToOne
    private Flight flight;

    public Booking(){}

    public Long getId(){
        return id;
    }

    public String getPassengerName(){
        return passengerName;
    }

    public void setPassengerName(String passengerName){
        this.passengerName = passengerName;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getTickets(){
        return tickets;
    }

    public void setTickets(int tickets){
        this.tickets = tickets;
    }

    public Flight getFlight(){
        return flight;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }
}