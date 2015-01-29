package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Menu;

public class pickFlax extends Task<ClientContext> {
	
	Tile lastFlaxTile;
	int flaxObjectID = 7134;
	Area fieldArea = new Area(new Tile(2737, 3452, 0), new Tile(2750, 3438, 0));
	
	public pickFlax(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return ctx.inventory.select().count()<28
				&&fieldArea.contains(ctx.players.local())
				&&!ctx.players.local().inMotion()
				&&!ctx.objects.select().id(flaxObjectID).isEmpty();
		
	}
	
	@Override
	public void execute(){
		
		GameObject flaxObject = ctx.objects.nearest().poll();
		
		if(ctx.menu.items()[0].toString().toLowerCase().contains("pick flax")&&!ctx.players.local().inMotion()){
			
			int clickMethod = (Random.nextInt(1, 16));
			
			if(clickMethod<3){
				
				ctx.input.move(ctx.input.getLocation().x + Random.nextInt(-2, 3),ctx.input.getLocation().y + Random.nextInt(-2, 3));
				
			}
			
			if(ctx.menu.items()[0].toString().toLowerCase().contains("pick flax")){
				ctx.input.click(ctx.input.getLocation().x,ctx.input.getLocation().y,true);
			}
			
			Condition.sleep(Random.nextInt(70, 210)); //adding more random offset
			
		}else if(!ctx.menu.items()[0].toString().toLowerCase().contains("pick flax")&&flaxObject.inViewport()&&flaxObject.tile().x()>2736&&flaxObject.tile().y()<3438&&flaxObject.valid()&&flaxObject.tile().matrix(ctx).reachable()){
						
			flaxObject.interact("Pick", flaxObject.name());
			
			Condition.wait(new Callable<Boolean>(){
				
            	@Override
            	public Boolean call() throws Exception{
            	
            		return ctx.menu.items()[0].toString().toLowerCase().contains("pick flax");
            	
            	}
            	
			},30, 30);
			
			Condition.sleep(Random.nextInt(70, 110)); //adding more random offset
			
		}else if(!flaxObject.inViewport()&&flaxObject.tile().x()>2736&&flaxObject.valid()&&flaxObject.tile().matrix(ctx).reachable()){
						
			ctx.movement.step(flaxObject);
			
			Condition.wait(new Callable<Boolean>(){
				
            	@Override
            	public Boolean call() throws Exception{
            	
            		return !ctx.players.local().inMotion()
            				&&ctx.players.local().animation()==-1;
            	
            	}
            	
			},500, 20);
			
		}else if(flaxObject.hover()&&ctx.menu.items()[0].toString().toLowerCase().contains("walk here")){
			
			//invisible flax or already been picked
			
			flaxObject = ctx.objects.id(flaxObjectID).within(fieldArea).name("Flax").viewable().select().limit(10).nearest().shuffle().poll();
						
			flaxObject.interact("Pick", "Flax");
			
			
		}
		
	}
}
