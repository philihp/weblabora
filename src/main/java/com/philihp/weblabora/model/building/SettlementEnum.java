package com.philihp.weblabora.model.building;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SettlementEnum {
	SR1(ShantyTown.R.class),
	SG1(ShantyTown.G.class),
	SB1(ShantyTown.B.class),
	SW1(ShantyTown.W.class),
	SR2(FarmingVillage.R.class),
	SG2(FarmingVillage.G.class),
	SB2(FarmingVillage.B.class),
	SW2(FarmingVillage.W.class),
	SR3(MarketTown.R.class),
	SG3(MarketTown.G.class),
	SB3(MarketTown.B.class),
	SW3(MarketTown.W.class),
	SR4(FishingVillage.R.class),
	SG4(FishingVillage.G.class),
	SB4(FishingVillage.B.class),
	SW4(FishingVillage.W.class),
	SR5(ArtistsColony.R.class),
	SG5(ArtistsColony.G.class),
	SB5(ArtistsColony.B.class),
	SW5(ArtistsColony.W.class),
	SR6(Hamlet.R.class),
	SG6(Hamlet.G.class),
	SB6(Hamlet.B.class),
	SW6(Hamlet.W.class),
	SR7(Village.R.class),
	SG7(Village.G.class),
	SB7(Village.B.class),
	SW7(Village.W.class),
	SR8(HilltopVillage.R.class),
	SG8(HilltopVillage.G.class),
	SB8(HilltopVillage.B.class),
	SW8(HilltopVillage.W.class);

	public final Class<? extends Settlement> clazz;

	SettlementEnum(Class<? extends Settlement> clazz) {
		this.clazz = clazz;
	}

	public Settlement getInstance() {
		Constructor<? extends Settlement> constructor;
		try {
			constructor = this.clazz.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
