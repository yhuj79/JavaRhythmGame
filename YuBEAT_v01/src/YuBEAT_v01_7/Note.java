package YuBEAT_v01_7;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private int x, y = 930 - ( 2000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 498;
		}
		else if(noteType.equals("D")) {
			x = 652;
		}
		else if(noteType.equals("F")) {
			x = 806;
		}
		else if(noteType.equals("J")) {
			x = 960;
		}
		else if(noteType.equals("K")) {
			x = 1114;
		}
		else if(noteType.equals("L")) {
			x = 1268;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) 
		{
			g.drawImage(noteImage, x, y,  null);
		}
		else
		{
			g.drawImage(noteImage, x, y,  null);
			g.drawImage(noteImage, x+100, y,  null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 971) {
			System.out.println("Break");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y >= 970) {
			System.out.println("Break");
			close();
			return "Break";
		}
		else if(y >= 870) {
			System.out.println("Max");
			close();
			return "Max";
		}
		return "none";
	}
	
	public int getY() {
		return y;
	}
}
