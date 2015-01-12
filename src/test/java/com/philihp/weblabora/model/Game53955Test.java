package com.philihp.weblabora.model;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.philihp.weblabora.model.Scorecard.PlayerScore;
import com.philihp.weblabora.model.building.*;

/**
 * Runs through game 53955 and checks ending board.
 */
public class Game53955Test {

	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(GamePlayers.THREE, GameLength.LONG,
				GameCountry.FRANCE);
		
		List<String> moves = Arrays.asList("C(0,1)", "U(LG3)",
				"u(LB3,Jo)|d(2,PLAINS)",
				"V(Gn)|B(G06,3,0)|U(G06,PtPtPt)*|P(0,COAST)",
				"b(F09,3,1)|U(F09)*|U(LG2,Sh)",
				"b(G01,3,1)|u(G01)*|u(F09)|u(LG2,Gn)", "U(LR1)|D(2,PLAINS)",
				"F(1,1)", "u(LB1,Jo)", "U(LR3)|D(3,HILLS)", "B(G12,1,1)",
				"c(0,1)", "U(LR2,Sh)", "u(LG2,Gn)", "f(1,1)", "F(0,2,Jo)",
				"U(LG1)", "u(LB3)|d(3,HILLS)", "C(0,0)",
				"V(Gn)|b(G02,3,0)|u(G02,GnSwCl,Pn)*|D(2,PLAINS)",
				"s(S02,2,2,PtWoShGn)", "S(S03,3,2,ShShShPn)",
				"S(S03,2,2,ShShShGn)", "v(Gn)|b(G16,3,2)|u(G16)*",
				"B(F14,3,3)|U(F14,Jo)*", "u(F09)|U(LG2,Sh)", "U(G01)|u(G16)",
				"W(BLUE,Pn)|U(G16)", "u(G12,ShShShPtWo)",
				"B(F04,4,3)|u(F04,GnGnGnGn)*", "F(2,0)", "u(F09)|U(LG2,Gn)",
				"U(LB1)", "U(LR3)|P(2,MOUNTAIN)",
				"V(Gn)|b(F08,1,2)|U(F08,GnSwClPn)*|P(1,MOUNTAIN)", "U(G16)",
				"C(0,3)", "C(0,1,Jo)",
				"B(F05,1,2)|u(F05,FlFlFlFlPt,BrBr)*|p(0,MOUNTAIN)",
				"U(LR2,Sh)", "v(Gn)|U(F08,GnSwClPn)|D(3,PLAINS)", "U(LB3)",
				"B(G07,2,0)|U(G07,PtPtPtPtPtPtPtPtPt)*", "s(S05,1,3,ShBrPt)",
				"S(S05,4,2,WoShBr)", "S(S04,-1,1,CoShShShSh)",
				"V(GnGn)|B(F21,2,3)|U(F21,GpGpGp,Wn)|P(2,COAST)",
				"B(G22,6,0)|u(G22,Jo)*|p(1,COAST)", "U(F14)", "f(0,3)",
				"B(F11,-1,2)", "U(LR2,Gn)", "u(LG1)", "U(G16)",
				"W(GREEN,PnPn)|U(F21,GpGpGpGpGpGpGp,Wn)",
				"P(0,COAST)|B(G26,-1,0)|U(G26,WoWo)*", "U(F11)",
				"V(GnGn)|B(F20,2,2)|U(F20,ShShShGnWn)*", "c(0,0)", "U(G22,Jo)",
				"W(GREEN,Wn)|U(F09)|U(LG2,Sh)", "w(BLUE,Wn)|U(F11)",
				"S(S03,1,1,ShShBr)", "S(S06,1,2,CoCoShShGn)",
				"S(S06,-1,3,PtPtPtBrPnPn)", "W(RED,Wn)|u(F14)", "F(1,1)",
				"b(F29,6,1)|U(F29)*", "B(F30,0,1)|u(F30,Po)*|p(2,MOUNTAIN)",
				"B(F33,-1,0)|U(F33,Co,JoMt)*", "u(F09)|U(LG2,Gn)",
				"B(F17,5,1)|u(F17,PnPnPn,Bo)", "U(LR3)", "u(LG1)",
				"U(G01)|u(F17,PnPnPn,Bo)", "U(LR2,Sh)", "W(BLUE,Wn)|u(F30,Po)",
				"B(F32,5,0)|u(F32,Pn)*|f(2,3)|c(0,3)", "U(F33,Co,Mt)",
				"u(F29)", "W(Green,Wn)|u(F21,GpGpGpGpGpGp,Wn)",
				"B(F27,4,2)|U(F27,Wn)*|U(F21,Gp,Wn)",
				"v(Gn)|u(F09)|U(G02,GnSwPn,Sh)",
				"W(Red,Wn)|U(G07,PtPtPtPtPtPtPtPtPtPtPt)", "W(BLUE,Wn)|U(F11)",
				"S(S04,-1,2,ShShShShPtPt)", "S(S04,-1,1,MtShPnCo)",
				"S(S07,0,2,MtMtMtCoCoCo)",
				"v(Gn)|b(F40,4,2)|U(F40)*|U(G28)|S(S02,3,3,ShPnPtPt)",
				"W(Red,Wn)|u(F33,Co,Mt)", "B(F36,1,1)|U(F36,Po,Or)*",
				"u(F09)|U(G02,ClSnPt,Sh)|D(4,HILLS)",
				"U(G01)|u(F40)|u(G28)|s(S06,3,3,CoCoMt)", "U(LR2,Sh)",
				"V(Ni)|u(G02,SnClGp,Sh)", "F(0,2)", "W(BLUE,Wn)|U(G22)",
				"B(F38,0,3)|u(F38,1,0,2,0,0,2,1,4,2,4)*",
				"W(Red,Wn)|u(F33,Co,Mt)",
				"B(G28,5,3)|U(G28)*|S(S08,4,3,CoMtMtShShShShShShShShShSh)",
				"C(0,4)", "U(G01)|u(G28)|s(S08,5,2,CoMtMtMtMtMtMt)",
				"U(LR3)|P(2,COAST)", "U(F09)|u(G02,GnPnGp,Sh)",
				"W(Red,Wn)|u(G28)|s(S01,0,2,WoPn)",
				"U(F27,Wn)|U(G28)|S(S05,-1,2,WoMt)", "B(F24,3,2)",
				"W(Red,Wn)|u(F33,Co,Mt)", "U(G28)|S(S02,0,1,CoShGn)",
				"U(G28)|s(S07,0,2,ShShShShShShShPnPtPtPtPtPt)",
				"B(G18,3,0)|u(G18,CoWoClClClSn)", "S(S01,5,2,WoGn)",
				"S(S08,4,3,ShShShShShShShShShShShShGnPnPnPnGpGpPtPt)",
				"S(S07,5,3,CoCoCoMtMtMt)");

