package com.philihp.weblabora.model.building;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SettlementEnum {
	S01(ShantyTown.class),
	S02(FarmingVillage.class),
	S03(MarketTown.class),
	S04(FishingVillage.class),
	S05(ArtistsColony.class),
	S06(Hamlet.class),
	S07(Village.class),
	S08(HilltopVillage.class);

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
