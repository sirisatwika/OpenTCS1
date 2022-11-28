/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel.extensions.servicewebapi.v1.status.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import org.opentcs.access.to.model.PlantModelCreationTO;
import java.util.HashSet;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import org.opentcs.access.to.model.LocationTypeCreationTO;
import org.opentcs.data.model.Location;
import org.opentcs.data.model.Location.Link;

/**
 *
 * @author 40018799
 */
public class Locationstate {
  
  @JsonProperty(required = true)
  @JsonPropertyDescription("The name of the destination location")
  private String locationName = "";
  
  @JsonProperty(required = true)
  @JsonPropertyDescription("The type of the location")
  private String locationType = "";
  

//  @JsonProperty(required = true)
//  @JsonPropertyDescription("The destination operation")
//  private Set<String> operation = new HashSet<>();

  public String getLocationName() {
    return locationName;
  }

  public String getLocationType() {
    return locationType;
  }

  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }


  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

//  public Set<String> getOperation() {
//    return operation;
//  }
//
//  public void setOperation(Set<String> operation) {
//    this.operation = requireNonNull(operation, "operation");
//  }
  public static Locationstate fromlocation(Location location) {
    if (location == null) {
      return null;
    }
    Locationstate locationstate = new Locationstate();
    Set<String> operations = new HashSet<>();
//    for(Link l:location.getAttachedLinks()){
//      for(String op:l.getAllowedOperations()){
//        operations.add(op);
//      }
//    }
    locationstate.setLocationName(location.getName());
   // locationstate.setOperation(operations);
   locationstate.setLocationType(location.getType().getName());
    
    return locationstate;
  } 
}
