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
public class Game46615Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,4)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,IRELAND)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "B(I03,3,1)|U(I03,Pn)*");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "U(LR3,Pn)|P(0,COAST)");
    MoveProcessor.processMove(board, "V(Gn)|B(I09,3,0)|U(I09)*");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "U(LW3,Jo)");
    MoveProcessor.processMove(board, "B(I08,-1,0)|U(I08,Be)*");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "B(G13,3,0)|U(G13,PnPn)*");
    MoveProcessor.processMove(board, "B(I11,-2,1)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "B(G07,0,0)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(I08,Be)");
    MoveProcessor.processMove(board, "U(LG2,ShJo)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "B(I05,0,0)");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "W(White,Pn)|U(LW2,Sh)");
    MoveProcessor.processMove(board, "S(S02,3,0,PtWoGnSh)");
    MoveProcessor.processMove(board, "S(S02,1,0,ShGnPtWo)");
    MoveProcessor.processMove(board, "S(S04,-1,1,WoPtBeBe)");
    MoveProcessor.processMove(board, "S(S02,3,1,ShPnCo)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(I08,Be)");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "U(LW3,Jo)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "B(I04,2,0)|U(I04,GnGnGnGnGnGnGnGnGnGnGnGn)*");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G07,PtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "F(1,0,Jo)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "V(Gn)|B(I17,3,1)|U(I17,Pn)");
    MoveProcessor.processMove(board, "S(S01,2,0,WoPn)");
    MoveProcessor.processMove(board, "S(S01,1,0,PnSwSw)");
    MoveProcessor.processMove(board, "S(S01,0,1,PnWo)");
    MoveProcessor.processMove(board, "S(S03,0,0,MtSh)");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "B(I14,1,1)|U(I14,Gn,Be)*");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "P(0,MOUNTAIN)|B(G22,6,0)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LW3)");
    MoveProcessor.processMove(board, "B(G12,3,0)|U(G12,ShShShShShPtPtPt)*");
    MoveProcessor.processMove(board, "U(G22,Jo)");
    MoveProcessor.processMove(board, "D(2,PLAINS)|B(G02,4,2)|U(G02,SwGnHo,Gn)*");
    MoveProcessor.processMove(board, "D(-1,PLAINS)|F(0,-1)");
    MoveProcessor.processMove(board, "D(2,HILLS)|B(I15,4,2)");
    MoveProcessor.processMove(board, "P(2,MOUNTAIN)|U(I09)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "U(LW2,Sh)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "F(1,0,Jo)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "F(1,2)");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "U(I04,GnGnGnGnGnGnGnGnGnGnGnGnGnGnGn)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G22)");
    MoveProcessor.processMove(board, "U(I15,ClClClClCl,WoWoWo)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "W(White,Pn)|U(I05,GnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHo,Be)");
    MoveProcessor.processMove(board, "S(S06,0,-1,BeCoCo)");
    MoveProcessor.processMove(board, "S(S05,0,1,WoBe)");
    MoveProcessor.processMove(board, "S(S06,5,2,CoCoShShSh)");
    MoveProcessor.processMove(board, "S(S05,1,2,BeSwSw)");
    MoveProcessor.processMove(board, "B(I29,1,-1)|U(I29,2,0)*");
    MoveProcessor.processMove(board, "p(2,COAST)|B(G26,-1,3)|U(G26)*");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "B(I23,3,1)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(LG2,Gn)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "C(0,2,Jo)");
    MoveProcessor.processMove(board, "D(2,HILLS)|U(I09)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G22)");
    MoveProcessor.processMove(board, "U(LW3)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G07,PtPtPtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "S(S07,2,2,SwSwSwSwSwSwSwSwSwSwSwSwSwSwSwSwSwSwBeBeBe)");
    MoveProcessor.processMove(board, "S(S05,2,-1,WoShShGn)");
    MoveProcessor.processMove(board, "S(S06,0,2,CoCoShShHo)");
    MoveProcessor.processMove(board, "S(S05,3,2,ShShGnPt)");
    MoveProcessor.processMove(board, "F(0,2)");
    MoveProcessor.processMove(board, "D(-2,PLAINS)|B(I37,2,0)|U(I37,Bo)*|D(2,PLAINS)|P(-1,COAST)");
    MoveProcessor.processMove(board, "U(I17,Pn)");
    MoveProcessor.processMove(board, "D(3,HILLS)|F(2,2,Jo)");
    MoveProcessor.processMove(board, "B(I21,1,1)|U(I21,HoWoPtHoWoPtHoWoPtHoWoPtHoWoPtHoWoPtHoWoPt)*");
    MoveProcessor.processMove(board, "U(I23,PnPn)|B(G39,2,-2)|U(G39,CoCoCoCo)*");
    MoveProcessor.processMove(board, "B(I31,-1,2)|U(I31,CoCoCo)*");
    MoveProcessor.processMove(board, "V(GnGnGnGn)|B(G19,0,1)|U(G19,ShShShShSwSwSwSw)*");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "U(LW2,Sh)");
    MoveProcessor.processMove(board, "U(I15,GnGnGnGnGn,ShShSh)");
    MoveProcessor.processMove(board, "B(G28,6,2)");
    MoveProcessor.processMove(board, "B(I24,3,2)");
    MoveProcessor.processMove(board, "B(G18,3,2)|U(G18,SnClClClCoWo)*");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "B(I27,4,3)");
    MoveProcessor.processMove(board, "U(I24,PnBeWhBeWhBeWh)");
    MoveProcessor.processMove(board, "U(LW3)");
    MoveProcessor.processMove(board, "V(GnGnGnGnGnGnGnGnGn)|B(G01,3,2)|U(G01)*|U(G19,ShSwShSwShSwShSwShSwShSwShSwShSwShSw)");
    MoveProcessor.processMove(board, "U(LG2,ShJo)");
    MoveProcessor.processMove(board, "V(Ni)|U(I24,PnBeWhBeWhBeWh)");
    MoveProcessor.processMove(board, "U(I23,PnPn)|B(I32,2,2)|U(I32,WoShSwGnPn)");
    MoveProcessor.processMove(board, "W(Green,PnPn)|U(G28)|S(S07,1,1,MtMtMtCoCoCo)");
    MoveProcessor.processMove(board, "C(0,2)");
    MoveProcessor.processMove(board, "W(Red,Wh)|U(I15,GnGnGnGnGn,PtPtPt)");
    MoveProcessor.processMove(board, "F(0,-2)");
    MoveProcessor.processMove(board, "D(3,HILLS)|W(Green,Wh)|U(G22)");
    MoveProcessor.processMove(board, "U(G28)|S(S08,4,2,MtMtMtMtShShShShShCo)");
    MoveProcessor.processMove(board, "W(White,Wh)|U(I05,GnHoGnHoGnHoGnHoGnHo,Be)");
    MoveProcessor.processMove(board, "U(LW2,Sh)");
    MoveProcessor.processMove(board, "V(Gn)|B(I40,1,2)|U(I40)*|U(I30,MtMtMtMt)");
    MoveProcessor.processMove(board, "U(I40)|U(I30)");
    MoveProcessor.processMove(board, "U(I24,PnBeWhBeWhBeWh)");
    MoveProcessor.processMove(board, "B(I20,0,-2)");
    MoveProcessor.processMove(board, "S(S08,4,3,CoMtMtMtMtBePnPnPnWh)");
    MoveProcessor.processMove(board, "S(S07,5,3,CoPtPtPtBeMtShShPn)");
    MoveProcessor.processMove(board, "S(S06,0,2,PtPtPtBe)");
    MoveProcessor.processMove(board, "S(S07,1,-2,CoWoWoWoWoWoWoShShShShShShShSh)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
