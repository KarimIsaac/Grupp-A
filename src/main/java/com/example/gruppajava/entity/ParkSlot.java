package com.example.gruppajava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long zone_id;
  private boolean available;
  
  public ParkSlot(){
  }

  //ParkPriceZone zone
  public ParkSlot(long zone_id){ 
    this.zone_id=zone_id;
  }

  //ParkPriceZone zone
  public ParkSlot(long zone_id,boolean available){
    this.zone_id=zone_id;
    this.available=available;
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
  public long getZone_id() {
    return zone_id;
  }
  public void setZone_id(long zone_id) {
    this.zone_id = zone_id;
  }
  
  
}
