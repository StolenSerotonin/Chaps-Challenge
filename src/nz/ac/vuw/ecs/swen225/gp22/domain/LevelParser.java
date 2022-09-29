// package nz.ac.vuw.ecs.swen225.gp22.domain;

// import java.io.FileReader;
// import java.io.BufferedReader;
// import java.io.IOException;

// import Domain.Door.DoorC;
// import Domain.Key.KeyC;
// import Domain.Level;

// public class LevelParser {
	
// 	public static Level readMapFile(String fileName, int x, int y, int chipsRequired){
// 		Level returnMap = new Level(x, y, chipsRequired);
// 		char[][] characters = new char[x][y];
// 		String line;
// 		int count = 0;
// 		try(
// 				BufferedReader in = new BufferedReader(new FileReader(fileName));
// 		) {
// 			while((line = in.readLine()) != null){
// 				for(int i = 0; i < line.length(); i++){
// 					characters[i][count] = line.charAt(i);
// 				}
// 				count++;
// 			}
// 		} catch(IOException e){
// 			e.printStackTrace();
// 		}
// 		for(int i = 0; i < characters.length; i++){
// 			for(int j = 0; j < characters.length; j++){
// 				if(characters[i][j] == ' '){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 				}
// 				else if(characters[i][j] == 'X'){
// 					returnMap.setTile(i, j, new WallTile(i, j));
// 				}
// 				else if(characters[i][j] == '@'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setStartingPosition(i, j);
// 				}
// 				else if(characters[i][j] == 'R'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Door(i, j, DoorC.RED));
// 				}
// 				else if(characters[i][j] == 'r'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Key(i, j, KeyC.RED));
// 				}
// 				else if(characters[i][j] == 'U'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Door(i, j, DoorC.BLUE));
// 				}
// 				else if(characters[i][j] == 'u'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Key(i, j, KeyC.BLUE));
// 				}
// 				else if(characters[i][j] == 'Y'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Door(i, j, DoorC.YELLOW));
// 				}
// 				else if(characters[i][j] == 'y'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Key(i, j, KeyC.YELLOW));
// 				}
// 				else if(characters[i][j] == 'G'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Door(i, j, DoorC.GREEN));
// 				}
// 				else if(characters[i][j] == 'g'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new Key(i, j, KeyC.GREEN));
// 				}
// 				else if(characters[i][j] == 'c'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new ComputerChip(i, j));
// 				}
// 				else if(characters[i][j] == 'C'){
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					returnMap.setObject(i, j, new ExitLock(i, j));
// 				}
// 				else{
// 					returnMap.setTile(i, j, new FloorTile(i, j));
// 					//returnMap.setObject(i, j, null);
// 				}
// 			}
// 		}
// 		return returnMap;
// }
	
// }
