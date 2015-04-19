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
public class Game27378Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board(GamePlayers.ONE, GameLength.NULL, GameCountry.IRELAND);
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "B(G02,3,1)|U(G02,PtClSh,Pn)*");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(G13,PnPn)*");
    MoveProcessor.processMove(board, "B(G01,3,0)|U(G01)*|U(G13,PnPn)*");
    MoveProcessor.processMove(board, "U(G02,PtSwSh,Pn)");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(LW3)");
    MoveProcessor.processMove(board, "P(0,COAST)|U(LR2,Gn)");
    MoveProcessor.processMove(board, "B(I08,-1,1)|U(I08,Wh)*");
    MoveProcessor.processMove(board, "P(2,COAST)|U(G01)|U(I08,Wh)");
    MoveProcessor.processMove(board, "U(LR2,GnJo)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "B(I11,-2,2)|U(I11)*");
    MoveProcessor.processMove(board, "B(I03,2,0)");
    MoveProcessor.processMove(board, "U(I03,Pn)");
    MoveProcessor.processMove(board, "U(LR3)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(LW2,Gn)");
    MoveProcessor.processMove(board, "B(I04,-1,0)|U(I04,GnGnGnGnGnGnGnGnGnGnGn)*");
    MoveProcessor.processMove(board, "B(I05,2,2)");
    MoveProcessor.processMove(board, "U(I05,GnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHoGnHo,Be)");
    MoveProcessor.processMove(board, "B(I10,1,0)");
    MoveProcessor.processMove(board, "B(I09,1,1)");
    MoveProcessor.processMove(board, "B(G06,0,0)|B(G07,0,0)");
    MoveProcessor.processMove(board, "B(G12,2,0)|U(I10)*|U(G07,PtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "S(S04,-1,2,BeBeCo)");
    MoveProcessor.processMove(board, "U(G01)|U(G12,BeBeCoPt)");
    MoveProcessor.processMove(board, "F(1,0)|P(4,COAST)");
    MoveProcessor.processMove(board, "B(I17,1,0)|U(I17,Pn)*");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(I09)");
    MoveProcessor.processMove(board, "U(LR1)|D(4,PLAINS)");
    MoveProcessor.processMove(board, "B(G18,0,0)|U(G18,ClClClSnCoSwSw)*");
    MoveProcessor.processMove(board, "B(G19,1,4)");
    MoveProcessor.processMove(board, "B(I15,0,0)");
    MoveProcessor.processMove(board, "B(I14,0,1)");
    MoveProcessor.processMove(board, "B(G16,3,1)");
    MoveProcessor.processMove(board, "S(S03,1,2,MtSh)");
    MoveProcessor.processMove(board, "U(G01)|U(I10)*|U(I15,Cl,Pn)");
    MoveProcessor.processMove(board, "U(I17,Pn)");
    MoveProcessor.processMove(board, "F(0,2)");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(I15,Pn,Wo)");
    MoveProcessor.processMove(board, "B(I23,0,1)|U(I23,PnPn)*|B(G26,-1,4)|U(G26,WoWo)*");
    MoveProcessor.processMove(board, "U(I23,PnPn)|B(I20,-1,3)|U(I20,BeWh)*|D(3,HILLS)");
    MoveProcessor.processMove(board, "C(0,3)");
    MoveProcessor.processMove(board, "V(Ni,Pn)|P(0,MOUNTAIN)|U(G01)|U(I23,PnPn)|B(G22,6,0)|U(G22,Jo)*");
    MoveProcessor.processMove(board, "W(WHITE,Pn)|U(I10)*|U(I15,Gn,Wo)");
    MoveProcessor.processMove(board, "U(I04,GnGnGnGnGn)");
    MoveProcessor.processMove(board, "B(I21,2,4)|U(I21,WoPtHoWoPtHoWoPtHoWoPtHoWoPtHoWoPtHoWoPtHo)*");
    MoveProcessor.processMove(board, "U(I23,PnPn)|B(I24,4,2)|U(I24,PnBeWh)*");
    MoveProcessor.processMove(board, "B(I25,3,1)");
    MoveProcessor.processMove(board, "S(S06,3,2,MtCoPtSwSw)");
    MoveProcessor.processMove(board, "U(I20,BeWh)");
    MoveProcessor.processMove(board, "B(I31,-1,5)|U(I31,Co)*");
    MoveProcessor.processMove(board, "U(G01)|U(I10)*|U(I15,Cl,Wo)");
    MoveProcessor.processMove(board, "B(I32,0,2)");
    MoveProcessor.processMove(board, "U(I20,BeWh)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(G19,ShSwShSwShSwShSwShSwShSwShSw)*");
    MoveProcessor.processMove(board, "P(2,MOUNTAIN)|V(Ni,Pn)|U(I23,PnPn)|B(I27,3,3)|U(I27,Wh)*|U(I23,PnPn)|B(G28,6,2)|U(G28)*|S(S07,4,3,MtMtMtCoCoWoPt)");
    MoveProcessor.processMove(board, "B(I29,1,1)");
    MoveProcessor.processMove(board, "B(I30,3,1)");
    MoveProcessor.processMove(board, "B(I33,3,0)");
    MoveProcessor.processMove(board, "S(S05,5,2,MtWo)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "F(0,4)");
    MoveProcessor.processMove(board, "B(G39,4,4)|U(G39,MtMtMtWhWhHo)*");
    MoveProcessor.processMove(board, "U(G28)|S(S01,0,3,HoPt)");
    MoveProcessor.processMove(board, "U(G22,Jo)");
    MoveProcessor.processMove(board, "B(G34,5,1)|U(G34,BoPoOrRq)*");
    MoveProcessor.processMove(board, "W(WHITE,Wh)|U(I30)");
    MoveProcessor.processMove(board, "V(Wh,Pn)|V(Wh,Pn)|V(PnPnPnPnPn,Ni)|U(I23,PnPn)|B(G41,5,0)|U(G41,Ni,PoPoPoBo)*");
    MoveProcessor.processMove(board, "U(I20,BeWh)");
    MoveProcessor.processMove(board, "B(I37,0,4)|U(I37,Bo)*|D(5,HILLS)|P(4,MOUNTAIN)");
    MoveProcessor.processMove(board, "W(WHITE,Wh)|U(I15,Sh,Pt)");
    MoveProcessor.processMove(board, "V(Wh,Pn)|U(I23,PnPn)|B(I35,5,4)|U(I35,NiWhWhOrPoPoPoPo)*");
    MoveProcessor.processMove(board, "B(I38,1,3)|U(I38,Be)*");
    MoveProcessor.processMove(board, "U(G28)|S(S02,2,3,PtWoShSh)");
    MoveProcessor.processMove(board, "S(S08,5,3,PtWoBeBeMtShShShShShWhWhWh)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
