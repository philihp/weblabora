package com.philihp.weblabora.model.building;

import java.lang.reflect.Constructor;

public enum BuildingEnum {
	F05(Bakery.class),
	I05(Brewery.class),
	G02(CloisterCourtyard.class),
	F11(HarborPromenade.class),
	I11(Houseboat.class),
	F08(Market.class),
	I08(FalseLighthouse.class),
	G07(PeatCoalKiln.class),
	G12(StoneMerchant.class),
	F04(Windmill.class),
	I04(Malhouse.class),
	F09(CloisterGarden.class),
	I09(SpinningMill.class),
	G06(FuelMerchant.class),
	G01(Priory.class),
	G13(BuildersMarket.class),
	F10(Carpentry.class),
	I10(Cottage.class),
	F03(GrainStorage.class),
	I03(Granary.class),
	F17(CloisterLibrary.class),
	I17(Scriptorium.class),
	G18(CloisterWorkshop.class),
	F14(GrapevineA.class),
	I14(SacredSite.class),
	G19(Slaughterhouse.class),
	G16(CloisterChapterHouse.class),
	F15(FinancedEstate.class),
	I15(DruidsHouse.class),
	F24(CloisterChurch.class),
	I24(Chapel.class),
	G22(QuarryA.class),
	G26(Shipyard.class),
	F21(Winery.class),
	I21(WhiskeyDistillery.class),
	F20(Inn.class),
	I20(Alehouse.class),
	F23(Bathhouse.class),
	I23(Locutory.class),
	F25(ChamberOfWonders.class),
	I25(Portico.class),
	G28(Castle.class),
	F27(Palace.class),
	I27(GrandManor.class),
	F33(ShippingCompany.class),
	I33(Cooperage.class),
	F30(TownEstate.class),
	I30(Refectory.class),
	F32(Calefactory.class),
	I32(FilialChurch.class),
	F29(QuarryB.class),
	I29(ForestHut.class),
	F31(GrapevineB.class),
	I31(CoalHarbor.class),
	F37(Dormitory.class),
	I37(Bulwark.class),
	F35(ForgersWorkshop.class),
	I35(RoundTower.class),
	G41(HouseOfTheBrotherhood.class),
	F38(PrintingOffice.class),
	I38(FestivalGround.class),
	G34(Sacristy.class),
	F40(Hospice.class),
	I40(Guesthouse.class),
	F36(PilgrimageSite.class),
	I36(Camera.class),
	G39(Estate.class),
	LR1(ClayMoundR.class),
	LG1(ClayMoundG.class),
	LB1(ClayMoundB.class),
	LW1(ClayMoundW.class),
	LR2(FarmyardR.class),
	LG2(FarmyardG.class),
	LB2(FarmyardB.class),
	LW2(FarmyardW.class),
	LR3(CloisterOfficeR.class),
	LG3(CloisterOfficeG.class),
	LB3(CloisterOfficeB.class),
	LW3(CloisterOfficeW.class);
	

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
