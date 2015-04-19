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
public class Game53276Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,3)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,FRANCE)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "u(LG2,ShJo)");
    MoveProcessor.processMove(board, "f(1,1)");
    MoveProcessor.processMove(board, "B(G12,3,0)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "b(G02,3,1)|u(G02,GnClPn,Pn)|d(2,PLAINS)");
    MoveProcessor.processMove(board, "B(F09,3,1)|U(F09)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "B(G01,3,1)|U(G01)*|U(F09)|U(G12,ShShPt)");
    MoveProcessor.processMove(board, "U(LB2,Gn)|p(1,COAST)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "u(LG3,Jo)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(G01)|U(F09)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(LB1)");
    MoveProcessor.processMove(board, "U(G12,PtWoShShSh)");
    MoveProcessor.processMove(board, "b(F08,1,2)|U(F08,ClPnShGn)*|d(3,PLAINS)");
    MoveProcessor.processMove(board, "U(LB2,ShJo)|d(3,PLAINS)");
    MoveProcessor.processMove(board, "V(Gn)|B(G06,1,1)|U(G06,PtPtPt)|P(1,COAST)");
    MoveProcessor.processMove(board, "c(0,1)");
    MoveProcessor.processMove(board, "s(S03,2,2,ShShShGn)");
    MoveProcessor.processMove(board, "S(S02,0,1,PtWoShPn)");
    MoveProcessor.processMove(board, "S(S03,2,2,ShShBr)");
    MoveProcessor.processMove(board, "v(Gn)|B(G16,3,2)|U(G16)*");
    MoveProcessor.processMove(board, "U(F09)|U(LR3)|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "u(G01)|U(G16)");
    MoveProcessor.processMove(board, "b(F14,4,3)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "W(BLUE,Pn)|U(G16)");
    MoveProcessor.processMove(board, "f(0,2)");
    MoveProcessor.processMove(board, "V(Gn)|W(GREEN,Pn)|U(F08,PnGnSwWo)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "U(F09)|U(LR3,Jo)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "b(F04,-1,1)|U(F04,GnGnGn)");
    MoveProcessor.processMove(board, "B(F11,-1,1)|U(F11)*");
    MoveProcessor.processMove(board, "U(G01)|u(F11)");
    MoveProcessor.processMove(board, "w(GREEN,Pn)|U(F08,PnPtSwCl)");
    MoveProcessor.processMove(board, "U(LR3)|D(3,HILLS)");
    MoveProcessor.processMove(board, "B(G07,0,0)|U(G07,PtPtPtPtPtPtPtPtPt)*|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(F14,Jo)|p(3,COAST)");
    MoveProcessor.processMove(board, "W(BLUE,Pn)|U(G16)");
    MoveProcessor.processMove(board, "s(S02,1,3,CoShPn)");
    MoveProcessor.processMove(board, "s(S05,4,2,WoShShFl)");
    MoveProcessor.processMove(board, "s(S05,1,2,WoShBr)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F21,0,1)|U(F21,GpGp,Wn)*");
    MoveProcessor.processMove(board, "f(0,3)");
    MoveProcessor.processMove(board, "B(G22,6,1)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "u(LG2,Gn)");
    MoveProcessor.processMove(board, "b(F05,1,2)|U(F05,FlFlPt,BrBr)|p(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(F09)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "u(LG1)");
    MoveProcessor.processMove(board, "c(0,1)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(F14)");
    MoveProcessor.processMove(board, "u(G01)|U(G22,Jo)");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "U(F11)");
    MoveProcessor.processMove(board, "V(Gn)|B(F17,3,2)|u(F17,PnPnPn,Bo)*");
    MoveProcessor.processMove(board, "u(G02,GnSwPn,Cl)|d(4,HILLS)");
    MoveProcessor.processMove(board, "U(F09)|U(LR3)");
    MoveProcessor.processMove(board, "v(Gn)|u(F08,GnSwShCl)");
    MoveProcessor.processMove(board, "s(S02,3,3,PtWoBr)");
    MoveProcessor.processMove(board, "S(S04,-1,2,ShShShShPtWo)");
    MoveProcessor.processMove(board, "s(S06,3,3,CoCoMt)");
    MoveProcessor.processMove(board, "B(F33,-1,3)|U(F33,PtWo,Mt)*");
    MoveProcessor.processMove(board, "B(F32,5,1)|U(F32,Pn)*|F(0,2)|C(0,3)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F20,2,3)|U(F20,ShShBrWn)*");
    MoveProcessor.processMove(board, "u(G16)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "u(LG2,Gn)");
    MoveProcessor.processMove(board, "U(F14)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(F21,GpGpGpGpGpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "b(F27,4,3)");
    MoveProcessor.processMove(board, "U(F33,PtWo,Mt)");
    MoveProcessor.processMove(board, "U(G22)");
    MoveProcessor.processMove(board, "u(LG2,Sh)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "P(3,COAST)|B(G26,-1,4)|U(G26,WoWo)*");
    MoveProcessor.processMove(board, "u(F20,ShShShGnWn)|P(1,COAST)");
    MoveProcessor.processMove(board, "w(Green,PnPn)|U(F21,GpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "U(F32,Pn)|C(0,0)|F(1,3)");
    MoveProcessor.processMove(board, "u(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(G01)|U(G26)");
    MoveProcessor.processMove(board, "s(S04,-1,1,ShShShShCo)");
    MoveProcessor.processMove(board, "s(S06,1,3,PtPtPtMt)");
    MoveProcessor.processMove(board, "S(S06,0,3,PtPtPtShPnPnPn)");
    MoveProcessor.processMove(board, "b(F29,6,0)|U(F29)*");
    MoveProcessor.processMove(board, "v(Gn)|B(F40,5,1)|U(F40)*|U(F38,1,4,2,4,1,0)");
    MoveProcessor.processMove(board, "W(Blur,Wn)|U(F33,PtWo,Mt)");
    MoveProcessor.processMove(board, "b(F24,3,0)");
    MoveProcessor.processMove(board, "b(F36,2,3)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F30,-1,3)|U(F30)*");
    MoveProcessor.processMove(board, "b(F38,-1,2)|u(F38,1,0,2,0,1,1,0,2,0,3)*");
    MoveProcessor.processMove(board, "w(RED,Wn)|U(F32,Pn,)|F(2,0)|c(0,4)");
    MoveProcessor.processMove(board, "U(F09)|U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(F27,Wn)|U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "w(Green,Wn)|u(G07,PtPtPtPtPt)");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(G16)");
    MoveProcessor.processMove(board, "W(BLUE,PnPn)|U(F40)|U(G28)|S(S07,0,2,MtMtMtCoCoCo)");
    MoveProcessor.processMove(board, "b(G18,3,0)|U(G18,CoClClCl)*");
    MoveProcessor.processMove(board, "V(Gn)|B(F35,2,2)|U(F35,NiNiNi)*");
    MoveProcessor.processMove(board, "u(LG2,Gn)");
    MoveProcessor.processMove(board, "w(Green,Wn)|u(F27,Wn)|u(F40)|U(G28)|s(S08,5,2,MtMtMtMtMtShShGnCo)");
    MoveProcessor.processMove(board, "W(GREEN,Wn)|U(G01)|U(F35,Ni)");
    MoveProcessor.processMove(board, "w(GREEN,Wn)|u(F33,Co,Mt)|V(GnGn)");
    MoveProcessor.processMove(board, "w(Red,Wn)|u(F30,Po)");
    MoveProcessor.processMove(board, "B(G28,4,3)|U(G28)*|S(S07,0,2,PtPtPtPtWoMtMtMt)");
    MoveProcessor.processMove(board, "U(G28)|S(S05,1,1,MtSwSw)");
    MoveProcessor.processMove(board, "U(G28)|s(S04,-1,2,NiPnPnPnCo)");
    MoveProcessor.processMove(board, "S(S08,4,2,PtPtShShShShShShShMtGnGnNiPnPnPnGp)");
    MoveProcessor.processMove(board, "S(S08,4,2,GnGnGnGnGnGnGnGnGnMtMtMtShShShCo)");
    MoveProcessor.processMove(board, "s(S07,0,2,CoCoCoNiNiPnPnShWn)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
