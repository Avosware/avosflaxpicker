package avosFlaxPicker;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class depositAll extends Task<ClientContext> {
	
	Area bankArea = new Area(new Tile(2730, 3495, 0),new Tile(2721, 3491, 0));
	int bankBoothID = 25808;
	
	public depositAll(ClientContext ctx){
		
		super(ctx);
		
	}
	
	@Override
	public boolean activate(){
		
		return ctx.inventory.select().count()==28
				&&bankArea.contains(ctx.players.local())
				&&ctx.bank.opened()==true;
		
	}
	
	@Override
	public void execute(){
	
		System.out.println("bank opened");
		
		if(ctx.inventory.select().count()>0){
		ctx.bank.depositInventory();
		ctx.inventory.select(); //refresh in case
		}
		
		Condition.sleep(500);
		
		
	}
}
