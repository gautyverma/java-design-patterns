package a4_CreationalPatterns.a5_PrototypePattern.solution;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Prototype<GameBoard> {
  private List<GamePiece> pieces = new ArrayList<>();

  public void addPiece(GamePiece piece) {
    pieces.add(piece);
  }

  public List<GamePiece> getPieces(){
    return pieces;
  }

  public void showBoardState() {
    System.out.println("Current Game Board State:");
    for (GamePiece piece : pieces) {
      System.out.println(piece);
    }
  }

  @Override
  public GameBoard clone() {
    GameBoard newBoard = new GameBoard();
   for(GamePiece piece:pieces){
     newBoard.addPiece(piece.clone()); // deep copy of game pieces
   }
    return newBoard;
  }
}
