/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.strategies.basic.routing.jgrapht;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.jgrapht.Graph;
import org.opentcs.components.kernel.routing.Edge;
import org.opentcs.data.model.Vehicle;
/**
 *
 * @author 40018799
 */


// JAVA program to print all
// paths from a source to
// destination.

// A directed graph using
// adjacency list representation
public class  AllPathAlgorithm  {

	// No. of vertices in graph
	private final Graph<String,Edge> graph;

	// adjacency list
	private Map<String,Set<String>> adjList;

  private  List<List<String>> allpaths;
	// Constructor
	public AllPathAlgorithm(Graph<String, Edge> graph)
	{
		// initialise vertex count
		this.graph = graph;
    this.adjList = new TreeMap<>();
    this.allpaths = new ArrayList<>();
		// initialise adjacency list
    //initAdjList();
	}
	//int v = graph.edgeSet().size();
//  private void initAdjList()
//	{
//		adjList=new HashMap<>();
//	}
//  
  @SuppressWarnings("unchecked")
  public void addEdge(String u, String v)
    {   
  // Add v to u's list.
      if(adjList == null){
          Set<String> s=new TreeSet<>();
          s.add(v);
          adjList.put(u,s);
      }else if(adjList.containsKey(u)){
          Set<String> s= adjList.get(u);
          s.add(v);
          adjList.put(u,s);
        }
        else
        {
          Set<String> s=new TreeSet<>();
          s.add(v);
          adjList.put(u,s);
        }
    }
  
  public void printAllPaths(String s, String d,Map<String,Set<String>> adjList)
  {
    //System.out.println("Inside printAllPaths");
    allpaths = new ArrayList<>();
    Map<String, Boolean> isVisited = new TreeMap<>();
    isVisited.put(s, Boolean.FALSE);
		ArrayList<String> pathList = new ArrayList<>();
		pathList.add(s);
		printAllPathsUtil(s, d,adjList, isVisited, pathList);
    
    
	}
	private void printAllPathsUtil(String u, String d,
                Map<String,Set<String>> adjList,
								Map<String, Boolean> isVisited,
								List<String> localPathList)	{
    System.out.println("Inside printAllPathsUtil "+ u +" to "+ d);  
		if (u.equals(d)) {
      ArrayList<String> paths = new ArrayList<>();
      for(String s:localPathList){
        paths.add(s);
      }
      allpaths.add(paths);
			return;
		}
		isVisited.put(u,Boolean.TRUE);
    Set<String> adj = adjList.get(u);
    if(adj != null){
		for (String i : adj) {
			if (isVisited.get(i) == null) {
				localPathList.add(i);
				printAllPathsUtil(i, d,adjList, isVisited, localPathList);
				localPathList.remove(i);
			}else if(isVisited.get(i) == Boolean.FALSE){
        localPathList.add(i);
				printAllPathsUtil(i, d,adjList, isVisited, localPathList);
				localPathList.remove(i);
      }
		}
    }
		isVisited.put(u,Boolean.FALSE);
	}

	public List<List<String>> getpaths(String s, String d)
	{
		// Create a sample graph
		//System.out.println("Graph:"+graph.edgeSet());
		//g.printAllPaths(s, d);
    //System.out.println("GraphSize:"+graph.edgeSet().size());
    adjList = new TreeMap<>();
    
    for(Edge e:graph.edgeSet()){
      String name = e.getPath().getName();
      String[] uv = name.split("---");
      addEdge(uv[0].trim(),uv[1].trim());
    }
    //System.out.println(adjList);
    printAllPaths(s,d,adjList);
    return allpaths;
	}
}



