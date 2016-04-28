package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class Highscore  {

	double[] array= new double[11];
	String[] nicks = new String[11];
	double[] nposition = new double[11];
	String[] newnicks = new String[11];

	public Highscore(double resoult, String nick){
	
		BigDecimal b = new BigDecimal(resoult);
		 b = b.setScale(2, BigDecimal.ROUND_FLOOR);    //ustawienie setnych
		 resoult=Double.parseDouble(b.toString());
		 	
		readToArray();
		array[10]=resoult;
		nicks[10]=nick;
		modifyPosition();
		Arrays.sort(array);
		modifyFile();
		scale();
	}

		void readToArray(){
		try{						//wczytanie z pliku do array
			int i=0;
			String line1=null;
			BufferedReader br1 = new BufferedReader(new FileReader("Highscore/Highscore.txt")); 
			 while ((line1 = br1.readLine()) != null)
			 {	 
				 array[i]=Double.parseDouble(line1);
				  i++;
			 }
			 br1.close();}
		catch (FileNotFoundException e) 
		 { 
			 System.out.println("Nie znaleziono pliku"); 
		 } 
		 catch (IOException e) {}
	
		
		try{					//wczytanie z pliku do nicks
			int i=0;
			String line=null;
			BufferedReader br2 = new BufferedReader(new FileReader("Highscore/Nicks.txt")); 
			 while ((line = br2.readLine()) != null)
			 {	 
				 nicks[i]=line;
				 i++; 
			 }
			 br2.close();
		}
		catch (FileNotFoundException e) 
		 { 
			 System.out.println("Nie znaleziono pliku"); 
		 } 
		 catch (IOException e) { } 	 	
		}
	
		void modifyPosition()
		{
			for(int i=0;i<11;++i)
				{
				nposition[i]=array[i];
		}
		}
		void modifyFile()
		{
			for(int i=0;i<11;++i)
			{
				for(int j=0;j<11;++j)
				{
					if(nposition[j]==array[i] )
						newnicks[i]=nicks[j];
						}
			}
			
			
			File nickfile = new File("Highscore/Nicks.txt");
			nickfile.delete();
			
			File file = new File("Highscore/Highscore.txt");
			file.delete();
			
			try{ 				//wpisywanie do pliku z wynikami
			File file1 = new File("Highscore/Highscore.txt");
		     BufferedWriter output = new BufferedWriter(new FileWriter(file1));
		     for (int i=0;i<10;++i)
		     { output.write(Double.toString(array[i]));
		     	output.newLine();}
		     output.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("blad w modyfikacji");
			}
			catch (IOException e){}

									//wpisywanie do pliku z nickami
			try{
				File file2 = new File("Highscore/Nicks.txt");
			     BufferedWriter output = new BufferedWriter(new FileWriter(file2));
			     for (int i=0;i<10;++i)
			     { 
			    	 output.write(newnicks[i]);
			     	output.newLine();}
			     output.close();
				}
				catch(FileNotFoundException e)
				{
					System.out.println("blad w modyfikacji");
				}
				catch (IOException e){}	
		}
		
		void scale()
		{
			File scalefile = new File("Highscore/Scale.txt");
			scalefile.delete();
			
			for (int i=0; i<11;++i)
			{
				nicks[i]="            "+ newnicks[i]+"                                                      "+Double.toString(array[i]);
				System.out.println(nicks[i]);
			}
			try{
				File file2 = new File("Highscore/Scale.txt");
			     BufferedWriter output = new BufferedWriter(new FileWriter(file2));
			     for (int i=0;i<10;++i)
			     { 
			    	 output.write(nicks[i]);
			     	output.newLine();}
			     output.close();
				}
				catch(FileNotFoundException e)
				{
					System.out.println("blad w modyfikacji");
				}
				catch (IOException e){}	
			
		}
}
