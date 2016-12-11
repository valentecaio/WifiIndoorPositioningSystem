package Dijkstra.test;

import android.graphics.Path;

import com.example.andreseidel.interculturel.DepartureEqualsDestination;
import com.example.andreseidel.interculturel.IO;
import com.example.andreseidel.interculturel.PathNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Dijkstra.engine.DijkstraAlgorithm;
import Dijkstra.model.Edge;
import Dijkstra.model.Graph;
import Dijkstra.model.Vertex;

import static org.junit.Assert.*;

public class Test {

	private List<Vertex> nodes;
	private List<Edge> edges;

	@org.junit.Test
    public void Test(){}
	public List<String> test(String departure, String destination) throws DepartureEqualsDestination, PathNotFound{

		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 120; i++) {
			Vertex location = new Vertex(String.valueOf(i), String.valueOf(i));
			nodes.add(location);
		}

		// initialize dictionary
		ArrayList<String> dictionary = new ArrayList<String>();

		String[] points = { "RAK", "GYM", "TENNIS", "RUGBY", "A01", "A01-1", "A01-PORT1", "B01", "B01-1", "B01-PORT1",
				"B02", "B02-1", "B03-PORT1", "B03", "B03-PORT2", "B04", "B04-PORT1", "C01", "C01-1", "C02", "C02-1",
				"C03", "C04", "C05", "C06", "C06-1", "C07", "C07-1", "D01", "D01-1", "D01-PORT1", "D02", "D02-1", "D03",
				"D03-1", "D03-PORT1", "E01", "E01-1", "F01", "H01", "H01-PORT1", "H02", "I01-PORT1", "I01-PORT2", "I02",
				"I03-PORT1", "I03-PORT2", "I03-PORT3", "I04-PORT1", "I04-PORT2", "I04-PORT3", "I05", "I06", "I07-PORT1",
				"I07-PORT2", "I08", "I09", "I10", "I11", "I12", "J01", "J02", "J03", "K01", "Point0", "Point1", "Point2",
				"Point3", "Point4", "Point5", "Point6", "Point7", "Point8", "Point9" };

		dictionary.addAll(Arrays.asList(points));

		
		// A01
		addBidirectionalLane("Edge_0", dictionary.indexOf("A01"), dictionary.indexOf("D02"), 21);
		addBidirectionalLane("Edge_1", dictionary.indexOf("A01-1"), dictionary.indexOf("C03"), 18);
		addBidirectionalLane("Edge_2", dictionary.indexOf("A01-1"), dictionary.indexOf("C02"), 22);
		addBidirectionalLane("Edge_3", dictionary.indexOf("A01-1"), dictionary.indexOf("C04"), 15);
		addBidirectionalLane("Edge_4", dictionary.indexOf("A01"), dictionary.indexOf("E01"), 32);
		addBidirectionalLane("Edge_6", dictionary.indexOf("A01"), dictionary.indexOf("A01-PORT1"), 12);
		addBidirectionalLane("Edge_7", dictionary.indexOf("A01"), dictionary.indexOf("A01-1"), 36);

		// B01
		addBidirectionalLane("Edge_9", dictionary.indexOf("B01"), dictionary.indexOf("B01-1"), 22);
		addBidirectionalLane("Edge_9", dictionary.indexOf("B01-1"), dictionary.indexOf("B02-1"), 21);
		addBidirectionalLane("Edge_6", dictionary.indexOf("B01-1"), dictionary.indexOf("B03"), 14);
		addBidirectionalLane("Edge_7", dictionary.indexOf("B01"), dictionary.indexOf("B04"), 26);
		addBidirectionalLane("Edge_8", dictionary.indexOf("B01"), dictionary.indexOf("E01-1"), 36);
		addBidirectionalLane("Edge_9", dictionary.indexOf("B01-1"), dictionary.indexOf("B01-PORT1"), 8);

