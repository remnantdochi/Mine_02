package chido.mine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
//import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Tnt_01 implements CommandExecutor {
	int[][] tnts = new int[10][2];
	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String s, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			//p.sendMessage("command 인식 완료");
			World w = p.getWorld();
			
			
			if (args[0].equalsIgnoreCase("start")) {
				
				
				
				for (int i=0;i<10;i++) { //tnt 위치할곳
					double tmp_x = Math.random();
					double tmp_z = Math.random();
					int x= (int)(tmp_x*10);
					int z = (int)(tmp_z*10);
					tnts[i][0] = x;
					tnts[i][1] = z;
					//p.sendMessage("test in f"+ tnts[i][0] + tnts[i][1]);
					Location loc = new Location(w,x,4,z);
					//p.teleport(loc);
					w.getBlockAt(loc).setType(Material.GOLD_BLOCK);
					
					//
					int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
					for (int j=0;j<4;j++) {
						//Random rand = new Random();
						double new_x = x+dir[j][0];
						double new_z = z+dir[j][1];
						if (new_x <0 || new_x > 10 || new_z <0 || new_z > 10) continue;
						loc = new Location(w,new_x,6,new_z);
						w.getBlockAt(loc).setType(Material.AIR);
						loc = new Location(w,new_x,7,new_z);
						//loc = new Location(w,new_x,5,new_z);
						//Entity hint = w.spawnEntity(loc,EntityType.ARROW);
						//Entity hint = w.spawnArrow(loc, null, 0, 0);

						//Item.apple = w.spawn(loc, Material.APPLE);
						Item apple = w.dropItemNaturally(loc, new ItemStack(Material.DIAMOND_BLOCK,1));
						apple.setPickupDelay(99999);
					}
				}
				
				//보드 준비
				for (int i=0;i<10;i++) {
					for (int j =0;j<10;j++) {
						
						Location board = new Location(w,i,5,j);
						w.getBlockAt(board).setType(Material.COBBLESTONE);
						board = new Location(w,i,6,j);
						w.getBlockAt(board).setType(Material.COBBLESTONE);
						board = new Location(w,i,8,j);
						w.getBlockAt(board).setType(Material.COBBLESTONE);
					}
				}
				
				
				
				//tnt 위의 화살 제거
//				for (int[] tntloc : tnts) {
//					Location tntL = new Location(w,tntloc[0],3,tntloc[1]);
//					
//						Collection<Entity> nearby = w.getNearbyEntities(tntL, 0, 1, 0);
//						for (Entity tmp: nearby) {
//							p.sendMessage(String.valueOf(tntloc[0])+" "+String.valueOf(tntloc[1])+tmp.getName());
//							if (tmp.getType() == EntityType.CHICKEN) 
//							{
//								p.sendMessage(tmp.getName());
//								tmp.remove();
//							}
//						}
//					//p.sendMessage("testfor tntloc"+tntloc[0] + tntloc[1]);
//					//Entity tmp = (Entity) w.getNearbyEntities(tntL, 0, 1, 0);
//					//p.sendMessage(tmp.getName());
//					//tmp.remove();
//				}
					
//					List<Entity> arrows = w.getEntities();
//					for 
//					for (int[] tntloc : tnts) {
//						Location tntL = new Location(w,tntloc[0],5,tntloc[1]);
//						//p.sendMessage("testfor tntloc"+tntloc[0] + tntloc[1]);
//						w.getBlockAt(tntL).setType(Material.AIR);
//						if (w.getEn))
//					}
					
					
			}
			else if(args[0].equalsIgnoreCase("undo")) {
				List<Entity> ents = w.getEntities();
				for (Entity ent : ents) 
				{
					if (ent.getType() != EntityType.PLAYER) 
					{
						ent.remove();
					}
				}
				for (int[] tntloc : tnts) {
					Location tntL = new Location(w,tntloc[0],4,tntloc[1]);
					//p.sendMessage("testfor tntloc"+tntloc[0] + tntloc[1]);
					w.getBlockAt(tntL).setType(Material.AIR);
				}
				
				for (int i=0;i<10;i++) {
					for (int j =0;j<10;j++) {
						Location board = new Location(w,i,5,j);
						w.getBlockAt(board).setType(Material.AIR);
						board = new Location(w,i,6,j);
						w.getBlockAt(board).setType(Material.AIR);
						board = new Location(w,i,8,j);
						w.getBlockAt(board).setType(Material.AIR);
					}
				}
				
			}

			
			
			
		}
		return false;
	}

}
