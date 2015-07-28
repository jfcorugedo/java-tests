package es.juan.graph.neo4j;

import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import static es.juan.graph.neo4j.NodeCreationTest.CauseByRelationship.*;

public class NodeCreationTest {

	@Test
	public void testCreateNodes() {
		
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("/Users/jfcorugedo/Documents/neo4j/");
		
		try(Transaction tx = graphDb.beginTx()) {
			Node nodeA = graphDb.createNode();
			nodeA.setProperty("name", "A");
			Node nodeB = graphDb.createNode();
			nodeB.setProperty("name", "B");
			
			Relationship rel = nodeA.createRelationshipTo(nodeB, CAUSE_BY);
			
			
			System.out.println(nodeA);
			System.out.println(nodeB);
			//tx.success();
		}
	}
	
	public static enum CauseByRelationship implements RelationshipType {
		CAUSE_BY
	}
}