		// B02
		addBidirectionalLane("Edge_10", dictionary.indexOf("B02"), dictionary.indexOf("D01-1"), 20);
		addBidirectionalLane("Edge_11", dictionary.indexOf("B02"), dictionary.indexOf("D03"), 24);
		addBidirectionalLane("Edge_12", dictionary.indexOf("B02"), dictionary.indexOf("E01-1"), 39);
		addBidirectionalLane("Edge_28", dictionary.indexOf("B02"), dictionary.indexOf("D03-PORT1"), 13);
		addBidirectionalLane("Edge_10", dictionary.indexOf("B02"), dictionary.indexOf("B02-1"), 17);

		// B03
		addBidirectionalLane("Edge_28", dictionary.indexOf("B03"), dictionary.indexOf("B03-PORT1"), 15);
		addBidirectionalLane("Edge_28", dictionary.indexOf("B03"), dictionary.indexOf("B03-PORT2"), 15);

		// B04
		addBidirectionalLane("Edge_28", dictionary.indexOf("B04"), dictionary.indexOf("B04-PORT1"), 21);

		// C01
		addBidirectionalLane("Edge_14", dictionary.indexOf("C01"), dictionary.indexOf("C02-1"), 13);
		addBidirectionalLane("Edge_15", dictionary.indexOf("C01"), dictionary.indexOf("C01-1"), 24);
		addBidirectionalLane("Edge_15", dictionary.indexOf("C01-1"), dictionary.indexOf("C06"), 24);

		// C02

		// C03

		// C04
		addBidirectionalLane("Edge_21", dictionary.indexOf("C04"), dictionary.indexOf("C07-1"), 15);

		// C05
		addBidirectionalLane("Edge_22", dictionary.indexOf("C05"), dictionary.indexOf("C06-1"), 9);

		// C06
		addBidirectionalLane("Edge_24", dictionary.indexOf("C06"), dictionary.indexOf("C06-1"), 14);
		addBidirectionalLane("Edge_25", dictionary.indexOf("C06-1"), dictionary.indexOf("C07-1"), 8);

		// C07
		addBidirectionalLane("Edge_25", dictionary.indexOf("C07"), dictionary.indexOf("E01"), 19);
		addBidirectionalLane("Edge_25", dictionary.indexOf("C07"), dictionary.indexOf("C07-1"), 12);

		// D01
		addBidirectionalLane("Edge_26", dictionary.indexOf("D01"), dictionary.indexOf("D02-1"), 34);
		addBidirectionalLane("Edge_28", dictionary.indexOf("D01"), dictionary.indexOf("D01-1"), 12);
		addBidirectionalLane("Edge_27", dictionary.indexOf("D01-1"), dictionary.indexOf("D03-1"), 22);
		addBidirectionalLane("Edge_28", dictionary.indexOf("D01-1"), dictionary.indexOf("D01-PORT1"), 12);

		// D02
		addBidirectionalLane("Edge_29", dictionary.indexOf("D02"), dictionary.indexOf("D02-1"), 16);
		addBidirectionalLane("Edge_30", dictionary.indexOf("D02"), dictionary.indexOf("E01-1"), 29);

		// D03
		addBidirectionalLane("Edge_31", dictionary.indexOf("D03"), dictionary.indexOf("D03-1"), 32);
		addBidirectionalLane("Edge_28", dictionary.indexOf("D03-1"), dictionary.indexOf("D01-PORT1"), 12);
		addBidirectionalLane("Edge_28", dictionary.indexOf("D03"), dictionary.indexOf("D03-PORT1"), 13);

		// E01
		addBidirectionalLane("Edge_32", dictionary.indexOf("E01"), dictionary.indexOf("E01-1"), 23);

