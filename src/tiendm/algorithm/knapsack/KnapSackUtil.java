package tiendm.algorithm.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapSackUtil {
	public static List<Item> initRandomListItem(int nbItem , int maxW){
		List<Item> lsItem = new ArrayList<>();
		Random rand = new Random();
		for(int i=0;i<nbItem;i++){
			int weight = rand.nextInt(nbItem)+3;
			int price = rand.nextInt(maxW)+1;
			lsItem.add(new Item((i+1)+"", weight, price));
		}
		return lsItem;
	}
	
	public static List<Item> initListItem(String strFile){
		List<Item> lsItem = new ArrayList<>();
		File fileData = new File(strFile);
		FileInputStream inStream = null;
		BufferedReader buff = null ;
		try {
			inStream = new FileInputStream(fileData);
			buff = new BufferedReader(new InputStreamReader(inStream));
			String line = null;
			int count = 1;
			while ((line = buff.readLine())!=null) {
				String[] attrs = line.split(" ");
				int w = Integer.parseInt(attrs[0]);
				int p = Integer.parseInt(attrs[1]);
				lsItem.add(new Item((count++)+"", w, p));	
			}
		} catch (NumberFormatException|IOException e) {
			e.printStackTrace();
			return lsItem;
		} finally {
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lsItem;
	}
	
	public static void printListItem(List<Item> lsItem){
		System.out.println("***************************");
		if(lsItem==null || lsItem.isEmpty()) {
			System.out.println("NULL");
		}
		for(Item x : lsItem){
			System.out.println(x);
		}
		System.out.println("+++++++++++++++++++++++++++");
	}
}
