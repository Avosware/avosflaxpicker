package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class enterFieldRandom extends Task<ClientContext> {
	
	Area fieldArea = new Area(new Tile(2737, 3452, 0), new Tile(2747, 3437, 0));
	
	//old Area fieldArea = new Area(new Tile(2739, 3451, 0), new Tile(2750, 3438, 0));
	
	public enterFieldRandom(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return fieldArea.getClosestTo(ctx.players.local()).distanceTo(ctx.players.local())<=11
				&&!fieldArea.contains(ctx.players.local())
				&&ctx.inventory.select().count()<28;
		
	}
	
	@Override
	public void execute(){
			
		ctx.movement.step(fieldArea.getRandomTile());
				
		Condition.wait(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				
				return ctx.movement.distance(ctx.movement.destination()) <= Random.nextInt(0, 3)
						|| !ctx.players.local().inMotion();
				
			}
			
		},400,150);
		
		
		
	}
}
