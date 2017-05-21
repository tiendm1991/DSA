package tiendm.algorithm.graph.dijsktra2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GraphUtil {
	public static void main(String[] args) {
		GraphUtil util = new GraphUtil();
		try {
			util.readGraph("file\\graph1.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Graph readGraph(String filePath) throws Exception{
		File file = new File(filePath);
		if (!file.exists()) throw new Exception("File Not Found");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String currentLine ;
			currentLine = br.readLine();
			int numberVertex = Integer.parseInt(currentLine);
			List<Vertex> vertexs = new ArrayList<>();
			List<Edge> edges = new ArrayList<>();
			for(int i=0;i<numberVertex;i++){
				vertexs.add(new Vertex(i+"", "Vertex_"+i));
			}
			int count = 0;
			while ((currentLine = br.readLine())!=null) {
				String[] arrStr = currentLine.split(" ");
				for(int i=0;i<arrStr.length;i++){
					Integer weight = Integer.parseInt(arrStr[i]);
					edges.add(new Edge(count+"-"+i, vertexs.get(count), vertexs.get(i), weight));
				}
				count++;
			}
			Graph grap = new Graph(vertexs, edges);
			return grap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			br.close();
			fr.close();
		}
		
	}
}	
