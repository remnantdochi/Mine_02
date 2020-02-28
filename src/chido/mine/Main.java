package chido.mine;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerEvent;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
	public static int[][] maps = new int[10][10];
	public static int[] tnts = new int[20];
	public static int[][] check = new int[10][10];
	public static int score = 20;

	public void onEnable() {
		//PluginDescriptionFile pdFile = this.getDescription();
		//System.out.println(pdFile.getName() + " ver" + pdFile.getVersion() + "is now activated");
		getServer().getConsoleSender().sendMessage("The Start and change3");
		getServer().getPluginManager().registerEvents(this, this); //왜 해야 해?????물음표 !!
		
		for(int[] row: maps) 
		{
			Arrays.fill(row, 0);
		}
		Arrays.fill(tnts, 0);
		for(int[] row: check) 
		{
			Arrays.fill(row, -1);
		}
		getCommand("tnt").setExecutor(new Tnt_03()); //plugin.yml에 추가해야함 근데 엔터후에 탭이 아니라 스페이스2번
	}
	
	public void onDisable() {
		//PluginDescriptionFile pdFile = this.getDescription();
		//System.out.println(pdFile.getName() + " ver" + pdFile.getVersion() + "is now deactivated");
		getServer().getConsoleSender().sendMessage("The End");
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		e.setJoinMessage("hello //// " + p.getName() +" nice to meet you");
		
	}
	
	@EventHandler
	public void bp(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		World w= p.getWorld();
		if (b.getType() == Material.REDSTONE_TORCH) {
			Location new_loc = b.getLocation();
			int x = new_loc.getBlockX();
			int z = new_loc.getBlockZ();
			if (maps[x][z] == -1) {
				score -=1;
				if (score ==0) {
					p.sendMessage("you win!");
					//boolean test = onCommand(getServer().getConsoleSender(),"tnt", "tnt", {"start"});
				}
			}
		}
		//p.sendMessage(b.toString());
	}
	
	@EventHandler
	public void bk(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		World w = p.getWorld();
		if (b.getType() == Material.SEA_LANTERN)
		{
			Location new_loc = b.getLocation();
			int x = new_loc.getBlockX();
			int z = new_loc.getBlockZ();
			//p.sendMessage(Integer.toString(x)+ " "+ Integer.toString(z)+" "+Integer.toString(maps[x][z]));
			if (maps[x][z] == 0) {
				breaking(w,x,z);
			}
			else if (maps[x][z] == -1) {
				p.sendMessage("be careful!");
				p.setHealth(0);
				//getServer().sendPluginMessage(this, "tnt ", "undo");
			}
		}
		else if (b.getType() == Material.REDSTONE_TORCH) {
			Location new_loc = b.getLocation();
			int x = new_loc.getBlockX();
			int z = new_loc.getBlockZ();
			if (maps[x][z] == -1) {
				score +=1;
			}
		}
	}
	
	public void breaking(World w, int loc_x, int loc_z) {
		int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
		for (int i=0;i<8;i++) {
			int new_x = loc_x+dir[i][0];
			int new_z = loc_z+dir[i][1];
			if (new_x <0 || new_x > 9 || new_z <0 || new_z > 9) continue;
			else if (maps[new_x][new_z] == 0 && check[new_x][new_z] == -1) {
				check[new_x][new_z] = 0;
				Location breaking = new Location(w,new_x,4,new_z);
				w.getBlockAt(breaking).setType(Material.AIR);
				breaking(w,new_x,new_z);
			}
			else if (maps[new_x][new_z] !=0 && maps[new_x][new_z] != -1) {
				Location number = new Location(w,new_x,4,new_z);
				w.getBlockAt(number).setType(Material.AIR);
				//return;
			}
			
		}
	}
	
	

}
