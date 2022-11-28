/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel.extensions.servicewebapi.v1.order.binding;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.List;
import java.util.Set;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.Point;
import org.opentcs.data.model.TCSResourceReference;
import org.opentcs.data.model.Triple;
import org.opentcs.data.order.OrderSequence;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.drivers.vehicle.LoadHandlingDevice;


/**
 *
 * @author 40018799
 */
public class VehicleC {
  @JsonPropertyDescription("a")
  private String name;
  
  @JsonPropertyDescription("a1")
  public static int ROUTE_INDEX_DEFAULT = -1;
  
  @JsonPropertyDescription("a2")
  public static String PREFERRED_ADAPTER = "tcs:preferredAdapterClass";
  
  @JsonPropertyDescription("a3")
  private int length;
  
  @JsonPropertyDescription("a4")
  private int energyLevelGood;
  
  @JsonPropertyDescription("a")
  private int energyLevelCritical;
  
  @JsonPropertyDescription("a")
  private int energyLevelFullyRecharged;
  
  @JsonPropertyDescription("a")
  private int energyLevelSufficientlyRecharged;
  
  @JsonPropertyDescription("a")
  private int energyLevel;
 
  @JsonPropertyDescription("a")
  private int maxVelocity;
  
  @JsonPropertyDescription("a")
  private int maxReverseVelocity;
 
  @JsonPropertyDescription("a")
  private String rechargeOperation;
  
  @JsonPropertyDescription("a")
  private List<LoadHandlingDevice> loadHandlingDevices;
  
  @JsonPropertyDescription("a")
  private org.opentcs.data.model.Vehicle.State state;
  
  @JsonPropertyDescription("a")
  private org.opentcs.data.model.Vehicle.ProcState procState;
  
  @JsonPropertyDescription("a")
  private org.opentcs.data.model.Vehicle.IntegrationLevel integrationLevel;
  
  @JsonPropertyDescription("a")
  private boolean paused;
  
  @JsonPropertyDescription("a")
  private TCSObjectReference<TransportOrder> transportOrder;
 
  @JsonPropertyDescription("a")
  private TCSObjectReference<OrderSequence> orderSequence;
  
  @JsonPropertyDescription("a")
  private Set<String> allowedOrderTypes;
  
  @JsonPropertyDescription("a")
  private int routeProgressIndex;
  
  @JsonPropertyDescription("a")
  private List<Set<TCSResourceReference<?>>> claimedResources;
  
  @JsonPropertyDescription("a")
  private List<Set<TCSResourceReference<?>>> allocatedResources;
  
  @JsonPropertyDescription("a")
  private TCSObjectReference<Point> currentPosition;
  
  @JsonPropertyDescription("a")
  private TCSObjectReference<Point> nextPosition;
  
  @JsonPropertyDescription("a")
  private Triple precisePosition;
  
  @JsonPropertyDescription("a")
  private double orientationAngle;
  
  @JsonPropertyDescription("a")
  private org.opentcs.data.model.Vehicle.Layout layout;
  
  @JsonPropertyDescription("a")
  private int voltage;
  
  @JsonPropertyDescription("a")
  private int current;
  
  @JsonPropertyDescription("a")
  private int temperature;

  public static int getROUTE_INDEX_DEFAULT() {
    return ROUTE_INDEX_DEFAULT;
  }

  public static void setROUTE_INDEX_DEFAULT(int ROUTE_INDEX_DEFAULT) {
    VehicleC.ROUTE_INDEX_DEFAULT = ROUTE_INDEX_DEFAULT;
  }

  public static String getPREFERRED_ADAPTER() {
    return PREFERRED_ADAPTER;
  }

