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
    board = new Board();
    MoveProcessor.processMove(board, "config PLAYERS 3");
    MoveProcessor.processMove(board, "config LENGTH LONG");
    MoveProcessor.processMove(board, "config COUNTRY FRANCE");
    MoveProcessor.processMove(board, "start");
    MoveProcessor.processMove(board, "fell_trees1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB3 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G02 3 1");
    MoveProcessor.processMove(board, "use G02 ClPnGn Pn *");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F09 3 1");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "build G01 3 1");
    MoveProcessor.processMove(board, "use G01 *");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "buy_plot 1 COAST");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB1 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G12 3 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees0 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G12 Pn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G12 ShPnPnPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G01 Pn");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use G12 PtPtShShShSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F04 4 2");
    MoveProcessor.processMove(board, "use F04 GnGnGnGn *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "use G02 GnSwPn Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F08 0 1");
    MoveProcessor.processMove(board, "use F08 ClPtGnSh *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S02 1 2 ShPnPtSwSw");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S03 1 2 ShShShSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "settle S03 2 2 ShShBr");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G16 3 0");
    MoveProcessor.processMove(board, "use G16 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F11 -1 1");
    MoveProcessor.processMove(board, "use F11 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3 Jo");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG3");
    MoveProcessor.processMove(board, "buy_plot 0 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F17 3 2");
    MoveProcessor.processMove(board, "use F17 PnPnPn Bo *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, " b F14 4 2");
    MoveProcessor.processMove(board, "use F14 Jo *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "use F08 PnPtGnSw");
    MoveProcessor.processMove(board, "buy_plot 1 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F17 PnPnPn Bo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F05 2 2");
    MoveProcessor.processMove(board, "use F05 PtFlFlFlFl BrBr *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G02 ClGnPn Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 1 PtPtMtBr");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S05 3 2 WoMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S05 3 2 ShShPnWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F21 1 2");
    MoveProcessor.processMove(board, "use F21 GpGpGpGp Wn *");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees0 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G22 6 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G22 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F20 2 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G16 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F14");
    MoveProcessor.processMove(board, "buy_plot 1 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F21 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F21 GpGpGpGpGpGp Wn");
    MoveProcessor.processMove(board, "buy_plot 1 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G16 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G16");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G22 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F20 ShShShWn");
    MoveProcessor.processMove(board, "buy_plot 3 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees2 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 2 ShShShShPtWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 2 ShShShShPtWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S02 0 0 PtWoShGp");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG3");
    MoveProcessor.processMove(board, "buy_plot 2 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Ni");
    MoveProcessor.processMove(board, "build F32 5 1");
    MoveProcessor.processMove(board, "use F32 Pn *");
    MoveProcessor.processMove(board, "cut_peat 0 1 Jo");
    MoveProcessor.processMove(board, "fell_trees2 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F33 -1 3");
    MoveProcessor.processMove(board, "use F33 WoPt Mt *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G22");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F30 0 2");
    MoveProcessor.processMove(board, "use F30 Po *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G01 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use F30 Po");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G07 2 0");
    MoveProcessor.processMove(board, "use G07 PtPtPtPtPtPtPtPt *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F20 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F20 ShShBrWn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F14");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract LR2 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G22 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F32 PnPn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F32 Pn");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "fell_trees2 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LG1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F11 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G26 -1 4");
    MoveProcessor.processMove(board, "use G26 WoWo *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGnGnGnGn");
    MoveProcessor.processMove(board, "build G19 5 1");
    MoveProcessor.processMove(board, "use G19 ShSwShSwShSwShSwShSw *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F27 3 3");
    MoveProcessor.processMove(board, "use F27 Wn *");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S07 -1 2 CoCoCoMtMtMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S03 2 3 ShBrPnPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S02 0 1 PtWoPnPnPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F38 -1 0");
    MoveProcessor.processMove(board, "use F38 1 0 1 1 0 2 2 3 *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F30 Po");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F36 0 2");
    MoveProcessor.processMove(board, "use F36 Or Po *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F33 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F33 Co Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F32 Pn");
    MoveProcessor.processMove(board, "fell_trees2 0");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F21 PnPn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F21 GpGpGpGpGpGpGpGpGp Wn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F11 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F33 PtPt Mt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees1 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "buy_plot 3 MOUNTAIN");
    MoveProcessor.processMove(board, "build G28 5 3");
    MoveProcessor.processMove(board, "use G28 *");
    MoveProcessor.processMove(board, "settle S01 4 3 WoPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G01 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G01");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S08 3 3 PtWoMtMtMtMtMtNi");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use F17 PnPnPn Bo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LB2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract G07 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use G07 PtPtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F40 3 3");
    MoveProcessor.processMove(board, "use F40 *");
    MoveProcessor.processMove(board, "use G18 ClClSnCo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S06 5 2 NiPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build G06 1 1");
    MoveProcessor.processMove(board, "use G06 Co *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "work_contract F27 Wn");
    MoveProcessor.processMove(board, "with CLERGYMAN");
    MoveProcessor.processMove(board, "use F27 Wn");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S08 4 2 MtMtMtMtMtMtCo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGnGnGnGn");
    MoveProcessor.processMove(board, "build F37 2 0");
    MoveProcessor.processMove(board, "use F37 WoSwWoSwWoSwWoSwWoSw *");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S06 2 3 CoCoGnGnGnGnGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S05 0 2 CoShShGp");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F09");
    MoveProcessor.processMove(board, "use LG2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S07 4 3 CoCoCoPnPnPnPnPnGnGnGnGnGnNi");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S06 4 3 CoCoShShGp");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S07 -1 1 ShShShShGpNiPnPtPtPtWoWoWo");
    MoveProcessor.processMove(board, "commit");
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