		// H01
		// addBidirectionalLane("Edge_32", dictionary.indexOf("H01"),dictionary.indexOf("H01-1"), 14);
		// addBidirectionalLane("Edge_32", dictionary.indexOf("H01"),
		// dictionary.indexOf("H01-PORT1"), 7);
		// addBidirectionalLane("Edge_32", dictionary.indexOf("H01-1"),
		// dictionary.indexOf("H02"), 18);

		// H02

		// Between the points on the road
		addBidirectionalLane("Edge_40", dictionary.indexOf("Point1"), dictionary.indexOf("Point2"), 50);
		addBidirectionalLane("Edge_41", dictionary.indexOf("Point1"), dictionary.indexOf("Point3"), 63);
		addBidirectionalLane("Edge_42", dictionary.indexOf("Point4"), dictionary.indexOf("Point3"), 42);
		addBidirectionalLane("Edge_43", dictionary.indexOf("Point4"), dictionary.indexOf("Point5"), 13);
		addBidirectionalLane("Edge_44", dictionary.indexOf("Point2"), dictionary.indexOf("Point6"), 110);

		// Between some ports of university and points on the road
		addBidirectionalLane("Edge_45", dictionary.indexOf("Point1"), dictionary.indexOf("D03-PORT1"), 25);
		addBidirectionalLane("Edge_46", dictionary.indexOf("Point1"), dictionary.indexOf("B03-PORT1"), 24);
		addBidirectionalLane("Edge_47", dictionary.indexOf("Point3"), dictionary.indexOf("B03-PORT2"), 35);
		addBidirectionalLane("Edge_70", dictionary.indexOf("Point3"), dictionary.indexOf("B01-PORT1"), 51);
		addBidirectionalLane("Edge_72", dictionary.indexOf("Point3"), dictionary.indexOf("B04-PORT1"), 49);
		// Between some ports of university
		addBidirectionalLane("Edge_48", dictionary.indexOf("D03-PORT1"), dictionary.indexOf("B03-PORT1"), 13);
		// addBidirectionalLane("Edge_49", dictionary.indexOf("B03-PORT2"),
		// dictionary.indexOf("B04"), 18);

		// Between ports of MAISEL
		addBidirectionalLane("Edge_49", dictionary.indexOf("I04-PORT3"), dictionary.indexOf("I08"), 58);
		addBidirectionalLane("Edge_50", dictionary.indexOf("I04-PORT3"), dictionary.indexOf("I09"), 19);
		addBidirectionalLane("Edge_51", dictionary.indexOf("I04-PORT3"), dictionary.indexOf("I04-PORT1"), 22);
		addBidirectionalLane("Edge_52", dictionary.indexOf("I04-PORT3"), dictionary.indexOf("I04-PORT2"), 14);

		// Between ports of MAISEL and ports FOYER
		addBidirectionalLane("Edge_53", dictionary.indexOf("I04-PORT3"), dictionary.indexOf("H01-PORT1"), 117);
		addBidirectionalLane("Edge_54", dictionary.indexOf("I09"), dictionary.indexOf("H01-PORT1"), 132);
		addBidirectionalLane("Edge_55", dictionary.indexOf("I07-PORT2"), dictionary.indexOf("H01-PORT1"), 79);
		addBidirectionalLane("Edge_56", dictionary.indexOf("Point4"), dictionary.indexOf("H01-PORT1"), 113);

		// Between ports of MAISEL and points on road
		addBidirectionalLane("Edge_57", dictionary.indexOf("Point1"), dictionary.indexOf("I03-PORT1"), 42);
		addBidirectionalLane("Edge_58", dictionary.indexOf("Point2"), dictionary.indexOf("I06"), 34);
		addBidirectionalLane("Edge_72", dictionary.indexOf("Point2"), dictionary.indexOf("I01-PORT1"), 70);
		addBidirectionalLane("Edge_59", dictionary.indexOf("Point3"), dictionary.indexOf("I03-PORT2"), 24);
		addBidirectionalLane("Edge_60", dictionary.indexOf("Point4"), dictionary.indexOf("I07-PORT1"), 11);
		addBidirectionalLane("Edge_71", dictionary.indexOf("Point4"), dictionary.indexOf("I01-PORT2"), 8);
		addBidirectionalLane("Edge_61", dictionary.indexOf("Point5"), dictionary.indexOf("I04-PORT1"), 40);
		addBidirectionalLane("Edge_62", dictionary.indexOf("Point5"), dictionary.indexOf("I04-PORT2"), 51);
		addBidirectionalLane("Edge_63", dictionary.indexOf("Point6"), dictionary.indexOf("I05"), 33);
		addBidirectionalLane("Edge_64", dictionary.indexOf("Point6"), dictionary.indexOf("I08"), 91);

