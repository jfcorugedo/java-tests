package es.juan.graph.jgrapht;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.Test;
import static com.vectorsf.kmd.common.collection.CollectionUtils.*;

public class JGraphTTest {

	/** Set this flag to true to view sysout traces */
	private static final boolean DEBUG = false;

	@Test
	public void basicJGraphTTest() {
		// constructs a directed graph with the specified vertices and edges
        DirectedGraph<String, Edge> directedGraph = buildBasicGraph();

        // computes all the strongly connected components of the directed graph
        StrongConnectivityInspector<String, Edge> sci =
            new StrongConnectivityInspector<String, Edge>(directedGraph);
        List<DirectedSubgraph<String, Edge>> 
        		stronglyConnectedSubgraphs = sci.stronglyConnectedSubgraphs();

        // prints the strongly connected components
        log("Strongly connected components:");
        if(DEBUG) {
	        stronglyConnectedSubgraphs.forEach(System.out::println);
        }

        // Prints the shortest path from vertex i to vertex c. This certainly
        // exists for our particular directed graph.
        log("Shortest path from i to c:");
        
        List<Edge> path =
            DijkstraShortestPath.findPathBetween(directedGraph, "i", "c");
        
        log(path.toString());
        assertThat(path).hasSize(4);

        // Prints the shortest path from vertex c to vertex i. This path does
        // NOT exist for our particular directed graph. Hence the path is
        // empty and the variable "path"; must be null.
        log("Shortest path from c to i:");
        path = DijkstraShortestPath.findPathBetween(directedGraph, "c", "i");
        assertThat(path).isNull();
    }
	
	@Test
	public void findParentsOfY() {
		
		log("\nFind parents of Y");
		DirectedGraph<String, Edge> directedGraph = buildTestGraph();
		
		Set<Edge> incomingEdge = directedGraph.incomingEdgesOf("Y");
		
		if(DEBUG) {
			incomingEdge.stream().forEach(System.out::println);
		}
		
		assertThat(incomingEdge).hasSize(5);
		assertThat(incomingEdge
					.stream()
						.map(
							edge -> edge.getSource()
							)
						.collect(toList()))
		.containsExactly("Q", "R", "Uy", "W", "Z");
	}
	
	@Test
	public void findParentsOfQ() {
		
		log("\nFind parents of Q");
		DirectedGraph<String, Edge> directedGraph = buildTestGraph();
		
		Set<Edge> incomingEdge = directedGraph.incomingEdgesOf("Q");
		
		if(DEBUG) {
			incomingEdge.stream().forEach(System.out::println);
		}
		
		assertThat(incomingEdge).hasSize(3);
		assertThat(incomingEdge
					.stream()
						.map(
							edge -> edge.getSource()
							)
						.collect(toList()))
		.containsExactly("X", "Uq", "Uy");
	}
	
	@Test
	public void findAllDirectedPathsFromXToY() {
		
		log("\nFind paths from X to Y");
		
		DirectedGraph<String, Edge> directedGraph = buildTestGraph();
		KShortestPaths<String, Edge> pathInspector = 
												new KShortestPaths<String, Edge>(
															directedGraph, 
															"X", 
															Integer.MAX_VALUE, 
															Integer.MAX_VALUE
														);
		
		
		List<GraphPath<String, Edge>> paths = pathInspector.getPaths("Y");
		if(DEBUG) {
			paths.stream().forEach(System.out::println);
		}
		
		assertThat(paths).hasSize(2);
		assertThat(
				paths.stream()
					.flatMap(
							graph -> graph.getEdgeList().stream()
					)
					.collect(Collectors.toList()))
		.containsExactly(newEdge("X", "Q"), newEdge("Q", "Y"), newEdge("X", "R"), newEdge("R", "Y"));
	}
	
	private DirectedGraph<String, Edge> buildBasicGraph() {
		
		DirectedGraph<String, Edge> directedGraph =
	            new DefaultDirectedGraph<String, Edge>
	            (Edge.class);
		directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        directedGraph.addEdge("h", "e");
        directedGraph.addEdge("i", "h");
        
        return directedGraph;
	}

	/**
	 * Builds a test graph as follows:
	 * 
	 * @return
	 */
	private DirectedGraph<String, Edge> buildTestGraph() {
		DirectedGraph<String, Edge> directedGraph =
            new DefaultDirectedGraph<String, Edge>
            (Edge.class);
        directedGraph.addVertex("X");
        directedGraph.addVertex("Q");
        directedGraph.addVertex("R");
        directedGraph.addVertex("Y");
        directedGraph.addVertex("Ur");
        directedGraph.addVertex("Uq");
        directedGraph.addVertex("Uy");
        directedGraph.addVertex("Ñ");
        directedGraph.addVertex("W");
        directedGraph.addVertex("T");
        directedGraph.addVertex("Z");
        directedGraph.addVertex("M");
        directedGraph.addVertex("Uz");
        directedGraph.addVertex("Um");
        directedGraph.addEdge("X", "Q");
        directedGraph.addEdge("X", "R");
        directedGraph.addEdge("Uq", "Q");
        directedGraph.addEdge("Ur", "R");
        directedGraph.addEdge("Q", "Y");
        directedGraph.addEdge("R", "Y");
        directedGraph.addEdge("Uy", "Y");
        directedGraph.addEdge("Uy", "Q");
        directedGraph.addEdge("Uy", "Ñ");
        directedGraph.addEdge("W", "Y");
        directedGraph.addEdge("T", "W");
        directedGraph.addEdge("Z", "Y");
        directedGraph.addEdge("M", "Z");
        directedGraph.addEdge("Uz", "Z");
        directedGraph.addEdge("Um", "M");
		return directedGraph;
	}
	
