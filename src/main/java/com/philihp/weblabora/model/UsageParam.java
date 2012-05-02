package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.philihp.weblabora.model.building.Building;
import com.philihp.weblabora.model.building.Erection;

public class UsageParam {
	
	private List<Coordinate> coordinates = new ArrayList<Coordinate>();
	
	private String param;
	
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

	private Erection card = null;
	
	private boolean withJoker = false;

	public int differentSingularGoods() {
		int different = 0;
		if(peat == 1) different++;
		if(penny == 1) different++;
		if(clay == 1) different++;
		if(wood == 1) different++;
		if(grain == 1) different++;
		if(sheep == 1) different++;
		if(stone == 1) different++;
		if(flour == 1) different++;
		if(grapes == 1) different++;
		if(nickel == 1) different++;
		if(hops == 1) different++;
		if(coal == 1) different++;
		if(book == 1) different++;
		if(pottery == 1) different++;
		if(whiskey == 1) different++;
		if(straw == 1) different++;
		if(meat == 1) different++;
		if(ornament == 1) different++;
		if(bread == 1) different++;
		if(wine == 1) different++;
		if(beer == 1) different++;
		if(reliquary == 1) different++;
		return different;
	}
	
	public boolean hasSomethingWith(int quantity) {
		if(peat == quantity) return true;
		else if(penny == quantity) return true;
		else if(clay == quantity) return true;
		else if(wood == quantity) return true;
		else if(grain == quantity) return true;
		else if(sheep == quantity) return true;
		else if(stone == quantity) return true;
		else if(flour == quantity) return true;
		else if(grapes == quantity) return true;
		else if(nickel == quantity) return true;
		else if(hops == quantity) return true;
		else if(coal == quantity) return true;
		else if(book == quantity) return true;
		else if(pottery == quantity) return true;
		else if(whiskey == quantity) return true;
		else if(straw == quantity) return true;
		else if(meat == quantity) return true;
		else if(ornament == quantity) return true;
		else if(bread == quantity) return true;
		else if(wine == quantity) return true;
		else if(beer == quantity) return true;
		else if(reliquary == quantity) return true;
		else return false;
	}
	
	public UsageParam(String in) throws WeblaboraException {
		this.param = in;
		for(int i = 0; i < in.length()/2; i++) {
			String token = in.substring(i*2,i*2+2);
			if("Wo".equals(token))
				wood++;
			else if("Gn".equals(token))
				grain++;
			else if("Sh".equals(token))
				sheep++;
			else if("Cl".equals(token))
				clay++;
			else if("Pt".equals(token))
				peat++;
			else if("Pn".equals(token))
				penny++;
			else if("Sn".equals(token))
				stone++;
			else if("Fl".equals(token))
				flour++;
			else if("Gp".equals(token))
				grapes++;
			else if("Ni".equals(token))
				nickel++;
			else if("Ho".equals(token))
				hops++;
			else if("Co".equals(token))
				coal++;
			else if("Bo".equals(token))
				book++;
			else if("Po".equals(token))
				pottery++;
			else if("Wh".equals(token))
				whiskey++;
			else if("Sw".equals(token))
				straw++;
			else if("Mt".equals(token))
				meat++;
			else if("Or".equals(token))
				ornament++;
			else if("Br".equals(token))
				bread++;
			else if("Wn".equals(token))
				wine++;
			else if("Rq".equals(token))
				reliquary++;
			else if("Bp".equals(token))
				bonusPoints++;
			else if("Be".equals(token))
				beer++;
			else if("Jo".equals(token))
			  withJoker = true;
			else throw new WeblaboraException("Unknown token ["+token+"]");
		}
	}

	public static UsageParam is() {
		try {
			return new UsageParam("");
		}
		catch(WeblaboraException e) {
			throw new RuntimeException("Error when instantiating an empty UsageParam", e);
		}
	}
	
