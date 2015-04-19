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
public class Game46808Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,4)|CONFIG(LENGTH,LONG)|CONFIG(COUNTRY,IRELAND)");
    MoveProcessor.processMove(board, "U(LR3)|P(0,COAST)");
    MoveProcessor.processMove(board, "V(Gn)|B(I09,3,0)|U(I09)*");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "B(I03,3,1)|U(I03,Pn)*");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "U(LB3,Jo)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "B(I08,-1,0)|U(I08,Be)*");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "B(G13,3,0)|U(G13,PnPn)*");
    MoveProcessor.processMove(board, "U(LW3)");
    MoveProcessor.processMove(board, "B(I11,-2,1)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(LW1)");
    MoveProcessor.processMove(board, "U(I08,Be)");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "U(LG3,Jo)");
    MoveProcessor.processMove(board, "B(I10,3,1)|U(I10)*|U(LB2,Sh)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "W(White,Pn)|U(I03,Pn)");
    MoveProcessor.processMove(board, "S(S03,1,0,ShShShGn)");
    MoveProcessor.processMove(board, "S(S01,1,0,GnWo)");
    MoveProcessor.processMove(board, "S(S04,-1,1,PtWoBeShGn)");
    MoveProcessor.processMove(board, "S(S01,3,1,GnPt)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(I08,Be)");
    MoveProcessor.processMove(board, "U(LG3,Sh)");
    MoveProcessor.processMove(board, "B(I14,0,0)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "C(0,0,Jo)");
    MoveProcessor.processMove(board, "U(LG2,Sh)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "B(I04,0,0)|U(I04,GnGnGnGnGnGnGnGnGnGnGnGnGnGnGnGn)*");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(I14,GnGn,Be)");
    MoveProcessor.processMove(board, "D(2,PLAINS)|U(LW3)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "S(S02,2,0,PtPtShGn)");
    MoveProcessor.processMove(board, "S(S05,1,1,BeWo)");
    MoveProcessor.processMove(board, "S(S02,1,2,SwSwSwSwSwSwShGn)");
    MoveProcessor.processMove(board, "S(S05,0,1,BePt)");
    MoveProcessor.processMove(board, "P(0,MOUNTAIN)|B(G22,6,0)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "U(I10)|U(LB2,Sh)");
    MoveProcessor.processMove(board, "W(Blue,Pn)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "U(I10)|U(LB3)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "U(I08,Be)");
    MoveProcessor.processMove(board, "U(I09)");
    MoveProcessor.processMove(board, "D(2,PLAINS)|U(LB3,Jo)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "W(Blue,Pn)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G22,Jo)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "B(G12,3,0)|U(G12,BeBePtPtPt)*");
    MoveProcessor.processMove(board, "B(G07,0,0)|U(G07,PtPtPtPtPt)*");
    MoveProcessor.processMove(board, "U(I10)|U(LB3)");
    MoveProcessor.processMove(board, "U(LW3,Jo)");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "U(I09)|P(2,MOUNTAIN)");
    MoveProcessor.processMove(board, "B(I15,4,2)|U(I15,PnPnPnPnPn,ShShSh)");
    MoveProcessor.processMove(board, "U(LW2,Gn)");
    MoveProcessor.processMove(board, "p(2,COAST)|B(G26,-1,3)|U(G26)*");
    MoveProcessor.processMove(board, "V(Ni)|W(Red,Pn)|U(I08,Be)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G22)");
    MoveProcessor.processMove(board, "U(I11)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "S(S05,2,2,GnGnGnGnGnSwSw)");
    MoveProcessor.processMove(board, "S(S06,0,0,NiPtPtPt)");
    MoveProcessor.processMove(board, "S(S05,5,2,BeWo)");
    MoveProcessor.processMove(board, "S(S06,0,1,PtPtPtShShGn)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "D(2,HILLS)|W(Red,Pn)|U(I08,Be)");
    MoveProcessor.processMove(board, "P(1,MOUNTAIN)|B(G18,5,1)|U(G18,ClClClSnPtPt)*");
    MoveProcessor.processMove(board, "B(I17,3,2)");
    MoveProcessor.processMove(board, "B(I31,-1,2)|U(I31,CoCoCo)*");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "B(I05,1,1)|U(I05,GnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHo,Be)");
    MoveProcessor.processMove(board, "D(2,HILLS)|C(0,2,Jo)");
    MoveProcessor.processMove(board, "B(I30,5,1)|U(I30)");
    MoveProcessor.processMove(board, "U(I14,GnGn,Be)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(G22)");
    MoveProcessor.processMove(board, "S(S02,1,2,PtWoShGn)");
    MoveProcessor.processMove(board, "S(S07,0,1,BeBeBePtPtPtPtPt)");
    MoveProcessor.processMove(board, "S(S02,1,0,CoShPn)");
    MoveProcessor.processMove(board, "S(S07,5,3,CoCoCoBeBeMt)");
    MoveProcessor.processMove(board, "U(I15,GnGnGnGnGn,PtPtPt)");
    MoveProcessor.processMove(board, "D(3,PLAINS)|B(I21,1,3)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(LG2,Gn)");
    MoveProcessor.processMove(board, "V(GnGnGnGnGnGnGn)|B(G19,3,2)|U(G19,ShSwShSwShSwShSwShSwShSwShSwShSw)*");
    MoveProcessor.processMove(board, "U(I21,HoPtWoHoPtWoHoPtWo)");
    MoveProcessor.processMove(board, "B(I38,0,2)");
    MoveProcessor.processMove(board, "B(G01,5,0)|U(G01)|V(GnGnGnGnGnGnGnGn)|U(G19,ShSwShSwShSwShSwShSwShSwShSwShSw)");
    MoveProcessor.processMove(board, "W(Green,PnPn)|U(G07,PtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "W(Blue,Wh)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "F(1,2,Jo)");
    MoveProcessor.processMove(board, "F(2,2)");
    MoveProcessor.processMove(board, "W(Green,PnPn)|U(G22)");
    MoveProcessor.processMove(board, "U(LW3)");
    MoveProcessor.processMove(board, "W(GREEN,Wh)|U(G07,PtPtPtPtPt)");
    MoveProcessor.processMove(board, "B(G28,6,2)|U(G28)|S(S08,4,2,MtMtMtMtMtMtCo)");
    MoveProcessor.processMove(board, "U(G18,ClClClSnCoWo)");
    MoveProcessor.processMove(board, "B(I24,3,3)|U(I24,BeWhBeWhBeWhPn)");
    MoveProcessor.processMove(board, "B(G39,1,2)|U(G39,CoCoCoCo)");
    MoveProcessor.processMove(board, "B(I27,3,2)");
    MoveProcessor.processMove(board, "U(I14,GnGn,Wh)");
    MoveProcessor.processMove(board, "W(Red,PnPn)|U(I08,Wh)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "D(3,PLAINS)|U(G28)|S(S06,4,3,CoCoMt)");
    MoveProcessor.processMove(board, "B(I37,2,2)|U(I37,Bo)|D(3,PLAINS)|P(1,COAST)");
    MoveProcessor.processMove(board, "W(Green,PnPn)|U(G01)|U(I24,PnBeWhBeWhBeWh)");
    MoveProcessor.processMove(board, "W(Green,PnPn)|U(I27)|U(G28)|S(S07,1,1,CoCoCoShShShShShShShHo)");
    MoveProcessor.processMove(board, "B(I20,2,3)");
    MoveProcessor.processMove(board, "U(G18,ClClClSnCoWo)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "B(I40,3,2)|U(I40)*|U(I35,PnPnPnPnPnWhOrOrOrBo)");
    MoveProcessor.processMove(board, "V(PnPnPnPnPn)|U(I30,MtMt)");
    MoveProcessor.processMove(board, "U(G28)|S(S08,4,3,MtMtMtMtMtMtCo)");
    MoveProcessor.processMove(board, "U(G28)|S(S06,2,3,BeSwSwSwSwSwSwWoWoWo)");
    MoveProcessor.processMove(board, "S(S03,3,1,ShShHoHoHo)");
    MoveProcessor.processMove(board, "S(S03,3,3,BeSh)");
    MoveProcessor.processMove(board, "S(S07,3,3,CoCoCoMtMtShShHo)");
    MoveProcessor.processMove(board, "S(S03,4,3,BeBe)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
