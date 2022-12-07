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
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.opentcs.access.to.model.LocationTypeCreationTO;
import org.opentcs.components.kernel.services.TransportOrderService;
import org.opentcs.components.kernel.services.VehicleService;
import org.opentcs.customizations.kernel.KernelExecutor;
import org.opentcs.data.model.Location;
import org.opentcs.data.model.Location.Link;
import org.opentcs.data.model.LocationType;

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
  

  @JsonProperty(required = true)
  @JsonPropertyDescription("The destination operation")
  private String operation = "";

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

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

 
  public static Locationstate fromlocation(Location location) {
    if (location == null) {
      return null;
    }
    Locationstate locationstate = new Locationstate();
    
   locationstate.setLocationName(location.getName());
   locationstate.setLocationType(location.getType().getName());
        
    return locationstate;
  } 

  @Override
  public String toString() {
    return "Locationstate{" + "locationName=" + locationName + ", locationType=" + locationType + ", operation=" + operation + '}';
  }

  public Locationstate() {
  }
}
