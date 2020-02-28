package chido.mine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Tnt_03 implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String s, String[] args) {
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			//p.sendMessage("command test");
			World w = p.getWorld();
			
			if (args[0].equalsIgnoreCase("start")) {
				if (p.isOp()) {
					p.getInventory().addItem(new ItemStack(Material.REDSTONE_TORCH,20));
				}
				for (int i=0;i<20;i++) {
					Random rand = new Random();
					int rand_tnt = rand.nextInt(100);
					Main.tnts[i] = rand_tnt;
					//p.sendMessage(Integer.toString(i)+ " " + Integer.toString(rand_tnt));
					boolean checkfordouble = false;
					for(int k=0;k<i;k++) { //중복 제거
						//p.sendMessage(Integer.toString(k));
						if (Main.tnts[k]==Main.tnts[i]) {
							i--;
							//p.sendMessage("there are two more");
							checkfordouble = true;
							break;
						}
					}
					if (checkfordouble) continue;
					
					//p.sendMessage(Integer.toString(rand_tnt));
					
					int x = rand_tnt /10;
					int z = rand_tnt %10;
					//p.sendMessage(Integer.toString(x)+Integer.toString(z));
					int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
					for (int j=0;j<8;j++) {
						int new_x = x+dir[j][0];
						int new_z = z+dir[j][1];
						if (new_x <0 || new_x > 9 || new_z <0 || new_z > 9) continue;
						
						Main.maps[new_x][new_z] +=1;
						//p.sendMessage("ck3: "+Integer.toString(new_x)+ " " + Integer.toString(new_z)+" " + Integer.toString(Main.maps[new_x][new_z]));
					}
					
				}
				
				for (int tnt : Main.tnts) //tnt 위치는 그냥 -1로 따로 리셋
				{
					int x = tnt /10;
					int z = tnt %10;
					Main.maps[x][z] = -1;
				}
				
				for (int i=0;i<10;i++) {
					for (int j=0;j<10;j++) {
						Location loc = new Location(w,i,3,j);
						if (Main.maps[i][j] == -1) w.getBlockAt(loc).setType(Material.REDSTONE_BLOCK);
						else if (Main.maps[i][j] == 0)  w.getBlockAt(loc).setType(Material.COBBLESTONE);
						else if (Main.maps[i][j] == 1)  w.getBlockAt(loc).setType(Material.WHITE_CONCRETE);
						else if (Main.maps[i][j] == 2)  w.getBlockAt(loc).setType(Material.YELLOW_CONCRETE);
						else if (Main.maps[i][j] == 3)  w.getBlockAt(loc).setType(Material.ORANGE_CONCRETE);
						else if (Main.maps[i][j] == 4)  w.getBlockAt(loc).setType(Material.LIME_CONCRETE);
						else if (Main.maps[i][j] == 5)  w.getBlockAt(loc).setType(Material.GREEN_CONCRETE);
						else if (Main.maps[i][j] == 6)  w.getBlockAt(loc).setType(Material.LIGHT_BLUE_CONCRETE);
						else if (Main.maps[i][j] == 7)  w.getBlockAt(loc).setType(Material.BLUE_CONCRETE);
						else if (Main.maps[i][j] == 8)  w.getBlockAt(loc).setType(Material.PURPLE_CONCRETE);
						loc = new Location(w,i,4,j);
						w.getBlockAt(loc).setType(Material.SEA_LANTERN);
						
						//p.sendMessage(Integer.toString(maps[i][j]));
						
						//tnt
//						if (IntStream.of(tnts).anyMatch(x->x==location)) {
//							
//							p.sendMessage(Integer.toString(location));
//						} 이거 그냥 나중에 필요해 보여서 저장저장
					}
				}
				
			}
			
			if (args[0].equalsIgnoreCase("undo")) {
				Main.score = 20;
				p.getInventory().remove(new ItemStack(Material.REDSTONE_TORCH,20));
				p.updateInventory();

				for(int[] row: Main.maps) 
				{
					Arrays.fill(row, 0);
				}
				Arrays.fill(Main.tnts, 0);
				for(int[] row: Main.check) 
				{
					Arrays.fill(row, -1);
				}
				
				for (int i=0;i<10;i++) {
					for (int j=0;j<10;j++) {
						Location loc = new Location(w,i,3,j);
						w.getBlockAt(loc).setType(Material.AIR);
						loc = new Location(w,i,4,j);
						w.getBlockAt(loc).setType(Material.AIR);
					}
				}
				
				List<Entity> ents = w.getEntities();
				for (Entity ent : ents) 
				{
					if (ent.getType() != EntityType.PLAYER) 
					{
						ent.remove();
					}
				}
			}
		}
		return false;
	}
}
