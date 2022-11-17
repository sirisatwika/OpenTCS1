/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel.extensions.servicewebapi.v1.order.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * A transport order to be processed by the kernel.
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public class Transport {

  @JsonPropertyDescription("Whether the name given for the transport order is incomplete")
  private boolean incompleteName;

  @JsonPropertyDescription("The (optional) deadline of the transport order")
  private Instant deadline;

  @JsonPropertyDescription("The (optional) intended vehicle of the transport order")
  private String intendedVehicle;

  @JsonPropertyDescription("The destinations")
  @JsonProperty(required = true)
  @Valid
  @Size(min = 1)
  private List<Destination> destinations = new LinkedList<>();


  /**
   * Creates a new instance.
   */
  public Transport() {
  }

  public boolean hasIncompleteName() {
    return incompleteName;
  }

  public void setIncompleteName(boolean incompleteName) {
    this.incompleteName = incompleteName;
  }

  public Instant getDeadline() {
    return deadline;
  }

  public void setDeadline(Instant deadline) {
    this.deadline = deadline;
  }

  public String getIntendedVehicle() {
    return intendedVehicle;
  }

  public void setIntendedVehicle(String intendedVehicle) {
    this.intendedVehicle = intendedVehicle;
  }

  public List<Destination> getDestinations() {
    return destinations;
  }

  public void setDestinations(List<Destination> destinations) {
    this.destinations = destinations;
  }

  
}
