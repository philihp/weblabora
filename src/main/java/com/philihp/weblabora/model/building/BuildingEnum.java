package com.philihp.weblabora.model.building;

import java.lang.reflect.Constructor;

public enum BuildingEnum {
	G07(PeatCoalKiln.class),
	G13(BuildersMarket.class),
	G16(CloisterChapterHouse.class), 
	G19(Slaughterhouse.class),
	F24(CloisterChurch.class),
	G22(Quarry.class),
	G26(Shipyard.class),
	F27(Palace.class),
	G34(Sacristy.class);

	public final Class<? extends AbstractBuilding> clazz;

	BuildingEnum(Class<? extends AbstractBuilding> clazz) {
		this.clazz = clazz;
	}
	
	public AbstractBuilding getInstance() {
		return getInstance(this.toString());
	}

	public static AbstractBuilding getInstance(String id) {
		BuildingEnum buildingId = BuildingEnum.valueOf(id);
		AbstractBuilding building = null;
		try {
			Constructor<? extends AbstractBuilding> constructor = buildingId.clazz
					.getDeclaredConstructor();
			building = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return building;
	}
}
