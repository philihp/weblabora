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
public class Game21872Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,4)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,FRANCE)");
    MoveProcessor.processMove(board, "U(LR3)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "u(LG3,Jo)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "B(G01,3,1)");
    MoveProcessor.processMove(board, "b(F09,3,1)|U(F09)|u(LG2,Sh)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(G01)|U(F09)|U(LG2,Gn)");
    MoveProcessor.processMove(board, "c(0,0,Jo)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "B(G02,3,1)|U(G02,ClPnGn,Pn)|D(-1,PLAINS)");
    MoveProcessor.processMove(board, "U(LR1,Jo)");
    MoveProcessor.processMove(board, "b(G12,0,0)");
    MoveProcessor.processMove(board, "U(LB3)|D(-1,HILLS)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "u(G12,PtPtShShShSh)");
    MoveProcessor.processMove(board, "B(F04,3,-1)");
    MoveProcessor.processMove(board, "B(G13,1,0)");
    MoveProcessor.processMove(board, "V(Gn)|B(F05,2,0)");
    MoveProcessor.processMove(board, "c(0,1)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "u(F09)|u(LG3,Jo)|d(2,PLAINS)");
    MoveProcessor.processMove(board, "U(LB3)|P(-1,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "b(F08,1,2)|u(F08,ClGpPtGn)|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "S(S03,3,0,ShShShSh)");
    MoveProcessor.processMove(board, "S(S01,2,-1,WoGn)");
    MoveProcessor.processMove(board, "S(S02,3,0,ShGnPtWo)");
    MoveProcessor.processMove(board, "s(S01,2,2,GpPt)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "B(F15,1,-1)|U(F15)");
    MoveProcessor.processMove(board, "U(G01)|U(F15,Pn)");
    MoveProcessor.processMove(board, "u(LG2,Sh)");
    MoveProcessor.processMove(board, "V(Gn)|B(G16,3,1)|U(G16)*");
    MoveProcessor.processMove(board, "U(G02,SwGnWo,Pn)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "u(F09)|u(LG2,JoGn)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "B(G07,1,2)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "u(LG3)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "B(F14,4,2)");
    MoveProcessor.processMove(board, "s(S05,4,2,BrShPt)");
    MoveProcessor.processMove(board, "S(S05,2,0,PtShShSh)");
    MoveProcessor.processMove(board, "S(S05,4,-1,WoShBr)");
    MoveProcessor.processMove(board, "S(S05,3,2,WoBrGnGn)");
    MoveProcessor.processMove(board, "b(G22,6,0)|u(G22,Jo)");
    MoveProcessor.processMove(board, "U(F04,GnGnGnGnGnGn)*");
    MoveProcessor.processMove(board, "V(Gn)|B(F21,2,0)|U(F21,GpGp,Wn)|P(-2,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(G01)|U(F21,GpGpGp,Wn)");
    MoveProcessor.processMove(board, "f(0,2)");
    MoveProcessor.processMove(board, "W(R,PnPn)|U(F05,FlFlFlFlFlFlPtPt,BrBr)");
    MoveProcessor.processMove(board, "V(GnGn)|D(-2,PLAINS)|B(F20,2,-2)|U(F20,PnWn)");
    MoveProcessor.processMove(board, "P(0,COAST)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "P(1,MOUNTAIN)|W(G,PnPn)|U(G12,BrBrBrPnPtPtSwSw)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "V(Ni)|W(G,PnPn)|U(G22,Jo)");
    MoveProcessor.processMove(board, "b(F24,3,2)");
    MoveProcessor.processMove(board, "B(F17,5,1)|U(F17,Pn,Bo)*");
    MoveProcessor.processMove(board, "U(F15,Pn)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "d(3,HILLS)|c(0,3)");
    MoveProcessor.processMove(board, "F(2,-1)");
    MoveProcessor.processMove(board, "B(F25,3,-1)");
    MoveProcessor.processMove(board, "B(F11,-1,0)|U(F11)");
    MoveProcessor.processMove(board, "u(F09)|u(LG3)");
    MoveProcessor.processMove(board, "B(G18,5,2)");
    MoveProcessor.processMove(board, "W(G,PnPn)|U(G22,Jo)");
    MoveProcessor.processMove(board, "W(B,Wn)|U(F04,GnGnGnGnGn)");
    MoveProcessor.processMove(board, "v(Gn)|b(F23,5,1)|u(F23,Pn)");
    MoveProcessor.processMove(board, "C(0,-1)");
    MoveProcessor.processMove(board, "U(G02,ClPnFl,Wo)");
    MoveProcessor.processMove(board, "U(F05,FlFlFlFlFlFlFlCoSw,BrBr)|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "u(F09)|u(LG2,Sh)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "S(S02,3,0,WoPtBr)");
    MoveProcessor.processMove(board, "S(S04,-1,1,CoBrBrSh)");
    MoveProcessor.processMove(board, "s(S03,0,2,PnPnPnPnPnSh)");
    MoveProcessor.processMove(board, "S(S06,5,0,PtPtPtMt)");
    MoveProcessor.processMove(board, "B(F31,4,-2)|U(F31)");
    MoveProcessor.processMove(board, "U(LR1)|P(2,COAST)");
    MoveProcessor.processMove(board, "u(G22)");
    MoveProcessor.processMove(board, "U(G18,ClClClSnPtPt)");
    MoveProcessor.processMove(board, "U(F21,GpGpGpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "B(G19,1,1)|U(G19,ShShShShSwSwSwSw)");
    MoveProcessor.processMove(board, "v(GnGn)|b(F30,4,3)|u(F30)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "P(0,MOUNTAIN)|B(F32,5,1)|U(F32,Pn)|F(0,-2)|C(0,0,Jo)");
    MoveProcessor.processMove(board, "U(G01)|U(F30)");
    MoveProcessor.processMove(board, "u(F09)|u(LG2,Gn)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "U(G02,ClWoFl,Sh)");
    MoveProcessor.processMove(board, "B(G26,-1,3)");
    MoveProcessor.processMove(board, "w(R,PnPn)|u(G07,PtPtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "V(Gn)|S(S07,2,-1,ShShShShShShBrPtWoWoWoWoWoSwSwSwSw)");
    MoveProcessor.processMove(board, "S(S03,3,-2,ShShShFl)");
    MoveProcessor.processMove(board, "S(S07,0,0,MtMtMtCoCoCo)");
    MoveProcessor.processMove(board, "s(S02,3,3,GpGpGpCo)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "B(F40,5,0)");
    MoveProcessor.processMove(board, "B(F29,6,0)|U(F29)");
    MoveProcessor.processMove(board, "p(2,MOUNTAIN)|b(F38,0,3)|u(F38,1,3,2,3,1,1,1,0,2,0)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(F14)");
    MoveProcessor.processMove(board, "b(F36,2,3)");
    MoveProcessor.processMove(board, "F(1,-1)");
    MoveProcessor.processMove(board, "W(G,Wn)|U(LG2,ShJo)");
    MoveProcessor.processMove(board, "U(F11)");
    MoveProcessor.processMove(board, "u(F08,WoSnPnGp)");
    MoveProcessor.processMove(board, "B(G39,5,-1)|U(G39,PtPtPt)*");
    MoveProcessor.processMove(board, "B(G28,6,-2)|U(G28)|S(S06,5,-2,ShShFlPtPtPt)");
    MoveProcessor.processMove(board, "U(G01)|U(G28)|S(S03,2,2,MtGpGp)");
    MoveProcessor.processMove(board, "w(R,PnPn)|v(GnGnGnGnGnGn)|u(G19,SwSwSwSwSwSwShShShShShSh)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "U(F40)|U(F27,Wn)|U(F08,WoClSwSh)");
    MoveProcessor.processMove(board, "C(0,1)|D(3,PLAINS)");
    MoveProcessor.processMove(board, "u(F09)|u(LG2,Sh)");
    MoveProcessor.processMove(board, "W(G,PnPn)|U(F36,Or,Po)");
    MoveProcessor.processMove(board, "U(F15,Pn)");
    MoveProcessor.processMove(board, "U(G01)|U(G28)|S(S06,2,3,PtPtPtBrGpGp)");
    MoveProcessor.processMove(board, "w(B,PnPn)|u(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "V(Gn)|B(F35,1,-1)|U(F35,PnPnPnPnPn)*");
    MoveProcessor.processMove(board, "u(F40)|u(F33,PtWo,Mt)");
    MoveProcessor.processMove(board, "W(G,PnPn)|U(F24,BrBrWnWn)");
    MoveProcessor.processMove(board, "w(W,PnPn)|u(G28)|s(S06,1,3,MtCoCo)");
    MoveProcessor.processMove(board, "U(G18,ClClClPtWo)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(G26,WoWo)");
    MoveProcessor.processMove(board, "s(S08,5,2,MtMtMtMtMtMtCo)");
    MoveProcessor.processMove(board, "U(F40)|U(G34)");
    MoveProcessor.processMove(board, "U(F25,WoClSnSwPnGnShFlGpMtWnBrBo)");
    MoveProcessor.processMove(board, "S(S01,4,3,GpPt)");
    MoveProcessor.processMove(board, "s(S07,5,3,ShShShShShBrGpPnCoCoCo)");
    MoveProcessor.processMove(board, "S(S02,4,-1,PtWoPnPnPn)");
    MoveProcessor.processMove(board, "S(S08,5,-1,ShShShFlGpBrMtMtMtMtWoWoWo)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