		MoveProcessor.processMoves(board, moves);
	}

	@Test
	public void testEndingScores() throws WeblaboraException {
		assertThat(board.isGameOver(), is(true));
		assertThat(board.isSettling(), is(false));
		Map<Color, PlayerScore> scores = board.getScorecard().getScores();

		assertThat(scores, hasKey(Color.RED));
		assertThat(scores.get(Color.RED), hasProperty("itemScore", is(9)));
		assertThat(scores.get(Color.RED), hasProperty("shieldScore", is(93)));
		assertThat(scores.get(Color.RED),
				hasProperty("settlementTotalScore", is(159)));
		Map<Class<? extends Settlement>, Integer> redScores = ImmutableMap
				.<Class<? extends Settlement>, Integer> builder()
				.put(FishingVillage.class, 19).put(ArtistsColony.class, 20)
				.put(Village.class, 16).put(Hamlet.class, 22)
				.put(MarketTown.class, 22).put(ShantyTown.class, 12)
				.put(HilltopVillage.class, 29).build();

		for (Entry<Class<? extends Settlement>, Integer> entry : redScores
				.entrySet()) {
			assertThat(
					scores.get(Color.RED).getSettlementScores(),
					hasItem(allOf(
							hasProperty("settlement",
									instanceOf(entry.getKey())),
							hasProperty("score", is(entry.getValue())))));
		}

		assertThat(scores, hasKey(Color.GREEN));
		assertThat(scores.get(Color.GREEN), hasProperty("itemScore", is(15)));
		assertThat(scores.get(Color.GREEN), hasProperty("shieldScore", is(97)));
		assertThat(scores.get(Color.GREEN),
				hasProperty("settlementTotalScore", is(148)));
		Map<Class<? extends Settlement>, Integer> greenScores = ImmutableMap
				.<Class<? extends Settlement>, Integer> builder()
				.put(FishingVillage.class, 19).put(Village.class, 25)
				.put(MarketTown.class, 26).put(Hamlet.class, 18)
				.put(ArtistsColony.class, 23).put(FarmingVillage.class, 23)
				.put(HilltopVillage.class, 14).build();
		for (Entry<Class<? extends Settlement>, Integer> entry : greenScores
				.entrySet()) {
			assertThat(
					scores.get(Color.GREEN).getSettlementScores(),
					hasItem(allOf(
							hasProperty("settlement",
									instanceOf(entry.getKey())),
							hasProperty("score", is(entry.getValue())))));
		}

		assertThat(scores, hasKey(Color.BLUE));
		assertThat(scores.get(Color.BLUE), hasProperty("itemScore", is(24)));
		assertThat(scores.get(Color.BLUE), hasProperty("shieldScore", is(77)));
		assertThat(scores.get(Color.BLUE),
				hasProperty("settlementTotalScore", is(149)));
		Map<Class<? extends Settlement>, Integer> blueScores = ImmutableMap
				.<Class<? extends Settlement>, Integer> builder()
				.put(FishingVillage.class, 21).put(MarketTown.class, 14)
				.put(ShantyTown.class, 14).put(FarmingVillage.class, 13)
				.put(ArtistsColony.class, 26).put(HilltopVillage.class, 26)
				.put(Hamlet.class, 15).put(Village.class, 20).build();
		for (Entry<Class<? extends Settlement>, Integer> entry : blueScores
				.entrySet()) {
			assertThat(
					scores.get(Color.BLUE).getSettlementScores(),
					hasItem(allOf(
							hasProperty("settlement",
									instanceOf(entry.getKey())),
							hasProperty("score", is(entry.getValue())))));
		}
	}
}