	public UsageParam bonusPoint(int quantity) {
		bonusPoints += quantity;
		return this;
	}

	public UsageParam peat(int quantity) {
		peat += quantity;
		return this;
	}

	public UsageParam penny(int quantity) {
		penny += quantity;
		return this;
	}

	public UsageParam clay(int quantity) {
		clay += quantity;
		return this;
	}

	public UsageParam wood(int quantity) {
		wood += quantity;
		return this;
	}

	public UsageParam grain(int quantity) {
		grain += quantity;
		return this;
	}

	public UsageParam sheep(int quantity) {
		sheep += quantity;
		return this;
	}

	public UsageParam stone(int quantity) {
		stone += quantity;
		return this;
	}

	public UsageParam flour(int quantity) {
		flour += quantity;
		return this;
	}

	public UsageParam grapes(int quantity) {
		grapes += quantity;
		return this;
	}

	public UsageParam nickel(int quantity) {
		nickel += quantity;
		return this;
	}

	public UsageParam hops(int quantity) {
		hops += quantity;
		return this;
	}

	public UsageParam coal(int quantity) {
		coal += quantity;
		return this;
	}

	public UsageParam book(int quantity) {
		book += quantity;
		return this;
	}

	public UsageParam pottery(int quantity) {
		pottery += quantity;
		return this;
	}

	public UsageParam whiskey(int quantity) {
		whiskey += quantity;
		return this;
	}

	public UsageParam straw(int quantity) {
		straw += quantity;
		return this;
	}

	public UsageParam meat(int quantity) {
		meat += quantity;
		return this;
	}

	public UsageParam ornament(int quantity) {
		ornament += quantity;
		return this;
	}

	public UsageParam bread(int quantity) {
		bread += quantity;
		return this;
	}

	public UsageParam wine(int quantity) {
		wine += quantity;
		return this;
	}

	public UsageParam beer(int quantity) {
		beer += quantity;
		return this;
	}

	public UsageParam reliquary(int quantity) {
		reliquary += quantity;
		return this;
	}

	public UsageParam card(Erection card) {
		this.card = card;
		return this;
	}

	public int getPeat() {
		return peat;
	}

	public int getPenny() {
		return penny;
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

	public int getNickel() {
		return nickel;
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

	public Erection getCard() {
		return card;
	}
	
	public int getBonusPoints() {
		return bonusPoints;
	}
	
	public void setWithJoker(boolean withJoker) {
		this.withJoker = withJoker;
	}
	
	public boolean isWithJoker() {
		return withJoker;
	}
	
	public boolean isWithGift() {
		return whiskey != 0 || wine != 0;
	}
	
	public double getEnergy() {
		return getCoal()*3 + getPeat()*2 + getWood() + getStraw()*0.5d; 
	}
	
	public double getFood() {
		return getPenny() + getGrain() + getSheep() * 2 + getFlour()
				+ getGrapes() + getNickel() * 5 + getHops() + getWhiskey() * 2
				+ getMeat() * 5 + getBread() * 3 + getWine() + getBeer() * 5;
	}
	
	public int getCoins() {
		return getPenny() + getNickel()*5 + getWine() + getWhiskey()*2;
	}
	
	public String getParam() {
		return this.param;
	}
	
	public Coordinate getCoordinate() {
		return coordinates.get(0);
	}
	
	public List<Coordinate> getCoordinates() {
		return Collections.unmodifiableList(coordinates);
	}
	
	public void pushCoordinate(Coordinate coordinate) {
		coordinates.add(coordinate);
	}
	
	public void pushCoordinate(int x, int y) {
		pushCoordinate(new Coordinate(x, y));
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("("+this.param+")"); 
		for(Coordinate coordinate : getCoordinates()) {
			return "("+coordinate.getX()+","+coordinate.getY()+")";
		}
		return builder.toString();
	}

	public void subtractWine(int wine) {
		this.wine -= wine;
	}
	
}