  public static void setPREFERRED_ADAPTER(String PREFERRED_ADAPTER) {
    VehicleC.PREFERRED_ADAPTER = PREFERRED_ADAPTER;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getEnergyLevelGood() {
    return energyLevelGood;
  }

  public void setEnergyLevelGood(int energyLevelGood) {
    this.energyLevelGood = energyLevelGood;
  }

  public int getEnergyLevelCritical() {
    return energyLevelCritical;
  }

  public void setEnergyLevelCritical(int energyLevelCritical) {
    this.energyLevelCritical = energyLevelCritical;
  }

  public int getEnergyLevelFullyRecharged() {
    return energyLevelFullyRecharged;
  }

  public void setEnergyLevelFullyRecharged(int energyLevelFullyRecharged) {
    this.energyLevelFullyRecharged = energyLevelFullyRecharged;
  }

  public int getEnergyLevelSufficientlyRecharged() {
    return energyLevelSufficientlyRecharged;
  }

  public void setEnergyLevelSufficientlyRecharged(int energyLevelSufficientlyRecharged) {
    this.energyLevelSufficientlyRecharged = energyLevelSufficientlyRecharged;
  }

  public int getEnergyLevel() {
    return energyLevel;
  }

  public void setEnergyLevel(int energyLevel) {
    this.energyLevel = energyLevel;
  }

  public int getMaxVelocity() {
    return maxVelocity;
  }

  public void setMaxVelocity(int maxVelocity) {
    this.maxVelocity = maxVelocity;
  }

  public int getMaxReverseVelocity() {
    return maxReverseVelocity;
  }

  public void setMaxReverseVelocity(int maxReverseVelocity) {
    this.maxReverseVelocity = maxReverseVelocity;
  }

  public String getRechargeOperation() {
    return rechargeOperation;
  }

  public void setRechargeOperation(String rechargeOperation) {
    this.rechargeOperation = rechargeOperation;
  }

  public List<LoadHandlingDevice> getLoadHandlingDevices() {
    return loadHandlingDevices;
  }

  public void setLoadHandlingDevices(List<LoadHandlingDevice> loadHandlingDevices) {
    this.loadHandlingDevices = loadHandlingDevices;
  }

  public org.opentcs.data.model.Vehicle.State getState() {
    return state;
  }

  public void setState(org.opentcs.data.model.Vehicle.State state) {
    this.state = state;
  }

  public org.opentcs.data.model.Vehicle.ProcState getProcState() {
    return procState;
  }

  public void setProcState(org.opentcs.data.model.Vehicle.ProcState procState) {
    this.procState = procState;
  }

  public org.opentcs.data.model.Vehicle.IntegrationLevel getIntegrationLevel() {
    return integrationLevel;
  }

  public void setIntegrationLevel(org.opentcs.data.model.Vehicle.IntegrationLevel integrationLevel) {
    this.integrationLevel = integrationLevel;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public TCSObjectReference<TransportOrder> getTransportOrder() {
    return transportOrder;
  }

  public void setTransportOrder(TCSObjectReference<TransportOrder> transportOrder) {
    this.transportOrder = transportOrder;
  }

  public TCSObjectReference<OrderSequence> getOrderSequence() {
    return orderSequence;
  }

  public void setOrderSequence(TCSObjectReference<OrderSequence> orderSequence) {
    this.orderSequence = orderSequence;
  }

  public Set<String> getAllowedOrderTypes() {
    return allowedOrderTypes;
  }

  public void setAllowedOrderTypes(Set<String> allowedOrderTypes) {
    this.allowedOrderTypes = allowedOrderTypes;
  }

  public int getRouteProgressIndex() {
    return routeProgressIndex;
  }

  public void setRouteProgressIndex(int routeProgressIndex) {
    this.routeProgressIndex = routeProgressIndex;
  }

  public List<Set<TCSResourceReference<?>>> getClaimedResources() {
    return claimedResources;
  }

  public void setClaimedResources(
                                  List<Set<TCSResourceReference<?>>> claimedResources) {
    this.claimedResources = claimedResources;
  }

  public List<Set<TCSResourceReference<?>>> getAllocatedResources() {
    return allocatedResources;
  }

  public void setAllocatedResources(
                                    List<Set<TCSResourceReference<?>>> allocatedResources) {
    this.allocatedResources = allocatedResources;
  }

  public TCSObjectReference<Point> getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(TCSObjectReference<Point> currentPosition) {
    this.currentPosition = currentPosition;
  }

  public TCSObjectReference<Point> getNextPosition() {
    return nextPosition;
  }

  public void setNextPosition(TCSObjectReference<Point> nextPosition) {
    this.nextPosition = nextPosition;
  }

  public Triple getPrecisePosition() {
    return precisePosition;
  }

  public void setPrecisePosition(Triple precisePosition) {
    this.precisePosition = precisePosition;
  }

  public double getOrientationAngle() {
    return orientationAngle;
  }

  public void setOrientationAngle(double orientationAngle) {
    this.orientationAngle = orientationAngle;
  }

  public org.opentcs.data.model.Vehicle.Layout getLayout() {
    return layout;
  }

  public void setLayout(org.opentcs.data.model.Vehicle.Layout layout) {
    this.layout = layout;
  }

  public int getVoltage() {
    return voltage;
  }

  public void setVoltage(int voltage) {
    this.voltage = voltage;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }
    
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
}
