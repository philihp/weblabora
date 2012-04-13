package com.philihp.weblabora.model;

import java.util.ArrayList;
import static com.philihp.weblabora.model.Clergyman.Type.*;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayTable;
import com.philihp.weblabora.jpa.User;

public class Player {
	
	private User user;
	
	private boolean active;
	
	private int bonusPoints = 0;
	
	private int peat = 0;
	private int penny = 0;
	private int clay = 0;
	private int wood = 0;
	private int grain = 0;
	private int sheep = 0;

	private int stone = 0;
	private int flour = 0;
	private int grapes = 0;
	private int nickel = 0;
	private int hops = 0;

	private int coal = 0;
	private int book = 0;
	private int pottery = 0;
	private int whiskey = 0;
	private int straw = 0;
	private int meat = 0;
	private int ornament = 0;
	private int bread = 0;
	private int wine = 0;
	private int beer = 0;
	private int reliquary = 0;

	private Landscape landscape;

	private Board board;

	private Color color;

	private List<Wonder> wonders = new ArrayList<Wonder>(0);
	
	private Clergyman layBrother1 = new Clergyman(this, LAYBROTHER);
	private Clergyman layBrother2 = new Clergyman(this, LAYBROTHER);
	private Clergyman prior = new Clergyman(this, PRIOR);
	
	private StartingMarker startingMarker = null;

	public Player(Board board, Color color) {
		this.board = board;
		this.color = color;
		//landscape must be initialized AFTER color, because it uses that color's 
		this.landscape = new Landscape(this);
	}

	public void gameStart() {
		peat = 1;
		penny = 1;
		clay = 1;
		wood = 1;
		grain = 1;
		sheep = 1;
		stone = 0;
		flour = 0;
		grapes = 0;
		nickel = 0;
		hops = 0;
		coal = 0;
		book = 0;
		pottery = 0;
		whiskey = 0;
		straw = 0;
		meat = 0;
		ornament = 0;
		bread = 0;
		wine = 0;
		beer = 0;
		reliquary = 0;
		bonusPoints = 0;
	}

	public Color getColor() {
		return color;
	}
	
	public int getBonusPoints() {
		return bonusPoints;
	}

	public int getPeat() {
		return peat;
	}

	public int getClay() {
		return clay;
	}

	public int getWood() {
		return wood;
	}

	public int getGrain() {
		return grain;
	}

	public int getSheep() {
		return sheep;
	}

	public int getStone() {
		return stone;
	}

	public int getFlour() {
		return flour;
	}

	public int getGrapes() {
		return grapes;
	}

	public int getHops() {
		return hops;
	}

	public int getCoal() {
		return coal;
	}

	public int getBook() {
		return book;
	}

	public int getPottery() {
		return pottery;
	}

	public int getWhiskey() {
		return whiskey;
	}

	public int getStraw() {
		return straw;
	}

	public int getMeat() {
		return meat;
	}

	public int getOrnament() {
		return ornament;
	}

	public int getBread() {
		return bread;
	}

	public int getWine() {
		return wine;
	}

	public int getBeer() {
		return beer;
	}

	public int getReliquary() {
		return reliquary;
	}

	public class InventoryEntry {
		private final String type;
		private final int quantity;

		public InventoryEntry(String type, int quantity) {
			this.type = type;
			this.quantity = quantity;
		}

		public String getType() {
			return type;
		}

		public Integer getQuantity() {
			return quantity;
		}
	}

	private void addEntry(ArrayList<InventoryEntry> inventory, String name, int quantity) {
		if (quantity > 0) {
			inventory.add(new InventoryEntry(name, quantity));
		}
	}

