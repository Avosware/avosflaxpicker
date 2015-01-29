package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class walkToBank extends Task<ClientContext> {
	
	Tile[] toBankTile = new Tile[]{new Tile(2732, 3443, 0), new Tile(2731, 3449, 0), new Tile(2730, 3455, 0), new Tile(2727, 3461, 0), new Tile(2728, 3467, 0), new Tile(2728, 3474, 0), new Tile(2728, 3481, 0), new Tile(2726, 3486, 0)};
	Area bankArea = new Area(new Tile(2730, 3495, 0),new Tile(2721, 3491, 0));
	
	public walkToBank(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return ctx.inventory.select().count()==28
				&&!bankArea.contains(ctx.players.local())
				&&bankArea.getClosestTo(ctx.players.local()).distanceTo(ctx.players.local())>12;
	}
	
	@Override
	public void execute(){
				
		ctx.movement.newTilePath(toBankTile).randomize(1, 1).traverse();
						
		Condition.wait(new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception{
				
				return ctx.movement.distance(ctx.players.local(), ctx.movement.destination()) <= Random.nextInt(0, 8)
						||!ctx.players.local().inMotion();
				
			}
			
		},300,380);
				
	}
}
