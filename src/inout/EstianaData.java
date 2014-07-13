package inout;

import interfaces.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class EstianaData implements Input<String>{

	public EstianaData(){
		estianaData = new File("DATA/estianaData.txt");
		try{	
			dataIn = new Scanner(estianaData);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	public String getInput(){
		return dataIn.next();
	}
	
	File estianaData = null;
	Scanner dataIn = null;
	TextOutput output = null;
}
