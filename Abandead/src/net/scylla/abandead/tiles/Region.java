package net.scylla.abandead.tiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

import net.scylla.abandead.core.Game;
import net.scylla.abandead.entities.Location;
import net.scylla.abandead.entities.Player;

public class Region implements Serializable {

	ArrayList<ArrayList<Tile>> tileList;
	ArrayList<Tile> cols;
	private Location location;
	private RegionType regType;
	private Random rand;
	public static final int sizeCorrection = Game.REGION_SIZE * Game.TILE_SIZE;

	public Region(int xx, int yy, float sX, float sY, RegionType type) {
		this.regType = type;
		location = new Location(xx, yy);

		tileList = new ArrayList<ArrayList<Tile>>();

		for (int x = 0; x < Game.REGION_SIZE; x++) {
			cols = new ArrayList<Tile>();
			for (int y = 0; y < Game.REGION_SIZE; y++) {
				Tile tile = new Tile();
				float newX = location.getX() * sizeCorrection + x
						* Game.TILE_SIZE + sX + Display.getWidth() / 2;
				float newY = location.getY() * sizeCorrection + y
						* Game.TILE_SIZE + sY + Display.getHeight() / 2;
				tile.setLocation(new Location(newX, newY));
				tile.setType(regType.baseType);

				cols.add(tile);
			}
			tileList.add(cols);
		}
	}

	public void setType(RegionType type) {
		this.regType = type;
	}

	public void populateTileTypes(float xStart, float yStart) {
		Random rand = new Random();

		for (int x = 0; x < Game.REGION_SIZE; x++) {
			for (int y = 0; y < Game.REGION_SIZE; y++) {
				Tile tile = tileList.get(x).get(y);
				// Tile c1 = new Tile();
				// Tile c2 = new Tile();
				int TN = 0;

				// c1.setType(TileType.WOOD);
				// c2.setType(TileType.SAND);

				if (regType == RegionType.DESERT) {
					if ((!tileLeft(tile, TileType.WOOD, 1) && !tileRight(tile,
							TileType.WOOD, 1))
							|| (!tileUp(tile, TileType.WOOD, 1) && !tileDown(
									tile, TileType.WOOD, 1))) {
						tile.setType(TileType.WOOD);
					}
				} else if (regType == RegionType.CITY) {
					if(!tileLeft(tile, TileType.SAND, 3) && tileRight(tile, TileType.SAND, 1)) {
						tile.setType(TileType.SAND);
					}
				} else if (regType == RegionType.HOUSE) {
					
				} else if (regType == RegionType.QUARRY) {
					
				}

				tile.getLocation().setX(
						location.getX() * sizeCorrection + x * Game.TILE_SIZE
								+ xStart + Display.getWidth() / 2);
				tile.getLocation().setY(
						location.getY() * sizeCorrection + y * Game.TILE_SIZE
								+ yStart + Display.getHeight() / 2);

			}
		}
	}

	public Location getLocation() {
		return location;
	}

	public boolean haveSurroundingTile(Tile t, TileType type, int a) {
		if (!tileRight(t, type, a)) {
			if (!tileRightDown(t, type, a)) {
				if (!tileRightUp(t, type, a)) {
					if (!tileLeft(t, type, a)) {
						if (!tileLeftDown(t, type, a)) {
							if (!tileLeftUp(t, type, a)) {
								if (!tileUp(t, type, a)) {
									if (!tileDown(t, type, a)) {
										return false;
									}
								}
							}

						}
					}
				}
			}
		}
		return true;
	}

	public boolean tileUp(Tile t, TileType type, int a) {
		if (tileAtLocation(t.getLocation().getX() + 1,
				((t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1))
				.getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileDown(Tile t, TileType type, int a) {
		if (tileAtLocation(t.getLocation().getX() + 1,
				((t.getLocation().getY() - (Game.TILE_SIZE * a)) + 1))
				.getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileLeftDown(Tile t, TileType type, int a) {
		if (tileAtLocation(
				((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1),
				((t.getLocation().getY() - (Game.TILE_SIZE * a)) + 1))
				.getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileLeftUp(Tile t, TileType type, int a) {
		if (tileAtLocation(
				((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1),
				((t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1))
				.getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileLeft(Tile t, TileType type, int a) {
		if (tileAtLocation(
				((t.getLocation().getX() - (Game.TILE_SIZE * a)) + 1),
				(t.getLocation().getY() + 1)).getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileRightDown(Tile t, TileType type, int a) {
		if (tileAtLocation(
				((t.getLocation().getX() + (Game.TILE_SIZE * a)) + 1),
				((t.getLocation().getY() - (Game.TILE_SIZE * a)) - 1))
				.getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileRightUp(Tile t, TileType type, int a) {
		if (tileAtLocation(
				((t.getLocation().getX() + (Game.TILE_SIZE * a)) + 1),
				(t.getLocation().getY() + (Game.TILE_SIZE * a)) + 1).getType() != type) {
			return false;
		}
		return true;
	}

	public boolean tileRight(Tile t, TileType type, int a) {
		if (tileAtLocation((t.getLocation().getX() + (Game.TILE_SIZE * a) + 1),
				t.getLocation().getY() + 1).getType() != type) {
			return false;
		}
		return true;
	}

	public Tile tileAtLocation(float x, float y) {
		for (int a = 0; a < tileList.size(); a++) {
			for (int b = 0; b < tileList.get(a).size(); b++) {
				if (x >= tileList.get(a).get(b).getLocation().getX()
						&& x <= (tileList.get(a).get(b).getLocation().getX() + Game.TILE_SIZE)
						&& y >= tileList.get(a).get(b).getLocation().getY()
						&& y <= (tileList.get(a).get(b).getLocation().getY() + Game.TILE_SIZE)) {
					return tileList.get(a).get(b);
				}
			}
		}

		return tileList.get(0).get(0);
	}

	public void render(float xStart, float yStart) {
		int x = 0;
		for (ArrayList<Tile> list : tileList) {
			float y = 0;
			for (Tile tile : list) {
				float newX = location.getX() * sizeCorrection + x
						* Game.TILE_SIZE + xStart + Display.getWidth() / 2;
				float newY = location.getY() * sizeCorrection + y
						* Game.TILE_SIZE + yStart + Display.getHeight() / 2;
				tile.setLocation(new Location(newX, newY));
				tile.getLocation().setX(newX);
				tile.getLocation().setY(newY);
				tile.render();
				y++;
			}
			x++;
		}
	}
}
