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
public class Game44462Test {

  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
    MoveProcessor.processMove(board, "CONFIG(PLAYERS,3)|CONFIG(LENGTH,SHORT)|CONFIG(COUNTRY,IRELAND)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "F(1,1,Jo)");
    MoveProcessor.processMove(board, "B(G02,3,1)|U(G02,GnShCl,Sh)*");
    MoveProcessor.processMove(board, "B(G12,2,0)|U(G12,ShWo,Sn)|U(G12,ShWo,Sn)*");
    MoveProcessor.processMove(board, "U(LR3,Pn)|P(0,COAST)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(LG2,Sh)");
    MoveProcessor.processMove(board, "B(I08,-1,0)|U(I08,Be)*|D(-1,PLAINS)");
    MoveProcessor.processMove(board, "S(S03,3,0,ShShShGn)");
    MoveProcessor.processMove(board, "S(S03,3,0,ShShShGn)");
    MoveProcessor.processMove(board, "S(S04,-1,1,BeShGnWoPt)");
    MoveProcessor.processMove(board, "W(RED,Pn)|U(G12,ShShPt)");
    MoveProcessor.processMove(board, "B(I14,0,0)|U(I14,HoHo,Be)*");
    MoveProcessor.processMove(board, "C(0,1,Jo)");
    MoveProcessor.processMove(board, "U(LB2,Gn)");
    MoveProcessor.processMove(board, "u(LR3,Pn)|D(-1,PLAINS)");
    MoveProcessor.processMove(board, "W(RED,Pn)|U(I14,HoHo,Be)");
    MoveProcessor.processMove(board, "U(G02,GnPtSh,Pn)|D(2,PLAINS)");
    MoveProcessor.processMove(board, "W(Green,Pn)|U(I08,Wh)|P(0,COAST)");
    MoveProcessor.processMove(board, "S(S05,1,1,BePt)");
    MoveProcessor.processMove(board, "S(S05,0,1,WoShGnGnGn)");
    MoveProcessor.processMove(board, "S(S05,1,0,BePt)");
    MoveProcessor.processMove(board, "V(Gn)|B(I05,0,1)|U(I05,GnHoGnHo,BePnPnPnPnPnPnPn)*");
    MoveProcessor.processMove(board, "V(Gn)|B(I17,3,2)|U(I17,Pn)*");
    MoveProcessor.processMove(board, "u(LR1)");
    MoveProcessor.processMove(board, "W(RED,Pn)|U(I14,HoHo,Wh)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "B(I24,3,1)");
    MoveProcessor.processMove(board, "V(GnGn)|B(I21,1,0)|U(I21,HoWoPtHoWoPt)*");
    MoveProcessor.processMove(board, "B(G18,2,2)|U(G18,ClSnPt)*");
    MoveProcessor.processMove(board, "S(S06,2,-1,PtPtPtShGnGnGn)");
    MoveProcessor.processMove(board, "S(S06,0,0,PtPtPtShShPn))");
    MoveProcessor.processMove(board, "S(S06,0,0,PtPtPtMt)");
    MoveProcessor.processMove(board, "v(Gn)|B(I33,1,-1)|U(I33,WoWoWo,Be)*");
    MoveProcessor.processMove(board, "U(I05,PnPnPnPnPnPnPn,Be)");
    MoveProcessor.processMove(board, "U(LB2,Sh)");
    MoveProcessor.processMove(board, "C(0,1)");
    MoveProcessor.processMove(board, "F(2,0)");
    MoveProcessor.processMove(board, "B(I30,1,2)|U(I30,Mt)*|P(0,COAST)");
    MoveProcessor.processMove(board, "U(I14,GnGn,Wh)");
    MoveProcessor.processMove(board, "B(I27,3,0)|U(I27,Wh)*|U(I30,Mt)");
    MoveProcessor.processMove(board, "S(S04,-1,1,PtWoBeShGn)");
    MoveProcessor.processMove(board, "S(S07,0,1,PtPtPtPtWoBeBeBe)");
    MoveProcessor.processMove(board, "S(S03,3,1,BeSh)");
    MoveProcessor.processMove(board, "U(LB1)");
    MoveProcessor.processMove(board, "U(I24,PnBeWhBeWh)");
    MoveProcessor.processMove(board, "U(LG2,Gn)");
    MoveProcessor.processMove(board, "F(1,1,Jo)");
    MoveProcessor.processMove(board, "b(G26,-1,0)|U(G26,WoWo)");
    MoveProcessor.processMove(board, "B(I04,2,-1)|U(I04,GnGnGnGn)");
    MoveProcessor.processMove(board, "U(LB3)");
    MoveProcessor.processMove(board, "u(G02,GnPnCl,Sh)");
    MoveProcessor.processMove(board, "U(I05,GnHoGnHoGnHoGnHo,PnPnPnPnPnPnPnBe)");
    MoveProcessor.processMove(board, "B(I37,-1,0)");
    MoveProcessor.processMove(board, "B(I38,3,-1)|U(I38)*");
    MoveProcessor.processMove(board, "W(RED,PnPn)|U(I24,PnBeWhBeWhBeWh)");
    MoveProcessor.processMove(board, "B(G41,1,1)|U(G41,PnPnPnPnPn,RqOr)*");
    MoveProcessor.processMove(board, "u(LR2,Sh)");
    MoveProcessor.processMove(board, "B(I35,4,-1)|U(I35,NiWhBoBoBoRq)*");
    MoveProcessor.processMove(board, "V(GnGn)|B(G34,1,0)");
    MoveProcessor.processMove(board, "S(S08,4,-1,WoPtShShShShShShShShShShShShShShMt)");
    MoveProcessor.processMove(board, "S(S02,3,-1,WoWoWoMt)");
    MoveProcessor.processMove(board, "S(S08,4,2,WoWoWoMtBeShShShShShShShShGnGnPnPn)");
  }

  @Test
  public void testEndingScores() throws WeblaboraException {
    assertThat(board.isGameOver(), is(true));
  }
}
