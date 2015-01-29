package avosFlaxPicker;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name="avosFlaxPicker", description="Picks flax and banks them in Seers Village", properties = "client=4")
public class AvosFlaxPicker extends PollingScript<ClientContext> implements MessageListener, PaintListener {
	
	public static long startTime;
	private int numFlax=0;
	
	private List<Task> taskList=new ArrayList<Task>();
	
	@Override
	public void start(){
	
		taskList.addAll(Arrays.asList(new walkToBank(ctx),new walkToField(ctx),new boothInteract(ctx),new depositAll(ctx),new pickFlax(ctx),new closeBank(ctx),new enterFieldRandom(ctx),new enterBankRandom(ctx),new cameraRotate(ctx)));
		System.out.println("Starting script...");
		startTime = System.currentTimeMillis();
		
	}
	
	@Override
	public void poll(){
		if(ctx.game.loggedIn()){
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }
		}
		
	}
	
	  @Override
	    public void messaged(MessageEvent e) {
	        String msg = e.getMessage().toLowerCase();
	        if (msg.contains("you pick some flax")) {
	            numFlax++;
	        }
	    }

	    @Override
	    public void repaint(Graphics g) {
	    	
        	g.setColor(new Color(90,71,32,180));
        	g.fillRect(9, 26, 150, 20);
        	g.fillRect(9, 27, 150, 90);
        	g.setColor(Color.BLACK);
        	g.drawRect(8, 25, 151, 21);
        	g.drawRect(8, 25, 151, 91);
	    	
	        g.setColor(Color.WHITE);
	        g.drawString("Avos Flax Picker", 12, 41);
        	
	        g.setColor(Color.WHITE);
	        
	        g.drawString("Flax picked: " + numFlax, 12, 66);
	        g.drawString("Flax/hour: " + perHour(numFlax), 12, 82);
	        g.drawString("Runtime: " + runTime(startTime), 12, 98);
	        
	        int mX = ctx.input.getLocation().x;
	        int mY = ctx.input.getLocation().y;
	        
	        int pX[] = {mX, mX + 10, mX + 5, mX + 9, mX + 7, mX + 3, mX, mX};
	        int pY[] = {mY, mY + 8, mY + 8, mY + 14, mY + 16, mY + 9, mY + 13, mY};
	        
	        g.setColor(Color.WHITE);
	        g.fillPolygon(pX,pY,8);
	        g.setColor(Color.BLACK);
	        g.drawPolygon(pX,pY,8);
	        
	        
	    }
	    
	    /* Runtime and flax/hour method from post http://www.powerbot.org/community/topic/719001-how-do-i-set-up-my-paint-with-exp-per-hour-time-ran-etc/#entry8676451 by user Kenneh */
	    
	    public String runTime(long i) {
	    	DecimalFormat nf = new DecimalFormat("00");
	    	long millis = System.currentTimeMillis() - i;
	    	long hours = millis / (1000 * 60 * 60);
	    	millis -= hours * (1000 * 60 * 60);
	    	long minutes = millis / (1000 * 60);
	    	millis -= minutes * (1000 * 60);
	    	long seconds = millis / 1000;
	    	return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	    	}
	    
	    public String perHour(int gained) {
	        return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - startTime)));
	    }
	    
	    public String formatNumber(int start) {
	        DecimalFormat nf = new DecimalFormat("0.0");
	        double i = start;
	        if(i >= 1000000) {
	            return nf.format((i / 1000000)) + "M";
	        }
	        if(i >=  1000) {
	            return nf.format((i / 1000)) + "K";
	        }
	        return ""+start;
	    }
	 
	
	
}