	public List<InventoryEntry> getInventory() {
		ArrayList<InventoryEntry> inventory = new ArrayList<InventoryEntry>();
		addEntry(inventory, "Peat", peat);
		addEntry(inventory, "Coin", penny);
		addEntry(inventory, "Clay", clay);
		addEntry(inventory, "Wood", wood);
		addEntry(inventory, "Grain", grain);
		addEntry(inventory, "Sheep", sheep);
		addEntry(inventory, "Stone", stone);
		addEntry(inventory, "Flour", flour);
		addEntry(inventory, "Grapes", grapes);
		addEntry(inventory, "5xCoins", nickel);
		addEntry(inventory, "Hops", hops);
		addEntry(inventory, "Coal", coal);
		addEntry(inventory, "Book", book);
		addEntry(inventory, "Pottery", pottery);
		addEntry(inventory, "Whiskey", whiskey);
		addEntry(inventory, "Straw", straw);
		addEntry(inventory, "Meat", meat);
		addEntry(inventory, "Ornament", ornament);
		addEntry(inventory, "Bread", bread);
		addEntry(inventory, "Wine", wine);
		addEntry(inventory, "Beer", beer);
		addEntry(inventory, "Reliquary", reliquary);
		addEntry(inventory, "Points", bonusPoints);
		return inventory;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	
	public void setPeat(int peat) {
		this.peat = peat;
	}

	public void setClay(int clay) {
		this.clay = clay;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public void setGrain(int grain) {
		this.grain = grain;
	}

	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public void setFlour(int flour) {
		this.flour = flour;
	}

	public void setGrapes(int grapes) {
		this.grapes = grapes;
	}

	public void setPenny(int penny) {
		this.penny = penny;
	}

	public void setNickel(int nickel) {
		this.nickel = nickel;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}

	public void setCoal(int coal) {
		this.coal = coal;
	}

	public void setBook(int book) {
		this.book = book;
	}

	public void setPottery(int pottery) {
		this.pottery = pottery;
	}

	public void setWhiskey(int whiskey) {
		this.whiskey = whiskey;
	}

	public void setStraw(int straw) {
		this.straw = straw;
	}

	public void setMeat(int meat) {
		this.meat = meat;
	}

	public void setOrnament(int ornament) {
		this.ornament = ornament;
	}

	public void setBread(int bread) {
		this.bread = bread;
	}

	public void setWine(int wine) {
		this.wine = wine;
	}

	public void setBeer(int beer) {
		this.beer = beer;
	}

	public void setReliquary(int reliquary) {
		this.reliquary = reliquary;
	}

	public int getPenny() {
		return penny;
	}

	public int getNickel() {
		return nickel;
	}

	public Board getBoard() {
		return board;
	}

	public void claimWonder(Wonder wonder) {
		wonders.add(wonder);
	}

	public Landscape getLandscape() {
		return landscape;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getActiveClass() {
		return isActive()?"active":"inactive";
	}
	public String getSelectedClass() {
		return isActive()?"selected":"unselected";
	}

	public boolean canAffordCost(BuildCost buildCost) {
		if (buildCost.getWood() > this.getWood())
			return false;
		if (buildCost.getClay() > this.getClay())
			return false;
		if (buildCost.getStone() > this.getStone())
			return false;
		if (buildCost.getStraw() > this.getStraw())
			return false;
		return true;
	}

	public void payBuildCost(BuildCost buildCost) {
		subtractWood(buildCost.getWood());
		subtractClay(buildCost.getClay());
		subtractStone(buildCost.getStone());
		subtractStraw(buildCost.getStraw());
		subtractCoins(buildCost.getCoin());
	}

	public void addBonusPoints(int bonusPoints) {
		this.bonusPoints += bonusPoints;
	}

	public void addPeat(int peat) {
		this.peat += peat;
	}

	public void addClay(int clay) {
		this.clay += clay;
	}

	public void addWood(int wood) {
		this.wood += wood;
	}

	public void addGrain(int grain) {
		this.grain += grain;
	}

	public void addSheep(int sheep) {
		this.sheep += sheep;
	}

	public void addStone(int stone) {
		this.stone += stone;
	}

	public void addFlour(int flour) {
		this.flour += flour;
	}

	public void addGrapes(int grapes) {
		this.grapes += grapes;
	}

	public void addPenny(int penny) {
		this.penny += penny;
	}

	public void addNickel(int nickel) {
		this.nickel += nickel;
	}

	public void addHops(int hops) {
		this.hops += hops;
	}

	public void addCoal(int coal) {
		this.coal += coal;
	}

	public void addBooks(int book) {
		this.book += book;
	}

	public void addPottery(int pottery) {
		this.pottery += pottery;
	}

	public void addWhiskey(int whiskey) {
		this.whiskey += whiskey;
	}

	public void addStraw(int straw) {
		this.straw += straw;
	}

	public void addMeat(int meat) {
		this.meat += meat;
	}

	public void addOrnament(int ornament) {
		this.ornament += ornament;
	}

	public void addBread(int bread) {
		this.bread += bread;
	}

	public void addWine(int wine) {
		this.wine += wine;
	}

	public void addBeer(int beer) {
		this.beer += beer;
	}

	public void addReliquary(int reliquary) {
		this.reliquary += reliquary;
	}
	
	public void subtractBonusPoints(int points) {
		this.bonusPoints -= points;
	}

	public void subtractPeat(int peat) {
		this.peat -= peat;
	}

	public void subtractClay(int clay) {
		this.clay -= clay;
	}

	public void subtractWood(int wood) {
		this.wood -= wood;
	}

	public void subtractGrain(int grain) {
		this.grain -= grain;
	}

	public void subtractSheep(int sheep) {
		this.sheep -= sheep;
	}

	public void subtractStone(int stone) {
		this.stone -= stone;
	}

	public void subtractFlour(int flour) {
		this.flour -= flour;
	}

	public void subtractGrapes(int grapes) {
		this.grapes -= grapes;
	}

	public void subtractCoins(int penniesToSubtract) {
		this.penny -= penniesToSubtract;
		//if subtracting those pennies pushes us into the negative, then convert the pennies into nickels
		while(this.penny < 0 && this.nickel > 0) {
			this.penny += 5;
			this.nickel -= 1;
		}
		
		//i think we can do the same with wine, but i haven't wrapped my head around if that's totally cool.
	}
	
	public void subtractPenny(int penny) {
		this.penny -= penny;
	}
	
	public int getCoins() {
		return getNickel()*5 + getPenny();
	}

	public void subtractNickel(int nickel) {
		this.nickel -= nickel;
	}

	public void subtractHops(int hops) {
		this.hops -= hops;
	}

	public void subtractCoal(int coal) {
		this.coal -= coal;
	}

	public void subtractBook(int book) {
		this.book -= book;
	}

	public void subtractPottery(int pottery) {
		this.pottery -= pottery;
	}

	public void subtractWhiskey(int whiskey) {
		this.whiskey -= whiskey;
	}

	public void subtractStraw(int straw) {
		this.straw -= straw;
		
		//if we are negative on straw, and there is enough grain to convert it into straw, then do it
		if(this.straw < 0 && this.grain + this.straw > 0) {
			this.grain += this.straw;
			this.straw = 0;
		}
	}

	public void subtractMeat(int meat) {
		this.meat -= meat;
	}

	public void subtractOrnament(int ornament) {
		this.ornament -= ornament;
	}

	public void subtractBread(int bread) {
		this.bread -= bread;
	}

	public void subtractWine(int wine) {
		this.wine -= wine;
	}

	public void subtractBeer(int beer) {
		this.beer -= beer;
	}

	public void subtractReliquary(int reliquary) {
		this.reliquary -= reliquary;
	}

	public void subtractEnergy(UsageParam param) {
		subtractCoal(param.getCoal());
		subtractPeat(param.getPeat());
		subtractWood(param.getWood());
		subtractStraw(param.getStraw());
	}
	public void subtractAll(UsageParam param) {
		subtractPeat(param.getPeat());
		subtractClay(param.getClay());
		subtractWood(param.getWood());
		subtractGrain(param.getGrain());
		subtractSheep(param.getSheep());
		subtractStone(param.getStone());
		subtractFlour(param.getFlour());
		subtractGrapes(param.getGrapes());
		subtractCoins(param.getPenny());
		subtractNickel(param.getNickel());
		subtractHops(param.getHops());
		subtractCoal(param.getCoal());
		subtractBook(param.getBook());
		subtractPottery(param.getPottery());
		subtractWhiskey(param.getWhiskey());
		subtractStraw(param.getStraw());
		subtractMeat(param.getMeat());
		subtractOrnament(param.getOrnament());
		subtractBread(param.getBread());
		subtractWine(param.getWine());
		subtractBeer(param.getBeer());
		subtractReliquary(param.getReliquary());
		subtractBonusPoints(param.getBonusPoints());
	}
	
	public void placeClergyman(Terrain location) throws WeblaboraException {
		Clergyman dude = null;
		if(layBrother1.getLocation() == null) dude = layBrother1;
		else if(layBrother2.getLocation() == null) dude = layBrother2;
		else if(prior.getLocation() == null) dude = prior;
		else
			throw new WeblaboraException("Attempted to place " + color
					+ " clergyman when none were free. They are on "
					+ layBrother1.getLocation().getErection() + ", "
					+ layBrother2.getLocation().getErection() + ", and "
					+ prior.getLocation().getErection() + ".");
		
		dude.setLocation(location);
	}
	
	public void placePrior(Terrain location) throws WeblaboraException {
		if (prior.getLocation() != null)
			throw new WeblaboraException("Attempted to place " + color
					+ " prior when it is already on "
					+ prior.getLocation().getErection() + ".");
		
		prior.setLocation(location);
	}
	
	public boolean isClergymenAllPlaced() {
		return layBrother1.getLocation() != null &&
				layBrother2.getLocation() != null &&
				prior.getLocation() != null;
	}
	
	public void resetClergymen() {
		//doing this directly circumvents the bi-directional protections in the setter
		layBrother1.location = null;
		layBrother2.location = null;
		prior.location = null;
	}

	public Clergyman getLayBrother1() {
		return layBrother1;
	}

	public Clergyman getLayBrother2() {
		return layBrother2;
	}

	public Clergyman getPrior() {
		return prior;
	}

	public void populatePlayer(com.philihp.weblabora.jpa.Game.Player player) {
		user = player.getUser();
	}
	public User getUser() {
		return user;
	}

	public void testValidity() throws WeblaboraException {
		if(getBonusPoints() < 0)
			throw new WeblaboraException(color + " has "+getBonusPoints()+" points");
		if(getPeat() < 0)
			throw new WeblaboraException(color + " has "+getPeat()+" peat");
		if(getPenny() < 0)
			throw new WeblaboraException(color + " has "+getPenny()+" pennies");
		if(getClay() < 0)
			throw new WeblaboraException(color + " has "+getClay()+" clay");
		if(getWood() < 0)
			throw new WeblaboraException(color + " has "+getWood()+" wood");
		if(getGrain() < 0)
			throw new WeblaboraException(color + " has "+getGrain()+" grain");
		if(getSheep() < 0)
			throw new WeblaboraException(color + " has "+getSheep()+" sheep");
		if(getStone() < 0)
			throw new WeblaboraException(color + " has "+getStone()+" stone");
		if(getFlour() < 0)
			throw new WeblaboraException(color + " has "+getFlour()+" flour");
		if(getGrapes() < 0)
			throw new WeblaboraException(color + " has "+getGrapes()+" grapes");
		if(getNickel() < 0)
			throw new WeblaboraException(color + " has "+getNickel()+" nickels");
		if(getHops() < 0)
			throw new WeblaboraException(color + " has "+getHops()+" hops");
		if(getCoal() < 0)
			throw new WeblaboraException(color + " has "+getCoal()+" coal");
		if(getBook() < 0)
			throw new WeblaboraException(color + " has "+getBook()+" books");
		if(getPottery() < 0)
			throw new WeblaboraException(color + " has "+getPottery()+" pottery");
		if(getWhiskey() < 0)
			throw new WeblaboraException(color + " has "+getWhiskey()+" whiskey");
		if(getStraw() < 0)
			throw new WeblaboraException(color + " has "+getStraw()+" straw");
		if(getMeat() < 0)
			throw new WeblaboraException(color + " has "+getMeat()+" meat");
		if(getOrnament() < 0)
			throw new WeblaboraException(color + " has "+getOrnament()+" ornaments");
		if(getWine() < 0)
			throw new WeblaboraException(color + " has "+getWine()+" wine");
		if(getBeer() < 0)
			throw new WeblaboraException(color + " has "+getBeer()+" beers");
		if(getReliquary() < 0)
			throw new WeblaboraException(color + " has "+getReliquary()+" reliquary");
		
		landscape.checkValidity();
	}

	public StartingMarker getStartingMarker() {
		return startingMarker;
	}

	public void setStartingMarker(StartingMarker startingMarker) {
		this.startingMarker = startingMarker;
	}
}