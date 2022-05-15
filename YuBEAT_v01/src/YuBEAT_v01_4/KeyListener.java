package YuBEAT_v01_4;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (YuBEAT.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			YuBEAT.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			YuBEAT.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			YuBEAT.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			YuBEAT.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			YuBEAT.game.pressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			YuBEAT.game.pressL();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (YuBEAT.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			YuBEAT.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			YuBEAT.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			YuBEAT.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			YuBEAT.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			YuBEAT.game.releaseK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			YuBEAT.game.releaseL();
		}
	}

}
