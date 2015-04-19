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
    board = new Board(GamePlayers.THREE, GameLength.LONG, GameCountry.FRANCE);

    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "u(LB3,Jo)|d(2,PLAINS)");
    MoveProcessor.processMove(board, "V(Gn)|B(G06,3,0)|U(G06,PtPtPt)*|P(0,COAST)");
    MoveProcessor.processMove(board, "b(F09,3,1)|U(F09)*|U(LG2,Sh)");
    MoveProcessor.processMove(board, "b(G01,3,1)|u(G01)*|u(F09)|u(LG2,Gn)");
    MoveProcessor.processMove(board, "U(LR1)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "u(LB1,Jo)");
    MoveProcessor.processMove(board, "U(LR3)|D(3,HILLS)");
    MoveProcessor.processMove(board, "B(G12,1,1)");
    MoveProcessor.processMove(board, "c(0,1)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(LG2,Gn)");
    MoveProcessor.processMove(board, "f(1,1)");
    MoveProcessor.processMove(board, "F(0,2,Jo)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "u(LB3)|d(3,HILLS)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "V(Gn)|b(G02,3,0)|u(G02,GnSwCl,Pn)*|D(2,PLAINS)");
    MoveProcessor.processMove(board, "s(S02,2,2,PtWoShGn)");
    MoveProcessor.processMove(board, "S(S03,3,2,ShShShPn)");
    MoveProcessor.processMove(board, "S(S03,2,2,ShShShGn)");
    MoveProcessor.processMove(board, "v(Gn)|b(G16,3,2)|u(G16)*");
    MoveProcessor.processMove(board, "B(F14,3,3)|U(F14,Jo)*");
    MoveProcessor.processMove(board, "u(F09)|U(LG2,Sh)");
    MoveProcessor.processMove(board, "U(G01)|u(G16)");
    MoveProcessor.processMove(board, "W(BLUE,Pn)|U(G16)");
    MoveProcessor.processMove(board, "u(G12,ShShShPtWo)");
    MoveProcessor.processMove(board, "B(F04,4,3)|u(F04,GnGnGnGn)*");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "u(F09)|U(LG2,Gn)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "U(LR3)|P(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "V(Gn)|b(F08,1,2)|U(F08,GnSwClPn)*|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "C(0,3)");
    MoveProcessor.processMove(board, "C(0,1,Jo)");
    MoveProcessor.processMove(board, "B(F05,1,2)|u(F05,FlFlFlFlPt,BrBr)*|p(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "v(Gn)|U(F08,GnSwClPn)|D(3,PLAINS)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "B(G07,2,0)|U(G07,PtPtPtPtPtPtPtPtPt)*");
    MoveProcessor.processMove(board, "s(S05,1,3,ShBrPt)");
    MoveProcessor.processMove(board, "S(S05,4,2,WoShBr)");
    MoveProcessor.processMove(board, "S(S04,-1,1,CoShShShSh)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F21,2,3)|U(F21,GpGpGp,Wn)|P(2,COAST)");
    MoveProcessor.processMove(board, "B(G22,6,0)|u(G22,Jo)*|p(1,COAST)");
    MoveProcessor.processMove(board, "U(F14)");
    MoveProcessor.processMove(board, "f(0,3)");
    MoveProcessor.processMove(board, "B(F11,-1,2)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "W(GREEN,PnPn)|U(F21,GpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "P(0,COAST)|B(G26,-1,0)|U(G26,WoWo)*");
    MoveProcessor.processMove(board, "U(F11)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F20,2,2)|U(F20,ShShShGnWn)*");
    MoveProcessor.processMove(board, "c(0,0)");
    MoveProcessor.processMove(board, "U(G22,Jo)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(F09)|U(LG2,Sh)");
    MoveProcessor.processMove(board, "w(BLUE,Wn)|U(F11)");
    MoveProcessor.processMove(board, "S(S03,1,1,ShShBr)");
    MoveProcessor.processMove(board, "S(S06,1,2,CoCoShShGn)");
    MoveProcessor.processMove(board, "S(S06,-1,3,PtPtPtBrPnPn)");
    MoveProcessor.processMove(board, "W(RED,Wn)|u(F14)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "b(F29,6,1)|U(F29)*");
    MoveProcessor.processMove(board, "B(F30,0,1)|u(F30,Po)*|p(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "B(F33,-1,0)|U(F33,Co,JoMt)*");
    MoveProcessor.processMove(board, "u(F09)|U(LG2,Gn)");
    MoveProcessor.processMove(board, "B(F17,5,1)|u(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "U(G01)|u(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|u(F30,Po)");
    MoveProcessor.processMove(board, "B(F32,5,0)|u(F32,Pn)*|f(2,3)|c(0,3)");
    MoveProcessor.processMove(board, "U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "u(F29)");
    MoveProcessor.processMove(board, "W(Green,Wn)|u(F21,GpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "B(F27,4,2)|U(F27,Wn)*|U(F21,Gp,Wn)");
    MoveProcessor.processMove(board, "v(Gn)|u(F09)|U(G02,GnSwPn,Sh)");
    MoveProcessor.processMove(board, "W(Red,Wn)|U(G07,PtPtPtPtPtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(F11)");
    MoveProcessor.processMove(board, "S(S04,-1,2,ShShShShPtPt)");
    MoveProcessor.processMove(board, "S(S04,-1,1,MtShPnCo)");
    MoveProcessor.processMove(board, "S(S07,0,2,MtMtMtCoCoCo)");
    MoveProcessor.processMove(board, "v(Gn)|b(F40,4,2)|U(F40)*|U(G28)|S(S02,3,3,ShPnPtPt)");
    MoveProcessor.processMove(board, "W(Red,Wn)|u(F33,Co,Mt)");
    MoveProcessor.processMove(board, "B(F36,1,1)|U(F36,Po,Or)*");
    MoveProcessor.processMove(board, "u(F09)|U(G02,ClSnPt,Sh)|D(4,HILLS)");
    MoveProcessor.processMove(board, "U(G01)|u(F40)|u(G28)|s(S06,3,3,CoCoMt)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "V(Ni)|u(G02,SnClGp,Sh)");
    MoveProcessor.processMove(board, "F(0,2)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(G22)");
    MoveProcessor.processMove(board, "B(F38,0,3)|u(F38,1,0,2,0,0,2,1,4,2,4)*");
    MoveProcessor.processMove(board, "W(Red,Wn)|u(F33,Co,Mt)");
    MoveProcessor.processMove(board, "B(G28,5,3)|U(G28)*|S(S08,4,3,CoMtMtShShShShShShShShShSh)");
    MoveProcessor.processMove(board, "C(0,4)");
    MoveProcessor.processMove(board, "U(G01)|u(G28)|s(S08,5,2,CoMtMtMtMtMtMt)");
    MoveProcessor.processMove(board, "U(LR3)|P(2,COAST)");
    MoveProcessor.processMove(board, "U(F09)|u(G02,GnPnGp,Sh)");
    MoveProcessor.processMove(board, "W(Red,Wn)|u(G28)|s(S01,0,2,WoPn)");
    MoveProcessor.processMove(board, "U(F27,Wn)|U(G28)|S(S05,-1,2,WoMt)");
    MoveProcessor.processMove(board, "B(F24,3,2)");
    MoveProcessor.processMove(board, "W(Red,Wn)|u(F33,Co,Mt)");
    MoveProcessor.processMove(board, "U(G28)|S(S02,0,1,CoShGn)");
    MoveProcessor.processMove(board, "U(G28)|s(S07,0,2,ShShShShShShShPnPtPtPtPtPt)");
    MoveProcessor.processMove(board, "B(G18,3,0)|u(G18,CoWoClClClSn)");
    MoveProcessor.processMove(board, "S(S01,5,2,WoGn)");
    MoveProcessor.processMove(board, "S(S08,4,3,ShShShShShShShShShShShShGnPnPnPnGpGpPtPt)");
    MoveProcessor.processMove(board, "S(S07,5,3,CoCoCoMtMtMt)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
    assertThat(board.isSettling(), is(false));
    Map<Color, PlayerScore> scores = board.getScorecard().getScores();

    assertThat(scores, hasKey(Color.RED));
    assertThat(scores.get(Color.RED), hasProperty("itemScore", is(9)));
    assertThat(scores.get(Color.RED), hasProperty("shieldScore", is(93)));
    assertThat(scores.get(Color.RED), hasProperty("settlementTotalScore", is(159)));
    Map<Class<? extends Settlement>, Integer> redScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(FishingVillage.class, 19)
            .put(ArtistsColony.class, 20)
            .put(Village.class, 16)
            .put(Hamlet.class, 22)
            .put(MarketTown.class, 22)
            .put(ShantyTown.class, 12)
            .put(HilltopVillage.class, 29)
            .build();

    for (Entry<Class<? extends Settlement>, Integer> entry : redScores.entrySet()) {
      assertThat(
          scores.get(Color.RED).getSettlementScores(),
          hasItem(allOf(
              hasProperty("settlement", instanceOf(entry.getKey())),
              hasProperty("score", is(entry.getValue())))));
    }

    assertThat(scores, hasKey(Color.GREEN));
    assertThat(scores.get(Color.GREEN), hasProperty("itemScore", is(15)));
    assertThat(scores.get(Color.GREEN), hasProperty("shieldScore", is(97)));
    assertThat(scores.get(Color.GREEN), hasProperty("settlementTotalScore", is(148)));
    Map<Class<? extends Settlement>, Integer> greenScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(FishingVillage.class, 19)
            .put(Village.class, 25)
            .put(MarketTown.class, 26)
            .put(Hamlet.class, 18)
            .put(ArtistsColony.class, 23)
            .put(FarmingVillage.class, 23)
            .put(HilltopVillage.class, 14)
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
    assertThat(scores.get(Color.BLUE), hasProperty("shieldScore", is(77)));
    assertThat(scores.get(Color.BLUE), hasProperty("settlementTotalScore", is(149)));
    Map<Class<? extends Settlement>, Integer> blueScores =
        ImmutableMap.<Class<? extends Settlement>, Integer>builder()
            .put(FishingVillage.class, 21)
            .put(MarketTown.class, 14)
            .put(ShantyTown.class, 14)
            .put(FarmingVillage.class, 13)
            .put(ArtistsColony.class, 26)
            .put(HilltopVillage.class, 26)
            .put(Hamlet.class, 15)
            .put(Village.class, 20)
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
