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
public class Game29696Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,4)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,FRANCE)");
    MoveProcessor.processMove(board, "U(LR3,Jo)|D(2,HILLS)");
    MoveProcessor.processMove(board, "B(G01,3,1)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LG3)|D(-1,PLAINS)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "B(G07,0,0,Cl)|U(G07,PtPtPt)*");
    MoveProcessor.processMove(board, "V(Gn)|B(F03,3,0)|U(F03)");
    MoveProcessor.processMove(board, "U(G01)|U(F03)");
    MoveProcessor.processMove(board, "U(LB1,Jo)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "F(2,2)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "B(G02,3,1)|U(G02,PtClPn,Gn)*");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(LR1,Jo)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "U(G02,ClGnSh,Pn)");
    MoveProcessor.processMove(board, "U(LW2,Sh)");
    MoveProcessor.processMove(board, "V(Gn)|B(F05,2,2)");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "V(Gn)|B(G06,3,0,SwCl)|U(G06,CoCoCo)*|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "B(F04,4,2)|U(F04,GnGnGnGnGn)");
    MoveProcessor.processMove(board, "U(G01)|U(F04,GnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "C(0,1,Jo)|D(2,HILLS)");
    MoveProcessor.processMove(board, "U(F05,FlFlFlFlFlSwSwSwSwSw,BrBr)|D(3,PLAINS)");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "S(S03,3,0,ShShShGn)");
    MoveProcessor.processMove(board, "S(S02,3,1,ShShCo)");
    MoveProcessor.processMove(board, "S(S03,2,3,BrBrPn)");
    MoveProcessor.processMove(board, "S(S02,3,-1,PnShCo)");
    MoveProcessor.processMove(board, "D(2,HILLS)|U(LB1)");
    MoveProcessor.processMove(board, "U(LW2,Sh)|D(3,HILLS)");
    MoveProcessor.processMove(board, "F(1,2)");
    MoveProcessor.processMove(board, "B(G13,0,0)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "W(Red,Pn)|U(F04,GnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "B(F09,3,1)|U(F09)|U(F03)");
    MoveProcessor.processMove(board, "W(Red,Pn)|U(F05,FlFlFlFlFlFlFlSwSwSwSwSwSwSw,BrBr)|D(-2,HILLS)");
    MoveProcessor.processMove(board, "B(G16,4,2)|U(G16)*");
    MoveProcessor.processMove(board, "V(GnGnGnGn)|B(G19,0,1,WoWoClCl)|U(G19,SwSwSwSwShShShSh)*");
    MoveProcessor.processMove(board, "U(F09)|U(F03)");
    MoveProcessor.processMove(board, "U(LG3)|P(-2,COAST)");
    MoveProcessor.processMove(board, "W(White,Pn)|U(G07,PtPtPtPtPt)");
    MoveProcessor.processMove(board, "C(0,2)");
    MoveProcessor.processMove(board, "U(F04,GnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "S(S04,-1,-1,BrBrBrCo)");
    MoveProcessor.processMove(board, "S(S02,3,2,ShGnCo)");
    MoveProcessor.processMove(board, "S(S03,4,2,MtSh)");
    MoveProcessor.processMove(board, "S(S02,1,2,BrPtWo)");
    MoveProcessor.processMove(board, "W(Red,Pn)|U(LR3,Jo)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "f(1,1)");
    MoveProcessor.processMove(board, "B(F23,3,0)|U(F23,Pn)");
    MoveProcessor.processMove(board, "W(Red,Pn)|U(F05,FlFlFlFlFlFlFlCoSw,BrBr)|D(3,HILLS)");
    MoveProcessor.processMove(board, "F(2,2,Jo)");
    MoveProcessor.processMove(board, "B(F14,4,3)|U(F14)");
    MoveProcessor.processMove(board, "B(G12,4,-2)|U(G12,BrBrCo)*");
    MoveProcessor.processMove(board, "U(G02,SwWoGn,Wo)");
    MoveProcessor.processMove(board, "B(G18,5,1,WoWoWo)|U(G18,ClClPt)*");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LG2,Gn)");
    MoveProcessor.processMove(board, "B(F10,0,1)|U(F10,2,2)*|B(F25,2,2)");
    MoveProcessor.processMove(board, "U(G07,PtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "B(F21,1,1)|U(F21,GpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(LB3)|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(G06,CoCoCo)|P(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(F05,FlFlFlFlFlFlFlWoSwSwSwSwSw,BrBr)|P(1,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(G01)|U(F10,2,0)|B(F15,2,-1)|U(F15,Pn)*");
    MoveProcessor.processMove(board, "U(G02,WoSwGn,Pn)");
    MoveProcessor.processMove(board, "V(Ni)|B(G22,6,2,PnPnPnPnPn)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "C(0,2)");
    MoveProcessor.processMove(board, "F(0,-1)|F(2,0)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "U(F14)");
    MoveProcessor.processMove(board, "U(F23,Pn)");
    MoveProcessor.processMove(board, "W(GREEN,PnPn)|U(G01)|U(G22)");
    MoveProcessor.processMove(board, "S(S06,2,2,CoCoMt)");
    MoveProcessor.processMove(board, "S(S01,5,2,GnPt)");
    MoveProcessor.processMove(board, "S(S05,1,-1,BrGnGnWo)");
    MoveProcessor.processMove(board, "S(S05,3,3,WoBrSh)");
    MoveProcessor.processMove(board, "B(F08,3,2,SnSn)");
    MoveProcessor.processMove(board, "U(F21,GpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "V(Gn)|B(F30,0,-1)|U(F30)*");
    MoveProcessor.processMove(board, "P(2,COAST)|B(F11,-1,2)|U(F11)*");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "U(F05,,BrBr)");
    MoveProcessor.processMove(board, "U(G13,PnPn)|P(0,MOUNTAIN)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "U(G22,Jo)");
    MoveProcessor.processMove(board, "B(F27,5,1)|U(F27)|U(G22)");
    MoveProcessor.processMove(board, "B(F32,5,1)|U(F32,Pn)*|C(0,-2)");
    MoveProcessor.processMove(board, "W(GREEN,PnPn)|U(F15,Pn)");
    MoveProcessor.processMove(board, "V(Gn)|B(F17,5,2,SnSnSw)|U(F17,PnPn,Bo)*");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(G01)|U(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "S(S04,-1,3,CoShShShSh)");
    MoveProcessor.processMove(board, "V(GnGn)|S(S05,3,3,SwSwMt)");
    MoveProcessor.processMove(board, "S(S06,3,3,PtPtPtShBr)");
    MoveProcessor.processMove(board, "S(S06,2,0,MtCoCo)");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "U(LW2,Sh)");
    MoveProcessor.processMove(board, "B(F24,3,2)|U(F24,BrBrWnWn)");
    MoveProcessor.processMove(board, "B(F31,3,-2)");
    MoveProcessor.processMove(board, "B(G41,4,3)|U(G41,PnPnPnPnPn,PoPoBo)*");
    MoveProcessor.processMove(board, "F(2,3)");
    MoveProcessor.processMove(board, "U(F09)|U(LR2,Gn)");
    MoveProcessor.processMove(board, "W(WHITE,Wn)|U(G22)");
    MoveProcessor.processMove(board, "U(F25,WoClSnSwPtGnShFlGpBoPoWnBr)");
    MoveProcessor.processMove(board, "V(Gn)|B(F40,5,3,WoWoWoSw)|U(F40)*|U(F38,1,0,1,1,1,2,1,3)");
    MoveProcessor.processMove(board, "V(GnGn)|W(BLUE,PnPn)|U(F10,0,3)|B(F20,1,3)");
    MoveProcessor.processMove(board, "B(F38,4,-1)|U(F38,1,-2,2,-2,1,0,1,1)*");
    MoveProcessor.processMove(board, "U(G16)");
    MoveProcessor.processMove(board, "U(LW2,GnJo)");
    MoveProcessor.processMove(board, "F(0,3,Jo)|U(F04,GnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "B(G39,1,0)");
    MoveProcessor.processMove(board, "U(F25,WoClSwPnPtGnShFlGpBoPoBrCo)");
    MoveProcessor.processMove(board, "C(0,3)");
    MoveProcessor.processMove(board, "U(F05,FlFlFlFlFlFlFlSwSwSwSwSwSwSw,BrBr)");
    MoveProcessor.processMove(board, "U(F23,Pn)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "V(GnGnGnGn)|U(G19,SwSwSwSwShShShSh)");
    MoveProcessor.processMove(board, "U(F09)|U(F24,BrBrWnWn)");
    MoveProcessor.processMove(board, "B(G26,-1,-2)|U(G26,WoWo)*");
    MoveProcessor.processMove(board, "F(2,3)");
    MoveProcessor.processMove(board, "U(G18,ClClClSnCoWo)");
    MoveProcessor.processMove(board, "B(F36,0,2)");
    MoveProcessor.processMove(board, "U(G01)|U(G26,WoWo)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F37,5,1)|U(F37,WoWoWoSwSwSw)*");
    MoveProcessor.processMove(board, "W(BLUE,Wn)|U(G16)");
    MoveProcessor.processMove(board, "U(F09)|U(F24,BrBrWnWn)");
    MoveProcessor.processMove(board, "U(F36,Or,Or)*");
    MoveProcessor.processMove(board, "U(F23,Pn)");
    MoveProcessor.processMove(board, "V(Gn)|U(F25,WoClSnPnPtGnSwShCoBoPoOrMt)");
    MoveProcessor.processMove(board, "S(S05,0,3,WoBrGpGp)");
    MoveProcessor.processMove(board, "S(S07,2,-2,WoPtPtPtPtGpGpFlFlGnNiNi)");
    MoveProcessor.processMove(board, "S(S07,2,3,CoCoWoWoWoBrBrBrShShSh)");
    MoveProcessor.processMove(board, "S(S08,4,3,PtWoMtMtMtMtMtShShPn)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
