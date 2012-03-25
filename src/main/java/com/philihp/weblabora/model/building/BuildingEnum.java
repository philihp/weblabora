package com.philihp.weblabora.model.building;

import java.lang.reflect.Constructor;

public enum BuildingEnum {
	F05(Bakery.class),
	G02(CloisterCourtyard.class),
	F11(HarborPromenade.class),
	F08(Market.class),
	G07(PeatCoalKiln.class),
	G12(StoneMerchant.class),
	F04(Windmill.class),
	F09(CloisterGarden.class),
	G06(FuelMerchant.class),
	G01(Priory.class),
	G13(BuildersMarket.class),
	F10(Carpentry.class),
	F03(GrainStorage.class),
	F17(CloisterLibrary.class),
	G18(CloisterWorkshop.class),
	F14(GrapevineA.class), 
	G19(Slaughterhouse.class),
	G16(CloisterChapterHouse.class),
	F15(FinancedEstate.class),
	F24(CloisterChurch.class),
	G22(QuarryA.class),
	G26(Shipyard.class),
	F21(Winery.class),
	F20(Inn.class),
	F23(Bathhouse.class),
	F25(ChamberOfWonders.class),
	G28(Castle.class),
	F27(Palace.class),
	F33(ShippingCompany.class),
	F30(TownEstate.class),
	F32(Calefactory.class),
	F29(QuarryB.class),
	F31(GrapevineB.class),
	F37(Dormitory.class),
	F35(ForgersWorkshop.class),
	G41(HouseOfTheBrotherhood.class),
	F38(PrintingOffice.class),
	G34(Sacristy.class),
	F40(Hospice.class),
	F36(PilgrimageSite.class),
	G39(Estate.class),
	LR1(ClayMound.class),
	LG1(ClayMound.class),
	LB1(ClayMound.class),
	LW1(ClayMound.class),
	LR2(Farmyard.class),
	LG2(Farmyard.class),
	LB2(Farmyard.class),
	LW2(Farmyard.class),
	LR3(CloisterOffice.class),
	LG3(CloisterOffice.class),
	LB3(CloisterOffice.class),
	LW3(CloisterOffice.class);
	

	public final Class<? extends Building> clazz;

	BuildingEnum(Class<? extends Building> clazz) {
		this.clazz = clazz;
	}
	
	public Building getInstance() {
		return getInstance(this.toString());
	}

	public static Building getInstance(String id) {
		BuildingEnum buildingId = BuildingEnum.valueOf(id);
		Building building = null;
		try {
			Constructor<? extends Building> constructor = buildingId.clazz
					.getDeclaredConstructor();
			building = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return building;
	}
}
