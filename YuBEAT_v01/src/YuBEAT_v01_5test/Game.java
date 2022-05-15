package YuBEAT_v01_5test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;

import YuBEAT_v01.Beat;

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
		
		g.setColor(Color.black);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("CookieRun Regular", Font.BOLD, 28));
		g.drawString(titleName, 33, 561);
		g.setFont(new Font("CookieRun Regular", Font.BOLD, 45));
		g.drawString(difficulty, 33, 662);
		g.drawString(gameScore.toString(), 33, 762);
		
	}
	
	public Integer getGameScore() { return gameScore; }
	
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
			int startTime = 5650 - Main.REACH_TIME * 2000;
			int gap = 225;
			beats = new Beat[] {
					 new Beat(startTime + gap * 2, "F"), new Beat(startTime + gap * 6, "K"),
					new Beat(startTime + gap * 11, "K"), new Beat(startTime + gap * 13, "D"),
					new Beat(startTime + gap * 16, "K"), new Beat(startTime + gap * 17, "K"),
					new Beat(startTime + gap * 18, "F"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 21, "K"), new Beat(startTime + gap * 22, "F"),
					new Beat(startTime + gap * 24, "F"), new Beat(startTime + gap * 26, "K"),
					new Beat(startTime + gap * 27, "K"), new Beat(startTime + gap * 30, "F"),
					new Beat(startTime + gap * 31, "K"), new Beat(startTime + gap * 32, "S"),
					new Beat(startTime + gap * 36, "D"), new Beat(startTime + gap * 37, "K"),
					new Beat(startTime + gap * 39, "K"), new Beat(startTime + gap * 42, "F"),
					new Beat(startTime + gap * 44, "K"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 50, "F"), new Beat(startTime + gap * 52, "K"),
					new Beat(startTime + gap * 53, "L"), new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 58, "K"), new Beat(startTime + gap * 60, "S"),
					new Beat(startTime + gap * 62, "S"), new Beat(startTime + gap * 64, "K"),
					new Beat(startTime + gap * 66, "K"), new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 73, "K"), new Beat(startTime + gap * 75, "F"),
					new Beat(startTime + gap * 78, "K"), new Beat(startTime + gap * 80, "K"),
					new Beat(startTime + gap * 81, "D"), new Beat(startTime + gap * 83, "J"),
					new Beat(startTime + gap * 85, "K"), new Beat(startTime + gap * 86, "K"),
					new Beat(startTime + gap * 97, "S"), new Beat(startTime + gap * 99, "K"),
					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 104, "K"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 110, "D"), new Beat(startTime + gap * 115, "K"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 126, "K"), new Beat(startTime + gap * 127, "S"),
					new Beat(startTime + gap * 129, "F"), new Beat(startTime + gap * 130, "K"),
					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 137, "K"), new Beat(startTime + gap * 139, "K"),
					new Beat(startTime + gap * 142, "F"), new Beat(startTime + gap * 144, "K"),
					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
					new Beat(startTime + gap * 152, "K"), new Beat(startTime + gap * 153, "F"),
					new Beat(startTime + gap * 156, "J"), new Beat(startTime + gap * 158, "K"),
					new Beat(startTime + gap * 160, "S"),
					new Beat(startTime + gap * 162, "S"), new Beat(startTime + gap * 164, "K"),
					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "S"),
					new Beat(startTime + gap * 170, "K"), new Beat(startTime + gap * 171, "S"),
					new Beat(startTime + gap * 174, "K"), new Beat(startTime + gap * 176, "K"),
					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 182, "K"), new Beat(startTime + gap * 183, "J"),
					new Beat(startTime + gap * 186, "K"), new Beat(startTime + gap * 190, "K"),
					new Beat(startTime + gap * 190, "L"), new Beat(startTime + gap * 190, "J"),
					new Beat(startTime + gap * 194, "S"), new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 205, "K"), new Beat(startTime + gap * 212, "D"),
					new Beat(startTime + gap * 216, "F"), new Beat(startTime + gap * 219, "K"),
					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "S"),
					new Beat(startTime + gap * 225, "K"), new Beat(startTime + gap * 227, "K"),
					new Beat(startTime + gap * 229, "F"), new Beat(startTime + gap * 230, "K"),
					new Beat(startTime + gap * 232, "K"), new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 237, "K"), new Beat(startTime + gap * 239, "K"),
					new Beat(startTime + gap * 242, "F"), new Beat(startTime + gap * 243, "K"),
					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 251, "K"), new Beat(startTime + gap * 253, "L"),
					new Beat(startTime + gap * 256, "J"), new Beat(startTime + gap * 257, "K"),
					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 263, "K"), new Beat(startTime + gap * 266, "K"),
					new Beat(startTime + gap * 269, "S"), new Beat(startTime + gap * 270, "K"),
					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
					new Beat(startTime + gap * 275, "K"), new Beat(startTime + gap * 278, "D"),
					new Beat(startTime + gap * 281, "F"), new Beat(startTime + gap * 282, "K"),
					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
					new Beat(startTime + gap * 287, "K"), new Beat(startTime + gap * 297, "S"),
					new Beat(startTime + gap * 300, "S"), new Beat(startTime + gap * 301, "K"),
					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
					new Beat(startTime + gap * 307, "K"), new Beat(startTime + gap * 310, "J"),
					new Beat(startTime + gap * 320, "S"), new Beat(startTime + gap * 321, "K"),
					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
					new Beat(startTime + gap * 326, "K"), new Beat(startTime + gap * 327, "K"),
					new Beat(startTime + gap * 329, "F"), new Beat(startTime + gap * 330, "K"),
					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
					new Beat(startTime + gap * 337, "K"), new Beat(startTime + gap * 339, "K"),
					new Beat(startTime + gap * 342, "F"), new Beat(startTime + gap * 343, "K"),
					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
					new Beat(startTime + gap * 351, "K"), new Beat(startTime + gap * 353, "L"),
					new Beat(startTime + gap * 356, "J"), new Beat(startTime + gap * 357, "K"),
					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
					new Beat(startTime + gap * 363, "K"), new Beat(startTime + gap * 366, "K"),
					new Beat(startTime + gap * 369, "L"), new Beat(startTime + gap * 370, "K"),
					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
					new Beat(startTime + gap * 375, "K"), new Beat(startTime + gap * 378, "D"),
					new Beat(startTime + gap * 381, "F"), new Beat(startTime + gap * 382, "K"),
					new Beat(startTime + gap * 383, "J"), new Beat(startTime + gap * 384, "S"),
					new Beat(startTime + gap * 385, "K"), new Beat(startTime + gap * 388, "S"),
					new Beat(startTime + gap * 392, "D"), new Beat(startTime + gap * 393, "K"),
					new Beat(startTime + gap * 396, "F"), new Beat(startTime + gap * 400, "S"),
					new Beat(startTime + gap * 401, "K"), new Beat(startTime + gap * 404, "F"),
					new Beat(startTime + gap * 407, "K"), new Beat(startTime + gap * 408, "K"),
					new Beat(startTime + gap * 410, "F"), new Beat(startTime + gap * 412, "K"),
					new Beat(startTime + gap * 413, "K"), new Beat(startTime + gap * 416, "D"),
					new Beat(startTime + gap * 419, "K"), new Beat(startTime + gap * 420, "K"),
					new Beat(startTime + gap * 422, "F"), new Beat(startTime + gap * 426, "J"),
					new Beat(startTime + gap * 427, "K"), new Beat(startTime + gap * 430, "F"),
					new Beat(startTime + gap * 433, "L"), new Beat(startTime + gap * 434, "K"),
					new Beat(startTime + gap * 436, "J"), new Beat(startTime + gap * 440, "S"),
					new Beat(startTime + gap * 441, "K"), new Beat(startTime + gap * 442, "F"),
					new Beat(startTime + gap * 446, "K"), new Beat(startTime + gap * 447, "K"),
					new Beat(startTime + gap * 449, "S"), new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 456, "K"), new Beat(startTime + gap * 458, "K"),
					new Beat(startTime + gap * 459, "D"), new Beat(startTime + gap * 460, "K"),
					new Beat(startTime + gap * 463, "J"), new Beat(startTime + gap * 466, "K"),
					new Beat(startTime + gap * 467, "K"), new Beat(startTime + gap * 477, "S"),
					new Beat(startTime + gap * 480, "S"), new Beat(startTime + gap * 482, "K"),
					new Beat(startTime + gap * 482, "D"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 487, "K"), new Beat(startTime + gap * 490, "D"),
					new Beat(startTime + gap * 493, "F"), new Beat(startTime + gap * 494, "K"),
					new Beat(startTime + gap * 494, "S"), new Beat(startTime + gap * 497, "S"),
					new Beat(startTime + gap * 498, "K"), new Beat(startTime + gap * 499, "F"),
					new Beat(startTime + gap * 502, "K"), new Beat(startTime + gap * 503, "K"),
					new Beat(startTime + gap * 506, "D"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 510, "K"), new Beat(startTime + gap * 512, "F"),
					new Beat(startTime + gap * 516, "K"), new Beat(startTime + gap * 517, "K"),
					new Beat(startTime + gap * 520, "F"), new Beat(startTime + gap * 523, "D"),
					new Beat(startTime + gap * 524, "K"), new Beat(startTime + gap * 526, "J"),
					new Beat(startTime + gap * 530, "S"), new Beat(startTime + gap * 531, "K"),
					new Beat(startTime + gap * 532, "F"), new Beat(startTime + gap * 536, "K"),
					new Beat(startTime + gap * 537, "K"), new Beat(startTime + gap * 539, "S"),
					new Beat(startTime + gap * 541, "S"), new Beat(startTime + gap * 542, "K"),
					new Beat(startTime + gap * 547, "K"), new Beat(startTime + gap * 548, "D"),
					new Beat(startTime + gap * 549, "K"), new Beat(startTime + gap * 551, "F"),
					new Beat(startTime + gap * 553, "J"), new Beat(startTime + gap * 554, "K"),
					new Beat(startTime + gap * 556, "K"), new Beat(startTime + gap * 564, "S"),
					new Beat(startTime + gap * 565, "K"), new Beat(startTime + gap * 568, "S"),
					new Beat(startTime + gap * 572, "D"), new Beat(startTime + gap * 573, "K"),
					new Beat(startTime + gap * 576, "F"), new Beat(startTime + gap * 580, "F"),
					new Beat(startTime + gap * 581, "K"), new Beat(startTime + gap * 584, "S"),
					new Beat(startTime + gap * 587, "K"), new Beat(startTime + gap * 588, "K"),
					new Beat(startTime + gap * 589, "F"), new Beat(startTime + gap * 592, "K"),
					new Beat(startTime + gap * 593, "K"), new Beat(startTime + gap * 596, "D"),
					new Beat(startTime + gap * 599, "K"), new Beat(startTime + gap * 600, "K"),
					new Beat(startTime + gap * 602, "F"), new Beat(startTime + gap * 606, "K"),
					new Beat(startTime + gap * 607, "K"), new Beat(startTime + gap * 610, "F"),
					new Beat(startTime + gap * 613, "L"), new Beat(startTime + gap * 614, "K"),
					new Beat(startTime + gap * 616, "J"), new Beat(startTime + gap * 620, "S"),
					new Beat(startTime + gap * 621, "K"), new Beat(startTime + gap * 622, "S"),
					new Beat(startTime + gap * 626, "K"), new Beat(startTime + gap * 627, "K"),
					new Beat(startTime + gap * 629, "S"), new Beat(startTime + gap * 631, "S"),
					new Beat(startTime + gap * 632, "K"), new Beat(startTime + gap * 634, "K"),
					new Beat(startTime + gap * 638, "D"), new Beat(startTime + gap * 639, "K"),
					new Beat(startTime + gap * 641, "F"), new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 644, "K"), new Beat(startTime + gap * 646, "K"),
					new Beat(startTime + gap * 647, "S"), new Beat(startTime + gap * 648, "K"),
					new Beat(startTime + gap * 650, "S"), new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 653, "K"), new Beat(startTime + gap * 656, "F"),
					new Beat(startTime + gap * 660, "D"), new Beat(startTime + gap * 661, "K"),
					new Beat(startTime + gap * 665, "F"), new Beat(startTime + gap * 671, "F"),
					new Beat(startTime + gap * 672, "K"), new Beat(startTime + gap * 674, "F"),
					new Beat(startTime + gap * 677, "K"), new Beat(startTime + gap * 678, "K"),
					new Beat(startTime + gap * 679, "F"), new Beat(startTime + gap * 682, "K"),
					new Beat(startTime + gap * 683, "K"), new Beat(startTime + gap * 686, "D"),
					new Beat(startTime + gap * 689, "K"), new Beat(startTime + gap * 690, "K"),
					new Beat(startTime + gap * 692, "F"), new Beat(startTime + gap * 696, "K"),
					new Beat(startTime + gap * 697, "K"), new Beat(startTime + gap * 700, "F"),
					new Beat(startTime + gap * 703, "L"), new Beat(startTime + gap * 704, "K"),
					new Beat(startTime + gap * 706, "J"), new Beat(startTime + gap * 710, "S"),
					new Beat(startTime + gap * 711, "K"), new Beat(startTime + gap * 712, "S"),
					new Beat(startTime + gap * 716, "K"), new Beat(startTime + gap * 717, "K"),
					new Beat(startTime + gap * 719, "F"), new Beat(startTime + gap * 720, "K"),
					new Beat(startTime + gap * 721, "S"), new Beat(startTime + gap * 722, "D"),
					new Beat(startTime + gap * 725, "F"), new Beat(startTime + gap * 728, "D"),
					new Beat(startTime + gap * 730, "K"), new Beat(startTime + gap * 733, "L"),
					new Beat(startTime + gap * 737, "F"), new Beat(startTime + gap * 737, "K")
			
					
					
					
					
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
			gameScore += 0;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeBreak.png")).getImage();
		}
		else if(judge.equals("Max")) {
			gameScore += 100;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMax.png")).getImage();
		}
	}
}
