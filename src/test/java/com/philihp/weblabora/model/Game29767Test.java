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
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,1)|CONFIG(COUNTRY,FRANCE)");
    MoveProcessor.processMove(board, "c(0,0)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "B(G07,3,0)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "V(Gn)|B(G06,0,0)|U(G06,CoCoCo)|P(0,COAST)");
    MoveProcessor.processMove(board, "V(Gn)|B(F03,1,1)");
    MoveProcessor.processMove(board, "U(F03)");
    MoveProcessor.processMove(board, "U(LR3)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "f(0,2)");
    MoveProcessor.processMove(board, "B(F04,-1,0)|U(F04,GnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "B(F05,0,2)|U(F05,FlFlFlFlFlFlCo,BrBr)|P(2,COAST)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(LR3,Jo)|D(3,HILLS)");
    MoveProcessor.processMove(board, "C(0,3)");
    MoveProcessor.processMove(board, "B(G12,2,0)");
    MoveProcessor.processMove(board, "U(G12,ShShShShShPtPtPtPtPt)");
    MoveProcessor.processMove(board, "B(F08,2,2)");
    MoveProcessor.processMove(board, "B(F11,-1,2)|U(F11)");
    MoveProcessor.processMove(board, "B(G02,3,1)|B(F09,3,0)|B(G01,2,0)");
    MoveProcessor.processMove(board, "S(S04,-1,1,BrBrShCo)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(LR3)|D(-1,HILLS)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "V(GnGn)|B(G19,4,-1)|U(G19,ShShShShShShShSwSwSwSwSwSwSw)");
    MoveProcessor.processMove(board, "V(Gn)|B(F17,4,2)");
    MoveProcessor.processMove(board, "V(GnGn)|U(G12,SwSwPtPtMtMt)");
    MoveProcessor.processMove(board, "B(F15,3,3)|U(F15,Pn)");
    MoveProcessor.processMove(board, "B(G18,3,1)");
    MoveProcessor.processMove(board, "B(G16,1,0)");
    MoveProcessor.processMove(board, "V(GnGn)|S(S05,0,1,MtSwSw)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "F(1,3)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F21,1,3)");
    MoveProcessor.processMove(board, "U(F21,GpGp,Wn)|P(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "V(Gn)|B(F23,5,2)|U(F23,Pn)|P(4,MOUNTAIN)");
    MoveProcessor.processMove(board, "B(F24,5,3)|U(F24,BrBrWnWn)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "B(G22,6,2)|U(G22,Jo)");
    MoveProcessor.processMove(board, "B(G26,-1,3)");
    MoveProcessor.processMove(board, "C(0,-1)");
    MoveProcessor.processMove(board, "U(G26,WoWo)|D(4,PLAINS)");
    MoveProcessor.processMove(board, "B(F20,0,1)|B(F25,1,1)");
    MoveProcessor.processMove(board, "S(S06,1,2,MtPtPtPt)");
    MoveProcessor.processMove(board, "f(2,3)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "B(G28,6,4)|U(G28)|S(S07,3,2,MtMtMtPtPtPtPtWo)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F30,2,4)");
    MoveProcessor.processMove(board, "U(F30)|P(4,COAST)");
    MoveProcessor.processMove(board, "B(F33,-1,4)|U(F33,PtWo,Wn)|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "B(F32,4,1)|B(F27,2,1)");
    MoveProcessor.processMove(board, "S(S01,2,3,WoSh)");
    MoveProcessor.processMove(board, "f(0,4)");
    MoveProcessor.processMove(board, "V(GnGnGnGnGnGn)|U(G19,ShShShShShShSwSwSwSwSwSw)");
    MoveProcessor.processMove(board, "U(G28)|S(S08,4,3,MtMtMtMtMtMtPtWo)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "V(Gn)|B(F40,5,1)|U(F40)|U(G34)");
    MoveProcessor.processMove(board, "B(F37,5,4)");
    MoveProcessor.processMove(board, "U(G22,Jo)");
    MoveProcessor.processMove(board, "U(G28)|S(S03,3,4,BrShSh)");
    MoveProcessor.processMove(board, "B(F38,0,4)|U(F38,1,-1,2,-1,1,0)");
    MoveProcessor.processMove(board, "V(GnGn)|B(G34,4,4)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "U(F40)|U(G41,PnPnPnPnPn,PoOr)|V(Wn)|V(PnPnPnPnPn)|V(PnPnPnPnPn)");
    MoveProcessor.processMove(board, "U(G34)");
    MoveProcessor.processMove(board, "V(GnGn)|S(S02,1,4,ShGnSwSwPt)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
