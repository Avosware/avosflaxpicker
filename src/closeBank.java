package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class closeBank extends Task<ClientContext> {
	
	Area bankArea = new Area(new Tile(2730, 3495, 0),new Tile(2721, 3491, 0));
	int bankBoothID = 25808;
	
	public closeBank(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return ctx.inventory.select().count()==0
				&&bankArea.contains(ctx.players.local())
				&&ctx.bank.opened()==true;
		
	}
	
	@Override
	public void execute(){
		
		int closeMethod = (Random.nextInt(1, 8));
		
		if(closeMethod > 1){
		
			System.out.println("closing bank");
			
			Condition.sleep(300);
			
			ctx.bank.close();
			
		}else{
			
			System.out.println("closing bank via running");
			
			Condition.sleep(300);
			
			ctx.movement.step(bankArea.getRandomTile());
			
		}
		
		Condition.wait(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				
				return !ctx.bank.opened();
				
			}
			
		},200,20);
		
		
		
	}
}
