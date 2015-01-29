package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class enterBankRandom extends Task<ClientContext> {
	
	Area bankArea = new Area(new Tile(2730, 3495, 0),new Tile(2721, 3491, 0));
	
	public enterBankRandom(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return bankArea.getClosestTo(ctx.players.local()).distanceTo(ctx.players.local())<=12
				&&!bankArea.contains(ctx.players.local())
				&&ctx.inventory.select().count()==28;
		
	}
	
	@Override
	public void execute(){
				
		ctx.movement.step(bankArea.getRandomTile());
		
		Condition.wait(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				
				return ctx.movement.distance(ctx.players.local(),ctx.movement.destination()) <= Random.nextInt(0, 3)
						|| !ctx.players.local().inMotion();
				
			}
			
		},400,100);
		
		
	}
}
