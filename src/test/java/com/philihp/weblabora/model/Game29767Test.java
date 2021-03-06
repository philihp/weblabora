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
public class Game29767Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "config PLAYERS 1");
    MoveProcessor.processMove(board, "config COUNTRY FRANCE");
    MoveProcessor.processMove(board, "start");
    MoveProcessor.processMove(board, "cut_peat 0 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G07 3 0");
    MoveProcessor.processMove(board, "use G07 PtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build G06 0 0");
    MoveProcessor.processMove(board, "use G06 CoCoCo");
    MoveProcessor.processMove(board, "buy_plot 0 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F03 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F03");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "buy_district 2 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 0 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F04 -1 0");
    MoveProcessor.processMove(board, "use F04 GnGnGnGnGnGn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F05 0 2");
    MoveProcessor.processMove(board, "use F05 FlFlFlFlFlFlCoBrBr");
    MoveProcessor.processMove(board, "buy_plot 2 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 2 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3 Jo");
    MoveProcessor.processMove(board, "buy_district 3 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G12 2 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G12 ShShShShShPtPtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F08 2 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F11 -1 2");
    MoveProcessor.processMove(board, "use F11");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G02 3 1");
    MoveProcessor.processMove(board, "build F09 3 0");
    MoveProcessor.processMove(board, "build G01 2 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S04 -1 1 BrBrShCo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "buy_district -1 HILLS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build G19 4 -1");
    MoveProcessor.processMove(board, "use G19 ShShShShShShShSwSwSwSwSwSwSw");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F17 4 2");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "use G12 SwSwPtPtMtMt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F15 3 3");
    MoveProcessor.processMove(board, "use F15 Pn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G18 3 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G16 1 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "settle S05 0 1 MtSwSw");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 1 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F21 1 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F21 GpGp Wn");
    MoveProcessor.processMove(board, "buy_plot 2 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F23 5 2");
    MoveProcessor.processMove(board, "use F23 Pn");
    MoveProcessor.processMove(board, "buy_plot 4 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F24 5 3");
    MoveProcessor.processMove(board, "use F24 BrBrWnWn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G22 6 2");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G26 -1 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "cut_peat 0 -1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G26 WoWo");
    MoveProcessor.processMove(board, "buy_district 4 PLAINS");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F20 0 1");
    MoveProcessor.processMove(board, "build F25 1 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S06 1 2 MtPtPtPt");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 2 3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build G28 6 4");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S07 3 2 MtMtMtPtPtPtPtWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Sh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build F30 2 4");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F30");
    MoveProcessor.processMove(board, "buy_plot 4 COAST");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F33 -1 4");
    MoveProcessor.processMove(board, "use F33 PtWo Wn");
    MoveProcessor.processMove(board, "buy_plot 0 MOUNTAIN");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F32 4 1");
    MoveProcessor.processMove(board, "build F27 2 1");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "settle S01 2 3 WoSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "fell_trees 0 4");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGnGnGnGnGn");
    MoveProcessor.processMove(board, "use G19 ShShShShShShSwSwSwSwSwSw");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S08 4 3 MtMtMtMtMtMtPtWo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR2 Gn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert Gn");
    MoveProcessor.processMove(board, "build F40 5 1");
    MoveProcessor.processMove(board, "use F40");
    MoveProcessor.processMove(board, "use G34");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F37 5 4");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G22 Jo");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G28");
    MoveProcessor.processMove(board, "settle S03 3 4 BrShSh");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "build F38 0 4");
    MoveProcessor.processMove(board, "use F38 1 -1 2 -1 1 0");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "build G34 4 4");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use LR3");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use F40");
    MoveProcessor.processMove(board, "use G41 PnPnPnPnPn PoOr");
    MoveProcessor.processMove(board, "convert Wn");
    MoveProcessor.processMove(board, "convert PnPnPnPnPn");
    MoveProcessor.processMove(board, "convert PnPnPnPnPn");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "use G34");
    MoveProcessor.processMove(board, "commit");
    MoveProcessor.processMove(board, "convert GnGn");
    MoveProcessor.processMove(board, "settle S02 1 4 ShGnSwSwPt");
    MoveProcessor.processMove(board, "commit");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
