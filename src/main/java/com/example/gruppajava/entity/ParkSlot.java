package com.example.gruppajava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ParkSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "price_zone_id", referencedColumnName = "id")
  private ParkingPriceZone priceZone;

  private boolean available;

  public ParkSlot() {
  }

  public ParkSlot(ParkingPriceZone priceZone) {
    this.priceZone = priceZone;
  }

  public ParkSlot(ParkingPriceZone priceZone, boolean available) {
    this.priceZone = priceZone;
    this.available = available;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean getAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public ParkingPriceZone getPriceZone() {
    return priceZone;
  }

  public void setPriceZone(ParkingPriceZone priceZone) {
    this.priceZone = priceZone;
  }
}
