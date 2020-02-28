package chido.mine;

//import java.util.stream.IntStream;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Tnt_02 implements CommandExecutor {
	public int[] tnts = new int[10];
	public static int[][] maps = {{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0} ,{0,0,0,0,0,0,0,0,0,0} ,{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0} , {0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}};
	//public static int[][] maps = new int[10][10];
	public static int[][] check = new int[10][10];

	
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String s, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			//p.sendMessage("command 인식 완료");
			World w = p.getWorld();
			
//			for(int[] row: maps) {
//		        Arrays.fill(row, 0);
//		    }
//			for(int[] row: check) {
//		        Arrays.fill(row, -1);
//		    }
			
			
			if (args[0].equalsIgnoreCase("start")) {
				
				//tnt 위치 생성
				for (int i=0;i<10;i++) 
				{
					//Random rand = new Random();
					double rand = Math.random();
					
					//p.sendMessage("random"+Double.toString(rand));
					int rand_tnt = (int)( rand*100);
					//p.sendMessage(Integer.toString(rand_tnt));
					while (IntStream.of(tnts).anyMatch(x->x==rand_tnt)) {
						rand = Math.random();
						
						//p.sendMessage("random"+Double.toString(rand));
						//rand_tnt = (int)( rand*100);
					}
//					
//					p.sendMessage(Integer.toString(location));
//				}
					tnts[i] = rand_tnt;
					p.sendMessage("ck1: "+Integer.toString(tnts[i]));
					int x = rand_tnt /10;
					int z = rand_tnt %10;
					p.sendMessage("ck2: " + Integer.toString(x)+ " " + Integer.toString(z));
					
					int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
					for (int j=0;j<4;j++) {
						int new_x = x+dir[j][0];
						int new_z = z+dir[j][1];
						if (new_x <0 || new_x > 9 || new_z <0 || new_z > 9) continue;
						
						maps[new_x][new_z] +=1;
						p.sendMessage("ck3: "+Integer.toString(new_x)+ " " + Integer.toString(new_z)+" " + Integer.toString(maps[new_x][new_z]));
					}
				}
				
				for (int tnt : tnts) //tnt 위치는 그냥 -1로 따로 리셋
				{
					int x = tnt /10;
					int z = tnt %10;
					maps[x][z] = -1;
				}
				
				//map에 따른 블럭 생성
				for (int i=0;i<10;i++) {
					for (int j=0;j<10;j++) {
						Location loc = new Location(w,i,4,j);
						if (maps[i][j] == -1) w.getBlockAt(loc).setType(Material.REDSTONE_BLOCK);
						else if (maps[i][j] == 0)  w.getBlockAt(loc).setType(Material.COBBLESTONE);
						else if (maps[i][j] == 1)  w.getBlockAt(loc).setType(Material.WHITE_CONCRETE);
						else if (maps[i][j] == 2)  w.getBlockAt(loc).setType(Material.YELLOW_CONCRETE);
						else if (maps[i][j] == 3)  w.getBlockAt(loc).setType(Material.ORANGE_CONCRETE);
						else if (maps[i][j] == 4)  w.getBlockAt(loc).setType(Material.LIME_CONCRETE);
						else if (maps[i][j] == 5)  w.getBlockAt(loc).setType(Material.GREEN_CONCRETE);
						//loc = new Location(w,i,5,j);
						//w.getBlockAt(loc).setType(Material.SEA_LANTERN);
						
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
				for (int i=0;i<10;i++) {
					for (int j=0;j<10;j++) {
						Location loc = new Location(w,i,4,j);
						w.getBlockAt(loc).setType(Material.AIR);
						loc = new Location(w,i,5,j);
						w.getBlockAt(loc).setType(Material.AIR);
					}
				}
				
			}
		}
		
		return false;
	}

}