		// Between ports of MAISEL and ports of university
		addBidirectionalLane("Edge_65", dictionary.indexOf("I03-PORT3"), dictionary.indexOf("B03-PORT2"), 52);
		addBidirectionalLane("Edge_66", dictionary.indexOf("I02"), dictionary.indexOf("B03-PORT2"), 30);
		addBidirectionalLane("Edge_67", dictionary.indexOf("I02"), dictionary.indexOf("B01-PORT1"), 40);
		addBidirectionalLane("Edge_68", dictionary.indexOf("I02"), dictionary.indexOf("B04-PORT1"), 47);

        // Between RAK and next point
        addBidirectionalLane("Edge_69", dictionary.indexOf("Point0"), dictionary.indexOf("RAK"), 100);
        addBidirectionalLane("Edge_70", dictionary.indexOf("Point7"), dictionary.indexOf("RAK"), 70);
        addBidirectionalLane("Edge_71", dictionary.indexOf("Point8"), dictionary.indexOf("RAK"), 50);
        addBidirectionalLane("Edge_72", dictionary.indexOf("Point8"), dictionary.indexOf("Point9"), 50);
        addBidirectionalLane("Edge_73", dictionary.indexOf("Point9"), dictionary.indexOf("A01-PORT1"), 50);
        addBidirectionalLane("Edge_74", dictionary.indexOf("Point9"), dictionary.indexOf("Point7"), 70);
        addBidirectionalLane("Edge_75", dictionary.indexOf("Point0"), dictionary.indexOf("Point7"), 70);
        addBidirectionalLane("Edge_76", dictionary.indexOf("D03"), dictionary.indexOf("Point7"), 70);
        addBidirectionalLane("Edge_76", dictionary.indexOf("Point3"), dictionary.indexOf("Point7"), 100);

		/*/ get user input
		System.out.println("Enter your departure: ");
		Scanner scanner = new Scanner(System.in);
		String departure = scanner.nextLine().toUpperCase();

		System.out.println("Enter your destination: ");
		Scanner scanner1 = new Scanner(System.in);
		String destination = scanner1.nextLine().toUpperCase();
        */
       // String departure = "A01";
        IO.print(departure + " ===================");

		// get index
		int point1 = dictionary.indexOf(departure);
		int point2 = dictionary.indexOf(destination);
		if(departure.equals(destination))
			throw new DepartureEqualsDestination();

		// Lets check from location nodes to nodes
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(point1));
		LinkedList<Vertex> path = dijkstra.getPath(nodes.get(point2));

		assertNotNull(path);
		assertTrue(path.size() > 0);

		List<String> pathString = new ArrayList<String>();
		for (Vertex vertex : path) {
			System.out.println("Nodes " + vertex + " - " + dictionary.get(Integer.valueOf(vertex.getId())));
			pathString.add(dictionary.get(Integer.valueOf(vertex.getId())));
		}

		if(pathString == null)
			throw new PathNotFound();

		return pathString;

	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		edges.add(lane);
	}

	private void addBidirectionalLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		Edge lane1 = new Edge(laneId + "'", nodes.get(destLocNo), nodes.get(sourceLocNo), duration);
		edges.add(lane);
		edges.add(lane1);
	}

}
