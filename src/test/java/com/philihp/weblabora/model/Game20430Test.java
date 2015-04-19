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
public class Game20430Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board(GamePlayers.TWO, GameLength.SHORT, GameCountry.FRANCE);
    MoveProcessor.processMove(board, "U(LR3,Jo)|D(-1,PLAINS)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "B(G13,3,0)");
    MoveProcessor.processMove(board, "U(LR1,Jo)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "U(LG3)");
    MoveProcessor.processMove(board, "B(F09,3,1)|U(F09)|U(LG2,Sh)");
    MoveProcessor.processMove(board, "B(G07,0,1)|U(G07,PtPtPt)*");
    MoveProcessor.processMove(board, "F(0,-1)");
    MoveProcessor.processMove(board, "u(F09)|U(LG3)");
    MoveProcessor.processMove(board, "D(2,PLAINS)|F(0,2,Jo)");
    MoveProcessor.processMove(board, "S(S02,3,-1,CoShGn)");
    MoveProcessor.processMove(board, "S(S01,1,2,WoGp)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "B(G02,3,2)|U(G02,ShPtGn,Pn)|P(1,COAST)");
    MoveProcessor.processMove(board, "B(F04,4,-1)|U(F04,GnGnGnGnGn)*");
    MoveProcessor.processMove(board, "B(G12,3,1)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "B(F05,0,2)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "W(Gn,Pn)|U(F05,FlFlFlFlFlCoCo,BrBr)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(F09)|U(LG3,Jo)|D(3,PLAINS)");
    MoveProcessor.processMove(board, "W(G,Pn)|U(G02,PnWoCl,Wo)|P(-1,COAST)");
    MoveProcessor.processMove(board, "B(G19,2,0)|U(G19,ShShShShShSwSwSwSwSw)*");
    MoveProcessor.processMove(board, "S(S03,0,1,ShShShGp)");
    MoveProcessor.processMove(board, "S(S04,-1,0,CoMtBr)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "B(F08,1,1)|U(F08,WoPtPnCl)|P(-1,COAST)");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(G12,MtMtPtPtWo)");
    MoveProcessor.processMove(board, "U(F09)|U(LG3)");
    MoveProcessor.processMove(board, "W(RED,Pn)|U(LR2,Gn)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(LR3,Jo)*|D(2,PLAINS)");
    MoveProcessor.processMove(board, "U(G13,PnPn)");
    MoveProcessor.processMove(board, "B(F23,2,2)|U(F23)");
    MoveProcessor.processMove(board, "B(F11,-1,-1)|U(F11)*");
    MoveProcessor.processMove(board, "W(G,Pn)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "B(F14,4,3)|U(F14)");
    MoveProcessor.processMove(board, "B(F25,-1,1)");
    MoveProcessor.processMove(board, "S(S05,0,-1,WoMt)");
    MoveProcessor.processMove(board, "S(S04,-1,0,PtWoBrPnPnPnPnPn)");
    MoveProcessor.processMove(board, "U(G07,PtPtPt)");
    MoveProcessor.processMove(board, "U(LR2,Sh)");
    MoveProcessor.processMove(board, "U(F23)");
    MoveProcessor.processMove(board, "B(F33,-1,-1)|U(F33,PtWo,Mt)");
    MoveProcessor.processMove(board, "F(1,0)");
    MoveProcessor.processMove(board, "B(F15,3,0)|U(F15)*");
    MoveProcessor.processMove(board, "C(0,0)");
    MoveProcessor.processMove(board, "U(LG1)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "U(LR3)|P(1,COAST)");
    MoveProcessor.processMove(board, "U(F23)");
    MoveProcessor.processMove(board, "V(Gn)|B(F21,1,3)|U(F21,GpGpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "W(G,PnPn)|U(F14)");
    MoveProcessor.processMove(board, "B(G01,4,2)|U(G01)*|U(F21,GpGpGpGpGpGpGp,Wn)");
    MoveProcessor.processMove(board, "S(S06,2,3,PtPtPtMt)");
    MoveProcessor.processMove(board, "P(-1,MOUNTAIN)|S(S06,5,-1,CoCoMt)");
    MoveProcessor.processMove(board, "P(2,MOUNTAIN)|B(G22,6,2)");
    MoveProcessor.processMove(board, "U(G22)");
    MoveProcessor.processMove(board, "U(G12,BrBrShShCoWoWo)");
    MoveProcessor.processMove(board, "B(G28,6,-1)");
    MoveProcessor.processMove(board, "B(F38,0,0)|U(F38,1,0,2,0,0,3)");
    MoveProcessor.processMove(board, "V(GnGn)|B(F30,2,0)");
    MoveProcessor.processMove(board, "U(LR3,Jo)");
    MoveProcessor.processMove(board, "P(1,MOUNTAIN)|B(F40,5,1)");
    MoveProcessor.processMove(board, "U(F30)");
    MoveProcessor.processMove(board, "D(-1,PLAINS)|V(GnGn)|B(F20,1,-1)");
    MoveProcessor.processMove(board, "U(LR3)");
    MoveProcessor.processMove(board, "B(F24,5,0)|U(F24,BrWn)");
    MoveProcessor.processMove(board, "U(F20,MtWn)");
    MoveProcessor.processMove(board, "P(3,COAST)|B(F36,-1,3)");
    MoveProcessor.processMove(board, "W(G,Wn)|U(G22)");
    MoveProcessor.processMove(board, "F(1,1)");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Gn)");
    MoveProcessor.processMove(board, "V(Gn)|B(F17,3,3)|U(F17,PnPnPn,Bo)");
    MoveProcessor.processMove(board, "B(G39,0,0)");
    MoveProcessor.processMove(board, "U(F40)|U(G18,ClClClSnCoWo)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G07,PtPtPtPt)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G28)|S(S07,1,0,CoCoCoMtMtMt)");
    MoveProcessor.processMove(board, "V(GnGn)|B(G34,3,2)|U(G34)*");
    MoveProcessor.processMove(board, "W(G,Wn)|U(LG1)");
    MoveProcessor.processMove(board, "U(F33,Co,Mt)");
    MoveProcessor.processMove(board, "B(G41,4,2)|U(G41,Ni,RqOr)");
    MoveProcessor.processMove(board, "W(G,Wn)|U(G22)");
    MoveProcessor.processMove(board, "B(G26,-1,2)|U(G26)*");
    MoveProcessor.processMove(board, "U(F09)|V(Gn)|U(G02,GnSwBo,Wo)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G28)|S(S05,-1,2,MtWo)");
    MoveProcessor.processMove(board, "W(G,Wn)|U(F20,WnShShShFl)");
    MoveProcessor.processMove(board, "P(-3,MOUNTAIN)|U(G01)|U(G26)");
    MoveProcessor.processMove(board, "U(F20,MtGnGnWn)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G28)|S(S02,0,3,CoMt)");
    MoveProcessor.processMove(board, "U(G26)");
    MoveProcessor.processMove(board, "B(F27,5,-2)|U(F27)*|U(G28)|S(S03,2,2,ShShGnGnFl)");
    MoveProcessor.processMove(board, "D(4,HILLS)|C(0,4)");
    MoveProcessor.processMove(board, "U(F09)|U(LG2,Sh)");
    MoveProcessor.processMove(board, "F(0,2)");
    MoveProcessor.processMove(board, "B(G18,5,2)|U(G18,ClClClSnWoWoWoWo)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G07,PtPtPtPtPtPt)");
    MoveProcessor.processMove(board, "V(Gn)|U(F25,WoClPnPtSwGnShGpCoMtWnBoPo)");
    MoveProcessor.processMove(board, "U(LR2,Gn)");
    MoveProcessor.processMove(board, "V(Gn)|B(F03,-1,1)");
    MoveProcessor.processMove(board, "U(F33,Co,Wn)");
    MoveProcessor.processMove(board, "V(Gn)|U(F25,WoClNiPnPtGnSwShGpCoMtBoWn)");
    MoveProcessor.processMove(board, "U(LR3)|D(3,PLAINS)");
    MoveProcessor.processMove(board, "V(GnGnGn)|B(F37,3,3)|U(F37,WoWoWoSwSwSw)*");
    MoveProcessor.processMove(board, "U(F09)|U(G13,PnPn)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(F27,Wn)|U(F25,WoClSnSwPnPtShGpCoMtBoPoOr)");
    MoveProcessor.processMove(board, "W(G,PnPn)|U(F36,Or,Or)");
    MoveProcessor.processMove(board, "U(LR1)");
    MoveProcessor.processMove(board, "U(F09)|U(G02,WoPtCo,Sh)");
    MoveProcessor.processMove(board, "W(RED,Wn)|U(G28)|S(S08,3,4,ShShShShShShShShShShShShShPnPnGpGpCo)");
    MoveProcessor.processMove(board, "U(G34)");
    MoveProcessor.processMove(board, "B(F32,2,3)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
