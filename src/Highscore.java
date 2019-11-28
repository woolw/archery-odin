import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Highscore {

	public String[] name;
	public int[] score;
	public int entries;

	public Highscore() throws URISyntaxException, IOException {
		name = new String[10];
		score = new int[10];
		entries = 0;
		
		File highscoreFile = null;
		Scanner read;
		
		highscoreFile = new File(this.getClass().getResource("/files/highscore.txt").toURI());
		
		if(!highscoreFile.exists()) {
			highscoreFile.createNewFile();
		}

		read = new Scanner(highscoreFile);
		
		int i = 0;
		
		while(read.hasNextLine() && i<10) {
			name[i] = read.nextLine();
			score[i] = Integer.valueOf(read.nextLine());
			
			i++;
			entries++;
		}
		
		read.close();
	}
	
	public void writeFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("the-file-name.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<entries;i++) {
			writer.println(name[i]+"\n"+score[i]);
		}
		
		writer.close();
	}


	public void setName(int i, String n) {
		name[i] = n;
	}
	
	public void setScore(int i, int s) {
		score[i] = s;
	}
	
	public String getName(int i) {
		return name[i];
	}
	
	public int getScore(int i) {
		return score[i];
	}
	
}
