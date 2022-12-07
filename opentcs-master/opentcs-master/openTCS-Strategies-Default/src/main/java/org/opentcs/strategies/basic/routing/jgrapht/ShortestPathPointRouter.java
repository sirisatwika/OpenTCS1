/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.strategies.basic.routing.jgrapht;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.opentcs.components.kernel.routing.Edge;
import org.opentcs.components.kernel.services.TCSObjectService;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.Point;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.order.Route;
import org.opentcs.strategies.basic.routing.PointRouter;
import static org.opentcs.strategies.basic.routing.PointRouter.INFINITE_COSTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Computes routes between points using a JGraphT-based shortest path algorithm.
 * <p>
 * <em>Note that this implementation does not integrate static routes.</em>
 * </p>
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public class ShortestPathPointRouter
    implements PointRouter {

  /**
   * This class's logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ShortestPathPointRouter.class);

  private final ShortestPathAlgorithm<String, Edge> algo;
  
  private final AllPathAlgorithm ag;
  private  Set<Vehicle> vs;
  private final Map<String, Point> points = new HashMap<>();
  private final TCSObjectService objectService;

  public ShortestPathPointRouter(ShortestPathAlgorithm<String, Edge> algo,Graph<String,Edge> g,
                                 Collection<Point> points,TCSObjectService objectService) {
    this.algo = requireNonNull(algo, "algo");
    this.ag = requireNonNull(new AllPathAlgorithm(g));
    requireNonNull(points, "points");
    for (Point point : points) {
      this.points.put(point.getName(), point);
    }
     this.objectService = requireNonNull(objectService); 
  }

  @Override
  public List<Route.Step> getRouteSteps(Point srcPoint, Point destPoint) {
    requireNonNull(srcPoint, "srcPoint");
    requireNonNull(destPoint, "destPoint");

    long timeBefore = System.currentTimeMillis();
    if (Objects.equals(srcPoint.getName(), destPoint.getName())) {
      return new ArrayList<>();
    }

    GraphPath<String, Edge> graphPath = algo.getPath(srcPoint.getName(), destPoint.getName());
    System.out.println("graphpath : " + graphPath);
    if (graphPath == null) {
      return null;
    }
    
    vs =  objectService.fetchObjects(Vehicle.class);
    List<List<String>> allpaths = ag.getpaths(srcPoint.getName(), destPoint.getName());
    System.out.println("allpaths");
    System.out.println(allpaths);
    //System.out.println(vs);
    List<String> vpts = new ArrayList<>();
    for(Vehicle tempv : vs){
      if(tempv.getCurrentPosition().getName().compareTo(srcPoint.getName()) != 0){
          vpts.add(tempv.getCurrentPosition().getName());
      }
    }
   // System.out.println(vpts);
   List<List<String>> accpaths = new ArrayList<>();
   //accpath = allpaths;
   Set<Integer> indices = new HashSet<>();
   
   for(String pt:vpts){
     for(int i=0;i<allpaths.size();i++){
       List<String> path = allpaths.get(i);
          //System.out.println("for for "+path);
       if(path.contains(pt)){
           // System.out.println("adding to indices");
         indices.add(i);
       }
     }
   }
   //System.out.println("list of indices "+indices);
  
  int j;
   for(j = 0;j < allpaths.size();j++){
     if(!indices.contains(j)){
       List<String> p = allpaths.get(j);
       accpaths.add(p);
     }
   }
   System.out.println("Accepted paths:\n"+accpaths);
   //int arr[] = new int[accpaths.size()];
   List<Integer> lpath = new ArrayList<>();
    for(int k = 0;k < accpaths.size();k++){
        lpath.add(accpaths.get(k).size());
    }
    System.out.println(lpath);
    //Collections.sort(lpath);
    
    
    int minindex = 0;
    int mini = 999;
    for(int i = 0;i < lpath.size();i++){
      int y = lpath.get(i);
      if(y < mini){
        mini = y;
        minindex = i;
      }
    }
    System.out.println("minidex" +minindex);
    
    System.out.println("mini" +mini);
    System.out.println("shortestpath" + accpaths.get(minindex));
    //System.out.println(lpath.get(minindex));
    
   // Integer min = Collections.min(lpath);
    //System.out.println(min);
   // List<String> choosepath = accpaths.get(min);
    //System.out.println(choosepath);
 /* List<String> lpath = accpaths.get(0);
    for(List<String> path : accpaths ){
             if(lpath!=null){
                 if(lpath.size()>path.size()){
                   lpath = path;
                 }
             }
    }
    
    System.out.println("Shortestpath:\n"+lpath);*/

    List<Route.Step> result = translateToSteps(graphPath);
    //System.out.println("in sppr" + result);
    LOG.debug("Looking up route from {} to {} took {} milliseconds.",
              srcPoint.getName(),
              destPoint.getName(),
              System.currentTimeMillis() - timeBefore);

    return result;
  }

  @Override
  public long getCosts(TCSObjectReference<Point> srcPointRef,
                       TCSObjectReference<Point> destPointRef) {
    requireNonNull(srcPointRef, "srcPointRef");
    requireNonNull(destPointRef, "destPointRef");

    if (Objects.equals(srcPointRef.getName(), destPointRef.getName())) {
      return 0;
    }

    GraphPath<String, Edge> graphPath = algo.getPath(srcPointRef.getName(),
                                                          destPointRef.getName());
    if (graphPath == null) {
      return INFINITE_COSTS;
    }

    return (long) graphPath.getWeight();
  }

  private List<Route.Step> translateToSteps(GraphPath<String, Edge> graphPath) {
    List<Edge> edges = graphPath.getEdgeList();
    List<Route.Step> result = new ArrayList<>(edges.size());

    int routeIndex = 0;
    for (Edge edge : edges) {
      Point sourcePoint = points.get(graphPath.getGraph().getEdgeSource(edge));
      Point destPoint = points.get(graphPath.getGraph().getEdgeTarget(edge));

      result.add(new Route.Step(edge.getPath(),
                                sourcePoint,
                                destPoint,
                                orientation(edge, sourcePoint),
                                routeIndex));
      routeIndex++;
    }

    return result;
  }

  private Vehicle.Orientation orientation(Edge edge, Point graphSourcePoint) {
    return Objects.equals(edge.getPath().getSourcePoint(), graphSourcePoint.getReference())
        ? Vehicle.Orientation.FORWARD
        : Vehicle.Orientation.BACKWARD;
  }
  
  
  
}
