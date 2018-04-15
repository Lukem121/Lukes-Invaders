package main;

import java.awt.Graphics2D;

public class Monster extends Actor{
	
	int shouldMove = 0;
	int direction = -1;
	int moveAmmount = 10;
	int moveDownAmmount = 20;
	int movedLeftAmmount = 4;
	int movedRightAmmount = 0;
	
	public Monster(SpaceInvaders si) {
		
	}

	public void act(){
		shouldMove++;
		if(shouldMove > 45){
			
			shouldMove = 0;
			
			if(direction == -1){
				this.x = this.x - moveAmmount;
				movedLeftAmmount++;
			}
			if(direction == 1){
				this.x = this.x + moveAmmount;
				movedRightAmmount++;
			}
		}
		
		if(movedLeftAmmount >=8){
			movedLeftAmmount = 0;
			this.y = this.y + moveDownAmmount;
			direction = 1;
		}
		if(movedRightAmmount >=8){
			movedRightAmmount = 0;
			this.y = this.y + moveDownAmmount;
			direction = -1;
		}
	}
		
}
