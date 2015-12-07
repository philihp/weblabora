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
    board = new Board();
    MoveProcessor.processMove(board, "config PLAYERS 3");
    MoveProcessor.processMove(board, "config LENGTH LONG");
    MoveProcessor.processMove(board, "config COUNTRY FRANCE");
    MoveProcessor.processMove(board, "start");
    MoveProcessor.processMove(board, "cut_peat 0 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB3 Jo");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build G06 3 0");
    MoveProcessor.processMove(board, "use G06 PtPtPt *");
    MoveProcessor.processMove(board, "buy_plot 0 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F09 3 1");
    MoveProcessor.processMove(board, "use F09 *");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G01 3 1");
    MoveProcessor.processMove(board, "use G01 *");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB1 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G12 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 0 2 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB3");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build G02 3 0");
    MoveProcessor.processMove(board, "use G02 GnSwCl Pn *");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S02 2 2 PtWoShGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S03 3 2 ShShShPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S03 2 2 ShShShGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build G16 3 2");
    MoveProcessor.processMove(board, "use G16 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F14 3 3");
    MoveProcessor.processMove(board, "use F14 Jo *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G16 Pn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G12 ShShShPtWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F04 4 3");
    MoveProcessor.processMove(board, "use F04 GnGnGnGn *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 2 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "buy_plot 2 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F08 1 2");
    MoveProcessor.processMove(board, "use F08 GnSwClPn *");
    MoveProcessor.processMove(board, "buy_plot 1 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 1 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F05 1 2");
    MoveProcessor.processMove(board, "use F05 FlFlFlFlPt BrBr *");
    MoveProcessor.processMove(board, "buy_plot 0 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "use F08 GnSwClPn");
    MoveProcessor.processMove(board, "buy_district 3 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G07 2 0");
    MoveProcessor.processMove(board, "use G07 PtPtPtPtPtPtPtPtPt *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S05 1 3 ShBrPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S05 4 2 WoShBr");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 1 CoShShShSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F21 2 3");
    MoveProcessor.processMove(board, "use F21 GpGpGp Wn");
    MoveProcessor.processMove(board, "buy_plot 2 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G22 6 0");
    MoveProcessor.processMove(board, "use G22 Jo *");
    MoveProcessor.processMove(board, "buy_plot 1 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F14");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F11 -1 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F21 PnPn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F21 GpGpGpGpGpGpGp Wn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "buy_plot 0 COAST");
    MoveProcessor.processMove(board, "build G26 -1 0");
    MoveProcessor.processMove(board, "use G26 WoWo *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F20 2 2");
    MoveProcessor.processMove(board, "use F20 ShShShGnWn *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F09 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F11 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S03 1 1 ShShBr");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S06 1 2 CoCoShShGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S06 -1 3 PtPtPtBrPnPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F14 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F14");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F29 6 1");
    MoveProcessor.processMove(board, "use F29 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F30 0 1");
    MoveProcessor.processMove(board, "use F30 Po *");
    MoveProcessor.processMove(board, "buy_plot 2 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F33 -1 0");
    MoveProcessor.processMove(board, "use F33 Co JoMt *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F17 5 1");
    MoveProcessor.processMove(board, "use F17 PnPnPn Bo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F17 PnPnPn Bo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F30 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F30 Po");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F32 5 0");
    MoveProcessor.processMove(board, "use F32 Pn *");
    MoveProcessor.processMove(board, "fell_trees 2 3");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F33 Co Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F29");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F21 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F21 GpGpGpGpGpGp Wn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F27 4 2");
    MoveProcessor.processMove(board, "use F27 Wn *");
    MoveProcessor.processMove(board, "use F21 Gp Wn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use G02 GnSwPn Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G07 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G07 PtPtPtPtPtPtPtPtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F11 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 2 ShShShShPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 1 MtShPnCo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S07 0 2 MtMtMtCoCoCo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F40 4 2");
    MoveProcessor.processMove(board, "use F40 *");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S02 3 3 ShPnPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F33 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F33 Co Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F36 1 1");
    MoveProcessor.processMove(board, "use F36 Po Or *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use G02 ClSnPt Sh");
    MoveProcessor.processMove(board, "buy_district 4 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F40");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S06 3 3 CoCoMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Ni");
    MoveProcessor.processMove(board, "use G02 SnClGp Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 0 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G22 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G22");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F38 0 3");
    MoveProcessor.processMove(board, "use F38 1 0 2 0 0 2 1 4 2 4 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F33 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F33 Co Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G28 5 3");
    MoveProcessor.processMove(board, "use G28 *");
    MoveProcessor.processMove(board, "settle S08 4 3 CoMtMtShShShShShShShShShSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 4");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S08 5 2 CoMtMtMtMtMtMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "buy_plot 2 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use G02 GnPnGp Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G28 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S01 0 2 WoPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F27 Wn");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S05 -1 2 WoMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F24 3 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F33 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F33 Co Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S02 0 1 CoShGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S07 0 2 ShShShShShShShPnPtPtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G18 3 0");
    MoveProcessor.processMove(board, "use G18 CoWoClClClSn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S01 5 2 WoGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S08 4 3 ShShShShShShShShShShShShGnPnPnPnGpGpPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S07 5 3 CoCoCoMtMtMt");
    MoveProcessor.processMove(board, "commit");
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
