package YuBEAT_v01_7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import YuBEAT_v01.Beat;
import YuBEAT_v01_6.Main;

public class Game extends Thread {
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameinfoImage = new ImageIcon(Main.class.getResource("../images/gameinfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image realGameinfoImage = new ImageIcon(Main.class.getResource("../images/realgameinfo.png")).getImage();
	private Image noneImage;
	private Image judgeImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	private Integer gameScore = 0;
	private Integer maxCount = 0;
	private Integer breakCount = 0;
	private boolean isGameMusicEnd = false;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g) {  
		g.drawImage(noteRouteSImage, 498, 30, null);
		g.drawImage(noteRouteDImage, 652, 30, null);
		g.drawImage(noteRouteFImage, 806, 30, null);
		g.drawImage(noteRouteJImage, 960, 30, null);
		g.drawImage(noteRouteKImage, 1114, 30, null);
		g.drawImage(noteRouteLImage, 1268, 30, null);
		
		g.drawImage(noteRouteLineImage, 494, 30, null);
		g.drawImage(noteRouteLineImage, 648, 30, null);
		g.drawImage(noteRouteLineImage, 802, 30, null);
		g.drawImage(noteRouteLineImage, 956, 30, null);
		g.drawImage(noteRouteLineImage, 1110, 30, null);
		g.drawImage(noteRouteLineImage, 1264, 30, null);
		g.drawImage(noteRouteLineImage, 1418, 30, null);
		g.drawImage(gameinfoImage, 0, 975, null);
		g.drawImage(judgementLineImage, 494, 914, null);
		g.drawImage(realGameinfoImage, 15, 500, null);
		g.drawImage(noneImage, 730, 350, null);
		g.drawImage(judgeImage, 730, 350, null);
		
		for(int i = 0; i < noteList.size(); i++)
		{
			Note note = noteList.get(i);
			if(note.getY() > 965) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeBreak.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
			note.screenDraw(g);
		}
		
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(Color.black);
		g.setFont(new Font("CookieRun Regular", Font.BOLD, 28));
		g.drawString(titleName, 33, 561);
		g.setFont(new Font("CookieRun Regular", Font.BOLD, 45));
		g.drawString(difficulty, 33, 662);
		g.drawString(gameScore.toString(), 33, 762);
		
		if (!gameMusic.isAlive())
			isGameMusicEnd = true;
	}
	
	public Integer getGameScore() { return gameScore; }
	public Integer getmaxCount() { return maxCount; }
	public Integer getbreakCount() { return breakCount; }
	public boolean getIsGameMusicEnd() { return isGameMusicEnd; }
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drum.mp4", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	public void close() {
		gameMusic.close();  
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("LostArk BGM - Nia Village") && difficulty.equals("Normal")) {
			int startTime = 2580 - Main.REACH_TIME * 2000;
			int gap = 126;
			beats = new Beat[] {
					new Beat(startTime + gap * 2, "S"),
					new Beat(startTime + gap * 6, "D"),
					new Beat(startTime + gap * 10, "K"),
					new Beat(startTime + gap * 15, "L"),
					new Beat(startTime + gap * 17, "F"),
					new Beat(startTime + gap * 19, "D"),
					new Beat(startTime + gap * 22, "S"),
					new Beat(startTime + gap * 24, "D"),
					new Beat(startTime + gap * 28, "F"),
					new Beat(startTime + gap * 40, "S"),
					new Beat(startTime + gap * 44, "D"),
					new Beat(startTime + gap * 47, "F"),
					new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "L"),
					new Beat(startTime + gap * 54, "D"),
					new Beat(startTime + gap * 56, "S"),
					new Beat(startTime + gap * 59, "S"),
					new Beat(startTime + gap * 63, "F"),
					new Beat(startTime + gap * 66, "L"),
					new Beat(startTime + gap * 78, "S"),
					new Beat(startTime + gap * 81, "D"),
					new Beat(startTime + gap * 84, "K"),
					new Beat(startTime + gap * 88, "L"),
					new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 95, "S"),
					new Beat(startTime + gap * 98, "D"),
					new Beat(startTime + gap * 101, "F"),
					new Beat(startTime + gap * 113, "S"),
					new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 121, "F"),
					new Beat(startTime + gap * 127, "L"),
					new Beat(startTime + gap * 128, "K"),
					new Beat(startTime + gap * 131, "J"),
					new Beat(startTime + gap * 152, "S"),
					new Beat(startTime + gap * 158, "S"),
					new Beat(startTime + gap * 158, "D"),
					new Beat(startTime + gap * 162, "F"),
					new Beat(startTime + gap * 164, "S"),
					new Beat(startTime + gap * 170, "D"),
					new Beat(startTime + gap * 176, "L"),
					new Beat(startTime + gap * 184, "L"),
					new Beat(startTime + gap * 188, "K"),
					new Beat(startTime + gap * 192, "D"),
					new Beat(startTime + gap * 196, "K"),
					new Beat(startTime + gap * 201, "S"),
					new Beat(startTime + gap * 204, "S"),
					new Beat(startTime + gap * 206, "D"),
					new Beat(startTime + gap * 210, "F"),
					new Beat(startTime + gap * 214, "J"),
					new Beat(startTime + gap * 216, "K"),
					new Beat(startTime + gap * 220, "L"),
					new Beat(startTime + gap * 222, "L"),
					
				    new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 237, "K"), 
					new Beat(startTime + gap * 242, "F"), 
					new Beat(startTime + gap * 246, "K"), 
					new Beat(startTime + gap * 251, "K"), 
					new Beat(startTime + gap * 256, "J"), 
					new Beat(startTime + gap * 260, "S"), 
					new Beat(startTime + gap * 263, "K"), 
					new Beat(startTime + gap * 269, "S"), 
					new Beat(startTime + gap * 271, "S"), 
					new Beat(startTime + gap * 275, "K"), 
					new Beat(startTime + gap * 281, "F"), 
					new Beat(startTime + gap * 283, "J"), 
					new Beat(startTime + gap * 287, "K"), 
					new Beat(startTime + gap * 300, "S"), 
					new Beat(startTime + gap * 302, "D"), 
					new Beat(startTime + gap * 307, "K"), 
					new Beat(startTime + gap * 320, "S"), 
					new Beat(startTime + gap * 322, "L"), 
					new Beat(startTime + gap * 326, "K"),
					new Beat(startTime + gap * 329, "F"),
					new Beat(startTime + gap * 332, "K"),
					
			};
		}
		else if(titleName.equals("LostArk BGM - Nia Village") && difficulty.equals("Hard")) {
			int startTime = 1500 - Main.REACH_TIME * 2000;
			int gap = 139;
			beats = new Beat[] {
					new Beat(startTime + gap * 27, "K")
					
			};
		}	
		else if(titleName.equals("MapleStory BGM - Morass") && difficulty.equals("Normal")) {
			int startTime = 1500 - Main.REACH_TIME * 2000;
			int gap = 139;
			beats = new Beat[] {
					new Beat(startTime + gap * 27, "K")
					
			};
		}
		else if(titleName.equals("MapleStory BGM - Morass") && difficulty.equals("Hard")) {
			int startTime = 1500 - Main.REACH_TIME * 2000;
			int gap = 139;
			beats = new Beat[] {
					new Beat(startTime + gap * 27, "K")
					
			};
		}
		else if(titleName.equals("FIFA Online3 BGM - Time Bomb") && difficulty.equals("Normal")) {
			int startTime = 5854 - Main.REACH_TIME * 2000;
			int gap = 859;
			beats = new Beat[] {
					new Beat(startTime + gap * 3, "J"),
					new Beat(startTime + gap * 4, "L"),
					new Beat(startTime + gap * 5, "S"),
					new Beat(startTime + gap * 6, "D"),
					new Beat(startTime + gap * 7, "S"),
					new Beat(startTime + gap * 8, "F"),
					new Beat(startTime + gap * 9, "S"),
					new Beat(startTime + gap * 10, "J"),
					new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 12, "S"),
					new Beat(startTime + gap * 13, "L"),
					new Beat(startTime + gap * 14, "F"),
					new Beat(startTime + gap * 15, "S"),
					new Beat(startTime + gap * 16, "J"),
					new Beat(startTime + gap * 16, "D"),
					new Beat(startTime + gap * 17, "K"),
					new Beat(startTime + gap * 18, "D"),
					new Beat(startTime + gap * 18, "K"),
					new Beat(startTime + gap * 19, "F"),
					new Beat(startTime + gap * 20, "S"),
					new Beat(startTime + gap * 21, "D"),
					new Beat(startTime + gap * 22, "K"),
					new Beat(startTime + gap * 23, "K"),
					new Beat(startTime + gap * 24, "J"),
					new Beat(startTime + gap * 24, "S"),
					new Beat(startTime + gap * 25, "L"),
					new Beat(startTime + gap * 26, "K"),
					new Beat(startTime + gap * 27, "K"),
					new Beat(startTime + gap * 28, "J"),
					new Beat(startTime + gap * 29, "S"),
					new Beat(startTime + gap * 29, "F"),
					new Beat(startTime + gap * 30, "S"),
					new Beat(startTime + gap * 30, "F"),
					new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 31, "D"),
					new Beat(startTime + gap * 32, "S"),
					new Beat(startTime + gap * 33, "D"),
					new Beat(startTime + gap * 33, "S"),
					new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 35, "S"),
					new Beat(startTime + gap * 36, "K"),
					new Beat(startTime + gap * 37, "J"),
					new Beat(startTime + gap * 38, "K"),
					new Beat(startTime + gap * 39, "L"),
					new Beat(startTime + gap * 39, "J"),
					new Beat(startTime + gap * 40, "S"),
					new Beat(startTime + gap * 40, "F"),
					new Beat(startTime + gap * 41, "L"),
					new Beat(startTime + gap * 42, "F"),
					new Beat(startTime + gap * 43, "D"),
					new Beat(startTime + gap * 43, "K"),
					new Beat(startTime + gap * 44, "S"),
					new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 46, "F"),
					new Beat(startTime + gap * 47, "S"),
					new Beat(startTime + gap * 48, "D"),
					new Beat(startTime + gap * 49, "D"),
					new Beat(startTime + gap * 49, "J"),
					new Beat(startTime + gap * 50, "K"),
					new Beat(startTime + gap * 51, "L"),
					new Beat(startTime + gap * 51, "F"),
					new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 53, "K"),
					new Beat(startTime + gap * 54, "S"),
					new Beat(startTime + gap * 54, "D"),
					new Beat(startTime + gap * 54, "F"),
					new Beat(startTime + gap * 55, "D"),
					new Beat(startTime + gap * 55, "J"),
					new Beat(startTime + gap * 55, "K"),
					new Beat(startTime + gap * 55, "L"),
					new Beat(startTime + gap * 56, "F"),
					new Beat(startTime + gap * 57, "S"),
					new Beat(startTime + gap * 58, "L"),
					new Beat(startTime + gap * 59, "K"),
					new Beat(startTime + gap * 59, "D"),
					new Beat(startTime + gap * 60, "J"),
					new Beat(startTime + gap * 60, "F"),
					new Beat(startTime + gap * 61, "L"),
					new Beat(startTime + gap * 62, "D"),
					new Beat(startTime + gap * 62, "K"),
					new Beat(startTime + gap * 63, "S"),
					new Beat(startTime + gap * 64, "K"),
					new Beat(startTime + gap * 65, "D"),
					new Beat(startTime + gap * 66, "J"),
					new Beat(startTime + gap * 67, "D"),
					new Beat(startTime + gap * 68, "F"),
					new Beat(startTime + gap * 68, "K"),
					new Beat(startTime + gap * 69, "K"),
					new Beat(startTime + gap * 70, "L"),
					new Beat(startTime + gap * 71, "J"),
					new Beat(startTime + gap * 72, "D"),
					new Beat(startTime + gap * 73, "L"),
					new Beat(startTime + gap * 73, "D"),
					new Beat(startTime + gap * 74, "F"),
					new Beat(startTime + gap * 75, "L"),
					new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"),
					new Beat(startTime + gap * 78, "F"),
					new Beat(startTime + gap * 79, "J"),
					new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 80, "S"),
					new Beat(startTime + gap * 81, "L"),
					new Beat(startTime + gap * 81, "D"),
					new Beat(startTime + gap * 81, "J"),
					new Beat(startTime + gap * 82, "F"),
					new Beat(startTime + gap * 83, "K"),
					new Beat(startTime + gap * 83, "F"),
					new Beat(startTime + gap * 84, "F"),
					new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 85, "L"),
					new Beat(startTime + gap * 86, "K"),
					new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 88, "K"),
					new Beat(startTime + gap * 89, "L"),
					new Beat(startTime + gap * 90, "K"),
					new Beat(startTime + gap * 90, "S"),
					new Beat(startTime + gap * 91, "K"),
					new Beat(startTime + gap * 92, "D"),
					new Beat(startTime + gap * 92, "L"),
					new Beat(startTime + gap * 93, "S"),
					new Beat(startTime + gap * 93, "K"),
					new Beat(startTime + gap * 93, "D"),
					new Beat(startTime + gap * 94, "D"),
					new Beat(startTime + gap * 95, "S"),
					new Beat(startTime + gap * 96, "D"),
					new Beat(startTime + gap * 97, "F"),
					new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 99, "K"),
					new Beat(startTime + gap * 100, "D"),
					new Beat(startTime + gap * 100, "J"),
					new Beat(startTime + gap * 101, "J"),
					new Beat(startTime + gap * 102, "L"),
					new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 103, "J"),
					new Beat(startTime + gap * 103, "F"),
					new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 106, "D"),
					new Beat(startTime + gap * 106, "S"),
					new Beat(startTime + gap * 106, "L"),
					new Beat(startTime + gap * 107, "K"),
					new Beat(startTime + gap * 107, "S"),
					new Beat(startTime + gap * 108, "F"),
					new Beat(startTime + gap * 109, "K"),
					new Beat(startTime + gap * 109, "S"),
					new Beat(startTime + gap * 110, "J"),
					new Beat(startTime + gap * 111, "K"),
					new Beat(startTime + gap * 112, "S"),
					new Beat(startTime + gap * 113, "K"),
					new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "F"),
					new Beat(startTime + gap * 115, "S"),
					new Beat(startTime + gap * 116, "J"),
					new Beat(startTime + gap * 117, "K"),
					new Beat(startTime + gap * 117, "L"),
					new Beat(startTime + gap * 117, "J"),
					new Beat(startTime + gap * 118, "D"),
					new Beat(startTime + gap * 119, "F"),
					new Beat(startTime + gap * 119, "S"),
					new Beat(startTime + gap * 120, "S"),
					new Beat(startTime + gap * 120, "L"),
					new Beat(startTime + gap * 121, "D"),
					new Beat(startTime + gap * 122, "K"),
					new Beat(startTime + gap * 122, "D"),
					new Beat(startTime + gap * 123, "K"),
					new Beat(startTime + gap * 124, "J"),
					new Beat(startTime + gap * 125, "L"),
					new Beat(startTime + gap * 126, "K"),
					new Beat(startTime + gap * 127, "F"),
					new Beat(startTime + gap * 127, "K"),
					new Beat(startTime + gap * 128, "J"),
					new Beat(startTime + gap * 129, "J"),
					new Beat(startTime + gap * 129, "S"),
					new Beat(startTime + gap * 130, "S"),
					new Beat(startTime + gap * 131, "D"),
					new Beat(startTime + gap * 132, "S"),
					new Beat(startTime + gap * 133, "D"),
					new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 134, "F"),
					new Beat(startTime + gap * 135, "S"),
					new Beat(startTime + gap * 136, "K"),
					new Beat(startTime + gap * 137, "J"),
					new Beat(startTime + gap * 138, "K"),
					new Beat(startTime + gap * 139, "J"),
					new Beat(startTime + gap * 140, "J"),
					new Beat(startTime + gap * 141, "L"),
					new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 142, "S"),
					new Beat(startTime + gap * 143, "L"),
					new Beat(startTime + gap * 144, "S"),
					new Beat(startTime + gap * 145, "D"),
					new Beat(startTime + gap * 146, "F"),
					new Beat(startTime + gap * 147, "K"),
					new Beat(startTime + gap * 147, "L"),
					new Beat(startTime + gap * 148, "D"),
					new Beat(startTime + gap * 148, "F"),
					new Beat(startTime + gap * 149, "D"),
					new Beat(startTime + gap * 150, "K"),
					new Beat(startTime + gap * 150, "S"),
					new Beat(startTime + gap * 151, "L"),
					new Beat(startTime + gap * 152, "J"),
					new Beat(startTime + gap * 153, "K"),
					new Beat(startTime + gap * 154, "S"),
					new Beat(startTime + gap * 154, "F"),
					new Beat(startTime + gap * 155, "D"),
					new Beat(startTime + gap * 156, "L"),
					new Beat(startTime + gap * 156, "F"),
					new Beat(startTime + gap * 156, "S"),
					new Beat(startTime + gap * 157, "S"),
					new Beat(startTime + gap * 158, "L"),
					new Beat(startTime + gap * 158, "D"),
					new Beat(startTime + gap * 159, "K"),
					new Beat(startTime + gap * 159, "S"),
					new Beat(startTime + gap * 159, "F"),
					new Beat(startTime + gap * 160, "J"),
					new Beat(startTime + gap * 161, "L"),
					new Beat(startTime + gap * 161, "J"),
					new Beat(startTime + gap * 162, "D"),
					new Beat(startTime + gap * 163, "S"),
					new Beat(startTime + gap * 163, "F"),
					new Beat(startTime + gap * 164, "K"),
					new Beat(startTime + gap * 165, "D"),
					new Beat(startTime + gap * 166, "J"),
					new Beat(startTime + gap * 167, "D"),
					new Beat(startTime + gap * 168, "F"),
					new Beat(startTime + gap * 169, "K"),
					new Beat(startTime + gap * 169, "S"),
					new Beat(startTime + gap * 170, "D"),
					new Beat(startTime + gap * 170, "K"),
					new Beat(startTime + gap * 171, "J"),
					new Beat(startTime + gap * 171, "L"),
					new Beat(startTime + gap * 172, "D"),
					new Beat(startTime + gap * 173, "L"),
					new Beat(startTime + gap * 174, "F"),
					new Beat(startTime + gap * 174, "S"),
					new Beat(startTime + gap * 175, "L"),
					new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 177, "D"),
					new Beat(startTime + gap * 178, "F"),
					new Beat(startTime + gap * 179, "J"),
					new Beat(startTime + gap * 179, "L"),
					new Beat(startTime + gap * 180, "L"),
					new Beat(startTime + gap * 180, "F"),
					new Beat(startTime + gap * 181, "L"),
					new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 182, "F"),
					new Beat(startTime + gap * 183, "K"),
					new Beat(startTime + gap * 183, "J"),
					new Beat(startTime + gap * 183, "F"),
					new Beat(startTime + gap * 184, "F"),
					new Beat(startTime + gap * 185, "S"),
					new Beat(startTime + gap * 185, "D"),
					new Beat(startTime + gap * 185, "L"),
					new Beat(startTime + gap * 186, "K"),
					new Beat(startTime + gap * 187, "D"),
					new Beat(startTime + gap * 188, "K"),
					new Beat(startTime + gap * 188, "L"),
					new Beat(startTime + gap * 188, "S"),
					new Beat(startTime + gap * 189, "L"),
					new Beat(startTime + gap * 190, "K"),
					new Beat(startTime + gap * 190, "F"),
					new Beat(startTime + gap * 191, "L"),
					new Beat(startTime + gap * 192, "J"),
					new Beat(startTime + gap * 193, "K"),
					new Beat(startTime + gap * 194, "L"),
					new Beat(startTime + gap * 195, "L"),
					new Beat(startTime + gap * 196, "S"),
					new Beat(startTime + gap * 196, "F"),
					new Beat(startTime + gap * 197, "S"),
					new Beat(startTime + gap * 197, "D"),
					new Beat(startTime + gap * 197, "L"),
					new Beat(startTime + gap * 198, "F"),
					new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 199, "D"),
					new Beat(startTime + gap * 199, "S"),
					new Beat(startTime + gap * 199, "J"),
					new Beat(startTime + gap * 200, "J"),
					new Beat(startTime + gap * 200, "L"),
					new Beat(startTime + gap * 200, "S"),
					new Beat(startTime + gap * 201, "K"),
					new Beat(startTime + gap * 201, "S"),
					new Beat(startTime + gap * 202, "L"),
					new Beat(startTime + gap * 203, "J"),
					new Beat(startTime + gap * 203, "S"),
					new Beat(startTime + gap * 204, "L"),
					new Beat(startTime + gap * 204, "D"),
					new Beat(startTime + gap * 204, "S"),
					new Beat(startTime + gap * 205, "S"),
					new Beat(startTime + gap * 205, "J"),
					new Beat(startTime + gap * 206, "D"),
					new Beat(startTime + gap * 206, "L"),
					new Beat(startTime + gap * 207, "S"),
					new Beat(startTime + gap * 207, "J"),
					new Beat(startTime + gap * 207, "F"),
					new Beat(startTime + gap * 208, "F"),
					new Beat(startTime + gap * 208, "K"),
					new Beat(startTime + gap * 208, "L"),
					new Beat(startTime + gap * 209, "S"),
					new Beat(startTime + gap * 210, "J"),
					new Beat(startTime + gap * 210, "S"),
					new Beat(startTime + gap * 210, "F"),
					new Beat(startTime + gap * 210, "L"),
					new Beat(startTime + gap * 211, "D"),
					new Beat(startTime + gap * 211, "F"),
					new Beat(startTime + gap * 212, "S"),
					new Beat(startTime + gap * 212, "D"),
					new Beat(startTime + gap * 213, "L"),
					new Beat(startTime + gap * 213, "S"),
					new Beat(startTime + gap * 213, "D"),
					new Beat(startTime + gap * 214, "F"),
					new Beat(startTime + gap * 214, "J"),
					new Beat(startTime + gap * 215, "S"),
					new Beat(startTime + gap * 215, "K"),
					new Beat(startTime + gap * 215, "F"),
					new Beat(startTime + gap * 215, "K"),
					new Beat(startTime + gap * 216, "J"),
					new Beat(startTime + gap * 216, "S"),
					new Beat(startTime + gap * 216, "K"),
					new Beat(startTime + gap * 216, "D"),
					new Beat(startTime + gap * 217, "K"),
					new Beat(startTime + gap * 217, "S"),
					new Beat(startTime + gap * 217, "L"),
					new Beat(startTime + gap * 218, "S"),
					new Beat(startTime + gap * 218, "F"),
					new Beat(startTime + gap * 218, "J"),
					new Beat(startTime + gap * 218, "K"),
					new Beat(startTime + gap * 219, "F"),
					new Beat(startTime + gap * 219, "S"),
					new Beat(startTime + gap * 219, "D"),
					new Beat(startTime + gap * 220, "S"),
					new Beat(startTime + gap * 220, "K"),
					new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 221, "L"),
					new Beat(startTime + gap * 221, "D"),
					new Beat(startTime + gap * 222, "K"),
					new Beat(startTime + gap * 222, "S"),
					new Beat(startTime + gap * 222, "F"),
					new Beat(startTime + gap * 223, "K"),
					new Beat(startTime + gap * 223, "S"),
					new Beat(startTime + gap * 224, "F"),
					new Beat(startTime + gap * 224, "J"),
					new Beat(startTime + gap * 225, "L"),
					new Beat(startTime + gap * 225, "J"),
					new Beat(startTime + gap * 225, "F"),
					new Beat(startTime + gap * 226, "K"),
					new Beat(startTime + gap * 226, "S"),
					new Beat(startTime + gap * 227, "K"),
					new Beat(startTime + gap * 227, "S"),
					new Beat(startTime + gap * 228, "J"),
					new Beat(startTime + gap * 228, "K"),
					new Beat(startTime + gap * 228, "L"),
					new Beat(startTime + gap * 229, "S"),
					new Beat(startTime + gap * 229, "D"),
					new Beat(startTime + gap * 229, "F"),
					new Beat(startTime + gap * 230, "S"),
					new Beat(startTime + gap * 230, "D"),
					new Beat(startTime + gap * 230, "F"),
					new Beat(startTime + gap * 230, "J"),
					new Beat(startTime + gap * 230, "K"),
					new Beat(startTime + gap * 230, "L"),
			};
		}
		else if(titleName.equals("FIFA Online3 BGM - Time Bomb") && difficulty.equals("Hard")) {
			int startTime = 1500 - Main.REACH_TIME * 2000;
			int gap = 139;
			beats = new Beat[] {
					new Beat(startTime + gap * 27, "K")
					
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}									// 텀을 두면서 노트를 떨어트림
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void judge(String input) {
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		
		if(!judge.equals("none")) {
			noneImage = new ImageIcon(Main.class.getResource("../images/none.png")).getImage();
		}
		if(judge.equals("Break")) {
			++breakCount;
			gameScore += 0;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeBreak.png")).getImage();
		}
		else if(judge.equals("Max")) {
			++maxCount;
			gameScore += 100;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMax.png")).getImage();
		}
	}
}

