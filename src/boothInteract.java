package avosFlaxPicker;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class boothInteract extends Task<ClientContext> {
	
	Area bankArea = new Area(new Tile(2730, 3495, 0),new Tile(2721, 3491, 0));
	int bankBoothID = 25808;
	
	public boothInteract(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return ctx.inventory.select().count()==28
				&&bankArea.contains(ctx.players.local())
				&&ctx.bank.opened()==false;
				
	}
	
	@Override
	public void execute(){
		
		GameObject bankBooth = ctx.objects.select().id(bankBoothID).nearest().poll();
		
		if(bankBooth==null){
			
			System.out.println("An error occured: Could not find bank booth. Using ID: " + bankBoothID);
			ctx.controller.stop();
			
		}
		
		bankBooth.interact("Bank", bankBooth.name());
		
        Condition.wait(new Callable<Boolean>(){

            @Override
            public Boolean call() throws Exception{
                return ctx.bank.opened();
            }

        },300,20);
		
	}
}
