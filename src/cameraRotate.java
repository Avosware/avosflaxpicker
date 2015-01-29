package avosFlaxPicker;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

public class cameraRotate extends Task<ClientContext> {
	
	public cameraRotate(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return !ctx.bank.opened();
				
	}
	
	@Override
	public void execute(){
		
		/*
		 * 
		 * Camera rotations and some random mouse movement/tab clicking
		 * 
		 */
		
		int doRotate = Random.nextInt(1, 460);
		
		if(!ctx.menu.items()[0].toString().toLowerCase().contains("pick flax")){
		
			switch(doRotate){
			
			case 1:
				
				ctx.camera.angle(Random.nextInt(1, 359));
				ctx.camera.pitch(Random.nextInt(1, 100));
				break;
			
			case 2:
				
				ctx.camera.angle(Random.nextInt(1, 359));
				break;
				
			case 3: 
				
				ctx.camera.pitch(Random.nextInt(1, 100));
				break;
				
			case 4: 
				
				ctx.input.move(Random.nextInt(1,ctx.game.dimensions().width),Random.nextInt(1, ctx.game.dimensions().height));
				
				Condition.sleep(400);
				break;
				
			case 5:
				
				int randomTab=Random.nextInt(1, 10);
				
				if(randomTab==1){
				
					ctx.game.tab(Game.Tab.STATS);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);
				
				}else if(randomTab==2){
					
					ctx.game.tab(Game.Tab.FRIENDS_LIST);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);	
					
				}else if(randomTab==3){
					
					ctx.game.tab(Game.Tab.OPTIONS);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);
					
				}else if(randomTab==4){
					
					ctx.game.tab(Game.Tab.CLAN_CHAT);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);
					
				}else if(randomTab==5){
					
					ctx.game.tab(Game.Tab.ATTACK);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);
					
				}else if(randomTab==6){
					
					ctx.game.tab(Game.Tab.QUESTS);
					
					Condition.sleep(Random.nextInt(900, 2300)); //more random offset
					
					ctx.game.tab(Game.Tab.INVENTORY);
					
				}
				
				break;
				
			case 6:
				
				ctx.camera.pitch(Random.nextInt(1, 100));
				break;
				
			case 7:
				
				ctx.camera.angle(Random.nextInt(1, 359));
				ctx.camera.pitch(Random.nextInt(1, 100));
				break;
				
			case 8:
				
				ctx.input.move(Random.nextInt(1,ctx.game.dimensions().width),Random.nextInt(1, ctx.game.dimensions().height));
				
				Condition.sleep(200);
				break;
				
			default:
			
			}
		
		}
		
	}
}