	public Edge newEdge(String source, String target) {
		
		
		return new Edge(source, target);
	}
	
	@Test
	public void detectCycles() {
		CycleDetector<String, Edge> cycleDetector = new CycleDetector<String, Edge>(buildBasicGraph());
		
		assertThat(cycleDetector.detectCycles()).isTrue();
		assertThat(cycleDetector.findCyclesContainingVertex("a")).containsExactly("a", "b", "c", "d");
	}
	
	@Test
	public void getAncestorsOrDescendants() {
		
		log("Finding ancestors or descendants....");
		DirectedGraph<String, Edge> directedGraph = buildTestGraph();
		
		List<String> B = newList("Uq", "Uy", "W", "Z", "Ur");
		List<String> D = newList("Q", "R", "Y");
		
		List<String> F = new ArrayList<String>();
		B.stream()
			.forEach(
				node -> {
					extractNeighbours(directedGraph, B, D, F, node);
				}
			);
		
		log(F.toString());
		assertThat(F).hasSize(5);
		assertThat(F).containsOnly("Ñ", "T", "M", "Uz", "Um");
	}
	
	@Test
	public void getAncestorsAndDescendansInCyclingGraph() {
		
		log("Finding ancestors or descendants in cycling graph...");
		DirectedGraph<String, Edge> directedGraph = buildBasicGraph();
		
		List<String> B = newList("e");
		List<String> D = newList("b", "d");
		
		List<String> F = new ArrayList<String>();
		B.stream()
			.forEach(
				node -> {
					extractNeighbours(directedGraph, B, D, F, node);
				}
			);
		
		log(F.toString());
		assertThat(F).hasSize(4);
		assertThat(F).containsOnly("f", "g", "h", "i");
	}

	private void extractNeighbours(DirectedGraph<String, Edge> directedGraph, List<String> B, List<String> D,
			List<String> F, String node) {
		List<String> neighbours = 
				Graphs.neighborListOf(directedGraph, node)
					.stream()
						.filter(
							neighbour -> !B.contains(neighbour) && !D.contains(neighbour) && !F.contains(neighbour)
						)
						.collect(toList());
		
		F.addAll(neighbours);
		
		neighbours.stream().forEach(neighbour -> extractNeighbours(directedGraph, B, D, F, neighbour));
	}
	
	@Test
	public void getUndirectedPaths() {
		
		log("Calculate proxy effect blanket....");
		DirectedGraph<String, Edge> directedGraph = buildTestGraph();
		
		UndirectedGraph<String, Edge> undirectedGraph = Graphs.undirectedGraph(directedGraph);
		
		KShortestPaths<String, Edge> pathInspector = 
				new KShortestPaths<String, Edge>(
						undirectedGraph, 
				"Ñ", 
				Integer.MAX_VALUE, 
				Integer.MAX_VALUE
			);
		
		List<GraphPath<String,Edge>> result = pathInspector.getPaths("Y");
		
		assertThat(result).hasSize(3);
	}
	
//	private Object isFullyConnectedToGoal(String node, String goal, List<String> observedNodes, Graph<String, Edge> graph) {
//		KShortestPaths<String, Edge> pathInspector = 
//				new KShortestPaths<String, Edge>(
//							graph, 
//							node, 
//							Integer.MAX_VALUE, 
//							Integer.MAX_VALUE
//						);
//
//
//		List<GraphPath<String, Edge>> paths = pathInspector.getPaths(goal);
//		
//		return paths.stream().map(path -> path.getEdgeList().stream().map(edge -> edge.get)).filter(path -> path.);
//	}

	@Test
	public void performance() {
		long time = System.currentTimeMillis();

        reportPerformanceFor("starting at", time);

        Graph<Object, Edge> g =
            new Pseudograph<Object, Edge>(Edge.class);
        Object prev;
        Object curr;

        curr = prev = new Object();
        g.addVertex(prev);

        int numVertices = 10000;
        int numEdgesPerVertex = 200;
        int numElements = numVertices * (1 + numEdgesPerVertex);

        log(
            "\n" + "allocating graph with " + numElements
            + " elements (may take a few tens of seconds)...");

        for (int i = 0; i < numVertices; i++) {
            curr = new Object();
            g.addVertex(curr);

            for (int j = 0; j < numEdgesPerVertex; j++) {
                g.addEdge(prev, curr);
            }

            prev = curr;
        }

        reportPerformanceFor("graph allocation", time);

        time = System.currentTimeMillis();

        for (
            Iterator<Object> i =
                new BreadthFirstIterator<Object, Edge>(g);
            i.hasNext();)
        {
            i.next();
        }

        reportPerformanceFor("breadth traversal", time);

        time = System.currentTimeMillis();

        for (
            Iterator<Object> i = new DepthFirstIterator<Object, Edge>(g);
            i.hasNext();)
        {
            i.next();
        }

        reportPerformanceFor("depth traversal", time);

        log("done.");
    }

    private void reportPerformanceFor(String msg, long refTime)
    {
        double time = (System.currentTimeMillis() - refTime) / 1000.0;
        double mem = usedMemory()
            / (1024.0 * 1024.0);
        mem = Math.round(mem * 100) / 100.0;
        log(msg + " (" + time + " sec, " + mem + "MB)");
    }

    private static long usedMemory()
    {
        Runtime rt = Runtime.getRuntime();

        return rt.totalMemory() - rt.freeMemory();
    }
    
    private void log(String message) {
    	if(DEBUG) {
    		log(message);
    	}
    }
}
