package com.philihp.weblabora.model;

import com.google.common.collect.ImmutableMap;
import com.philihp.weblabora.model.Scorecard.PlayerScore;
import com.philihp.weblabora.model.building.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Map.Entry;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Runs through game 53955 and checks ending board.
 */
public class Game53772Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board(GamePlayers.THREE, GameLength.LONG, GameCountry.FRANCE);
    MoveProcessor.processMove(board, "f(1,1) ");
    MoveProcessor.processMove(board, "U(LG3) ");
    MoveProcessor.processMove(board, "u(LB3,Jo) ");
    MoveProcessor.processMove(board, "b(G02,3,1)|U(G02,ClPnGn,Pn)*|D(2,PLAINS) ");
    MoveProcessor.processMove(board, "B(F09,3,1)|U(F09)|U(LG2,Sh) ");
    MoveProcessor.processMove(board, "D(2,PLAINS)|b(G01,3,1)|U(G01)*|U(F09)|U(LG2,Gn) ");
    MoveProcessor.processMove(board, "P(1,COAST)|U(LR1) ");
    MoveProcessor.processMove(board, "C(0,1) ");
    MoveProcessor.processMove(board, "u(LB1,Jo) ");
    MoveProcessor.processMove(board, "u(LR3) ");
    MoveProcessor.processMove(board, "B(G12,3,0) ");
    MoveProcessor.processMove(board, "f(0,2) ");
    MoveProcessor.processMove(board, "W(GREEN,Pn)|U(G12,ShPnPnPt) ");
    MoveProcessor.processMove(board, "W(BLUE,Pn)|U(G01)|U(F09)|U(LG2,Sh) ");
    MoveProcessor.processMove(board, "u(LB1) ");
    MoveProcessor.processMove(board, "u(LR2,Gn) ");
    MoveProcessor.processMove(board, "U(F09)|U(G12,PtPtShShShSh) ");
    MoveProcessor.processMove(board, "b(F04,4,2)|U(F04,GnGnGnGn)* ");
    MoveProcessor.processMove(board, "v(Gn)|u(G02,GnSwPn,Sh) ");
    MoveProcessor.processMove(board, "B(F08,0,1)|U(F08,ClPtGnSh)* ");
    MoveProcessor.processMove(board, "s(S02,1,2,ShPnPtSwSw) ");
    MoveProcessor.processMove(board, "s(S03,1,2,ShShShSh) ");
    MoveProcessor.processMove(board, "D(2,PLAINS)|S(S03,2,2,ShShBr) ");
    MoveProcessor.processMove(board, "b(G16,3,0)|u(G16)* ");
    MoveProcessor.processMove(board, "b(F11,-1,1)|U(F11)* ");
    MoveProcessor.processMove(board, "C(0,0) ");
    MoveProcessor.processMove(board, "u(G01)|U(F11) ");
    MoveProcessor.processMove(board, "u(LR3,Jo)|d(3,HILLS) ");
    MoveProcessor.processMove(board, "U(LG3)|P(0,COAST) ");
    MoveProcessor.processMove(board, "f(1,1) ");
    MoveProcessor.processMove(board, "u(F11) ");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Gn) ");
    MoveProcessor.processMove(board, "u(LB2,Sh) ");
    MoveProcessor.processMove(board, "U(LR1) ");
    MoveProcessor.processMove(board, "V(Gn)|B(F17,3,2)|U(F17,PnPnPn,Bo)* ");
    MoveProcessor.processMove(board, "u(G16) ");
    MoveProcessor.processMove(board, " b(F14,4,2)|U(F14,Jo)* ");
    MoveProcessor.processMove(board, "V(Gn)|U(F08,PnPtGnSw)|P(1,MOUNTAIN) ");
    MoveProcessor.processMove(board, "u(G01)|u(F17,PnPnPn,Bo) ");
    MoveProcessor.processMove(board, "c(0,1) ");
    MoveProcessor.processMove(board, "U(LG1) ");
    MoveProcessor.processMove(board, "b(F05,2,2)|U(F05,PtFlFlFlFl,BrBr)* ");
    MoveProcessor.processMove(board, "u(G02,ClGnPn,Gn) ");
    MoveProcessor.processMove(board, "S(S04,-1,1,PtPtMtBr) ");
    MoveProcessor.processMove(board, "s(S05,3,2,WoMt) ");
    MoveProcessor.processMove(board, "s(S05,3,2,ShShPnWo) ");
    MoveProcessor.processMove(board, "V(GnGn)|B(F21,1,2)|U(F21,GpGpGpGp,Wn)*|D(3,HILLS) ");
    MoveProcessor.processMove(board, "u(LB3) ");
    MoveProcessor.processMove(board, "f(0,2) ");
    MoveProcessor.processMove(board, "B(G22,6,1) ");
    MoveProcessor.processMove(board, "w(GREEN,Wn)|U(G22,Jo)|D(3,HILLS) ");
    MoveProcessor.processMove(board, "v(GnGn)|b(F20,2,2) ");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(G16) ");
    MoveProcessor.processMove(board, "u(G01)|U(F14)|P(1,MOUNTAIN) ");
    MoveProcessor.processMove(board, "u(LR2,Sh) ");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Gn) ");
    MoveProcessor.processMove(board, "w(GREEN,Wn)|U(F21,GpGpGpGpGpGp,Wn)|P(1,COAST) ");
    MoveProcessor.processMove(board, "w(Blue,Wn)|U(G16) ");
    MoveProcessor.processMove(board, "C(0,3) ");
    MoveProcessor.processMove(board, "w(GREEN,Wn)|U(G22,Jo) ");
    MoveProcessor.processMove(board, "u(F20,ShShShWn)|p(3,COAST) ");
    MoveProcessor.processMove(board, "F(2,0) ");
    MoveProcessor.processMove(board, "S(S04,-1,2,ShShShShPtWo) ");
    MoveProcessor.processMove(board, "s(S04,-1,2,ShShShShPtWo) ");
    MoveProcessor.processMove(board, "S(S02,0,0,PtWoShGp) ");
    MoveProcessor.processMove(board, "u(LB1) ");
    MoveProcessor.processMove(board, "u(F11) ");
    MoveProcessor.processMove(board, "U(F09)|U(LG3)|P(2,COAST) ");
    MoveProcessor.processMove(board, "V(Ni)|b(F32,5,1)|U(F32,Pn)*|C(0,1,Jo)|F(2,3) ");
    MoveProcessor.processMove(board, "B(F33,-1,3)|U(F33,WoPt,Mt)* ");
    MoveProcessor.processMove(board, "U(G22) ");
    MoveProcessor.processMove(board, "v(GnGn)|B(F30,0,2)|U(F30,Po)* ");
    MoveProcessor.processMove(board, "w(BLUE,Wn)|U(G01)|U(F30,Po) ");
    MoveProcessor.processMove(board, "B(G07,2,0)|U(G07,PtPtPtPtPtPtPtPt)* ");
    MoveProcessor.processMove(board, "w(RED,Wn)|U(F20,ShShBrWn) ");
    MoveProcessor.processMove(board, "U(F14) ");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(LR2,Sh) ");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(G22,Jo) ");
    MoveProcessor.processMove(board, "w(Blue,PnPn)|U(F32,Pn)|C(0,3)|F(2,3) ");
    MoveProcessor.processMove(board, "U(LG1) ");
    MoveProcessor.processMove(board, "w(RED,Wn)|U(F11) ");
    MoveProcessor.processMove(board, "B(G26,-1,4)|U(G26,WoWo)* ");
    MoveProcessor.processMove(board, "V(GnGnGnGnGn)|B(G19,5,1)|U(G19,ShSwShSwShSwShSwShSw)* ");
    MoveProcessor.processMove(board, "B(F27,3,3)|u(F27,Wn)*|u(F11) ");
    MoveProcessor.processMove(board, "u(LR2,Gn) ");
    MoveProcessor.processMove(board, "S(S07,-1,2,CoCoCoMtMtMt) ");
    MoveProcessor.processMove(board, "S(S03,2,3,ShBrPnPn) ");
    MoveProcessor.processMove(board, "s(S02,0,1,PtWoPnPnPn) ");
    MoveProcessor.processMove(board, "B(F38,-1,0)|U(F38,1,0,1,1,0,2,2,3)* ");
    MoveProcessor.processMove(board, "u(F30,Po) ");
    MoveProcessor.processMove(board, "b(F36,0,2)|U(F36,Or,Po)* ");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(F33,Co,Mt) ");
    MoveProcessor.processMove(board, "u(F32,Pn)|F(2,0)|C(0,3) ");
    MoveProcessor.processMove(board, "w(Green,PnPn)|U(F21,GpGpGpGpGpGpGpGpGp,Wn) ");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Sh) ");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(F11) ");
    MoveProcessor.processMove(board, "u(F33,PtPt,Mt) ");
    MoveProcessor.processMove(board, "F(1,3) ");
    MoveProcessor.processMove(board, "P(3,MOUNTAIN)|B(G28,5,3)|U(G28)*|S(S01,4,3,WoPn) ");
    MoveProcessor.processMove(board, "w(Blue,Wn)|U(G01)|U(G28)|s(S08,3,3,PtWoMtMtMtMtMtNi) ");
    MoveProcessor.processMove(board, "U(F09)|U(F17,PnPnPn,Bo) ");
    MoveProcessor.processMove(board, "u(LB2,Gn) ");
    MoveProcessor.processMove(board, "w(Green,Wn)|u(G07,PtPtPtPtPt) ");
    MoveProcessor.processMove(board, "V(Gn)|B(F40,3,3)|U(F40)*|U(G18,ClClSnCo) ");
    MoveProcessor.processMove(board, "u(G28)|S(S06,5,2,NiPtPtPt) ");
    MoveProcessor.processMove(board, "v(Gn)|b(G06,1,1)|U(G06,Co)* ");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(F27,Wn)|U(G28)|S(S08,4,2,MtMtMtMtMtMtCo) ");
    MoveProcessor.processMove(board, "v(GnGnGnGnGn)|b(F37,2,0)|U(F37,WoSwWoSwWoSwWoSwWoSw)* ");
    MoveProcessor.processMove(board, "U(G28)|s(S06,2,3,CoCoGnGnGnGnGn) ");
    MoveProcessor.processMove(board, "S(S05,0,2,CoShShGp) ");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Sh) ");
    MoveProcessor.processMove(board, "s(S07,4,3,CoCoCoPnPnPnPnPnGnGnGnGnGnNi) ");
    MoveProcessor.processMove(board, "S(S06,4,3,CoCoShShGp) ");
    MoveProcessor.processMove(board, "s(S07,-1,1,ShShShShGpNiPnPtPtPtWoWoWo) ");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {


    assertThat(board.isGameOver(), is(true));
    assertThat(board.isSettling(), is(false));
    Map<Color, PlayerScore> scores = board.getScorecard().getScores();

    assertThat(scores, hasKey(Color.RED));
    assertThat(scores.get(Color.RED), hasProperty("itemScore", is(25)));
    assertThat(scores.get(Color.RED), hasProperty("shieldScore", is(69)));
    assertThat(scores.get(Color.RED), hasProperty("settlementTotalScore", is(148)));
    Map<Class<? extends Settlement>, Integer> redScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(FarmingVillage.class, 16)
            .put(FishingVillage.class, 26)
            .put(MarketTown.class, 16)
            .put(ArtistsColony.class, 29)
            .put(Hamlet.class, 18)
            .put(HilltopVillage.class, 23)
            .put(Village.class, 20)
            .build();
    for (Entry<Class<? extends Settlement>, Integer> entry : redScores.entrySet()) {
      assertThat(
          scores.get(Color.RED).getSettlementScores(),
          hasItem(allOf(
              hasProperty("settlement", instanceOf(entry.getKey())),
              hasProperty("score", is(entry.getValue())))));
    }

    assertThat(scores, hasKey(Color.GREEN));
    assertThat(scores.get(Color.GREEN), hasProperty("itemScore", is(26)));
    assertThat(scores.get(Color.GREEN), hasProperty("shieldScore", is(85)));
    assertThat(scores.get(Color.GREEN), hasProperty("settlementTotalScore", is(140)));
    Map<Class<? extends Settlement>, Integer> greenScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(FarmingVillage.class, 14)
            .put(FishingVillage.class, 28)
            .put(Village.class, 20)
            .put(ArtistsColony.class, 24)
            .put(MarketTown.class, 16)
            .put(HilltopVillage.class, 21)
            .put(Hamlet.class, 17)
            .build();
    for (Entry<Class<? extends Settlement>, Integer> entry : greenScores.entrySet()) {
      assertThat(
          scores.get(Color.GREEN).getSettlementScores(),
          hasItem(allOf(
              hasProperty("settlement", instanceOf(entry.getKey())),
              hasProperty("score", is(entry.getValue())))));
    }

    assertThat(scores, hasKey(Color.BLUE));
    assertThat(scores.get(Color.BLUE), hasProperty("itemScore", is(24)));
    assertThat(scores.get(Color.BLUE), hasProperty("shieldScore", is(88)));
    assertThat(scores.get(Color.BLUE), hasProperty("settlementTotalScore", is(128)));
    Map<Class<? extends Settlement>, Integer> blueScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(Village.class, 15)
            .put(FishingVillage.class, 20)
            .put(FarmingVillage.class, 11)
            .put(ArtistsColony.class, 27)
            .put(Hamlet.class, 22)
            .put(MarketTown.class, 15)
            .put(ShantyTown.class, 18)
            .build();
    for (Entry<Class<? extends Settlement>, Integer> entry : blueScores.entrySet()) {
      assertThat(
          scores.get(Color.BLUE).getSettlementScores(),
          hasItem(allOf(
              hasProperty("settlement", instanceOf(entry.getKey())),
              hasProperty("score", is(entry.getValue())))));
    }
  }
}
