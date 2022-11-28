/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.access.to.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.opentcs.access.to.CreationTO;
import org.opentcs.data.ObjectHistory;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.Point;
import org.opentcs.data.model.TCSResourceReference;
import org.opentcs.data.model.Triple;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.TCSObject;
import static org.opentcs.data.model.Vehicle.ROUTE_INDEX_DEFAULT;
import org.opentcs.data.order.OrderConstants;
import org.opentcs.data.order.OrderSequence;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.drivers.vehicle.LoadHandlingDevice;
import static org.opentcs.util.Assertions.checkArgument;
import static org.opentcs.util.Assertions.checkInRange;

/**
 *
 * @author 40018799
 */
public class VehicleCreationTO 
    extends CreationTO
    implements Serializable {
 
   
  private String name;
  
  
  public static int ROUTE_INDEX_DEFAULT = -1;
   public static String PREFERRED_ADAPTER = "tcs:preferredAdapterClass";
  private int length;
  private int energyLevelGood;
  private int energyLevelCritical;
  private int energyLevelFullyRecharged;
  
  
  private int energyLevelSufficientlyRecharged;
  
  
  private int energyLevel;
 
  
  private int maxVelocity;
  
  
  private int maxReverseVelocity;
 
  
  private String rechargeOperation;
  
  
  private List<LoadHandlingDevice> loadHandlingDevices;
  
  
  private org.opentcs.data.model.Vehicle.State state;
  
  
  private org.opentcs.data.model.Vehicle.ProcState procState;
  
  
  private org.opentcs.data.model.Vehicle.IntegrationLevel integrationLevel;
  
  
  private boolean paused;
    
  private TCSObjectReference<TransportOrder> transportOrder;
 
  
  private TCSObjectReference<OrderSequence> orderSequence;
  
  
  private Set<String> allowedOrderTypes;
  
  
  private int routeProgressIndex;
  
  
  private List<Set<TCSResourceReference<?>>> claimedResources;
  
  
  private List<Set<TCSResourceReference<?>>> allocatedResources;
  
  
  private TCSObjectReference<Point> currentPosition;
  
  
  private TCSObjectReference<Point> nextPosition;
  
  
  private Triple precisePosition;
  
  
  private double orientationAngle;
  
  
  private org.opentcs.data.model.Vehicle.Layout layout;
  
  
  private int voltage;
  
  
  private int current;
  
  
  private int temperature;

  
  
  public VehicleCreationTO(@Nonnull String name,@Nonnull int maxVelocity, @Nonnull int energyLevel) {
    super(name);
      this.maxVelocity = maxVelocity;
      this.energyLevel = energyLevel;
      
  }

  private VehicleCreationTO(@Nonnull String name,
                  int length,
                  int energyLevel,
                  int energyLevelGood,
                  int energyLevelCritical,
                  int energyLevelFullyRecharged,
                  int energyLevelSufficientlyRecharged,
                  int maxVelocity,
                  int maxReverseVelocity,
                  String rechargeOperation,
                  Vehicle.ProcState procState,
                  TCSObjectReference<TransportOrder> transportOrder,
                  TCSObjectReference<OrderSequence> orderSequence,
                  Set<String> allowedOrderTypes,
                  int routeProgressIndex,
                  List<Set<TCSResourceReference<?>>> claimedResources,
                  List<Set<TCSResourceReference<?>>> allocatedResources,
                  Vehicle.State state,
                  Vehicle.IntegrationLevel integrationLevel,
                  boolean paused,
                  TCSObjectReference<Point> currentPosition,
                  TCSObjectReference<Point> nextPosition,
                  Triple precisePosition,
                  double orientationAngle,
                  Vehicle.Layout layout,
                  int voltage,
                  int current,
                  int temperature) {
    super(name);
    this.length = checkInRange(length, 1, Integer.MAX_VALUE, "length");
    this.energyLevelGood = checkInRange(energyLevelGood, 0, 100, "energyLevelGood");
    this.energyLevelCritical = checkInRange(energyLevelCritical, 0, 100, "energyLevelCritical");
    this.energyLevelFullyRecharged = checkInRange(energyLevelFullyRecharged,
                                                  0,
                                                  100,
                                                  "energyLevelFullyRecharged");
    this.energyLevelSufficientlyRecharged = checkInRange(energyLevelSufficientlyRecharged,
                                                         0,
                                                         100,
                                                         "energyLevelSufficientlyRecharged");
    this.maxVelocity = checkInRange(maxVelocity, 0, Integer.MAX_VALUE, "maxVelocity");
    this.maxReverseVelocity = checkInRange(maxReverseVelocity,
                                           0,
                                           Integer.MAX_VALUE,
                                           "maxReverseVelocity");
    this.rechargeOperation = requireNonNull(rechargeOperation, "rechargeOperation");
    this.procState = requireNonNull(procState, "procState");
    this.transportOrder = transportOrder;
    this.orderSequence = orderSequence;
    this.allowedOrderTypes = requireNonNull(allowedOrderTypes, "allowedOrderTypes");
    this.routeProgressIndex = routeProgressIndex;
    this.claimedResources = requireNonNull(claimedResources, "claimedResources");
    this.allocatedResources = requireNonNull(allocatedResources, "allocatedResources");
    this.state = requireNonNull(state, "state");
    this.integrationLevel = requireNonNull(integrationLevel, "integrationLevel");
    this.paused = paused;
    this.currentPosition = currentPosition;
    this.nextPosition = nextPosition;
    this.precisePosition = precisePosition;
    checkArgument(Double.isNaN(orientationAngle)
        || (orientationAngle >= -360.0 && orientationAngle <= 360.0),
                  "Illegal orientation angle: %s",
                  orientationAngle);
    this.orientationAngle = orientationAngle;
    this.energyLevel = checkInRange(energyLevel, 0, 100, "energyLevel");
    this.layout = requireNonNull(layout, "layout");
    this.voltage = checkInRange(voltage,
                                                  0,
                                                  40,
                                                  "voltage");
    this.current = checkInRange(current,
                                                  0,
                                                  40,
                                                  "current");
    this.temperature = checkInRange(temperature,
                                                  0,
                                                  60,
                                                  "temperature");
    
  }
  
  public VehicleCreationTO withmaxVelocity(@Nonnull int maxVelocity){
    return new VehicleCreationTO(name,
                       length,
                       energyLevel,
                       energyLevelGood,
                       energyLevelCritical,
                       energyLevelFullyRecharged,
                       energyLevelSufficientlyRecharged,
                       maxVelocity,
                       maxReverseVelocity,
                       rechargeOperation,
                       procState,
                       transportOrder,
                       orderSequence,
                       allowedOrderTypes,
                       routeProgressIndex,
                       claimedResources,
                       allocatedResources,
                       state,
                       integrationLevel,
                       paused,
                       currentPosition,
                       nextPosition,
                       precisePosition,
                       orientationAngle,
                       layout,
                       voltage,
                       current,
                       temperature);
  }
  
  public VehicleCreationTO withenergyLevel(@Nonnull int maxVelocity){
    return new VehicleCreationTO(name,
                       length,
                       energyLevel,
                       energyLevelGood,
                       energyLevelCritical,
                       energyLevelFullyRecharged,
                       energyLevelSufficientlyRecharged,
                       maxVelocity,
                       maxReverseVelocity,
                       rechargeOperation,
                       procState,
                       transportOrder,
                       orderSequence,
                       allowedOrderTypes,
                       routeProgressIndex,
                       claimedResources,
                       allocatedResources,
                       state,
                       integrationLevel,
                       paused,
                       currentPosition,
                       nextPosition,
                       precisePosition,
                       orientationAngle,
                       layout,
                       voltage,
                       current,
                       temperature);
  }
  
}
