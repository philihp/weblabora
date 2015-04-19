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
public class Game53626Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board(GamePlayers.THREE, GameLength.LONG, GameCountry.FRANCE);
    MoveProcessor.processMove(board, "V(Gn)|B(G06,3,0)|U(G06,PtWo)*|D(2,PLAINS)");
    MoveProcessor.processMove(board, "c(0,1,Jo)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(LR3)|P(1,COAST)");
    MoveProcessor.processMove(board, "b(G01,3,1)|U(G01)*|U(G06,PtPtPt)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "B(F09,3,1)");
    MoveProcessor.processMove(board, "u(LG2,Gn)|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "c(0,1)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "u(LG1,Jo)");
    MoveProcessor.processMove(board, "u(LB3)|d(2,PLAINS)");
    MoveProcessor.processMove(board, "F(0,2)");
    MoveProcessor.processMove(board, "w(RED,Pn)|U(F09)|U(LR2,Gn)");
    MoveProcessor.processMove(board, "b(G12,3,0)|U(G12,PtShSh)*");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(LG2,ShJo)");
    MoveProcessor.processMove(board, "u(LB1)");
    MoveProcessor.processMove(board, "B(G02,3,2)|U(G02,WoClPn,Gn)");
    MoveProcessor.processMove(board, "u(LG3)|P(1,COAST)");
    MoveProcessor.processMove(board, "s(S02,2,2,ShShPtWo)");
    MoveProcessor.processMove(board, "S(S03,2,2,ShShShSh)");
    MoveProcessor.processMove(board, "s(S03,1,2,ShShShGn)");
    MoveProcessor.processMove(board, "v(Gn)|B(G16,3,1)|U(G16)*");
    MoveProcessor.processMove(board, "U(F09)|U(G02,GpClWo,Wo)");
    MoveProcessor.processMove(board, "u(G01)|U(G16)");
    MoveProcessor.processMove(board, "u(LB2,Sh)");
    MoveProcessor.processMove(board, "U(LR1,Jo)");
    MoveProcessor.processMove(board, "c(0,0)");
    MoveProcessor.processMove(board, "B(F08,1,2)|U(F08,WoClGnSh)|d(3,PLAINS)");
    MoveProcessor.processMove(board, "B(F04,-1,1)|U(F04,GnGnGnGnGnGn)*");
    MoveProcessor.processMove(board, "w(RED,Pn)|U(F04,GnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "b(F14,4,3)");
    MoveProcessor.processMove(board, "B(F05,1,2)|U(F05,WoWoWoFlFlFlFlFlFl,BrBr)|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "u(LG2,Gn)");
    MoveProcessor.processMove(board, "U(LB3)|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "b(G07,5,1)|U(G07,PtPtPtPtPtPt)*");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "U(F09)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(G01)|U(F05,FlFlFlFlFlFlFlCoSw,BrBr)");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "C(0,1,Jo)");
    MoveProcessor.processMove(board, "S(S04,-1,2,BrBrShCo)");
    MoveProcessor.processMove(board, "s(S03,3,3,ShShBr)");
    MoveProcessor.processMove(board, "S(S05,0,1,WoShBr)");
    MoveProcessor.processMove(board, "b(G22,6,1)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "U(LB1)|p(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(F09)|U(G02,WoClSw,Cl)");
    MoveProcessor.processMove(board, "b(F21,2,2)");
    MoveProcessor.processMove(board, "v(Gn)|u(F08,ClGnSwPt)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "u(G01)|U(F08,GnSwClPn)");
    MoveProcessor.processMove(board, "f(0,2)");
    MoveProcessor.processMove(board, "W(BLUE,PnPn)|U(F14)");
    MoveProcessor.processMove(board, "u(LG3)|D(3,HILLS)");
    MoveProcessor.processMove(board, "u(G12,PtPtPtShShShBrPn)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "u(G22,Jo)");
    MoveProcessor.processMove(board, "u(LB2,Sh)");
    MoveProcessor.processMove(board, "W(GREEN,PnPn)|U(F21,GpGpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "b(F11,-1,1)|U(F11)*");
    MoveProcessor.processMove(board, "s(S05,4,2,ShShPnPt)");
    MoveProcessor.processMove(board, "S(S04,-1,2,BrBrBrPtWo)");
    MoveProcessor.processMove(board, "s(S06,0,1,ShBrCoCo)");
    MoveProcessor.processMove(board, "b(F29,6,0)|U(F29)*");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(G07,PtPtPtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "b(F32,3,0)|U(F32,Pn)|C(0,3)|F(0,2)");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "P(3,COAST)|B(F33,-1,3)|U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "b(F24,3,2)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(F14)");
    MoveProcessor.processMove(board, "B(F17,4,2)|U(F17,PnPnPn,Bo)*");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "U(F09)|U(G06,CoCoCo)");
    MoveProcessor.processMove(board, "b(F30,0,2)");
    MoveProcessor.processMove(board, "w(Green,PnPn)|U(F32,Pn)|c(0,0)|f(1,0)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(F21,GpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "u(LB2,Sh)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(F29)");
    MoveProcessor.processMove(board, "u(F11)");
    MoveProcessor.processMove(board, "w(Green,PnPn)|U(G07,PtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "B(F27,5,2)|U(F27,Wn)*|U(F11)");
    MoveProcessor.processMove(board, "S(S05,3,2,WoMt)");
    MoveProcessor.processMove(board, "s(S01,1,1,WoPn)");
    MoveProcessor.processMove(board, "S(S07,0,2,MtMtMtCoCoCo)");
    MoveProcessor.processMove(board, "V(Gn)|B(F40,4,3)|U(F40)*|U(F38,1,0,2,0,1,1,1,3,2,3)");
    MoveProcessor.processMove(board, "u(LB2,Gn)");
    MoveProcessor.processMove(board, "U(F09)|U(LR3)");
    MoveProcessor.processMove(board, "b(F36,1,3)");
    MoveProcessor.processMove(board, "b(G19,0,0)|U(G19,GnGnGnGnGnGnGnGnGnShShShShShShShShSh)");
    MoveProcessor.processMove(board, "D(3,HILLS)|U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "u(F40)|U(G28)|S(S02,0,3,BrCo)");
    MoveProcessor.processMove(board, "u(F08,WoClShCo)");
    MoveProcessor.processMove(board, "B(F38,0,0)|U(F38,1,0,1,1,1,3,2,3)*");
    MoveProcessor.processMove(board, "v(GnGn)|B(F20,1,1)|U(F20,BrBrWn)*");
    MoveProcessor.processMove(board, "b(G28,6,2)|u(G28)*|s(S08,5,2,MtMtMtMtMtMtCo)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(G01)|U(G28)|S(S06,1,1,CoCoMt)");
    MoveProcessor.processMove(board, "v(Gn)|B(F35,0,0)|U(F35,Ni)*");
    MoveProcessor.processMove(board, "v(Ni)|w(Green,PnPn)|u(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "B(F37,3,3)|U(F37)*");
    MoveProcessor.processMove(board, "u(LG2,Sh)");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "B(G34,2,3)");
    MoveProcessor.processMove(board, "u(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "u(F24,BrWn)");
    MoveProcessor.processMove(board, "U(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "u(G28)|S(S07,2,3,CoCoCoMtMtMt)");
    MoveProcessor.processMove(board, "u(G28)|s(S06,5,3,CoCoMt)");
    MoveProcessor.processMove(board, "V(GnGnGnGn)|S(S08,4,2,SwSwSwSwSwSwGnGnShShShShMtMtMtMt)");
    MoveProcessor.processMove(board, "s(S08,3,3,ShShShShShShPnPnPnNiNiNiCo)");
    MoveProcessor.processMove(board, "s(S07,2,3,MtMtMtCoCoCo)");

  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
